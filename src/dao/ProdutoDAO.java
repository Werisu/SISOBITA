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

import factory.ConnectionFactory;
import model.Produto; // importando o modelo de produto.class
import java.sql.*; // importando as bibliotecas de conexão sql
import java.sql.PreparedStatement; // importando PreparedStatement
import java.util.ArrayList; // importando Array list para salvar os dados em uma lista
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    private Connection connection;

    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void addProduto(Produto produto){
        String sql = "INSERT INTO tbl_produtos (nome, quantidade, valor) VALUES (?,?,?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getQuanditade());
            stmt.setString(3, produto.getValor());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto: " + ex.getMessage());
        }
    }
}
