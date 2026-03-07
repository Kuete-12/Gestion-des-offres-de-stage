package interfaces;

import bdd.Connecter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreerEtudiant extends JFrame {

    private JPanel JPANEL1;
    private JLabel labelTitre, labelNom, labelPrenom, labelDate, labelNiveau, labelEmail, labelMdp;
    private JTextField texteNom, textePrenom, texteDate, texteNiveau, texteEmail;
    private JPasswordField texteMdp;
    private JButton boutonEnregistrer, boutonAnnuler;

    private Connection con;
    private PreparedStatement PR;

    public CreerEtudiant() {
        con = Connecter.connexion();
        initComponents();

        this.setTitle("Création de compte Étudiant");
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1);
    }

    private void initComponents() {
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(255, 229, 204)); // Orange très clair

        labelTitre = new JLabel("Inscription Étudiant");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(150, 20, 300, 30);
        JPANEL1.add(labelTitre);

        labelNom = new JLabel("Nom :");
        labelNom.setBounds(50, 80, 100, 25);
        JPANEL1.add(labelNom);
        texteNom = new JTextField();
        texteNom.setBounds(200, 80, 200, 25);
        JPANEL1.add(texteNom);

        labelPrenom = new JLabel("Prénom :");
        labelPrenom.setBounds(50, 130, 100, 25);
        JPANEL1.add(labelPrenom);
        textePrenom = new JTextField();
        textePrenom.setBounds(200, 130, 200, 25);
        JPANEL1.add(textePrenom);

        labelDate = new JLabel("Né(e) le :");
        labelDate.setBounds(50, 180, 100, 25);
        JPANEL1.add(labelDate);
        texteDate = new JTextField();
        texteDate.setBounds(200, 180, 200, 25);
        JPANEL1.add(texteDate);

        labelNiveau = new JLabel("Niveau d'étude :");
        labelNiveau.setBounds(50, 230, 100, 25);
        JPANEL1.add(labelNiveau);
        texteNiveau = new JTextField();
        texteNiveau.setBounds(200, 230, 200, 25);
        JPANEL1.add(texteNiveau);

        labelEmail = new JLabel("Email :");
        labelEmail.setBounds(50, 280, 100, 25);
        JPANEL1.add(labelEmail);
        texteEmail = new JTextField();
        texteEmail.setBounds(200, 280, 200, 25);
        JPANEL1.add(texteEmail);

        labelMdp = new JLabel("Mot de passe :");
        labelMdp.setBounds(50, 330, 100, 25);
        JPANEL1.add(labelMdp);
        texteMdp = new JPasswordField();
        texteMdp.setBounds(200, 330, 200, 25);
        JPANEL1.add(texteMdp);

        boutonEnregistrer = new JButton("S'inscrire");
        boutonEnregistrer.setBounds(100, 420, 120, 40);
        JPANEL1.add(boutonEnregistrer);

        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setBounds(260, 420, 120, 40);
        JPANEL1.add(boutonAnnuler);

        boutonEnregistrer.addActionListener(e -> enregistrer());
        boutonAnnuler.addActionListener(e -> {
            new Accueil().setVisible(true);
            this.dispose();
        });
    }

    private void enregistrer() {
        String sql = "INSERT INTO etudiant (nom_etudiant, prenom_etudiant, date_de_naissance, niveau_etude, email_etudiant, mot_de_passe) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PR = con.prepareStatement(sql);
            PR.setString(1, texteNom.getText());
            PR.setString(2, textePrenom.getText());
            PR.setString(3, texteDate.getText());
            PR.setString(4, texteNiveau.getText());
            PR.setString(5, texteEmail.getText());
            PR.setString(6, new String(texteMdp.getPassword()));

            PR.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compte créé avec succès ! Connectez-vous maintenant.");

            new Accueil().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur inscription : " + ex.getMessage());
        }
    }
}