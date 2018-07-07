/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

/**
 *
 * @author Wellysson
 */

import java.sql.Connection; // conexão SQL para Java
import java.sql.DriverManager; // drive de conexão SQL para java
import java.sql.SQLException; // classe para tratamento de exceções

public class ConnectionFactory {
    
    public Connection getConnection(){
        try {
            // banco de dados sisobita
            return DriverManager.getConnection("jdbc:mysql://localhost/sisobita", "root", "");
        } catch (SQLException ex) {
            throw new RuntimeException("Falha na conexão: " + ex.getMessage());
        }
    }
    
}
