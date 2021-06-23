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
import modelo.Produto;
import modelo.ProdutoDAO;

/**
 * REST Web Service
 * 
 * @author leonardoleite
 */
@Path("produto")
public class ProdutoResources {

    @Context
    private UriInfo context;
    private Gson gson;
    private ProdutoDAO produtoDAO;
    
    public ProdutoResources() throws SQLException, ClassNotFoundException  {
        this.gson = new Gson();
        this.produtoDAO = new ProdutoDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @QueryParam("id_usuario, nome")
    public String showProduto(@QueryParam("id_usuario") String id_usuario, @QueryParam("nome") String nome) throws SQLException, ClassNotFoundException {            
        return gson.toJson(produtoDAO.showProduto(id_usuario, nome));        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String selectProduto(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {
        return gson.toJson(produtoDAO.selectProduto(id));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String updateProduto(@PathParam("id") Integer id, String json) throws SQLException, ClassNotFoundException {
        Produto produto = gson.fromJson(json, Produto.class);
        
        return gson.toJson(produtoDAO.updateProduto(
            id,
            produto.getNome(),
            produto.getPreco(),
            produto.getFoto(),
            produto.getQuantidade(),
            produto.getDescricao(),
            produto.getPromocao()));      
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertProduto(String json) throws SQLException, ClassNotFoundException {       
        Produto produto = gson.fromJson(json, Produto.class);
        Produto novoProduto = produtoDAO.insertProduto(
            produto.getId_usuario(),
            produto.getNome(),
            produto.getPreco(),
            produto.getFoto(),
            produto.getQuantidade(),
            produto.getDescricao(),
            produto.getPromocao());
        
        return gson.toJson(produtoDAO.selectProduto(novoProduto.getId()));
    }
    
    @DELETE
    @Path("{id}")
    public void insertProduto(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {  
        produtoDAO.deleteProduto(id);
    }    

}
