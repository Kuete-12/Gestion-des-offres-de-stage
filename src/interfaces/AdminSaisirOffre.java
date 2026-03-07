package interfaces;

import bdd.Connecter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminSaisirOffre extends JFrame {

    private JPanel JPANEL1;
    private JLabel labelTitre, labelLibelle, labelDomaine, labelDate, labelDuree, labelEntreprise, labelDescription;
    private JTextField texteLibelle, texteDomaine, texteDate, texteDuree;
    private JTextArea texteDescription;
    private JComboBox<String> comboEntreprise;
    private JButton boutonEnregistrer, boutonAnnuler;

    private Connection con;
    private PreparedStatement PS;
    private ResultSet RS;

    public AdminSaisirOffre() {
        con = Connecter.connexion();
        initComponents();

        this.setTitle("Saisir une offre de stage");
        this.setSize(600, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1);

        chargerEntreprises();
    }

    private void initComponents() {
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(204, 229, 255));

        labelTitre = new JLabel("Saisie d'une nouvelle offre");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(150, 20, 350, 30);
        JPANEL1.add(labelTitre);

        labelLibelle = new JLabel("Libellé :");
        labelLibelle.setBounds(50, 80, 100, 25);
        JPANEL1.add(labelLibelle);
        texteLibelle = new JTextField();
        texteLibelle.setBounds(200, 80, 300, 25);
        JPANEL1.add(texteLibelle);

        labelDomaine = new JLabel("Domaine :");
        labelDomaine.setBounds(50, 120, 100, 25);
        JPANEL1.add(labelDomaine);
        texteDomaine = new JTextField();
        texteDomaine.setBounds(200, 120, 300, 25);
        JPANEL1.add(texteDomaine);

        labelDate = new JLabel("Date début :");
        labelDate.setBounds(50, 160, 100, 25);
        JPANEL1.add(labelDate);
        texteDate = new JTextField();
        texteDate.setBounds(200, 160, 300, 25);
        JPANEL1.add(texteDate);

        labelDuree = new JLabel("Durée :");
        labelDuree.setBounds(50, 200, 100, 25);
        JPANEL1.add(labelDuree);
        texteDuree = new JTextField();
        texteDuree.setBounds(200, 200, 300, 25);
        JPANEL1.add(texteDuree);

        labelEntreprise = new JLabel("Entreprise :");
        labelEntreprise.setBounds(50, 240, 100, 25);
        JPANEL1.add(labelEntreprise);
        comboEntreprise = new JComboBox<>();
        comboEntreprise.setBounds(200, 240, 300, 25);
        JPANEL1.add(comboEntreprise);

        labelDescription = new JLabel("Description :");
        labelDescription.setBounds(50, 280, 100, 25);
        JPANEL1.add(labelDescription);
        texteDescription = new JTextArea();
        texteDescription.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(texteDescription);
        scroll.setBounds(200, 280, 300, 150);
        JPANEL1.add(scroll);

        boutonEnregistrer = new JButton("Enregistrer");
        boutonEnregistrer.setBounds(150, 480, 130, 40);
        JPANEL1.add(boutonEnregistrer);

        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setBounds(320, 480, 130, 40);
        JPANEL1.add(boutonAnnuler);

        boutonEnregistrer.addActionListener(e -> enregistrer());
        boutonAnnuler.addActionListener(e -> {
            new GestionDesOffres().setVisible(true);
            this.dispose();
        });
    }

    private void chargerEntreprises() {
        try {
            String sql = "SELECT nom FROM entreprise";
            PS = con.prepareStatement(sql);
            RS = PS.executeQuery();
            while (RS.next()) {
                comboEntreprise.addItem(RS.getString("nom"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur entreprises : " + e.getMessage());
        }
    }

    private void enregistrer() {
        String sql = "INSERT INTO offre (libelle, domaine, date_debut_offre, duree, description, nom_entreprise, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PS = con.prepareStatement(sql);
            PS.setString(1, texteLibelle.getText());
            PS.setString(2, texteDomaine.getText());
            PS.setString(3, texteDate.getText());
            PS.setString(4, texteDuree.getText());
            PS.setString(5, texteDescription.getText());
            PS.setString(6, comboEntreprise.getSelectedItem().toString());
            PS.setInt(7, 1); // Statut valide par défaut [1]

            PS.executeUpdate();
            JOptionPane.showMessageDialog(null, "Offre publiée avec succès");

            new GestionDesOffres().setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur enregistrement : " + e.getMessage());
        }
    }
}