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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TesteThread extends Thread {
    long tempo;
    
    public TesteThread(){}

    public TesteThread(String name, int tempo) {
        super(name);
        this.tempo=tempo;
    }

    @Override
    public void run() {
        Object data = Calendar.getInstance().getTime();
        System.out.println(data);
        
        while (true) {
            try {
                sleep(tempo);
                System.out.println(getName());
                
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public static void main(String[] args) {
        
        TesteThread a = new TesteThread("TICO",2000);
        TesteThread b = new TesteThread("TECO",4000);
        a.start();
        //b.start();
    
    }
}
