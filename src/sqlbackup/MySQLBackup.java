/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlbackup;

/**
 *
 * @author Wellysson
 */

import java.util.*;
import java.io.File;

public class MySQLBackup {
    
    // Constante da Classe
    private static String SEPARATOR = File.separator;
    
    /**
     * a constante da classe chamada MYSQL_PATH deve apontar devidamente para o caminho do seu banco de dados MySQL 
     * onde está o utilitário mysqldump.exe; em nosso caso, ela é "E:\xampp\mysql\bin\";
     */
    private static String MYSQL_PATH =
    "E:" + SEPARATOR + "xampp" + SEPARATOR + "mysql" + SEPARATOR + "bin" + SEPARATOR;
    
    // Lista dos bancos de dados a serem "backupeados"; se desejar adicionar mais,
    // basta colocar o nome separado por espaços dos outros nomes
    
    private static String DATABASES = "sisobita";
    private List<String> dbList = new ArrayList<String>();
    public MySQLBackup(){
        String command = MYSQL_PATH + "mysqldump.exe";
        String[] databases = DATABASES.split(" ");
         for (int i = 0; i < databases.length; i++){
              dbList.add(databases[i]);
         }
         System.out.println("Iniciando backups...\n\n");
         
         int i = 1; // Contador
         
         long time1, time2, time; // tempo
         time1 = System.currentTimeMillis(); //inicio
         
            for (String dbName : dbList) {

                ProcessBuilder pb = new ProcessBuilder(

                  command,

                  "--user=root",

                  "--password=",

                  dbName,

                  "--result-file=" + "src" + SEPARATOR + "Backup" + SEPARATOR + dbName + ".sql");

                try {
                  System.out.println("Backup do banco de dados (" + i + "): " + dbName + " ...");
                  pb.start();
                }
                catch (Exception e) {
                  e.printStackTrace();
                }
                
                i++;
            }
            
            time2 = System.currentTimeMillis(); //Fim
            
            time = time2 - time1; // Tempo total da operação
            
            System.out.println("\nBackups realizados com sucesso.\n\n");
            System.out.println("Tempo total de processamento: " + time + " ms\n");
            System.out.println("Finalizando...");
            
            try {
                // Paralisa por 2 segundos
                Thread.sleep(2000);
              }
              catch (Exception e) {}
              // Termina o aplicativo
              //System.exit(0);
    }
}
