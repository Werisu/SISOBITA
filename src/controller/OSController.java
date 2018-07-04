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

import dao.OsDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.OS;

public class OSController {
    
    public int countOS = 0;
    
    public void adicionaOS(OS os){
        if(os.getCliente() == 0 || os.getDesc_problema().isEmpty() || os.getDesc_servico().isEmpty() || os.getValor_servico() == 0){
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatório");
        }else{
            OsDAO dao = new OsDAO();
            dao.addOS(os);
            JOptionPane.showMessageDialog(null, "Ordem de serviço encaminhado com sucesso!");
        }
    }
    
}
