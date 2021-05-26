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
public class UsuarioDAO extends DAO {
    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    
    // GET
    public ArrayList<Usuario> showUsuario() throws SQLException {
        String sql = "SELECT * FROM usuario";
                                                  
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Usuario> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("nome")));
        }
        
        return list;
    }
    
    public Usuario selectUsuario(Integer id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("nome"));
        
            return usuario;
        }
        
        return null;
    }
    
    public Usuario insertUsuario(String email, String nome, String senha, Boolean administrador) throws SQLException {
        String sql = "INSERT INTO usuario (email, nome, senha, administrador) "
                + "VALUES('" + email + "','" + nome + "','"  + senha + "'," + administrador + ")";
        
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.executeUpdate();
        ResultSet rs = stm.getGeneratedKeys();
        
        if(rs.next() && rs != null){
            Usuario usuario = this.selectUsuario(rs.getInt(1));
        
            return usuario;
        }    

        return null;
    }
    
    public Usuario updateUsuario(Integer id, String nome, String email) throws SQLException {
        String sql = "UPDATE usuario SET nome = '" + nome + "',email = '" + email + "' WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
        
        String sqlReturn = "SELECT * FROM usuario WHERE id = " + id;
            
        PreparedStatement stm2 = con.prepareStatement(sqlReturn);
        ResultSet rs = stm2.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("nome"));

            return usuario;
        }

        return null;
    }
    
    public void deleteUsuario(Integer id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = " + id;
               
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public Boolean login(String email, String senha, Boolean administrador) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE "
                + "email = '" + email + "'" 
                + "AND senha = '" + senha + "'";
        
        if (administrador) {
            sql = sql + "AND administrador = " + administrador;
        }
               
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        return rs.next() && rs.getInt("id") >= 1;
    }
}
