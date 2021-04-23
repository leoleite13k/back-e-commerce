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
public class ProdutoDAO extends DAO {
    public ProdutoDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    public ArrayList<Produto> showProduto() throws SQLException {
        String sql = "SELECT * FROM produto";
                                                  
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Produto> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getFloat("preco"), rs.getString("foto"), rs.getFloat("quantidade"), rs.getString("descricao"), rs.getBoolean("promocao")));
        }
        
        return list;
    }
    
    public Produto selectProduto(Integer id) throws SQLException {
        String sql = "SELECT * FROM produto WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getFloat("preco"), rs.getString("foto"), rs.getFloat("quantidade"), rs.getString("descricao"), rs.getBoolean("promocao"));
        
            return produto;
        }
        
        return null;
    }
    
    public Produto insertAcao(String descricao) throws SQLException {
        String sql = "INSERT INTO Acao (descricao) VALUES('" + descricao + "')";
               
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        
        if(rs.next() && rs != null){
            Produto acao = this.selectAcao(rs.getInt(1));
        
            return acao;
        }    

        return null;
    }
    
    public Produto updateAcao(Integer id, String descricao) throws SQLException {
        String sql = "UPDATE Acao SET descricao = '" + descricao + "' WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
        
        String sqlReturn = "SELECT * FROM Acao WHERE id = " + id;
            
        PreparedStatement stm2 = con.prepareStatement(sqlReturn);
        ResultSet rs = stm2.executeQuery();

        if (rs.next()) {
            Produto acao = new Produto(rs.getInt("id"), rs.getString("descricao"));

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
