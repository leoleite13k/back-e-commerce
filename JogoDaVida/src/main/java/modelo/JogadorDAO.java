/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class JogadorDAO extends DAO {
    
    public JogadorDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    public ArrayList<Jogador> listJogadores() throws SQLException {
        String sql = "SELECT * FROM Jogador";
        return selectJogadoresPorQuery(sql);
    }
    
    public Jogador selectJogador(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Jogador WHERE id = " + id;
        
        ArrayList<Jogador> jogadores = selectJogadoresPorQuery(sql);
        if(jogadores.size() > 0) {
            PartidaDAO partidaDAO = new PartidaDAO();
            Jogador jogador = jogadores.get(0);
            ArrayList<Partida> partidas = partidaDAO.selectPartidasAtivaPorJogador(jogador.getId());
            jogador.setPartidasAtivas(partidas != null ? partidas : new ArrayList<>());
            return jogador;
        }
        
        return null;
    }
    
    public ArrayList<Jogador> selectJogadoresComPartida(Integer idPartida) throws SQLException {
        String sql = "SELECT * FROM Jogador j WHERE j.id IN(SELECT id_jogador FROM JogadorPartida WHERE id_partida = " + idPartida + ")";
        return selectJogadoresPorQuery(sql);
    }
    
    public Jogador selectJogadorComNomeSenha(String nome, String senha) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Jogador WHERE nome = '" + nome + "' AND senha = '" + senha + "'";
        
        ArrayList<Jogador> jogadores = selectJogadoresPorQuery(sql);
        if(jogadores.size() > 0) {
            PartidaDAO partidaDAO = new PartidaDAO();
            Jogador jogador = jogadores.get(0);
            ArrayList<Partida> partidas = partidaDAO.selectPartidasAtivaPorJogador(jogador.getId());
            jogador.setPartidasAtivas(partidas != null ? partidas : new ArrayList<>());
            return jogador;
        }
        
        return null;
    }
        
    public ArrayList<Jogador> selectJogadoresPorQuery(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Jogador> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Jogador(rs.getInt("id"), rs.getString("nome"), null)); // Retirado o retorno da senha por segurança rs.getString("senha")
        }
        return list;
    }
    
    public Integer nextJogador(Integer idJogador, Integer idPartida, Integer numeroRoletaAtual) throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT MIN(primeiro_numero_roleta) AS primeiro_numero_roleta, id_jogador "
                + "FROM JogadorPartida "
                + "WHERE id_partida = " + idPartida + " "
                + "AND primeiro_numero_roleta > " + numeroRoletaAtual + " "
                + "AND NOT id_jogador = " + idJogador + " "
                + "GROUP BY id_jogador "
                + "LIMIT 1";
        
        PreparedStatement stm = con.prepareStatement(sql1);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next() && rs != null) {
            return rs.getInt("id_jogador");
        }
        
        String sql2 = "SELECT MIN(primeiro_numero_roleta) AS primeiro_numero_roleta, id_jogador "
                + "FROM JogadorPartida "
                + "WHERE id_partida = " + idPartida + " "
                + "GROUP BY id_jogador "
                + "LIMIT 1";
        
        PreparedStatement stm2 = con.prepareStatement(sql2);
        ResultSet rs2 = stm2.executeQuery();
        
         if (rs2.next() && rs2 != null) {
            return rs2.getInt("id_jogador");
        }
         
        return null;
    }
    
    public Jogador insertJogador(String nome, String senha) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Jogador (nome, senha) VALUES ('" + nome + "', " + senha + ")";
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        
        if(rs.next() && rs != null){
            Jogador jogador = this.selectJogador(rs.getInt(1));
        
            return jogador;
        }    

        return null;
    }
    
    public Jogador updateSenha(Integer id, String senha) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Jogador SET senha = '" + senha + "' WHERE id = " + id;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
        
        Jogador jogador = this.selectJogador(id);

        return jogador;
    }
    
    public void deleteJogador(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Jogador WHERE id = " + id;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();        
    }
}
