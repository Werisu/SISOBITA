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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
    
    /**
     * Esse método captura os dados de um produto no banco de dados através do id passado como parâmentro
     * @param idDoProduto do tipo inteiro, é o id dos dados que serão alterados ou excluídos
     * @return retorna o produto
     */
    public Produto selecionaProduto(int idDoProduto){
        String sql = "SELECT * FROM tbl_produtos WHERE id = ?";
        Produto produto = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idDoProduto);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("valor"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro");
            throw new RuntimeException("Erro ao procurar produto" + ex.getMessage());
        }
        
        
        return produto;
    }
    
    /**
     * Esse método irá atualizar o produto selecionado no banco de dados
     * @param produto do tipo Produto, seleciona os dados alterados
     */
    public void atualizarProduto(Produto produto){
        String sql = "UPDATE tbl_produtos SET nome=?, quantidade=?, valor=? WHERE id=?;";
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Atualizar dados do produto "+produto.getNome()+"?", "Atenção, você está atualizando os dados!", JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION){
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                stmt.setInt(2, produto.getQuanditade());
                stmt.setDouble(3, produto.getValor());
                stmt.setInt(4, produto.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Produto Atualizado!");
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao cadastrar cliente: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Esse método deleta, do banco de dados o produto selecionado
     * @param produto Tipo produto passado como parâmetro
     */
    public void DeleteProduto(Produto produto){
        String sql = "DELETE FROM tbl_produtos WHERE tbl_produtos.id=?;";
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Deletar produto "+produto.getNome()+"?", "Atenção! Você está deletando um produto.", JOptionPane.YES_NO_OPTION);
        
        if(confirmar==JOptionPane.YES_OPTION){
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, produto.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Produto deletado!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar produto: "+ex.getMessage());
                throw new RuntimeException("Erro ao deletar produto: " + ex.getMessage());
            }
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
    
    public void ReportProducts(){
        int confirma = JOptionPane.showConfirmDialog(null, "Confirmar impresão relatório de produtos?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(confirma == JOptionPane.YES_OPTION){
            try {
                JasperPrint print = JasperFillManager.fillReport("src/reports/ReportProdutos.jasper", null, connection);
                
                JasperViewer.viewReport(print, false);
            } catch (JRException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
