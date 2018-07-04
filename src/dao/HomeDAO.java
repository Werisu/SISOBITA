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
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class HomeDAO {
    private Connection connection;

    public HomeDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public ArrayList procuraDadosTableHome(DefaultTableModel modelo){
        String sql = "SELECT u.nome, os.num_os, os.status, os.total FROM usuarios u JOIN tbl_os os ON u.id = os.funcionario;";
        ArrayList resultado = new ArrayList();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String[] dados = new String[4];
                dados[0] = rs.getString("u.nome");
                dados[1] = rs.getString("os.num_os");
                dados[2] = rs.getString("os.status");
                dados[3] = rs.getString("os.total");
                modelo.addRow(dados);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
        
    }
    
    
}
