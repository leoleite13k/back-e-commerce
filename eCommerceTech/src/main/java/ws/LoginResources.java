/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 * REST Web Service
 * 
 * @author leonardoleite
 */
@Path("login")
public class LoginResources {
    @Context
    private UriInfo context;
    private Gson gson;
    private UsuarioDAO usuarioDAO;
    
    public LoginResources() throws SQLException, ClassNotFoundException  {
        this.gson = new Gson();
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String json) throws SQLException, ClassNotFoundException {       
        Usuario usuario = gson.fromJson(json, Usuario.class);
        return gson.toJson(usuarioDAO.login(
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getAdministrador()));
    }
}
