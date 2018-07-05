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
import model.OS;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;

public class OsDAO {
    
    private Connection connection;

    public OsDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void addOS(OS os){
        String sql = "INSERT INTO tbl_os (data, hora, cliente, funcionario, desc_problema, desc_servico, valor_servico, total, status) VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, os.getDataDeLancamento());
            stmt.setString(2, os.getHoraDeLancamento());
            stmt.setInt(3, os.getCliente());
            stmt.setInt(4, os.getFuncionario());
            stmt.setString(5, os.getDesc_problema());
            stmt.setString(6, os.getDesc_servico());
            stmt.setDouble(7, os.getValor_servico());
            stmt.setDouble(8, os.getTotal());
            stmt.setString(9, os.getStatus());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public void addPecasOS(int produto, int os){
        String sql = "INSERT INTO tbl_produtos_has_tbl_os (tbl_produtos_id,tbl_os_num_os) VALUES (?,?);";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto);
            stmt.setInt(2, os);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public int pegaUltimoNumOS(){
        String sql2 = "SELECT `Auto_increment` as cod FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sisobita' AND TABLE_NAME = 'tbl_os';";
        
        ArrayList<Integer> num = new ArrayList();
        int num_os = 0;
        
        try {
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            ResultSet rs = stmt2.executeQuery();
            while(rs.next()){
                num.add(rs.getInt("cod"));
            }
            num_os = num.get(0);
            stmt2.close();
            rs.close();
        } catch (SQLException ex) {
           throw new RuntimeException("O problema está em: " +ex.getMessage());
        }
        return num_os;
    }
    
    public ArrayList<OS> procuraOS(){
        String sql = "SELECT * FROM tbl_os";
        ArrayList<OS> os = new ArrayList();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                os.add(new OS(rs.getInt("id"), rs.getString("data"), rs.getString("hora"), rs.getInt("cliente"), rs.getInt("funcionario"), rs.getString("desc_problema"), rs.getString("desc_servico"), rs.getDouble("valor_servico"), rs.getDouble("total"), rs.getString("status")));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return os;
    }
    
    public ArrayList<Produto> ProcuraPeca(int cod){
        String sql = "SELECT * FROM tbl_produtos WHERE id = ?;";
        ArrayList<Produto> produto = new ArrayList();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cod);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               produto.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("valor")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        
        return produto;
    }
    
}
