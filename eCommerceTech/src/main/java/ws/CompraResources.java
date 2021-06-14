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
import modelo.Compra;
import modelo.CompraDAO;

/**
 * REST Web Service
 * 
 * @author leonardoleite
 */
@Path("compra")
public class CompraResources {

    @Context
    private UriInfo context;
    private Gson gson;
    private CompraDAO compraDAO;
    
    public CompraResources() throws SQLException, ClassNotFoundException  {
        this.gson = new Gson();
        this.compraDAO = new CompraDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @QueryParam("id_usuario")
    public String showCompra(@QueryParam("id_usuario") Integer id_usuario) throws SQLException, ClassNotFoundException {       
        return gson.toJson(compraDAO.showCompra(id_usuario));        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String selectCompra(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {
        return gson.toJson(compraDAO.selectCompra(id));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertCompra(String json) throws SQLException, ClassNotFoundException {       
        Compra compra = gson.fromJson(json, Compra.class);
        Compra novaCompra = compraDAO.insertCompra(
            compra.getId_usuario(),
            compra.getFrete(),
            compra.getTotal(),
            compra.getCompraProdutos());
        
        return gson.toJson(compraDAO.selectCompra(novaCompra.getId()));
    }
    
    @DELETE
    @Path("{id}")
    public void insertCompra(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {  
        compraDAO.deleteCompra(id);
    }
}
