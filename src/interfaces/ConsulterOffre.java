package interfaces;

import bdd.Connecter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsulterOffre extends JFrame {

    private JPanel JPANEL1;
    private JLabel labelTitre;
    private JTable tableOffres;
    private JScrollPane scrollPane;
    private JButton boutonAnnuler;

    private Connection con;
    private PreparedStatement PS;
    private ResultSet RS;

    public ConsulterOffre() {
        con = Connecter.connexion();
        initComponents();

        this.setTitle("Liste des offres de stage");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1);

        chargerDonnees();
    }

    private void initComponents() {
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(230, 230, 250)); // Lavande

        labelTitre = new JLabel("Toutes les offres de stage");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(250, 20, 350, 30);
        JPANEL1.add(labelTitre);

        // Configuration de la JTable
        tableOffres = new JTable();
        scrollPane = new JScrollPane(tableOffres);
        scrollPane.setBounds(30, 80, 720, 300);
        JPANEL1.add(scrollPane);

        boutonAnnuler = new JButton("Retour");
        boutonAnnuler.setBounds(340, 400, 120, 40);
        JPANEL1.add(boutonAnnuler);

        boutonAnnuler.addActionListener(e -> {
            new GestionDesOffres().setVisible(true);
            this.dispose();
        });
    }

    private void chargerDonnees() {
        // Modèle de tableau avec les colonnes correspondant à la table SQL 'offre'
        DefaultTableModel modele = new DefaultTableModel();
        modele.addColumn("ID");
        modele.addColumn("Libellé");
        modele.addColumn("Domaine");
        modele.addColumn("Entreprise");
        modele.addColumn("Ville");
        modele.addColumn("Durée");
        modele.addColumn("Statut");

        try {
            String sql = "SELECT id_offre, libelle, domaine, nom_entreprise, ville_entreprise, duree, statut FROM offre";
            PS = con.prepareStatement(sql);
            RS = PS.executeQuery();

            while (RS.next()) {
                modele.addRow(new Object[]{
                        RS.getInt("id_offre"),
                        RS.getString("libelle"),
                        RS.getString("domaine"),
                        RS.getString("nom_entreprise"),
                        RS.getString("ville_entreprise"),
                        RS.getString("duree"),
                        RS.getInt("statut") == 1 ? "Valide" : "Invalide"
                });
            }
            tableOffres.setModel(modele);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur chargement : " + e.getMessage());
        }
    }
}