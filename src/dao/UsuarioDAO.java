/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Wellysson
 */

import factory.ConnectionFactory; // importanto conexão
import model.Usuario; // importanto modelo de usuario
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UsuarioDAO {
    private Connection connection;
    
    public UsuarioDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void addUsuario(Usuario usuario){
        String sql = "INSERT INTO usuarios(nome,senha) VALUES (?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public ArrayList<Usuario> procuraUsuario(){
        String sql = "SELECT * FROM usuarios";
        ArrayList<Usuario> usuario = new ArrayList();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuario.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha")));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
