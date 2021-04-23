/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author gdragoni
 */
public class HistoricoPartida {
    private Integer id;
    private Integer idPartida;
    private Integer idJogador;
    private Integer idAcao;
    private Date data;

    public HistoricoPartida(Integer id, Integer idPartida, Integer idJogador, Integer idAcao, Date data) {
        this.id = id;
        this.idPartida = idPartida;
        this.idJogador = idJogador;
        this.idAcao = idAcao;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public Integer getIdAcao() {
        return idAcao;
    }

    public Date getData() {
        return data;
    }
}
