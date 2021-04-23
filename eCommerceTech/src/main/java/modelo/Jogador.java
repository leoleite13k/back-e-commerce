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
public class Jogador {
    private Integer id;
    private String nome;
    private String senha;
    private ArrayList<Partida> partidasAtivas;

    public Jogador(Integer id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @return the partidasAtivas
     */
    public ArrayList<Partida> getPartidasAtivas() {
        return partidasAtivas;
    }

    public void setPartidasAtivas(ArrayList<Partida> partidasAtivas) {
        this.partidasAtivas = partidasAtivas;
    }
}
