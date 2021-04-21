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
import modelo.Acao;
import modelo.AcaoDAO;

/**
 * REST Web Service
 * 
 * @author leonardoleite
 */
@Path("acao")
public class AcaoResources {

    @Context
    private UriInfo context;
    private Gson gson;
    private AcaoDAO acaoDAO;
    
    public AcaoResources() throws SQLException, ClassNotFoundException  {
        this.gson = new Gson();
        this.acaoDAO = new AcaoDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listAcao(@QueryParam("descricao") String descricao) throws SQLException, ClassNotFoundException {       
        return gson.toJson(acaoDAO.listAcao(descricao));        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id_acao}")
    public String selectAcao(@PathParam("id_acao") Integer idAcao) throws SQLException, ClassNotFoundException {
        return gson.toJson(acaoDAO.selectAcao(idAcao));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id_acao}")
    public String updateAcao(@PathParam("id_acao") Integer idAcao, String json) throws SQLException, ClassNotFoundException {
        Acao acao = gson.fromJson(json, Acao.class);
        
        return gson.toJson(acaoDAO.updateAcao(idAcao, acao.getDescricao()));      
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertAcao(String json) throws SQLException, ClassNotFoundException {  
        Acao acao = gson.fromJson(json, Acao.class);
        Acao novaAcao = acaoDAO.insertAcao(acao.getDescricao());
        
        return gson.toJson(acaoDAO.selectAcao(novaAcao.getId()));
    }
    
    @DELETE
    @Path("{id_acao}")
    public void insertAcao(@PathParam("id_acao") Integer idAcao) throws SQLException, ClassNotFoundException {  
        acaoDAO.deleteAcao(idAcao);
    }
}
