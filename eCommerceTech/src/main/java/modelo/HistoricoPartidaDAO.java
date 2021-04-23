/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class HistoricoPartidaDAO extends DAO {
    
    public HistoricoPartidaDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    public void insertHistorico(Integer idPartida, Integer idAcao, Integer idJogador) throws SQLException {
        String sql = "INSERT INTO HistoricoPartida (id_partida, id_acao, id_jogador) VALUES (" + idPartida + ", " + idAcao + ", " + idJogador + ")";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }    
    
    public ArrayList<HistoricoPartida> selectHistorico(Integer idPartida, Integer idJogador) throws SQLException {
        String sql = "SELECT * FROM HistoricoPartida ";
            if (idPartida != null) {
                sql = sql + "WHERE id_partida = " + idPartida + " ";
                
                if (idJogador != null) {
                    sql = sql + "AND id_jogador = " + idJogador;
                }
            } else {
                sql = sql + "WHERE id_jogador = " + idJogador;
            }

        return selectHistoricoPorSQL(sql);
    }
    
    public ArrayList<HistoricoPartida> selectHistoricoPorSQL(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
               
        ArrayList<HistoricoPartida> list = new ArrayList<>();
        while(rs.next()){
            list.add(new HistoricoPartida(rs.getInt("id"), rs.getInt("id_partida"), rs.getInt("id_jogador"), rs.getInt("id_acao"), rs.getDate("data")));
        }
        
        return list;
    }
}
