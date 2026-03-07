package interfaces;

import bdd.Connecter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModifierEntreprise extends JFrame {

    // 1. Déclaration des composants graphiques
    private JPanel JPANEL1;
    private JLabel labelTitre, labelChoix, labelRue, labelCodePostal, labelVille, labelEmail, labelTelephone, labelSecteur;
    private JTextField texteRue, texteCodePostal, texteVille, texteEmail, texteTelephone, texteSecteur;
    private JComboBox<String> comboEntreprise;
    private JButton boutonMiseAjour, boutonSupprimer, boutonAnnuler;

    // 2. Variables pour la base de données
    private Connection con;
    private PreparedStatement PS;
    private ResultSet RS;

    // 3. Constructeur
    public ModifierEntreprise() {
        con = Connecter.connexion();
        initComponents();

        // Paramètres de la fenêtre
        this.setTitle("Modifier une entreprise");
        this.setSize(700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1);

        // Chargement initial des entreprises dans la combo box
        chargerEntreprise();
    }

    private void initComponents() {
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(255, 255, 204)); // Jaune très clair

        // Titre
        labelTitre = new JLabel("Modification / Suppression d'entreprise");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        labelTitre.setBounds(150, 20, 450, 30);
        JPANEL1.add(labelTitre);

        // Liste déroulante des entreprises
        labelChoix = new JLabel("Choisir entreprise :");
        labelChoix.setBounds(50, 80, 150, 25);
        JPANEL1.add(labelChoix);

        comboEntreprise = new JComboBox<>();
        comboEntreprise.setBounds(200, 80, 250, 25);
        JPANEL1.add(comboEntreprise);

        // Champs de modification
        labelRue = new JLabel("Rue :");
        labelRue.setBounds(100, 140, 100, 25);
        JPANEL1.add(labelRue);
        texteRue = new JTextField();
        texteRue.setBounds(250, 140, 250, 25);
        JPANEL1.add(texteRue);

        labelCodePostal = new JLabel("Code Postal :");
        labelCodePostal.setBounds(100, 180, 100, 25);
        JPANEL1.add(labelCodePostal);
        texteCodePostal = new JTextField();
        texteCodePostal.setBounds(250, 180, 250, 25);
        JPANEL1.add(texteCodePostal);

        labelVille = new JLabel("Ville :");
        labelVille.setBounds(100, 220, 100, 25);
        JPANEL1.add(labelVille);
        texteVille = new JTextField();
        texteVille.setBounds(250, 220, 250, 25);
        JPANEL1.add(texteVille);

        labelEmail = new JLabel("Email :");
        labelEmail.setBounds(100, 260, 100, 25);
        JPANEL1.add(labelEmail);
        texteEmail = new JTextField();
        texteEmail.setBounds(250, 260, 250, 25);
        JPANEL1.add(texteEmail);

        labelTelephone = new JLabel("Téléphone :");
        labelTelephone.setBounds(100, 300, 100, 25);
        JPANEL1.add(labelTelephone);
        texteTelephone = new JTextField();
        texteTelephone.setBounds(250, 300, 250, 25);
        JPANEL1.add(texteTelephone);

        labelSecteur = new JLabel("Secteur :");
        labelSecteur.setBounds(100, 340, 100, 25);
        JPANEL1.add(labelSecteur);
        texteSecteur = new JTextField();
        texteSecteur.setBounds(250, 340, 250, 25);
        JPANEL1.add(texteSecteur);

        // Boutons d'action
        boutonMiseAjour = new JButton("Mettre à jour");
        boutonMiseAjour.setBounds(80, 420, 150, 40);
        JPANEL1.add(boutonMiseAjour);

        boutonSupprimer = new JButton("Supprimer");
        boutonSupprimer.setBounds(260, 420, 150, 40);
        boutonSupprimer.setBackground(new Color(255, 102, 102));
        JPANEL1.add(boutonSupprimer);

        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setBounds(440, 420, 150, 40);
        JPANEL1.add(boutonAnnuler);

        // --- Événements ---

        // Charger les infos quand on sélectionne une entreprise
        comboEntreprise.addActionListener(e -> chargerInfoEntreprise());

        boutonMiseAjour.addActionListener(e -> mettreAjourEntreprise());

        boutonSupprimer.addActionListener(e -> supprimerEntreprise());

        boutonAnnuler.addActionListener(e -> {
            new GestionDesOffres().setVisible(true);
            this.dispose();
        });
    }

    // Méthode pour remplir la JComboBox avec les noms des entreprises (Read)
    private void chargerEntreprise() {
        try {
            String sql = "SELECT nom FROM entreprise";
            PS = con.prepareStatement(sql);
            RS = PS.executeQuery();
            while (RS.next()) {
                comboEntreprise.addItem(RS.getString("nom"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur chargement entreprise : " + e.getMessage());
        }
    }

    // Méthode pour remplir les champs selon l'entreprise choisie (Read)
    private void chargerInfoEntreprise() {
        String nom = comboEntreprise.getSelectedItem().toString();
        try {
            String sql = "SELECT * FROM entreprise WHERE nom = ?";
            PS = con.prepareStatement(sql);
            PS.setString(1, nom);
            RS = PS.executeQuery();
            if (RS.next()) {
                texteRue.setText(RS.getString("rue"));
                texteCodePostal.setText(RS.getString("code_postal"));
                texteVille.setText(RS.getString("ville"));
                texteEmail.setText(RS.getString("email_entreprise"));
                texteTelephone.setText(RS.getString("telephone_entreprise"));
                texteSecteur.setText(RS.getString("secteur_entreprise"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur chargement infos : " + e.getMessage());
        }
    }

    // Méthode de mise à jour (Update)
    private void mettreAjourEntreprise() {
        String nom = comboEntreprise.getSelectedItem().toString();
        try {
            String sql = "UPDATE entreprise SET rue=?, code_postal=?, ville=?, email_entreprise=?, telephone_entreprise=?, secteur_entreprise=? WHERE nom=?";
            PS = con.prepareStatement(sql);
            PS.setString(1, texteRue.getText());
            PS.setString(2, texteCodePostal.getText());
            PS.setString(3, texteVille.getText());
            PS.setString(4, texteEmail.getText());
            PS.setString(5, texteTelephone.getText());
            PS.setString(6, texteSecteur.getText());
            PS.setString(7, nom);

            PS.executeUpdate();
            JOptionPane.showMessageDialog(null, "Entreprise mise à jour avec succès");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur mise à jour : " + e.getMessage());
        }
    }

    // Méthode de suppression (Delete)
    private void supprimerEntreprise() {
        String nom = comboEntreprise.getSelectedItem().toString();
        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer " + nom + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM entreprise WHERE nom = ?";
                PS = con.prepareStatement(sql);
                PS.setString(1, nom);
                PS.executeUpdate();

                JOptionPane.showMessageDialog(null, "Entreprise supprimée");
                // Mise à jour de l'interface : on retire l'item de la liste
                comboEntreprise.removeItem(nom);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur suppression : " + e.getMessage());
            }
        }
    }
}