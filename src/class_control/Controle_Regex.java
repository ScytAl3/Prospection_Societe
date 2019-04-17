/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class_control;

import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Stagiaire
 */
public class Controle_Regex {
    
    //---------------------Les saisies alphanumeriques--------------------------
    
    /**
     * Methode qui controle la validation de la saisie grace a un RegEx
     * @param inputRaison
     * @return True si la saisie est valide
     */
    public boolean controlRaison(String inputRaison){
        boolean bRaison = true;
        Pattern pRaison = Pattern.compile("^[A-ZÉÈÀÙÂÊÎÔÛÄËÏÖÜÇ]{1}[-a-zéèàùâêîôûäëïöüç \\']{1,19}$");
        if(!pRaison.matcher(inputRaison).matches()){
            showMessageDialog(null, "Raison sociale :\n20 caractères commençant par une majuscule");
            bRaison = false;
        }
        return bRaison;
    }
    
    /**
     * Methode qui controle la validation de la saisie grace a un RegEx
     * @param inputDomaine
     * @return True si la saisie est valide
     */
    public boolean controlDomaine(String inputDomaine){
        boolean bDomaine = true;
        Pattern pDom = Pattern.compile("^[A-ZÉÈÀÙÂÊÎÔÛÄËÏÖÜÇ]{1}[-a-zéèàùâêîôûäëïöüç \\']{1,19}$");
        if(!pDom.matcher(inputDomaine).matches()){
            showMessageDialog(null, "Domaine :\n20 caractères commençant par une majuscule");
            bDomaine = false;
        }
        return bDomaine;
    }
    
    /**
     * Methode qui controle la validite de l adresse
     * @param inputAdresse
     * @return 
     */
    public boolean controlAdresse(String inputAdresse){
        boolean bAdresse = true;
        Pattern pAdress = Pattern.compile("^[[0-9]+[0-9A-Za-zéèàùâêîôûäëïöüç \\,\\'\\-]]+{1,100}$");
        if(!pAdress.matcher(inputAdresse).matches()){
            showMessageDialog(null, "Adresse : 100 caractères max");
            bAdresse = false;
        }
        return bAdresse;
    }
    
    /**
     * Methode qui controle la validite du numero de telephone
     * @param inputNumTel
     * @return 
     */
    public boolean controlNumTel(String inputNumTel){
        boolean bNumTel = true;
        Pattern pTel = Pattern.compile("^\\+33\\s?[1-9]\\d{8}$");
        if(!pTel.matcher(inputNumTel).matches()){
            showMessageDialog(null, "Le Numéro de téléphone n'est pas valide");
            bNumTel = false;
        }
        return bNumTel;
    }
    
    /**
     * Methode qui controle la validite de l adresse Email
     * @param inputMail
     * @return 
     */
    public boolean controlMail(String inputMail){
        boolean bMail = true;
        Pattern pMail = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        if(!pMail.matcher(inputMail).matches()){
            showMessageDialog(null, "L'adresse mail n'est pas valide");
            bMail = false;
        }
        return bMail;
    }
    
    /**
     * Methode qui controle la validite du commentaire
     * @param inputComm
     * @return 
     */
    public boolean controlCommentaire(String inputComm){
        boolean bComm = true;
        Pattern pComm = Pattern.compile("^[0-9a-zA-Zàâäéèêëïîôöùûüç \\-\\,\\']{1,100}$");
        if(!pComm.matcher(inputComm).matches()){
            showMessageDialog(null, "Le commentaire n'est pas valide");
            bComm = false;
        }
        return bComm;
    }
    
    //---------------------Les saisies numeriques-------------------------------
    
    /**
     * Methode qui controle la validite du chiffre d affaire
     * @param inputCa
     * @return 
     */
    public boolean controlChiffrAffaire(String inputCa){
        boolean bCa = true;
        Pattern pCa = Pattern.compile("^\\d{1,10}$");
        if(!pCa.matcher(inputCa).matches()){
            showMessageDialog(null, "Le chiffre d'affaire n'est pas valide");
            bCa = false;
        }
        return bCa;
    }
    
    /**
     * Methode qui controle la validite du nombre d employe(s)
     * @param inputNbEmp
     * @return 
     */
    public boolean controlNbEmploye(String inputNbEmp){
        boolean bNbEmp = true;
        Pattern pNbEmp = Pattern.compile("^\\d{1,3}$");
        if(!pNbEmp.matcher(inputNbEmp).matches()){
            showMessageDialog(null, "Le nombre d'employé(s) n'est pas valide");
            bNbEmp = true;
        }
        return bNbEmp;
    }
    
    //------------Remplacement avant insertion vers MySQL-----------------------

  /**
   *
   * @param toReplace
   * @return
   */
    
    public String RegexToSQL(String toReplace){
        String replaced = toReplace.replace("'", "\'");
        return replaced;
    }
}
