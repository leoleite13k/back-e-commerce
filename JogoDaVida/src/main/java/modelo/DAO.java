/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gdragoni
 */

public class DAO {
    
    protected Connection con;
    
    public DAO() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogodavida?useTimezone=true&serverTimezone=GMT&allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
    }
}
