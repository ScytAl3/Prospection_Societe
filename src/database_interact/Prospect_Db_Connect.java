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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import lesclasses.Prospect;

/**
 * Classe pour les operations a la base de donnees liees a la table Prospect, herite
 * de la classe MySQL_Db_Connect qui creee la connection
 * @author Stagiaire
 */
public class Prospect_Db_Connect extends MySQL_Db_Connect {
        
  /**
   * Constructeur par defaut
   */
  public Prospect_Db_Connect(){
        super();
    }
    
    /**
     * Methode pour recuperer les entetes de la table Prospect
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Object[] enteteTableau() throws SQLException, ClassNotFoundException{
        //  Creation de la requete
        String SQLquery = "SELECT * FROM prospect";
        // creation d une nouvelle connection 
        try (Connection myProspConn = Prospect_Db_Connect.getConnection();
             Statement statement = myProspConn.createStatement();
             ResultSet rSet = statement.executeQuery(SQLquery)) {                               
            
            ResultSetMetaData  rSetMeta = rSet.getMetaData();
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
     * Methode pour recuperer la liste des Prospects
     * @return
     * @throws Exception 
     */
    public static List<Prospect> tousLesProspects() throws Exception{
        //  Creation de la liste
        List<Prospect> mesProspects = new ArrayList<>();
        //  Creation de la requete
        String SQLquery = "SELECT * FROM prospect";
        try (Connection myProspectConn = Prospect_Db_Connect.getConnection();
             Statement statement = myProspectConn.createStatement();
             ResultSet rSet = statement.executeQuery(SQLquery)){            
            
            while(rSet.next()){
                Prospect leProspect = new Prospect();
                
                leProspect.setIdent(rSet.getInt("IDPROSPECT"));
                leProspect.setRaison(rSet.getString("RSPROSPECT"));
                leProspect.setTypeSo(rSet.getString("TYPEPROSPECT"));
                leProspect.setDomaine(rSet.getString("DOMAINEPROSPECT"));
                leProspect.setAdress(rSet.getString("ADRPROSPECT"));
                leProspect.setTel(rSet.getString("TELPROSPECT"));
                leProspect.setCa(rSet.getInt("CAPROSPECT"));
                leProspect.setComment(rSet.getString("COMMENTAIRESPROSPECT"));
                leProspect.setNbrEmp(rSet.getInt("NBREMPPROSPECT"));
                leProspect.setDateProspect(rSet.getDate("DATEPROSPECT"));
                leProspect.setInteretPropspect(rSet.getBoolean("INTERETPROSPECT"));
                
                mesProspects.add(leProspect);
            }
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
        return mesProspects;
    }
    
    /**
     * Methode pour recuperer la valeur max d indentification de l annee en cours
     * @param annee
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int exister(int annee) throws SQLException, ClassNotFoundException{
        //boolean bExiste = false;
        int maxId = 0;
        String query = "SELECT MAX(RIGHT(idprospect, 2)) FROM prospect "
                + " WHERE YEAR(dateprospect) = " + annee;
        try (Connection myProspectConn = Prospect_Db_Connect.getConnection();
             Statement statement = myProspectConn.createStatement();
             ResultSet rSet = statement.executeQuery(query)){
            
            if(rSet.next()){
                maxId = rSet.getInt("MAX(RIGHT(idprospect, 2))");
            }
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
        return maxId;
    }
    
    /**
     * Methode pour creer un nouveau Prospect
     * @param newProspect
     * @throws Exception 
     */
    public void InsertInto(Prospect newProspect) throws Exception{
        String query = "INSERT INTO Prospect ("
                + " IDPROSPECT,"
                + " RSPROSPECT,"
                + " TYPEPROSPECT,"
                + " DOMAINEPROSPECT,"
                + " ADRPROSPECT,"
                + " TELPROSPECT,"
                + " CAPROSPECT,"
                + " COMMENTAIRESPROSPECT,"
                + " NBREMPPROSPECT,"
                + " DATEPROSPECT,"
                + " INTERETPROSPECT) VALUES ("
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection myProspectConn = Prospect_Db_Connect.getConnection();
                PreparedStatement pstatement = myProspectConn.prepareStatement(query)){
            
            //  Recuperation des parametres pour le PreparedStatement
            pstatement.setInt(1, newProspect.getIdent());
            pstatement.setString(2, newProspect.getRaison());
            pstatement.setString(3, newProspect.getTypeSo());
            pstatement.setString(4, newProspect.getDomaine());
            pstatement.setString(5, newProspect.getAdresse());
            pstatement.setString(6, newProspect.getTel());
            pstatement.setInt(7, newProspect.getCa());
            pstatement.setString(8, newProspect.getComment());
            pstatement.setInt(9, newProspect.getNbrEmp());
            java.sql.Date sqlDate = new java.sql.Date(newProspect.getDateProspetc().getTime());
            pstatement.setDate(10, sqlDate);
            pstatement.setBoolean(11, newProspect.getInteretPropspect());
            //  Execution du PreparedStatement pour insertion
            pstatement.executeUpdate();
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }    
    
    /**
     * Methode pour mettre a jour un Client
     * @param upProspect
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void update(Prospect upProspect) throws SQLException, ClassNotFoundException{
        String query = "UPDATE prospect "
                + "SET RSPROSPECT = ? "
                + ",TYPEPROSPECT = ? "
                + ",DOMAINEPROSPECT = ? "
                + ",ADRPROSPECT = ? "
                + ",TELPROSPECT = ? "
                + ",CAPROSPECT = ? "
                + ",COMMENTAIRESPROSPECT = ? "
                + ",NBREMPPROSPECT = ? "
                + ",DATEPROSPECT = ? "
                + ",INTERETPROSPECT = ? "
                + "WHERE IDPROSPECT = ? ";
        try(Connection myProspectConn = Prospect_Db_Connect.getConnection();
                PreparedStatement pstatement = myProspectConn.prepareStatement(query)){
            //  Recuperation des parametres pour le PreparedStatement
            pstatement.setString(1, upProspect.getRaison());
            pstatement.setString(2, upProspect.getTypeSo());
            pstatement.setString(3, upProspect.getDomaine());
            pstatement.setString(4, upProspect.getAdresse());
            pstatement.setString(5, upProspect.getTel());
            pstatement.setInt(6, upProspect.getCa());
            pstatement.setString(7, upProspect.getComment());
            pstatement.setInt(8, upProspect.getNbrEmp());
            java.sql.Date sqlDate = new java.sql.Date(upProspect.getDateProspetc().getTime());
            pstatement.setDate(9, sqlDate);
            pstatement.setBoolean(10, upProspect.getInteretPropspect());
            pstatement.setInt(11, upProspect.getIdent());
            //  Execution du PreparedStatement pour insertion
            pstatement.executeUpdate();
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }    
    
    /**
     * Methode pour supprimer un Client
     * @param delProspect
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void delete(Prospect delProspect) throws SQLException, ClassNotFoundException{
        String query = "DELETE FROM prospect "
                + "WHERE IDPROSPECT = ? ";
        try(Connection myProspectConn = Prospect_Db_Connect.getConnection();
                PreparedStatement pstatement = myProspectConn.prepareStatement(query)){
            //  Recuperation du parametre pour le PreparedStatement
            pstatement.setInt(1, delProspect.getIdent());
            //  Execution du PreparedStatement pour insertion
            pstatement.executeUpdate();
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }
    
    /**
     * Methode pour sauvegarder les Prospect interesses de plus de 30 jours a relancer
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException 
     */
    public static void relanceProspects() throws SQLException, ClassNotFoundException, IOException{
        //  Requete sql pour selectionner les Prospects
        String SQLquery = "SELECT rsprospect, adrprospect, telprospect, dateprospect FROM prospect "
                + "WHERE (DATE_ADD(dateprospect, INTERVAL 30 DAY) < NOW()) "
                + "AND interetprospect = 1";
        //  Nom du fichier de sauvegarde
        String nomFichier = "Propsect_Relance.csv";         
        // creation d une nouvelle connection 
        try (Connection myProspectConn = Prospect_Db_Connect.getConnection();
             Statement statement = myProspectConn.createStatement();
             ResultSet rSet = statement.executeQuery(SQLquery)) {
            //  Creation du fichier de sauvegarde si il n existe pas
            File fProspect = new File(nomFichier);
            if(!fProspect.exists()){fProspect.createNewFile();}
            
            try (FileWriter fw = new FileWriter(fProspect); 
                    BufferedWriter bw = new BufferedWriter(fw)) {
                
                showMessageDialog(null, "Création du fichier " + fProspect);
                //  Recuperation des entetes de colonnes rsprospect, adrprospect, telprospect, dateprospect
                //  grace au MetaData et ecriture
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
    
    /**
     * * Methode pour appeler un procedure stockee qui copie un Prospect selectionne
     * dans la table Client
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void callProcedure(int id) throws SQLException, ClassNotFoundException{
        //  Requete sql pour appeller la procedure
        String SQLquery = "{CALL copy_prospect_to_client(?)}";
        ResultSet rSet;
        
        try (Connection myProspectConn = Prospect_Db_Connect.getConnection();
             CallableStatement  callstmt = myProspectConn.prepareCall(SQLquery)){
            
            callstmt.setInt(1, id);
            rSet = callstmt.executeQuery();
            
        }catch(SQLException SQLex){
            System.out.println(SQLex.getMessage());
        }
    }
}
