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
            stmt.setInt(2, produto.getQuanditade());
            stmt.setDouble(3, produto.getValor());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto: " + ex.getMessage());
        }
    }
    
    public ArrayList<Produto> procuraProduto(){
        String sql = "SELECT * FROM tbl_produtos";
        ArrayList<Produto> produto = new ArrayList();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                produto.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("valor")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na tabela: " + ex);
            throw new RuntimeException(ex);
        }
        
        return produto;
    }
}
