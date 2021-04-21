/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class Partida {
    private Integer id;
    private Boolean ativa;
    private Integer jogadorTurnoAtual;
    private Integer jogadorVencedor;
    private ArrayList<Integer> jogadores;
    private ArrayList<Jogador> jogadoresPartida;
    
    public Partida(Integer id, Boolean ativa, Integer jogadorTurnoAtual, Integer jogadorVencedor) {
        this.id = id;
        this.ativa = ativa;
        this.jogadorTurnoAtual = jogadorTurnoAtual;
        this.jogadorVencedor = jogadorVencedor;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setJogadores(ArrayList<Integer> jogadores) {
        this.jogadores = jogadores;
    }
    
    public void setJogadoresPartida(ArrayList<Jogador> jogadoresPartida) {
        this.jogadoresPartida = jogadoresPartida;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public void setJogadorTurnoAtual(Integer jogadorTurnoAtual) {
        this.jogadorTurnoAtual = jogadorTurnoAtual;
    }

    public void setJogadorVencedor(Integer jogadorVencedor) {
        this.jogadorVencedor = jogadorVencedor;
    }

    public ArrayList<Integer> getJogadores() {
        return jogadores;
    }
    
     public ArrayList<Jogador> getJogadoresPartida() {
        return jogadoresPartida;
    }
   

    public Integer getJogadorTurnoAtual() {
        return jogadorTurnoAtual;
    }

    public Integer getJogadorVencedor() {
        return jogadorVencedor;
    }
}