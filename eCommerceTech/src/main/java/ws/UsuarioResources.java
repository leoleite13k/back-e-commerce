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
@Path("usuario")
public class UsuarioResources {
    @Context
    private UriInfo context;
    private Gson gson;
    private UsuarioDAO usuarioDAO;
    
    public UsuarioResources() throws SQLException, ClassNotFoundException  {
        this.gson = new Gson();
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showUsuario() throws SQLException, ClassNotFoundException {       
        return gson.toJson(usuarioDAO.showUsuario());        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String selectUsuario(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {
        return gson.toJson(usuarioDAO.selectUsuario(id));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String updateUsuario(@PathParam("id") Integer id, String json) throws SQLException, ClassNotFoundException {
        Usuario usuario = gson.fromJson(json, Usuario.class);
        
        return gson.toJson(usuarioDAO.updateUsuario(
            id,
            usuario.getNome(), 
            usuario.getEmail()));      
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUsuario(String json) throws SQLException, ClassNotFoundException {       
        Usuario usuario = gson.fromJson(json, Usuario.class);
        Usuario novoUsuario = usuarioDAO.insertUsuario(
            usuario.getEmail(),
            usuario.getNome(),
            usuario.getSenha(),
            usuario.getAdministrador());
        
        return gson.toJson(usuarioDAO.selectUsuario(novoUsuario.getId()));
    }
    
    @DELETE
    @Path("{id}")
    public void deleteUsuario(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {  
        usuarioDAO.deleteUsuario(id);
    }
}
