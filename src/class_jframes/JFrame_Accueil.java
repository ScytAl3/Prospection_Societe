/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package class_jframes;

import lesclasses.Client;
import lesclasses.Prospect;
import database_Interact.Client_Db_Connect;
import database_Interact.Prospect_Db_Connect;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



/**
 *  JFrame d accueil pour l application
 * @author Stagiaire
 */
public class JFrame_Accueil extends javax.swing.JFrame {

    //**************************************************************************//
    //         Declaration des attributs de la class JFrame_Accueil
    //**************************************************************************//
    
    //  Declaration des listes Clients et Prospects
    private List<Client> laListeClient;
    private List<Prospect> laListeProspeect;
    //  Tableau de String du nom des entetes des colonnes Client et Prospects
    private String[] colonnesClient = null;
    private String[] colonnesProspect = null;
    //  Declaration des Tableaux Clients et Prospects
    DefaultTableModel modelClient;
    DefaultTableModel modelProspect;
    //  Variable qui stocke la valeur numerique de l onglet
    int tab = 0;
    //  Compteur d instance
    private static int comptInstance = 0;
    
    //**************************************************************************//
    //         Declaration des constructeurs de la class Formulaire_Client
    //**************************************************************************//
    
    /**
     * Creates new form Accueil
     * @param numTab
     * @throws java.lang.Exception
     */
    @SuppressWarnings("empty-statement")
    public JFrame_Accueil(int numTab) throws Exception {
        initComponents();
        //  On centre la fenetre
        this.setLocationRelativeTo(null);
        //  Lors du retour sur la page d accueil l onglet correspondant au formulaire
        //  qui vient d etre ferme sera visible
        this.tab = numTab;
        jTabPane_Accueil.setSelectedIndex(tab);
        //  Initialisation des tables Client et Prospect
        tableClient();
        tableProspect();
        //  Au lancement de l application le compteur d instance = 0 
        if(JFrame_Accueil.comptInstance == 0){
            //  Donc initialisation du compteur de numero d identification pour la 
            //  creation d un prospect
            Prospect.dernierIdCreer();
        }
        //  On increment le compteur a chaque instanciation
        JFrame_Accueil.comptInstance++;
        //  Appelle de la methode pour cacher les boutons si table vide
        hideButton();
        //  Methode pour rendre inaccessible des options du menu si certaines conditions
        //  ne sont pas remplies
        showMenuListClient();
        showMenuListProspect();
    }
    
    //**************************************************************************//
    //                   Creation des entetes des tableaux                      //
    //**************************************************************************//
    
    /**
     * Methode qui retourne le nom des colonnes sous forme de tableau de String
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[] headerClient() throws SQLException, ClassNotFoundException{
        colonnesClient = (String[]) Client_Db_Connect.enteteTableau();
        return colonnesClient;
    }    
    
    /**
     * Methode qui retourne le nom des colonnes sous forme de tableau de String
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[] headerProspect() throws SQLException, ClassNotFoundException{
        colonnesProspect = (String[]) Prospect_Db_Connect.enteteTableau();
        return colonnesProspect;
    }
    
    //**************************************************************************//
    //              Recuperation des listes Clients/Prospects                   //
    //**************************************************************************//
    
    /**
     * Recuperation de tous les clients dans une List
     * @throws Exception 
     */
    public void AfficherListClient() throws Exception{
        laListeClient = Client_Db_Connect.tousLesClients();
        laListeClient.forEach((c) -> {
            modelClient.addRow(new Object[]{  c.getIdent(),
                                                c.getRaison(),
                                                c.getTypeSo(),
                                                c.getDomaine(),
                                                c.getAdresse(),
                                                c.getTel(),
                                                c.getCa(),
                                                c.getComment(),
                                                c.getNbrEmp(),
                                                c.getDateContrat(),
                                                c.getAdresseMail()});
        });
    } 
    
    /**
     * Recuperation de tous les clients dans une List
     * @throws Exception 
     */
    public void AfficherListProspect() throws Exception{
        laListeProspeect = Prospect_Db_Connect.tousLesProspects();
        laListeProspeect.forEach((p) -> {
            modelProspect.addRow(new Object[]{  p.getIdent(),
                                                p.getRaison(),
                                                p.getTypeSo(),
                                                p.getDomaine(),
                                                p.getAdresse(),
                                                p.getTel(),
                                                p.getCa(),
                                                p.getComment(),
                                                p.getNbrEmp(),
                                                p.getDateProspetc(),
                                                p.getInteretPropspect()});
        });
    }
    
    //**************************************************************************//
    //              Creation des Tableaux Clients/Prospects                     //
    //**************************************************************************//
    
    /**
     * Creation de la table Client avec le constructeur DefaultTableModel() qui
     * prend en parametres les entetes colonnesClient et la liste de Clients
     * @throws Exception 
     */
    private void tableClient() throws Exception{
        modelClient = new DefaultTableModel(new Object[][]{}, headerClient());
        jTable_Clients.setModel(modelClient);
        TableColumnModel columnModel = jTable_Clients.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(9).setPreferredWidth(100);
        columnModel.getColumn(10).setPreferredWidth(150);
        AfficherListClient();
    }
    
    /**
     * Creation de la table Prospect avec le constructeur DefaultTableModel() qui
     * prend en parametres les entetes colonnesProspect et la liste de Prospects
     * @throws Exception 
     */
    private void tableProspect() throws Exception{
        modelProspect = new DefaultTableModel(new Object[][]{}, headerProspect());
        jTable_Prospects.setModel(modelProspect);
        TableColumnModel columnModel = jTable_Prospects.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(80);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(9).setPreferredWidth(100);
        columnModel.getColumn(10).setPreferredWidth(30);
        AfficherListProspect();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabPane_Accueil = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Clients = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Prospects = new javax.swing.JTable();
        jLbl_Accueil_Titre = new javax.swing.JLabel();
        jBtn_Ajouter = new javax.swing.JButton();
        jBtn_Modifier = new javax.swing.JButton();
        jBtn_Supprimer = new javax.swing.JButton();
        jBtn_Quitter = new javax.swing.JButton();
        jBtn_ProspectToClient = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuItem_SaveClient = new javax.swing.JMenuItem();
        jMenuItem_ShowClient = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_SavePropsect = new javax.swing.JMenuItem();
        jMenuItem_ShowProspect = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fenêtre d'accueil");

        jTabPane_Accueil.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabPane_Accueil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabPane_AccueilMouseClicked(evt);
            }
        });

        jTable_Clients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_Clients.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable_Clients);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabPane_Accueil.addTab("Liste des CLIENTS", jPanel1);

        jTable_Prospects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_Prospects.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTable_Prospects);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabPane_Accueil.addTab("Liste des PROSPECTS", jPanel2);

        jLbl_Accueil_Titre.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLbl_Accueil_Titre.setText("Choix du type de gestion");

        jBtn_Ajouter.setText("AJOUTER");
        jBtn_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_AjouterActionPerformed(evt);
            }
        });

        jBtn_Modifier.setText("MODIFIER");
        jBtn_Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_ModifierActionPerformed(evt);
            }
        });

        jBtn_Supprimer.setText("SUPPRIMER");
        jBtn_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_SupprimerActionPerformed(evt);
            }
        });

        jBtn_Quitter.setText("QUITTER");
        jBtn_Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_QuitterActionPerformed(evt);
            }
        });

        jBtn_ProspectToClient.setBackground(new java.awt.Color(204, 255, 204));
        jBtn_ProspectToClient.setText("Prospect to Client");
        jBtn_ProspectToClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtn_ProspectToClientActionPerformed(evt);
            }
        });

        jMenuFichier.setText("Fichier");

        jMenuItem_SaveClient.setText("Sauvergarde meilleurs Clients");
        jMenuItem_SaveClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SaveClientActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItem_SaveClient);

        jMenuItem_ShowClient.setText("Afficher meilleurs Clients");
        jMenuItem_ShowClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ShowClientActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItem_ShowClient);
        jMenuFichier.add(jSeparator1);

        jMenuItem_SavePropsect.setText("Sauvergarde relance Prospect ");
        jMenuItem_SavePropsect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SavePropsectActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItem_SavePropsect);

        jMenuItem_ShowProspect.setText("Afficher relance Prospects");
        jMenuItem_ShowProspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ShowProspectActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItem_ShowProspect);

        jMenuBar1.add(jMenuFichier);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabPane_Accueil))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLbl_Accueil_Titre)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtn_Quitter)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jBtn_Ajouter)
                .addGap(41, 41, 41)
                .addComponent(jBtn_Modifier)
                .addGap(79, 79, 79)
                .addComponent(jBtn_ProspectToClient, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtn_Supprimer)
                .addGap(242, 242, 242))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLbl_Accueil_Titre)
                .addGap(46, 46, 46)
                .addComponent(jTabPane_Accueil, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn_Ajouter)
                    .addComponent(jBtn_Modifier)
                    .addComponent(jBtn_Supprimer)
                    .addComponent(jBtn_ProspectToClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtn_Quitter)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Methode qui renvoie le numero de l onglet selectionne
     * @return 0=Client, 1=Prospect
     */
    private int numOnglet(){
        int selIndex = jTabPane_Accueil.getSelectedIndex();
        return selIndex;
    }    
    
    /**
     * Methode qui cache les boutons Modifier et Supprimer si la table est vide
     */
    private void hideButton(){
        //  Si onget Client
        if(numOnglet() == 0 ){
            jBtn_ProspectToClient.setVisible(false);
            if(jTable_Clients.getRowCount() == 0){
                jBtn_Modifier.setVisible(false);
                jBtn_Supprimer.setVisible(false);
            }
        }else{
            if(jTable_Prospects.getRowCount() == 0){
                jBtn_Modifier.setVisible(false);
                jBtn_Supprimer.setVisible(false);
                jBtn_ProspectToClient.setVisible(false);
            }
        }
    }
    
    //**************************************************************************//
    //              DEBUT traitements des methodes associees                    //
    //                          aux boutons                                     //
    //**************************************************************************//
   
    /**
     * Methode pour quitter l application
     * @param evt 
     */
    private void jBtn_QuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_QuitterActionPerformed
        ImageIcon img = new ImageIcon("images/hal-9000-space-odyssey.png");
        JOptionPane.showMessageDialog(null, "I'm sorry Dave, I'm afraid I can't "
                + "do that", "HAL 9000",
                JOptionPane.ERROR_MESSAGE, img);
        JOptionPane.showMessageDialog(null, "I know you and Frank were planning "
                + "to disconnect me. And that's something I cannot allow to happen", "HAL 9000",
                JOptionPane.ERROR_MESSAGE, img);
        JOptionPane.showMessageDialog(null, "Look Dave, I can see you're really "
                + "upset about this. I honestly think you ought to sit down calmly,"
                + " take a stress pill, and think things over", "HAL 9000",
                JOptionPane.ERROR_MESSAGE, img);
        /*Runtime runtime = Runtime.getRuntime();
        try {
            Process proc = runtime.exec("shutdown -s -t 0");
        } catch (IOException ex) {
            Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.exit(0);
    }//GEN-LAST:event_jBtn_QuitterActionPerformed
 
    /**
     * Methode associee au bouton Ajouter qui instancie suivant l onglet en cours
     * de selection un formulaire Client ou Prospect
     * @param evt 
     */
    private void jBtn_AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_AjouterActionPerformed
        //  Si onglet Client
        if(numOnglet() == 0){
            Formulaire_Client creaClient = new Formulaire_Client();
            creaClient.setVisible(true);
            this.setVisible(false);
            
        }
        else{
            Formulaire_Prospect creaProspect = new Formulaire_Prospect();
            creaProspect.setVisible(true);
            this.setVisible(false);
        }        
    }//GEN-LAST:event_jBtn_AjouterActionPerformed

    /**
     * Methode associee au bouton Modifier qui instancie suivant l onglet en cours
     * de selection et la ligne choisie un formulaire Client ou Prospect pour modification
     * @param evt 
     */
    private void jBtn_ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_ModifierActionPerformed
        //  Si onglet Client
        if(numOnglet() == 0){
            //  On stock l index quand une ligne de la table est selectionnnee
            int rowIndex = jTable_Clients.getSelectedRow();
            //  Si pas de ligne selectionnee
            if(rowIndex == -1){
                showMessageDialog(null, "Veuillez sélectionner la ligne du Client"
                        + " à modifier");
            }
            else{
                Formulaire_Client creaClient = new Formulaire_Client(laListeClient.get(rowIndex));
                creaClient.setVisible(true);
                this.setVisible(false);
            }
        }
        //  Onglet Propsect
        else{
             //  On stock l index quand une ligne de la table est selectionnnee
            int rowIndex = jTable_Prospects.getSelectedRow();
            //  Si pas de ligne selectionnee
            if(rowIndex == -1){
                showMessageDialog(null, "Veuillez sélectionner la ligne du Prospect"
                        + " à modifier");
            }
            else{
                Formulaire_Prospect creaProspect = new Formulaire_Prospect(laListeProspeect.get(rowIndex));
                creaProspect.setVisible(true);
                this.setVisible(false);
            }
        }         
    }//GEN-LAST:event_jBtn_ModifierActionPerformed

    /**
     * Methode associee au bouton Supprimer pour la suppression d un Client ou 
     * d un Prospect
     * @param evt 
     */
    private void jBtn_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_SupprimerActionPerformed
        // Si onglet Client
        if(numOnglet() == 0){
            //  On stock l index quand une ligne de la table est selectionnnee
            int rowIndex = jTable_Clients.getSelectedRow();
            //  Si pas de ligne selectionnee
            if(rowIndex == -1){
                showMessageDialog(null, "Veuillez sélectionner la ligne du Client "
                        + "à supprimer");
            }
            else{
                //  On appelle la methode d instance associee au Client selectionne
                laListeClient.get(rowIndex).Suppression();
                try {
                    tableClient();
                } catch (Exception ex) {
                    Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //  Onglet Prospect
        else{
            //  On stock l index quand une ligne de la table est selectionnnee
            int rowIndex = jTable_Prospects.getSelectedRow();
            //  Si pas de ligne selectionnee
            if(rowIndex == -1){
                showMessageDialog(null, "Veuillez sélectionner la ligne du Prospect "
                        + "à supprimer");
            }
            else{
                //  On appelle la methode d instance associee au Client selectionne
                laListeProspeect.get(rowIndex).Suppression();
                try {
                    tableProspect();
                } catch (Exception ex) {
                    Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        hideButton();
    }//GEN-LAST:event_jBtn_SupprimerActionPerformed

    //-------------DEBUT Traitements associes a la barre de menu----------------//
    
    /**
     * Methode associe au menu de sauvegarde d un fichier clients
     * @param evt 
     */
    private void jMenuItem_SaveClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SaveClientActionPerformed
        try {
            Client_Db_Connect.saveMeilleursClient();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        jMenuItem_ShowClient.setEnabled(true);
    }//GEN-LAST:event_jMenuItem_SaveClientActionPerformed

    /**
     * Methode associe au menu d affichage du fichier Clients, appelle l application
     * par defaut installee sur le systeme pour ouvrir le CSV
     * @param evt 
     */
    private void jMenuItem_ShowClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ShowClientActionPerformed
        File fClient = new File("Meilleurs_Client.csv");
        try {
            Desktop.getDesktop().open(fClient);
        } catch (IOException ex) {
            Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem_ShowClientActionPerformed

    /**
     * Methode associe au menu de sauvegarde d un fichier propspects
     * @param evt 
     */
    private void jMenuItem_SavePropsectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SavePropsectActionPerformed
        try {
            Prospect_Db_Connect.relanceProspects();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        jMenuItem_ShowProspect.setEnabled(true);
    }//GEN-LAST:event_jMenuItem_SavePropsectActionPerformed

    /**
     * Methode associe au menu d affichage du fichier Prospects, appelle l application
     * par defaut installee sur le systeme pour ouvrir le CSV
     * @param evt 
     */
    private void jMenuItem_ShowProspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ShowProspectActionPerformed
        File fProspect = new File("Propsect_Relance.csv");
        try {
            Desktop.getDesktop().open(fProspect);
        } catch (IOException ex) {
            Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem_ShowProspectActionPerformed

    /**
     * Methode qui rend inaccessible l affichage de la liste des clients s il n y 
     * a pas eu de sauvegarde prealable
     */
    private void showMenuListClient(){
        File fClient = new File("Meilleurs_Client.csv");
        if(!fClient.exists()){
            jMenuItem_ShowClient.setEnabled(false);
        }
    }
    
    /**
     * Methode qui rend inaccessible l affichage de la liste des prospects s il n y 
     * a pas eu de sauvegarde prealable
     */
    private void showMenuListProspect(){
        File fProspect = new File("Propsect_Relance.csv");
        if(!fProspect.exists()){
            jMenuItem_ShowProspect.setEnabled(false);
        }
    }
    
    //-------------FIN Traitements associes a la barre de menu------------------//
    
    /**
     * Methode qui cache les boutons en fonction de l onglet selectionne
     * @param evt 
     */
    private void jTabPane_AccueilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabPane_AccueilMouseClicked
        if(numOnglet() == 0 ){
            // On cache le bouton qui lance la procedure Prospect vers Client
            jBtn_ProspectToClient.setVisible(false);
            if(jTable_Clients.getRowCount() == 0){
                jBtn_Modifier.setVisible(false);
                jBtn_Supprimer.setVisible(false);
            }
            else{
                jBtn_Modifier.setVisible(true);
                jBtn_Supprimer.setVisible(true);
            }
        }else{
            if(jTable_Prospects.getRowCount() == 0){
            jBtn_Modifier.setVisible(false);
            jBtn_Supprimer.setVisible(false);
            jBtn_ProspectToClient.setVisible(false);
            }else{
            jBtn_Modifier.setVisible(true);
            jBtn_Supprimer.setVisible(true);
            // On fait apparaitre le bouton qui lance la procedure Prospect vers Client
            jBtn_ProspectToClient.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTabPane_AccueilMouseClicked

    /**
     * Methode pour appeler un procedure stockee qui copie un Prospect selectionne
     * dans la table Client
     * @param evt 
     */
    private void jBtn_ProspectToClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtn_ProspectToClientActionPerformed
        //  On stock l index quand une ligne de la table est selectionnnee
        int rowIndex = jTable_Prospects.getSelectedRow();
        //  Si pas de ligne selectionnee
        if(rowIndex == -1){
            showMessageDialog(null, "Veuillez sélectionner la ligne du Prospect "
                    + "à transférer");
        }
        else{
            if(laListeProspeect.get(rowIndex).getInteretPropspect()){
                try {
                    Prospect_Db_Connect.callProcedure(laListeProspeect.get(rowIndex).getIdent());
                    //showMessageDialog(null, laListeProspeect.get(rowIndex).getIdent());
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                showMessageDialog(null, "Ce prospect n'est pas intéressé !");
            }               
        }
        try {
            tableProspect();
            tableClient();
        } catch (Exception ex) {
            Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        showMessageDialog(null, "N'oubliez pas de modifier l'adresse mail par défaut !");
    }//GEN-LAST:event_jBtn_ProspectToClientActionPerformed

    //**************************************************************************//
    //              FIN traitements des methodes associees                      //
    //                          aux boutons                                     //
    //**************************************************************************//
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame_Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new JFrame_Accueil(0).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(JFrame_Accueil.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn_Ajouter;
    private javax.swing.JButton jBtn_Modifier;
    private javax.swing.JButton jBtn_ProspectToClient;
    private javax.swing.JButton jBtn_Quitter;
    private javax.swing.JButton jBtn_Supprimer;
    private javax.swing.JLabel jLbl_Accueil_Titre;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItem_SaveClient;
    private javax.swing.JMenuItem jMenuItem_SavePropsect;
    private javax.swing.JMenuItem jMenuItem_ShowClient;
    private javax.swing.JMenuItem jMenuItem_ShowProspect;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabPane_Accueil;
    private javax.swing.JTable jTable_Clients;
    private javax.swing.JTable jTable_Prospects;
    // End of variables declaration//GEN-END:variables
}
