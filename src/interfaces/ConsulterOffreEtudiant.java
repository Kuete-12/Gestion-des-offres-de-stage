package interfaces;

import bdd.Connecter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsulterOffreEtudiant extends JFrame {

    private JPanel JPANEL1;
    private JLabel labelTitre;
    private JTable tableOffres;
    private JScrollPane scrollPane;
    private JButton boutonPostuler, boutonAnnuler;

    private Connection con;
    private PreparedStatement PS;
    private ResultSet RS;

    public ConsulterOffreEtudiant() {
        con = Connecter.connexion();
        initComponents();

        this.setTitle("Offres de stage disponibles");
        this.setSize(800, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1);

        chargerDonnees();
    }

    private void initComponents() {
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(255, 248, 220));

        labelTitre = new JLabel("Consulter et postuler aux offres");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(230, 20, 400, 30);
        JPANEL1.add(labelTitre);

        tableOffres = new JTable();
        scrollPane = new JScrollPane(tableOffres);
        scrollPane.setBounds(30, 70, 720, 300);
        JPANEL1.add(scrollPane);

        boutonPostuler = new JButton("Postuler à l'offre");
        boutonPostuler.setBounds(200, 410, 180, 40);
        boutonPostuler.setBackground(new Color(144, 238, 144));
        JPANEL1.add(boutonPostuler);

        boutonAnnuler = new JButton("Déconnexion");
        boutonAnnuler.setBounds(420, 410, 180, 40);
        JPANEL1.add(boutonAnnuler);

        boutonPostuler.addActionListener(e -> postuler());

        boutonAnnuler.addActionListener(e -> {
            new Accueil().setVisible(true);
            this.dispose();
        });
    }

    private void chargerDonnees() {
        DefaultTableModel modele = new DefaultTableModel();
        modele.addColumn("ID");
        modele.addColumn("Libellé");
        modele.addColumn("Domaine");
        modele.addColumn("Entreprise");
        modele.addColumn("Ville");
        modele.addColumn("Durée");

        try {
            String sql = "SELECT id_offre, libelle, domaine, nom_entreprise, ville_entreprise, duree FROM offre WHERE statut = 1";
            PS = con.prepareStatement(sql);
            RS = PS.executeQuery();

            while (RS.next()) {
                modele.addRow(new Object[]{
                        RS.getInt("id_offre"),
                        RS.getString("libelle"),
                        RS.getString("domaine"),
                        RS.getString("nom_entreprise"),
                        RS.getString("ville_entreprise"),
                        RS.getString("duree")
                });
            }
            tableOffres.setModel(modele);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur chargement : " + e.getMessage());
        }
    }

    private void postuler() {
        int ligne = tableOffres.getSelectedRow();
        if (ligne != -1) {
            int idOffre = (int) tableOffres.getValueAt(ligne, 0);
            String nomEnt = tableOffres.getValueAt(ligne, 3).toString();

            // Dans ce projet, on simule l'ID de l'étudiant connecté
            int idEtudiant = 1;

            try {
                String sql = "INSERT INTO candidature (id_offre, id_etudiant, nom_entreprise) VALUES (?, ?, ?)";
                PS = con.prepareStatement(sql);
                PS.setInt(1, idOffre);
                PS.setInt(2, idEtudiant);
                PS.setString(3, nomEnt);

                PS.executeUpdate();
                JOptionPane.showMessageDialog(null, "Votre candidature a été envoyée avec succès !");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la candidature : " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une offre dans le tableau.");
        }
    }
}