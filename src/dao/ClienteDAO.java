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

import factory.ConnectionFactory; // importanto conexão
import model.Cliente; // importando modelo
import java.sql.*; // importanto a biblioteca sql
import java.sql.PreparedStatement; // importanto o prepared Statement
import java.util.ArrayList; // importanto o arraylist
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ClienteDAO {
    
    private Connection connection; // criando a variável connection
    
    /**
     * Construtor que inicializa o Connection
     */
    public ClienteDAO(){
        // inicializando connection
        this.connection = new ConnectionFactory().getConnection();
    }
    
    /**
     * Função para adicionar um novo cliente ao banco de dados
     * @param cliente tipo Cliente contendo as informações do cliente
     * @return retorna uma string com a mensagem de sucesso
     */
    public String addCliente(Cliente cliente){
        // criando a string com o comando SQL
        String sql = "INSERT INTO tbl_cliente(nome_completo, telefone, endereco) VALUES (?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql); // dispara(abre) o sql e coloca os itens abaixo no "?"
            stmt.setString(1, cliente.getNome()); // pega o nome do cliente e salva no banco de dados
            stmt.setString(2, cliente.getTelefone()); // pega o telefone e salva no banco de dados
            stmt.setString(3, cliente.getEndereco()); // pega o endereço e salva no banco de dados
            stmt.execute(); // executa tudo
            stmt.close(); // depois fecha o Prepared Statement
            return "Cliente " + cliente.getNome() + "cadastrado com sucesso";
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar cliente: " + e);
        }
    }
    
    /**
     * Método para atualizar os dados do cliente
     * @param cliente é passado como parâmetro para buscar os dados alterados
     */
    public void atualizarCliente(Cliente cliente){
        String sql = "UPDATE tbl_cliente SET nome_completo=?, telefone=?, endereco =? WHERE id=?;";
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Atualizar dados do cliente "+cliente.getNome()+"?", "Atenção",JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION){
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getTelefone());
                stmt.setString(3, cliente.getEndereco());
                stmt.setInt(4, cliente.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Dados atualizados!");
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao cadastrar cliente: " + ex.getMessage());
            }
        }
    }
    
    public void deleteCliente(Cliente cliente){
        String sql = "DELETE FROM `tbl_cliente` WHERE `tbl_cliente`.`id` = ?;";
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Deletar cliente "+cliente.getNome()+"?", "Atenção!", JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION){
           PreparedStatement stmt;
            try {
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, cliente.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Cliente deletado!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: "+ex.getMessage());
                throw new RuntimeException("Erro ao deletar cliente: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Função para procurar cliente dentro do banco de dados
     * @return retorna um vetor com clientes achados na base de dados
     */
    public ArrayList<Cliente> procuraCliente(){
        
        String sql = "SELECT * FROM tbl_cliente";
        ArrayList<Cliente> cliente = new ArrayList();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                cliente.add(new Cliente(rs.getInt("id"), rs.getString("nome_completo"), rs.getString("endereco"), rs.getString("telefone")));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar Cliente" + e.getMessage());
        }
        
        
        return cliente;
    }
    
    public Cliente SelecionaCliente(int idDoCliente){
        
        String sql = "SELECT * FROM tbl_cliente WHERE id = ?";
        //ArrayList<Cliente> cliente = new ArrayList();
        Cliente cliente = null;
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idDoCliente);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cliente = new Cliente(rs.getInt("id"), rs.getString("nome_completo"), rs.getString("endereco"), rs.getString("telefone"));
            }
                
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar Cliente" + e.getMessage());
        }
        
        
        return cliente;
    }
    
    public void reportCostume(){
        // gerar relatorio
        int confirma = JOptionPane.showConfirmDialog(null, "Confirmar Impressão relatório de Clientes?", "Atenção",JOptionPane.YES_NO_OPTION);
        
        if(confirma == JOptionPane.YES_OPTION){
            // imprimindo relatório com o framework JasperReports
            try {
                // Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("src/reports/report1.jasper", null, connection);
                
                // A seguir exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
}
