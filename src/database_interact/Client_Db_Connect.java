/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_Interact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import lesclasses.Client;

/**
 * Classe pour les operations a la base de donnees liees a la table Client, herite
 * de la classe MySQL_Db_Connect qui creee la connection
 * @author Stagiaire
 */
public class Client_Db_Connect extends MySQL_Db_Connect {
    
  /**
   * Constructeur par defaut
   */
  public Client_Db_Connect(){
        super();
    }
    
    /**
     * Methode pour recuperer les entetes de la table Client
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Object[] enteteTableau() throws SQLException, ClassNotFoundException{
        //  Creation de la requete
        String SQLquery = "SELECT * FROM client";
        // creation d une nouvelle connection 
        try (Connection myClientConn = Client_Db_Connect.getConnection();
             Statement statement = myClientConn.createStatement();
             ResultSet rSet = statement.executeQuery(SQLquery)) {                               
            //  Creation du MetaData pour lire le nom des colonnes
            ResultSetMetaData  rSetMeta = rSet.getMetaData();
            //  On stocke le nom des colonnes dans un tableau de String
            String header[] = new String[]{
                            rSetMeta.getColumnName(1),
                            rSetMeta.getColumnName(2),
                            rSetMeta.getColumnName(3),
                            rSetMeta.getColumnName(4),
                            rSetMeta.getColumnName(5),
                            rSetMeta.getColumnName(6),
                            rSetMeta.getColumnName(7),
                            rSetMeta.getColumnName(8),
                            rSetMeta.getColumnName(9),
                            rSetMeta.getColumnName(10),
                            rSetMeta.getColumnName(11),
            };
            return header;
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
        return null;
    }
    
    /**
     * Methode pour recuperer la liste des Clients
     * @return
     * @throws Exception 
     */
    public static List<Client> tousLesClients() throws Exception{
        //  Creation de la liste
        List<Client> mesClients = new ArrayList<>();
        //  Creation de la requete
        String SQLquery = "SELECT * FROM client";
        // creation d une nouvelle connection 
        try (Connection myClientConn = Client_Db_Connect.getConnection();
             Statement statement = myClientConn.createStatement();
             ResultSet rSet = statement.executeQuery(SQLquery)) {                               
            
            
            while(rSet.next()){
                Client leClient = new Client();
                
                leClient.setIdent(rSet.getInt("IDCLIENT"));
                leClient.setRaison(rSet.getString("RSCLIENT"));
                leClient.setTypeSo(rSet.getString("TYPECLIENT"));
                leClient.setDomaine(rSet.getString("DOMAINECLIENT"));
                leClient.setAdress(rSet.getString("ADRCLIENT"));
                leClient.setTel(rSet.getString("TELCLIENT"));
                leClient.setCa(rSet.getInt("CACLIENT"));
                leClient.setComment(rSet.getString("COMMENTAIRESCLIENT"));
                leClient.setNbrEmp(rSet.getInt("NBREMPCLIENT"));
                leClient.setDateContrat(rSet.getDate("DATECONTRAT"));
                leClient.setAdresseMail(rSet.getString("ADRESSEMAIL"));
                
                mesClients.add(leClient);
            }                       
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
        return mesClients;
    }
    
    /**
     * Methode pour creer un nouveau Client
     * @param newClient
     * @throws Exception 
     */
    public void InsertInto(Client newClient) throws Exception{
        String query = "INSERT INTO Client ("
                + " IDCLIENT,"
                + " RSCLIENT,"
                + " TYPECLIENT,"
                + " DOMAINECLIENT,"
                + " ADRCLIENT,"
                + " TELCLIENT,"
                + " CACLIENT,"
                + " COMMENTAIRESCLIENT,"
                + " NBREMPCLIENT,"
                + " DATECONTRAT,"
                + " ADRESSEMAIL) VALUES ("
                + "null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection myClientConn = Client_Db_Connect.getConnection();
                PreparedStatement pstatement = myClientConn.prepareStatement(query)){
            
            //  Recuperation des parametres pour le PreparedStatement
            pstatement.setString(1, newClient.getRaison());
            pstatement.setString(2, newClient.getTypeSo());
            pstatement.setString(3, newClient.getDomaine());
            pstatement.setString(4, newClient.getAdresse());
            pstatement.setString(5, newClient.getTel());
            pstatement.setInt(6, newClient.getCa());
            pstatement.setString(7, newClient.getComment());
            pstatement.setInt(8, newClient.getNbrEmp());
            java.sql.Date sqlDate = new java.sql.Date(newClient.getDateContrat().getTime());
            pstatement.setDate(9, sqlDate);
            pstatement.setString(10, newClient.getAdresseMail());
            //  Execution du PreparedStatement pour insertion
            pstatement.executeUpdate();
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }
    
    /**
     * Methode pour mettre a jour un Client
     * @param upClient
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void update(Client upClient) throws SQLException, ClassNotFoundException{
        String query = "UPDATE client "
                + "SET RSCLIENT = ? "
                + ",TYPECLIENT = ? "
                + ",DOMAINECLIENT = ? "
                + ",ADRCLIENT = ? "
                + ",TELCLIENT = ? "
                + ",CACLIENT = ? "
                + ",COMMENTAIRESCLIENT = ? "
                + ",NBREMPCLIENT = ? "
                + ",DATECONTRAT = ? "
                + ",ADRESSEMAIL = ? "
                + "WHERE IDCLIENT = ? ";
        try(Connection myClientConn = Client_Db_Connect.getConnection();
                PreparedStatement pstatement = myClientConn.prepareStatement(query)){
            //  Recuperation des parametres pour le PreparedStatement
            pstatement.setString(1, upClient.getRaison());
            pstatement.setString(2, upClient.getTypeSo());
            pstatement.setString(3, upClient.getDomaine());
            pstatement.setString(4, upClient.getAdresse());
            pstatement.setString(5, upClient.getTel());
            pstatement.setInt(6, upClient.getCa());
            pstatement.setString(7, upClient.getComment());
            pstatement.setInt(8, upClient.getNbrEmp());
            java.sql.Date sqlDate = new java.sql.Date(upClient.getDateContrat().getTime());
            pstatement.setDate(9, sqlDate);
            pstatement.setString(10, upClient.getAdresseMail());
            pstatement.setInt(11, upClient.getIdent());
            //  Execution du PreparedStatement pour insertion
            pstatement.executeUpdate();
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }
    
    /**
     * Methode pour supprimer un Client
     * @param delClient
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void delete(Client delClient) throws SQLException, ClassNotFoundException{
        String query = "DELETE FROM client "
                + "WHERE IDCLIENT = ? ";
        try(Connection myClientConn = Client_Db_Connect.getConnection();
                PreparedStatement pstatement = myClientConn.prepareStatement(query)){
            //  Recuperation du parametre pour le PreparedStatement
            pstatement.setInt(1, delClient.getIdent());
            //  Execution du PreparedStatement pour insertion
            pstatement.executeUpdate();
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }
    
    /**
     * Methode pour sauvegarder les 4 Clients avec le meilleurs chiffre d affaire
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException 
     */
    public static void saveMeilleursClient() throws SQLException, ClassNotFoundException, IOException{
        //  Requete sql pour selectionner les clients
        String SQLquery = "SELECT rsclient, caclient, datecontrat, adressemail FROM client "
                + "ORDER BY caclient DESC LIMIT 4";
        //  Nom du fichier de sauvegarde
        String nomFichier = "Meilleurs_Client.csv";         
        // creation d une nouvelle connection 
        try (Connection myClientConn = Client_Db_Connect.getConnection();
             Statement statement = myClientConn.createStatement();
             ResultSet rSet = statement.executeQuery(SQLquery)) {
            //  Creation du fichier de sauvegarde si il n existe pas
            File fClient = new File(nomFichier);
            if(!fClient.exists()){fClient.createNewFile();}
            
            try (FileWriter fw = new FileWriter(fClient); 
                    BufferedWriter bw = new BufferedWriter(fw)) {
                
                showMessageDialog(null, "Création du fichier " + fClient);
                //  Recuperation des entetes de colonnes rsclient, caclient, 
                //  datecontrat, adressemail grace au MetaData et ecriture
                ResultSetMetaData  rSetMeta = rSet.getMetaData();
                bw.write(rSetMeta.getColumnName(1));
                bw.write(',');
                bw.write(rSetMeta.getColumnName(2));
                bw.write(',');
                bw.write(rSetMeta.getColumnName(3));
                bw.write(',');
                bw.write(rSetMeta.getColumnName(4));
                bw.write('\n');
                //  Parcour du ResultSet pour ecrire les resultats dans le fichier
                while (rSet.next()){
                    bw.write(rSet.getString(1));
                    bw.write(',');
                    bw.write(rSet.getString(2));
                    bw.write(',');
                    bw.write(rSet.getString(3));
                    bw.write(',');
                    bw.write(rSet.getString(4));
                    bw.write('\n');
                }
                fw.flush();
            }
            showMessageDialog(null, "Création du fichier CSV réussi !");
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }
}
