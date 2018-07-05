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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.OS;
import model.Produto;

public class OSController {
    
    public int countOS = 0;
    
    public void adicionaOS(OS os, ArrayList<Integer> codigos, int num_os){
        if(os.getCliente() == 0 || os.getDesc_problema().isEmpty() || os.getDesc_servico().isEmpty() || os.getValor_servico() == 0){
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatório");
        }else{
            OsDAO dao = new OsDAO();
            dao.addOS(os);
            for(int i=0; i<codigos.size(); i++)
                dao.addPecasOS(codigos.get(i), num_os);
            
            JOptionPane.showMessageDialog(null, "Ordem de serviço encaminhado com sucesso!");
        }
    }
    
    public int pegaNumOS(){
        OsDAO dao = new OsDAO();
        return dao.pegaUltimoNumOS();
    }
    
    public double TablePecasSelecionadas(DefaultTableModel modelo, int peca){
        OsDAO dao = new OsDAO();
        double i = 0;
        for(Produto p : dao.ProcuraPeca(peca)){
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor()
            });
            i = p.getValor();
        }
        return i;
    }
    
}
