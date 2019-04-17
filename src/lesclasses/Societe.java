/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesclasses;

/**
 * Classe abstraite Societe
 * @author Stagiaire
 */
public abstract class Societe {

    //***********************************************************
    //  Declaration des variables de la classe abstraite Societe
    //***********************************************************
    private int idSociete;
    private String rsSociete;
    private String typeSociete;
    private String domaineSociete;
    private String adrSociete;
    private String telSociete;
    private int caSociete;
    private String commSociete;
    private int nbrEmp;

    //*********************************
    //  Declaration des constructeurs 
    //*********************************
    
    /**
     * Constructeur par defaut
     */
    public Societe(){
        
    }
    
    /**
     * Constructeur pour la creation avec un identifiant auto-incremente
     *
     * @param rs
     * @param type
     * @param domaine
     * @param adresse
     * @param tel
     * @param ca
     * @param comm
     * @param nbr
     * @throws java.lang.Exception
     */
    public Societe(String rs, String type, String domaine, String adresse, String tel,
            int ca, String comm, int nbr) throws Exception {
        this.rsSociete = rs;
        this.typeSociete = type;
        this.domaineSociete = domaine;
        this.adrSociete = adresse;
        this.telSociete = tel;
        this.caSociete = ca;
        this.commSociete = comm;
        this.nbrEmp = nbr;
    }

    /**
     * Constructeur pour la mise a jour
     *
     * @param id
     * @param rs
     * @param type
     * @param domaine
     * @param adresse
     * @param tel
     * @param ca
     * @param comm
     * @param nbr
     * @throws java.lang.Exception
     */
    public Societe(int id, String rs, String type, String domaine, String adresse, String tel,
            int ca, String comm, int nbr) throws Exception {
        this.idSociete = id;
        this.rsSociete = rs;
        this.typeSociete = type;
        this.domaineSociete = domaine;
        this.adrSociete = adresse;
        this.telSociete = tel;
        this.caSociete = ca;
        this.commSociete = comm;
        this.nbrEmp = nbr;
    }

    //*********************************
    //  Accesseurs et Mutateurs 
    //*********************************
    
    //**********Accesseurs*************
    
  /**
   * Retourne le numero d identification
   * @return
   */
    public int getIdent() {
        return idSociete;
    }

  /**
   * Retourne la raison sociale
   * @return
   */
    public String getRaison() {
        return rsSociete;
    }

  /**
   * Retourne le type de societe
   * @return
   */
    public String getTypeSo() {
        return typeSociete;
    }
	
  /**
   * Retourne le domaine de la societe
   * @return
   */
    public String getDomaine() {
        return domaineSociete;
    }

  /**
   * Retourne l adresse de la societe
   * @return
   */
    public String getAdresse() {
        return adrSociete;
    }

  /**
   * Retourne le numero de telephone de la societe
   * @return
   */
    public String getTel() {
        return telSociete;
    }

  /**
   * Retourne le chiffre d affaire de la societe
   * @return
   */
    public int getCa() {
        return caSociete;
    }

  /**
   * Retourne le commentaire sur la societe
   * @return
   */
    public String getComment() {
        return commSociete;
    }

  /**
   * Retourn le nombre d employe(s) de la societe
   * @return
   */
    public int getNbrEmp() {
        return nbrEmp;
    }

    //**********Mutateurs**************

  /**
   * Definit le numero identifiant
   * @param sId
   */
    public void setIdent(int sId) {
        this.idSociete = sId;
    } 

  /**
   * Definit la raison sociale
   * @param sRs
   */
    public void setRaison(String sRs) {
        this.rsSociete = sRs;
    }

  /**
   * Definit le type de societe
   * @param sType
   */
    public void setTypeSo(String sType) {
        this.typeSociete = sType;
    }

  /**
   * Definit le domaine de la societe
   * @param sDomaine
   */
    public void setDomaine(String sDomaine) {
        this.domaineSociete = sDomaine;
    }

  /**
   * Definit l adresse de la societe
   * @param sAdresse
   */
    public void setAdress(String sAdresse) {
        this.adrSociete = sAdresse;
    }

  /**
   * Definit le numero de telephone de la societe
   * @param sTel
   */
    public void setTel(String sTel) {
        this.telSociete = sTel;
    }

  /**
   * Definit le chiffre d affaire de la societe
   * @param sCa
   */
    public void setCa(int sCa) {
        this.caSociete = sCa;
    }

  /**
   * Definit le commentaire sur la societe
   * @param sComm
   */
    public void setComment(String sComm) {
        this.commSociete = sComm;
    }

  /**
   * Definit le nombre d employe(s)
   * @param sNbr
   */
  public void setNbrEmp(int sNbr) {
        this.nbrEmp = sNbr;
    }

    //************************
    //  Methodes abstraites 
    //************************
        
    abstract void Creation();

    abstract void MiseAJour();

    abstract void Suppression();
}
