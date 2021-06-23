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
    
    // GET
    public ArrayList<Produto> showProduto(String id_usuario) throws SQLException {
        String sql = "SELECT * FROM produto";
        
        if (!"".equals(id_usuario) && id_usuario != null) {
            sql = sql + " WHERE id_usuario = " + id_usuario;
        }
                                                  
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Produto> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Produto(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getString("foto"),
                    rs.getInt("quantidade"),
                    rs.getString("descricao"),
                    rs.getBoolean("promocao")));
        }
        
        return list;
    }
    
    public Produto selectProduto(Integer id) throws SQLException {
        String sql = "SELECT * FROM produto WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getString("foto"),
                    rs.getInt("quantidade"),
                    rs.getString("descricao"),
                    rs.getBoolean("promocao"));
        
            return produto;
        }
        
        return null;
    }
    
    public Produto insertProduto(Integer id_usuario, String nome, Float preco, String foto, Integer quantidade, String descricao, Boolean promocao) throws SQLException {
        String sql = "INSERT INTO produto (id_usuario, nome, preco, foto, quantidade, descricao, promocao) "
                + "VALUES(" + id_usuario + ",'" + nome + "'," + preco + ",'" + foto  + "'," + quantidade + ",'" + descricao + "'," + promocao + ")";
               
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        
        if(rs.next() && rs != null){
            Produto produto = this.selectProduto(rs.getInt(1));
        
            return produto;
        }    

        return null;
    }
    
    public Produto updateProduto(Integer id, String nome, Float preco, String foto, Integer quantidade, String descricao, Boolean promocao) throws SQLException {
        String sql = "UPDATE produto SET nome = '" + nome + "',"
                + "preco = " + preco + ","
                + "foto = '" + foto + "',"
                + "quantidade = " + quantidade + ","
                + "descricao = '" + descricao + "',"
                + "promocao = " + promocao + ""
                + " WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
        
        String sqlReturn = "SELECT * FROM produto WHERE id = " + id;
            
        PreparedStatement stm2 = con.prepareStatement(sqlReturn);
        ResultSet rs = stm2.executeQuery();

        if (rs.next()) {
            Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getString("foto"),
                    rs.getInt("quantidade"),
                    rs.getString("descricao"),
                    rs.getBoolean("promocao"));

            return produto;
        }

        return null;
    }
    
    public void deleteProduto(Integer id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
}
