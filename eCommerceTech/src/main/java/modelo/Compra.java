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
public class Compra {
    private Integer id;
    private Integer id_usuario;
    private Float frete;
    private Float total;
    private ArrayList<Produto> produtos;
    private ArrayList<CompraProduto> compraProdutos;
    
    public Compra(Integer id, Integer id_usuario, Float frete, Float total) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.frete = frete;
        this.total = total;
    }
    
    public Compra(Integer id, Integer id_usuario, Float frete, Float total, ArrayList<Produto> produtos) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.frete = frete;
        this.total = total;
        this.produtos = produtos;
    }
    
    public Compra(Integer id_usuario, Float frete, Float total, ArrayList<CompraProduto> compraProdutos) {
        this.id_usuario = id_usuario;
        this.frete = frete;
        this.total = total;
        this.compraProdutos = compraProdutos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Float getFrete() {
        return frete;
    }

    public void setFrete(Float frete) {
        this.frete = frete;
    }
    
    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public ArrayList<CompraProduto> getCompraProdutos() {
        return compraProdutos;
    }

    public void setCompraProdutos(ArrayList<CompraProduto> compraProdutos) {
        this.compraProdutos = compraProdutos;
    }
}
