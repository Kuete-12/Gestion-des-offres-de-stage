package interfaces;

import bdd.Connecter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class GestionDesOffres extends JFrame {

    // Déclaration des composants graphiques [1]
    private JPanel JPANEL1;
    private JLabel labelTitre, labelAdminLibelle, labelAdminIdentifiant;
    private JButton boutonSaisirOffre, boutonCreerEntreprise, boutonConsulterOffres, boutonModifierEntreprise, boutonAnnuler;

    // Variable pour la base de données [2]
    private Connection con;

    public GestionDesOffres() {
        // Établissement de la connexion à l'ouverture de la fenêtre [2]
        con = Connecter.connexion();

        // Initialisation des composants de l'interface [2]
        initComponents();

        // Paramètres de la fenêtre principale [2, 4]
        this.setTitle("Administration des offres de stage");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrer la fenêtre à l'écran [4]
        this.setContentPane(JPANEL1);
    }

    private void initComponents() {
        // Configuration du panneau principal [4]
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null); // Placement manuel (coordonnées X, Y) [4]
        JPANEL1.setBackground(new Color(204, 255, 255)); // Bleu clair [5]

        // Titre de la fenêtre [5]
        labelTitre = new JLabel("Administration des offres de stage");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        labelTitre.setBounds(150, 30, 450, 40);
        JPANEL1.add(labelTitre);

        // Affichage de l'identifiant de l'administrateur [6]
        labelAdminLibelle = new JLabel("Identifiant :");
        labelAdminLibelle.setBounds(50, 100, 100, 30);
        JPANEL1.add(labelAdminLibelle);

        // Identifiant écrit "en dur" car il n'y a qu'un seul admin global [3, 6]
        labelAdminIdentifiant = new JLabel("admin");
        labelAdminIdentifiant.setBounds(150, 100, 100, 30);
        labelAdminIdentifiant.setForeground(Color.BLUE);
        JPANEL1.add(labelAdminIdentifiant);

        // --- Création et positionnement des boutons [3] ---

        boutonSaisirOffre = new JButton("Saisir une offre");
        boutonSaisirOffre.setBounds(250, 150, 200, 40);
        JPANEL1.add(boutonSaisirOffre);

        boutonCreerEntreprise = new JButton("Créer une entreprise");
        boutonCreerEntreprise.setBounds(250, 200, 200, 40);
        JPANEL1.add(boutonCreerEntreprise);

        boutonConsulterOffres = new JButton("Consulter les offres");
        boutonConsulterOffres.setBounds(250, 250, 200, 40);
        JPANEL1.add(boutonConsulterOffres);

        boutonModifierEntreprise = new JButton("Modifier une entreprise");
        boutonModifierEntreprise.setBounds(250, 300, 200, 40);
        JPANEL1.add(boutonModifierEntreprise);

        boutonAnnuler = new JButton("Annuler / Déconnexion");
        boutonAnnuler.setBounds(250, 370, 200, 40);
        boutonAnnuler.setBackground(new Color(255, 204, 204)); // Rouge clair [7]
        JPANEL1.add(boutonAnnuler);

        // --- Gestion des événements (ActionListeners) [3, 7, 8] ---

        boutonSaisirOffre.addActionListener(e -> {
            new AdminSaisirOffre().setVisible(true);
            this.dispose();
        });

        boutonCreerEntreprise.addActionListener(e -> {
            new CreerEntreprise().setVisible(true);
            this.dispose();
        });

        boutonConsulterOffres.addActionListener(e -> {
            new ConsulterOffre().setVisible(true);
            this.dispose();
        });

        boutonModifierEntreprise.addActionListener(e -> {
            new ModifierEntreprise().setVisible(true);
            this.dispose();
        });

        // Bouton pour fermer la fenêtre actuelle et revenir à l'accueil [7]
        boutonAnnuler.addActionListener(e -> {
            new Accueil().setVisible(true);
            this.dispose();
        });
    }
}