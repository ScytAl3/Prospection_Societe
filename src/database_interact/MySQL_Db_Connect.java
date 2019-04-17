/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_Interact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe servant a creer la connection a la base de donnees
 * @author Stagiaire
 */
public abstract class MySQL_Db_Connect {
    
    /**
     * Methode pour obtenir la connexion à la base de données
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException{        
        Properties props = new Properties();
        File configFile = null;
        FileInputStream fInput = null;
        Connection connect = null;
        
        try{ 
            configFile = new File("database.properties");
            if(!configFile.exists()){
                System.out.println("Attention au chemin du fichier properties ! " + configFile );
                configFile = new File("C:\\Users\\Stagiaire\\Documents\\NetBeansProjects"
                        + "\\New_FilRouge\\database.properties");
            }
            fInput= new FileInputStream(configFile);
            //  Chargement du fichier properties            
            props.load(fInput);
            fInput.close();
            //  Assignements des parametre de la base de donnees
            String JDBC_DRIVER = props.getProperty("jdbc.JDBC_DRIVER");
            String DB_URL = props.getProperty("jdbc.DB_URL");
            String USER = props.getProperty("jdbc.USER");
            String PWD = props.getProperty("jdbc.PWD");
            
            //Enregistrement du driver JDBC
            Class.forName(JDBC_DRIVER);
            //  Creation de la connection a la base de donnee
            connect = DriverManager.getConnection(DB_URL, USER, PWD);
            
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return connect;
    }    
}
