/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesclasses;

import java.util.Date;
import class_control.Controle_Regex;
import database_Interact.Prospect_Db_Connect;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Classe prospect qui herite de la classe societe avec 2 attributs en plus
 * @author Stagiaire
 */
public class Prospect extends Societe {
    
    //***************************************************************
    //  Declaration des variables supplementaires de classe Prospect
    //***************************************************************
    
    private Date    dateProspect;
    private boolean interetPropspect;

  /**
   * Variable de classe pour stocker le dernier numero identifiant cree
   */
    public static int dernierIdentifiant;

  /**
   * Et la derniere annee
   */
    public static int derniereAnee;
    
    //*********************************
    //  Declaration des constructeurs 
    //*********************************
    
    /**
     * Constructeur par defaut
     */
    public Prospect(){
        super();
    }
    
    /**
     * Constructeur pour la creation et la modification d un prospect
     * @param id
     * @param rs
     * @param type
     * @param domaine
     * @param adresse
     * @param tel
     * @param ca
     * @param comm
     * @param nbr
     * @param dProsp
     * @param bInteret
     * @throws Exception 
     */
    public Prospect(int id, String rs, String type, String domaine, String adresse, String tel,
            int ca, String comm, int nbr, Date dProsp, boolean bInteret)throws Exception {
        super(id, rs, type, domaine, adresse, tel, ca,comm, nbr);
        this.dateProspect = dProsp;
        this.interetPropspect = bInteret;
    }
    
    //*********************************
    //  Accesseurs et Mutateurs 
    //*********************************
    
    //**********Accesseurs*************

  /**
   * Retourne la date de prospection
   * @return
   */
    public Date getDateProspetc(){
        return dateProspect;
    }

  /**
   * Retourne l interet du prospect
   * @return
   */
    public boolean getInteretPropspect(){
        return interetPropspect;
    }
    
    //**********Mutateurs**************

  /**
   * Definit la date de prospection
   * @param sDate
   */
    public void setDateProspect(Date sDate){
        this.dateProspect = sDate;
    }

  /**
   * Definit l interet du prospect
   * @param sInteret
   */
    public void setInteretPropspect(boolean sInteret){
        this.interetPropspect = sInteret;
    }    
    
    //**************************************************************************//
    //          Traitements pour la creation du numero d identification
    //**************************************************************************//
    
    /**
     * Methode de recuperation de la partie droite du numero d identification
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void dernierIdCreer() throws SQLException, ClassNotFoundException{
        //  On recupere l anne en cours
        Calendar now = Calendar.getInstance();
        int an  = now.get(Calendar.YEAR);
        //  On initialise le compteur du numero d identification
        int numId = 0;
        //  On recupere le numero du dernier Prospect cree pour l annee en cours 
        try{
            numId = Prospect_Db_Connect.exister(an);
        }catch(ClassNotFoundException | SQLException ex)
        {
            showMessageDialog(null, "Probleme lors de la récupération du dernier"
                    + " identifiant Prospect ! " + ex.getMessage());
        }  
        dernierIdentifiant = numId;
        derniereAnee = an;
    }
    
    /**
     * Methode pour creer le numero d identification lors de la saisie d un 
     * nouveau Prospect
     * @return 
     */
    public static int crea_NumIdProspect(){
        //  On recupere la valeur du compteur de class
        int leDernierId = dernierIdentifiant;
        //  On recupere l anne en cours
        Calendar now = Calendar.getInstance();
        int anEnCours  = now.get(Calendar.YEAR);
        int numIncre;
        int leNewId;
        String idPros;
        //  Si le compteur = 0 donc pas de ligne pour l annee en cours
        if (leDernierId == 0){
                //  On initialise le compteur du numero d identification 
                numIncre = 1;
        }
        //  Sinon il existe des prospects pour l annee en cours
        else
        {     
            //  Si changement d annee en cours
            if (derniereAnee < anEnCours)
            {
                numIncre = 1;
            }
            else
            {
                numIncre = leDernierId+1;
            }             
        }
        //  On concatene l annee en cours et le chiffre sous la forme "01"
        idPros =  String.valueOf(anEnCours) + String.format("%02d", numIncre);
        //  Et on recupere la valeur soius la forme d un entier
        leNewId = Integer.parseInt(idPros);
        //  On incremete le compteur de Class
        dernierIdentifiant = numIncre;
        return leNewId;
    }
    
    //**************************************************************************//
    //          Definition des methodes d action sur la base de donnees  
    //**************************************************************************//
    
    /**
     * Methode pour la creation d un Prospect passe a la class Prospect_Db_Connect
     * le Prospect cree
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
            this.setComment(replace.RegexToSQL(this.getComment()));                      
         }catch (Exception ex){
            showMessageDialog(null, "Probleme lors de la création du Prospect ! " 
                    + ex.getMessage());
        }        
        //  On instance une connection Client a la bdd
        Prospect_Db_Connect bdConnect = new Prospect_Db_Connect();
        try {
            bdConnect.InsertInto(this);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    /**
     * Methode pour la mise a jour d un Prospect passe a la class Prospect_Db_Connect
     * le Prospect modifie
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
            this.setComment(replace.RegexToSQL(this.getComment()));                     
         }catch (Exception ex){
            showMessageDialog(null, "Probleme lors de la mise à jour du Prospect ! "
                    + ex.getMessage());
        }        
        //  On instance une connection Client a la bdd
        Prospect_Db_Connect bdConnect = new Prospect_Db_Connect();
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
        Prospect_Db_Connect bdConnect = new Prospect_Db_Connect();
        try {
            bdConnect.delete(this);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
