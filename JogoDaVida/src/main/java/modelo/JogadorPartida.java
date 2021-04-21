/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author leonardoleite
 */
public class JogadorPartida {
    private Integer id;
    private Integer idJogador;
    private Integer idPartida;
    private Integer posicaoAtual;
    private Double salarioAtual;
    private Double dinheiroAtual;
    private Double promissoriaAtual;
    private Integer idAcao;
    private Integer primeiroNumeroRoleta;
    
    public JogadorPartida(Integer id, Integer idJogador, Integer idPartida, Integer posicaoAtual, Double salarioAtual, Double dinheiroAtual, Double promissoriaAtual, Integer idAcao, Integer primeiroNumeroRoleta) {
        this.id = id;
        this.idJogador = idJogador;
        this.idPartida = idPartida;
        this.posicaoAtual = posicaoAtual;
        this.salarioAtual = salarioAtual;
        this.dinheiroAtual = dinheiroAtual;
        this.promissoriaAtual = promissoriaAtual;
        this.idAcao = idAcao;
        this.primeiroNumeroRoleta = primeiroNumeroRoleta;
    }
    

    public Integer getId() {
        return id;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public Double getSalarioAtual() {
        return salarioAtual;
    }

    public Double getDinheiroAtual() {
        return dinheiroAtual;
    }

    public Double getPromissoriaAtual() {
        return promissoriaAtual;
    }

    public Integer getIdAcao() {
        return idAcao;
    }
    
    public Integer getPrimeiroNumeroRoleta() {
        return primeiroNumeroRoleta;
    }
}
