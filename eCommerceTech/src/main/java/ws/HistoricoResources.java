/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.HistoricoPartidaDAO;

/**
 * REST Web Service
 *
 * @author gdragoni
 */
@Path("historico")
public class HistoricoResources {

    @Context
    private UriInfo context;
    private Gson gson;
    private HistoricoPartidaDAO historicoPartidaDAO;
    
    public HistoricoResources() throws SQLException, ClassNotFoundException  {
        this.gson = new Gson();
        this.historicoPartidaDAO = new HistoricoPartidaDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTodosJogadores(@QueryParam("id_partida") Integer idPartida, @QueryParam("id_jogador") Integer idJogador) throws SQLException, ClassNotFoundException {
        return gson.toJson(historicoPartidaDAO.selectHistorico(idPartida, idJogador));
    }
}