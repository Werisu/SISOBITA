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
    
    public void adicionaProduto(Produto produto){
        if(produto.getNome().isEmpty() || produto.getQuanditade() == 0 || produto.getValor() == 0){
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatório");
        }else{
            ProdutoDAO dao = new ProdutoDAO();
            dao.addProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto " +produto.getNome()+ " cadastrado com sucesso!");
        }
        
    }
    
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
    
    public void imprimirRelatorio(){
        ProdutoDAO dao = new ProdutoDAO();
        dao.ReportProducts();
    }
    
}
