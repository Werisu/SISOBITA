/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe para gerenciar usuários simples somente com nome e senha
 * 
 * @author Wellysson Nascimento Rocha
 */
public class Usuario {
    
    protected Integer id;
    protected String nome;
    protected String sobrenome;
    protected String senha;

    
    /**
     * Construtor com Id
     * 
     * @param id recebe o id do usuário
     * @param nome recebe o nome do usuário
     * @param senha recebe a senha do usuário
     */
    public Usuario(Integer id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Construtor sem ID
     * 
     * @param nome recebe o nome
     * @param senha recebe a senha
     */
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(Integer id, String nome, String sobrenome, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
    }
    
    public Usuario(){}

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return getId() + " - "+ getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
