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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leonardoleite
 */
public class CompraDAO extends DAO {
    public CompraDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    // GET
    public ArrayList<Compra> showCompra(Integer id_usuario) throws SQLException {
        String sql = "SELECT * FROM compra WHERE id_usuario = " + id_usuario;
                                                  
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Compra> compras = new ArrayList<>();
        while(rs.next()){
            compras.add(new Compra(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getFloat("frete"),
                    rs.getFloat("total")));
        }
        
        compras.forEach(compra -> {
            try {
                String sqlProduto = "SELECT produto.id, produto.nome, produto.foto, compra_produto.preco"
                        + " FROM compra_produto"
                        + " INNER JOIN produto ON compra_produto.id_produto = produto.id"
                        + " WHERE compra_produto.id = " + compra.getId();
                
                PreparedStatement stmProduto = con.prepareStatement(sqlProduto);
                ResultSet rsProduto = stmProduto.executeQuery();
                
                ArrayList<Produto> produtos = new ArrayList<>();
                while(rsProduto.next()){
                    produtos.add(new Produto(
                            rsProduto.getInt("id"),
                            rsProduto.getString("nome"),
                            rsProduto.getString("foto"),
                            rsProduto.getFloat("preco")));
                }
                
                compra.setProdutos(produtos);
            } catch (SQLException ex) {
                Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return compras;
    }
    
    public Compra selectCompra(Integer id) throws SQLException {
        String sql = "SELECT * FROM compra WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
                
        if (rs.next()) {
            Compra compra = new Compra(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getFloat("frete"),
                        rs.getFloat("total"));

            String sqlProduto = "SELECT produto.id, produto.nome, produto.foto, compra_produto.preco"
                    + " FROM compra_produto"
                    + " INNER JOIN produto ON compra_produto.id_produto = produto.id"
                    + " WHERE compra_produto.id = " + compra.getId();

            PreparedStatement stmProduto = con.prepareStatement(sqlProduto);
            ResultSet rsProduto = stmProduto.executeQuery();

            ArrayList<Produto> produtos = new ArrayList<>();
            while(rsProduto.next()){
                produtos.add(new Produto(
                        rsProduto.getInt("id"),
                        rsProduto.getString("nome"),
                        rsProduto.getString("foto"),
                        rsProduto.getFloat("preco")));
            }

            compra.setProdutos(produtos);

            return compra;
        }
        
        return null;
    }
    
    public Compra insertCompra(Integer id_usuario, Float frete, Float total, ArrayList<CompraProduto> compraProdutos) throws SQLException {
        String sql = "INSERT INTO compra (id_usuario, frete, total) "
                + "VALUES(" + id_usuario + "," + frete + "," + total + ")";
               
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        Integer id;
        
        if(rs.next() && rs != null){
            id = rs.getInt(1);
            
            compraProdutos.forEach(produto -> {
                try {                
                    String sqlProduto = "INSERT INTO compra_produto(id, id_produto, preco) "
                            + "VALUES(" + id + "," + produto.getId_produto() + "," + produto.getPreco() + ")";
                    

                    PreparedStatement stmProduto = con.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS);
                    stmProduto.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            Compra compra = this.selectCompra(id);
            
            return compra;
        }
        
        return null;
    }
    
    public void deleteCompra(Integer id) throws SQLException {
        String sql = "DELETE FROM compra WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
}
