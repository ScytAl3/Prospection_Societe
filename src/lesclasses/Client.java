/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesclasses;

import java.util.Date;
import class_control.Controle_Regex;
import database_Interact.Client_Db_Connect;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Classe client qui herite de la classe societe avec 2 attributs en plus
 * @author Stagiaire
 */
public class Client extends Societe {  
        
    //**************************************************************
    //  Declaration des variables supplementaires de classe Client
    //**************************************************************
    
    private Date    dateContrat;
    private String  adresseMail;
    
    //*********************************
    //  Declaration des constructeurs 
    //*********************************
    
    /**
     *  Constructeur par defaut
     */
    public Client(){
        super();
    }
    
    /**
     * Constructeur pour la creation avec un identifiant auto-incremente
     * @param rs
     * @param type
     * @param domaine
     * @param adresse
     * @param tel
     * @param ca
     * @param comm
     * @param nbr
     * @param dContrat 
     * @param mail 
     * @throws java.lang.Exception 
     */
    public Client(String rs, String type, String domaine, String adresse, String tel,
            int ca, String comm, int nbr, Date dContrat, String mail)throws Exception {
        super(rs, type, domaine, adresse, tel, ca,comm, nbr);
        this.dateContrat = dContrat;
        this.adresseMail = mail;
    }
    
    /**
     * Constructeur pour la mise a jour
     * @param id
     * @param rs
     * @param type
     * @param domaine
     * @param adresse
     * @param tel
     * @param ca
     * @param comm
     * @param nbr
     * @param dContrat
     * @param mail 
     * @throws java.lang.Exception 
     */
    public Client(int id, String rs, String type, String domaine, String adresse, String tel,
            int ca, String comm, int nbr, Date dContrat, String mail)throws Exception {
        super(id, rs, type, domaine, adresse, tel, ca,comm, nbr);
        this.dateContrat = dContrat;
        this.adresseMail = mail;
    }
    
    //*********************************
    //  Accesseurs et Mutateurs 
    //*********************************
    
    //**********Accesseurs*************
	
  /**
   * Retourne la date de debut de contrat
   * @return
   */
    public Date getDateContrat(){
        return dateContrat;
    }

  /**
   * Retourne la duree du contrat
   * @return
   */
    public String getAdresseMail(){
        return adresseMail;
    }
    
    //**********Mutateurs**************

  /**
   * Definit la date de debut de contrat
   * @param sDate
   */
    public void setDateContrat(Date sDate){
        this.dateContrat = sDate;
    }

  /**
   * Definit la duree du contrat
   * @param sMail
   */
    public void setAdresseMail(String sMail){
        this.adresseMail = sMail;
    }
    
    //**********************************************************
    //  Definition des methodes d action sur la base de donnees  
    //**********************************************************

    
    /**
     * Methode pour la creation d un Client passe a la class Client_Db_Connect
     * le Client cree
     */
    @Override
    public void Creation() {
        try{
            //  On instancie la Class Controle_Regex
            Controle_Regex replace = new Controle_Regex();
            //  On remplace dans les Getters les "'" et on passe les nouvelles valeurs
            //  aux Setters
            this.setRaison(replace.RegexToSQL(this.getRaison()));
            this.setDomaine(replace.RegexToSQL(this.getDomaine()));
            this.setAdress(replace.RegexToSQL(this.getAdresse()));
            this.setTel(replace.RegexToSQL(this.getTel()));
            this.setComment(replace.RegexToSQL(this.getComment()));
            this.setAdresseMail(replace.RegexToSQL(this.getAdresseMail()));                       
         }catch (Exception ex){
            showMessageDialog(null, "Probleme lors de la mise à jour du Client ! " 
                    + ex.getMessage());
        }        
        //  On instance une connection Client a la bdd
        Client_Db_Connect bdConnect = new Client_Db_Connect();
        try {
            bdConnect.InsertInto(this);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    
    /**
     * Methode pour la mise a jour d un Client passe a la class Client_Db_Connect
     * le Client modifie
     */
    @Override
    public void MiseAJour() {
        try{
            //  On instancie la Class Controle_Regex
            Controle_Regex replace = new Controle_Regex();
            //  On remplace dans les Getters les "'" et on passe les nouvelles valeurs
            //  aux Setters
            this.setRaison(replace.RegexToSQL(this.getRaison()));
            this.setDomaine(replace.RegexToSQL(this.getDomaine()));
            this.setAdress(replace.RegexToSQL(this.getAdresse()));
            this.setTel(replace.RegexToSQL(this.getTel()));
            this.setComment(replace.RegexToSQL(this.getComment()));
            this.setAdresseMail(replace.RegexToSQL(this.getAdresseMail()));                       
         }catch (Exception ex){
            showMessageDialog(null, "Probleme lors de la création du Client ! " + ex.getMessage());
        }        
        //  On instance une connection Client a la bdd
        Client_Db_Connect bdConnect = new Client_Db_Connect();
        try {
            bdConnect.update(this); 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    
    /**
     * Methode pour supprimer le Client selectionne
     */
    @Override
    public void Suppression() {
        //  On instance une connection Client a la bdd
        Client_Db_Connect bdConnect = new Client_Db_Connect();
        try {
            bdConnect.delete(this);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
