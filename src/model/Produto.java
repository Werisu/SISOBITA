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
    protected int quanditade;
    protected double valor;

    public Produto(Integer id, String nome, int quanditade, double valor) {
        this.id = id;
        this.nome = nome;
        this.quanditade = quanditade;
        this.valor = valor;
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

    public int getQuanditade() {
        return quanditade;
    }

    public void setQuanditade(int quanditade) {
        this.quanditade = quanditade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
