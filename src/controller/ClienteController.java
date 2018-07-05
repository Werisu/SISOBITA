/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Wellysson Rocha <wellysson35@gmail.com>
 * @version 1.0
 */

import dao.ClienteDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;

public class ClienteController {
    public int clienteCount = 0;
    
    public void adicionaCliente(Cliente cliente){
        // Validação
        if(cliente.getNome().isEmpty() || cliente.getTelefone().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatório");
        }else{
            ClienteDAO dao = new ClienteDAO();
            dao.addCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente " +cliente.getNome()+ " salvo com sucesso!");
        }
    }
    
    public void removeCliente(Cliente cliente){
        int isDelete = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar o usuário " + cliente.getNome() + "?");
        
        if(isDelete == JOptionPane.YES_OPTION){
            ClienteDAO dao = new ClienteDAO();
        }
    }
    
    public void verTabela(DefaultTableModel modelo){
        modelo.setNumRows(0);
        ClienteDAO dao = new ClienteDAO();
        
        for(Cliente costume : dao.procuraCliente()){
            modelo.addRow(new Object[]{
                costume.getId(),
                costume.getNome(),
                costume.getEndereco(),
                costume.getTelefone()
            });
        }
    }
    
    public void Box(javax.swing.JComboBox box){
        box.removeAllItems();
        box.addItem("Selecione");
        
        ClienteDAO dao = new ClienteDAO();
        
        for(Cliente costume: dao.procuraCliente()){
            box.addItem(costume);
        }
    }
    
    public Cliente pegaCliente(int idDoCliente){
        ClienteDAO dao = new ClienteDAO();
        Cliente custume = dao.SelecionaCliente(idDoCliente);
        
        return custume;
    }
}
