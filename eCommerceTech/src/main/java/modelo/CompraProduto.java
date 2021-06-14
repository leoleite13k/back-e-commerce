/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author leonardo.leite
 */
public class CompraProduto {
    private Integer id;
    private Integer id_produto;
    private Float preco;
    
    public CompraProduto(Integer id, Integer id_produto, Float preco) {
        this.id = id;
        this.id_produto = id_produto;
        this.preco = preco;
    }
    
    public CompraProduto(Integer id_produto, Float preco) {
        this.id_produto = id_produto;
        this.preco = preco;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
