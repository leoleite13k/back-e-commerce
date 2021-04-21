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
 * @author leonardoleite
 */
public class AcaoDAO extends DAO {
    public AcaoDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    public ArrayList<Acao> listAcao(String descricao) throws SQLException {
        String sql = "SELECT * FROM Acao ";
        
        if (descricao != null) {
            sql = sql + "WHERE descricao LIKE " + "'%" + descricao + "%'";
        }
                                          
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Acao> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Acao(rs.getInt("id"), rs.getString("descricao")));
        }
        
        return list;
    }
    
    public Acao selectAcao(Integer id) throws SQLException {
        String sql = "SELECT * FROM Acao WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            Acao acao = new Acao(rs.getInt("id"), rs.getString("descricao"));
        
            return acao;
        }
        
        return null;
    }
    
    public Acao insertAcao(String descricao) throws SQLException {
        String sql = "INSERT INTO Acao (descricao) VALUES('" + descricao + "')";
               
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        
        if(rs.next() && rs != null){
            Acao acao = this.selectAcao(rs.getInt(1));
        
            return acao;
        }    

        return null;
    }
    
    public Acao updateAcao(Integer id, String descricao) throws SQLException {
        String sql = "UPDATE Acao SET descricao = '" + descricao + "' WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
        
        String sqlReturn = "SELECT * FROM Acao WHERE id = " + id;
            
        PreparedStatement stm2 = con.prepareStatement(sqlReturn);
        ResultSet rs = stm2.executeQuery();

        if (rs.next()) {
            Acao acao = new Acao(rs.getInt("id"), rs.getString("descricao"));

            return acao;
        }

        return null;
    }
    
    public void deleteAcao(Integer id) throws SQLException {
        String sql = "DELETE FROM Acao WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
}
