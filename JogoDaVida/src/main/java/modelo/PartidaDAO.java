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
public class PartidaDAO extends DAO {
    
    public PartidaDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    public ArrayList<Partida>  listPartida() throws SQLException {
        String sql = "SELECT * FROM Partida";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Partida> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Partida(rs.getInt("id"), rs.getBoolean("ativa"), rs.getInt("jogador_turno_atual"), rs.getInt("jogador_vencedor")));
        }
        
        return list;
    }
    
    public Partida selectPartida(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Partida WHERE id = " + id;
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next() && rs != null) {
            Partida partida = new Partida(rs.getInt("id"), rs.getBoolean("ativa"), rs.getInt("jogador_turno_atual"), rs.getInt("jogador_vencedor"));
            
            JogadorDAO jogadorDAO = new JogadorDAO();
            ArrayList<Jogador> jogadores = jogadorDAO.selectJogadoresComPartida(id);
            partida.setJogadoresPartida(jogadores);
            
            return partida;
        }
        
        return null;
    }
    
    public Partida insertPartida() throws SQLException {
        String sql = "INSERT INTO Partida (ativa) VALUES (true)";
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        
        if(rs.next() && rs != null) {
            Partida partida = selectPartidasPorID(rs.getInt(1));

            if(partida != null) {
                return partida;
            }
        }
        
        return null;
    }
    
    public Partida selectPartidasPorID(Integer id) throws SQLException {
        String sql = "SELECT * FROM Partida WHERE id = " + id;
        ArrayList<Partida> list = selectPartidasPorQuery(sql);
        
        if(list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
    
    public ArrayList<Partida> selectPartidasAtivaPorJogador(Integer jogador) throws SQLException {
        String sql = "SELECT * FROM Partida p WHERE p.id IN(SELECT id_partida FROM JogadorPartida WHERE id_jogador = " + jogador + ")";
        return selectPartidasPorQuery(sql);
    }
    
    public ArrayList<Partida> selectPartidasPorQuery(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Partida> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Partida(rs.getInt("id"), rs.getBoolean("ativa"), rs.getInt("jogador_turno_atual"), rs.getInt("jogador_vencedor")));
        }
        
        return list;
    }
    
    public void updatePartida(Partida partida) throws SQLException {
        String sql = "UPDATE Partida "
                + "SET jogador_turno_atual = " + (partida.getJogadorTurnoAtual() == null ? "null" : partida.getJogadorTurnoAtual())
                + ", jogador_vencedor = " + (partida.getJogadorVencedor() == null ? "null" : partida.getJogadorVencedor())
                + ", ativa = " + (partida.getAtiva() ? 1 : 0) + " "
                + "WHERE id  = " + partida.getId();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public void deletePartida(Integer id) throws SQLException {
        String sql = "DELETE FROM Partida WHERE id = " + id;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
}
