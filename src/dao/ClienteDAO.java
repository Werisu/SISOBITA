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
    
}
