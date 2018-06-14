/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDAO;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author Wellysson
 */
public class UsuarioController {
    
    /*
    public Usuario verificarUsuario(String login, String senha){
        UsuarioDAO dao = new UsuarioDAO();
        Usuarios usuario = new Usuarios(login, senha);
        Usuarios user = dao.verificaUsuario(usuario);
        return user;
    }*/
    
    public void adicionaUsuario(String nome, String senha){              
        // Validação
        if(nome.isEmpty() || senha.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatório");
        }else{
            
            // Criando novo usuario
            Usuario usuario = new Usuario(nome, senha);
            
            // Instaciando UsuarioDAO para inserir novo usuario
            UsuarioDAO dao = new UsuarioDAO();
            dao.addUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Usuario " +nome+ " salvo com sucesso!");
        }
    }
    
}
