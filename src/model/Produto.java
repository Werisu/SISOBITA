/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Wellysson
 */
public class Produto {
    
    protected Integer id;
    protected String nome;
    protected String quanditade;
    protected String valor;

    public Produto(Integer id, String nome, String quanditade, String senha) {
        this.id = id;
        this.nome = nome;
        this.quanditade = quanditade;
        this.valor = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuanditade() {
        return quanditade;
    }

    public void setQuanditade(String quanditade) {
        this.quanditade = quanditade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
}
