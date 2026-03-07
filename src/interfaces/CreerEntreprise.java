package interfaces;

import bdd.Connecter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreerEntreprise extends JFrame {

    // 1. Déclaration des composants graphiques
    private JPanel JPANEL1;
    private JLabel labelTitre, labelNom, labelRue, labelCodePostal, labelVille, labelEmail, labelMdp, labelTelephone, labelSecteur;
    private JTextField texteNom, texteRue, texteCodePostal, texteVille, texteEmail, texteTelephone, texteSecteur;
    private JPasswordField texteMdp;
    private JButton boutonEnvoyer, boutonAnnuler;

    // 2. Variables pour la base de données
    private Connection con;
    private PreparedStatement PR;

    // 3. Constructeur
    public CreerEntreprise() {
        // Initialisation de la connexion via la classe Connecter [1]
        con = Connecter.connexion();

        // Initialisation de l'interface
        initComponents();

        // Paramètres de la fenêtre [1]
        this.setTitle("Créer une entreprise");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(JPANEL1); // Définit le panneau principal [2]
    }

    private void initComponents() {
        // Configuration du panneau principal [3]
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null);
        JPANEL1.setBackground(new Color(204, 255, 204)); // Vert clair

        // Titre [3]
        labelTitre = new JLabel("Création d'une entreprise");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(150, 20, 300, 30);
        JPANEL1.add(labelTitre);

        // Nom de l'entreprise [2]
        labelNom = new JLabel("Nom :");
        labelNom.setBounds(100, 80, 100, 25);
        JPANEL1.add(labelNom);

        texteNom = new JTextField();
        texteNom.setBounds(250, 80, 200, 25);
        JPANEL1.add(texteNom);

        // Adresse : Rue [2]
        labelRue = new JLabel("Rue :");
        labelRue.setBounds(100, 120, 100, 25);
        JPANEL1.add(labelRue);

        texteRue = new JTextField();
        texteRue.setBounds(250, 120, 200, 25);
        JPANEL1.add(texteRue);

        // Adresse : Code Postal [2]
        labelCodePostal = new JLabel("Code Postal :");
        labelCodePostal.setBounds(100, 160, 100, 25);
        JPANEL1.add(labelCodePostal);

        texteCodePostal = new JTextField();
        texteCodePostal.setBounds(250, 160, 200, 25);
        JPANEL1.add(texteCodePostal);

        // Adresse : Ville [2]
        labelVille = new JLabel("Ville :");
        labelVille.setBounds(100, 200, 100, 25);
        JPANEL1.add(labelVille);

        texteVille = new JTextField();
        texteVille.setBounds(250, 200, 200, 25);
        JPANEL1.add(texteVille);

        // Contact : Email [2]
        labelEmail = new JLabel("Email :");
        labelEmail.setBounds(100, 240, 100, 25);
        JPANEL1.add(labelEmail);

        texteEmail = new JTextField();
        texteEmail.setBounds(250, 240, 200, 25);
        JPANEL1.add(texteEmail);

        // Contact : Mot de passe [2]
        labelMdp = new JLabel("Mot de passe :");
        labelMdp.setBounds(100, 280, 100, 25);
        JPANEL1.add(labelMdp);

        texteMdp = new JPasswordField();
        texteMdp.setBounds(250, 280, 200, 25);
        JPANEL1.add(texteMdp);

        // Contact : Téléphone [2]
        labelTelephone = new JLabel("Téléphone :");
        labelTelephone.setBounds(100, 320, 100, 25);
        JPANEL1.add(labelTelephone);

        texteTelephone = new JTextField();
        texteTelephone.setBounds(250, 320, 200, 25);
        JPANEL1.add(texteTelephone);

        // Secteur d'activité [4]
        labelSecteur = new JLabel("Secteur :");
        labelSecteur.setBounds(100, 360, 100, 25);
        JPANEL1.add(labelSecteur);

        texteSecteur = new JTextField();
        texteSecteur.setBounds(250, 360, 200, 25);
        JPANEL1.add(texteSecteur);

        // Boutons [4]
        boutonEnvoyer = new JButton("Envoyer");
        boutonEnvoyer.setBounds(150, 430, 120, 40);
        JPANEL1.add(boutonEnvoyer);

        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.setBounds(330, 430, 120, 40);
        JPANEL1.add(boutonAnnuler);

        // --- Gestion des Actions ---

        // Action pour envoyer les données à MySQL [4]
        boutonEnvoyer.addActionListener(e -> envoyer());

        // Action pour annuler et revenir à la gestion [4]
        boutonAnnuler.addActionListener(e -> {
            new GestionDesOffres().setVisible(true);
            this.dispose();
        });
    }

    private void envoyer() {
        // Requête SQL d'insertion (Create du CRUD) [4, 5]
        String sql = "INSERT INTO entreprise (nom, rue, code_postal, ville, email_entreprise, mot_de_passe, telephone_entreprise, secteur_entreprise) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PR = con.prepareStatement(sql);

            // On récupère les textes et on les injecte dans la requête [6]
            PR.setString(1, texteNom.getText());
            PR.setString(2, texteRue.getText());
            PR.setString(3, texteCodePostal.getText());
            PR.setString(4, texteVille.getText());
            PR.setString(5, texteEmail.getText());
            PR.setString(6, new String(texteMdp.getPassword()));
            PR.setString(7, texteTelephone.getText());
            PR.setString(8, texteSecteur.getText());

            // Exécution de la mise à jour [6]
            PR.executeUpdate();

            JOptionPane.showMessageDialog(null, "Entreprise enregistrée avec succès");

            // Retour à la page précédente [6]
            new GestionDesOffres().setVisible(true);
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement : " + ex.getMessage());
        }
    }
}