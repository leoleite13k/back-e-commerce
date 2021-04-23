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
public class Produto {
    private Integer id;
    private String nome;
    private Float preco;
    private String foto;
    private Float quantidade;
    private String descricao;
    private Boolean promocao;
    
    public Produto(Integer id, String nome, Float preco, String foto, Float quantidade, String descricao, Boolean promocao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.foto = foto;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.promocao = promocao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

    public String getFoto() {
        return foto;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getPromocao() {
        return promocao;
    }
}
