/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Wellysson
 */

import dao.ProdutoDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;

public class ProdutoController {
    
    public int produtoCount = 0;
    
    /**
     * Esse método adiciona um novo produto no banco de dados
     * @param produto 
     */
    public void adicionaProduto(Produto produto){
        if(produto.getNome().isEmpty() || produto.getQuanditade() == 0 || produto.getValor() == 0){
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatório");
        }else{
            ProdutoDAO dao = new ProdutoDAO();
            dao.addProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto " +produto.getNome()+ " cadastrado com sucesso!");
        }
        
    }
    
    /**
     * Esse método mostra uma tabela de produtos cadastrados no banco de dados
     * @param modelo 
     */
    public void verTabela(DefaultTableModel modelo){
        modelo.setNumRows(0);
        ProdutoDAO dao = new ProdutoDAO();
        
        for(Produto prod : dao.procuraProduto()){
            modelo.addRow(new Object[]{
                prod.getId(),
                prod.getNome(),
                prod.getQuanditade(),
                prod.getValor()
            });
        }
    }
    
    /**
     * Esse método seleciona o produto
     * @param idDoProduto
     * @return 
     */
    public Produto pegaProduto(int idDoProduto){
        ProdutoDAO dao = new ProdutoDAO();
        Produto product = dao.selecionaProduto(idDoProduto);
        
        return product;
    }
    
    /**
     * Esse método atualiza o produto selecionado
     * @param id
     * @param nome
     * @param quantidade
     * @param valor 
     */
    public void atualizarProduto(int id, String nome, int quantidade, double valor){
        ProdutoDAO dao = new ProdutoDAO();
        Produto p = new Produto(id, nome, quantidade, valor);
        dao.atualizarProduto(p);
    }
    
    /**
     * Esse método envia o produto à ser deletado para o dao
     * @param id
     * @param nome
     * @param quantidade
     * @param valor 
     */
    public void deleteProduto(int id, String nome, int quantidade, double valor){
        Produto p = new Produto(id, nome, quantidade, valor);
        ProdutoDAO dao = new ProdutoDAO();
        dao.DeleteProduto(p);
    }
    
    /**
     * Esse método imprime um relatório de produtos iReport
     */
    public void imprimirRelatorio(){
        ProdutoDAO dao = new ProdutoDAO();
        dao.ReportProducts();
    }
    
}
