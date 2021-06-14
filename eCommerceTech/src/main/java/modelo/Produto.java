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
    private Integer id_usuario;
    private String nome;
    private Float preco;
    private String foto;
    private Integer quantidade;
    private String descricao;
    private Boolean promocao;
    
    public Produto(Integer id, Integer id_usuario, String nome, Float preco, String foto, Integer quantidade, String descricao, Boolean promocao) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.preco = preco;
        this.foto = foto;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.promocao = promocao;
    }
    
    public Produto(Integer id, String nome, String foto, Float preco) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }
    
    public Integer getId_usuario() {
        return id_usuario;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getPromocao() {
        return promocao;
    }
}
