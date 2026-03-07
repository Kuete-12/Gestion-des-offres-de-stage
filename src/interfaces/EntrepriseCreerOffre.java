package interfaces;

import bdd.Connecter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EntrepriseCreerOffre extends JFrame {

    private JPanel JPANEL1;
    private JLabel labelTitre, labelLibelle, labelDomaine, labelDate, labelDuree, labelDescription, labelNomEnt, labelEmailEnt, labelVilleEnt;
    private JTextField texteLibelle, texteDomaine, texteDate, texteDuree, texteNomEnt, texteEmailEnt, texteVilleEnt;
    private JTextArea texteDescription;
    private JButton boutonEnregistrer, boutonAnnuler;

    private Connection con;
    private PreparedStatement PS;

    public EntrepriseCreerOffre() {
        con = Connecter.connexion();
        initComponents();

        this.setTitle("Publier une offre de stage");
        this.setSize(600, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1);
    }

    private void initComponents() {
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(229, 255, 204)); // Vert très clair

        labelTitre = new JLabel("Poster une nouvelle offre");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(150, 20, 350, 30);
        JPANEL1.add(labelTitre);

        // Informations de l'offre
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

        // Rappel des informations entreprise (nécessaire pour la table 'offre')
        labelNomEnt = new JLabel("Votre Entreprise :");
        labelNomEnt.setBounds(50, 240, 120, 25);
        JPANEL1.add(labelNomEnt);
        texteNomEnt = new JTextField();
        texteNomEnt.setBounds(200, 240, 300, 25);
        JPANEL1.add(texteNomEnt);

        labelEmailEnt = new JLabel("Email contact :");
        labelEmailEnt.setBounds(50, 280, 120, 25);
        JPANEL1.add(labelEmailEnt);
        texteEmailEnt = new JTextField();
        texteEmailEnt.setBounds(200, 280, 300, 25);
        JPANEL1.add(texteEmailEnt);

        labelVilleEnt = new JLabel("Ville du stage :");
        labelVilleEnt.setBounds(50, 320, 120, 25);
        JPANEL1.add(labelVilleEnt);
        texteVilleEnt = new JTextField();
        texteVilleEnt.setBounds(200, 320, 300, 25);
        JPANEL1.add(texteVilleEnt);

        labelDescription = new JLabel("Description :");
        labelDescription.setBounds(50, 360, 100, 25);
        JPANEL1.add(labelDescription);
        texteDescription = new JTextArea();
        texteDescription.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(texteDescription);
        scroll.setBounds(200, 360, 300, 150);
        JPANEL1.add(scroll);

        boutonEnregistrer = new JButton("Publier");
        boutonEnregistrer.setBounds(150, 550, 130, 40);
        JPANEL1.add(boutonEnregistrer);

        boutonAnnuler = new JButton("Déconnexion");
        boutonAnnuler.setBounds(320, 550, 130, 40);
        JPANEL1.add(boutonAnnuler);

        boutonEnregistrer.addActionListener(e -> enregistrer());
        boutonAnnuler.addActionListener(e -> {
            new Accueil().setVisible(true);
            this.dispose();
        });
    }

    private void enregistrer() {
        // Requête basée sur la structure de la table 'offre' définie dans les sources [3, 4]
        String sql = "INSERT INTO offre (libelle, domaine, date_debut_offre, duree, description, nom_entreprise, email_entreprise, ville_entreprise, statut) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PS = con.prepareStatement(sql);
            PS.setString(1, texteLibelle.getText());
            PS.setString(2, texteDomaine.getText());
            PS.setString(3, texteDate.getText());
            PS.setString(4, texteDuree.getText());
            PS.setString(5, texteDescription.getText());
            PS.setString(6, texteNomEnt.getText());
            PS.setString(7, texteEmailEnt.getText());
            PS.setString(8, texteVilleEnt.getText());
            PS.setInt(9, 1); // Statut valide par défaut [5]

            PS.executeUpdate();
            JOptionPane.showMessageDialog(null, "Offre publiée avec succès !");

            texteLibelle.setText("");
            texteDescription.setText("");
            // On peut rester sur la page pour en saisir une autre
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur publication : " + e.getMessage());
        }
    }
}