package interfaces;

import bdd.Connecter;
import javax.swing.*;
        import java.awt.*;
        import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Accueil extends JFrame {

    // 1. Les composants graphiques (le contenant et les textes/boutons)
    private JPanel JPANEL1;
    private JLabel labelTitre, labelIdentifiant, labelMotDePasse, labelChoix;
    private JTextField texteIdentifiant;
    private JPasswordField texteMotDePasse; // Pour cacher les caractères du mot de passe
    private JButton boutonValider, boutonCreerEtudiant, boutonCreerEntreprise;
    private JComboBox<String> comboUtilisateur; // Liste déroulante (Admin, Etudiant, Entreprise)

    // 2. Les variables pour la base de données (SQL)
    private Connection con;
    private PreparedStatement PR; // Pour préparer les requêtes SQL sécurisées
    private ResultSet RS; // Pour stocker le résultat des requêtes

    // Le constructeur viendra juste après

    public Accueil() {
        // 1. On appelle la méthode qui va placer les composants (on la crée juste en dessous)
        initComponents();

        // 2. On établit la connexion avec la base de données Wamp dès l'ouverture
        con = Connecter.connexion();

        // 3. Paramètres de base de la fenêtre
        setTitle("Accueil");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Arrête le programme quand on ferme [4]
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran [2]
    }

    // Méthode pour configurer et placer les éléments sur le "tableau blanc" (le Panel)
    private void initComponents() {
        // 1. Configuration du panneau principal
        JPANEL1 = new JPanel();
        JPANEL1.setLayout(null); // Désactive la gestion automatique pour placer les éléments aux pixels près [1]
        JPANEL1.setBackground(new Color(204, 255, 204)); // Couleur vert clair [2]
        setContentPane(JPANEL1);

        // 2. Titre de la fenêtre
        labelTitre = new JLabel("Bonjour veuillez vous connecter");
        labelTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 20)); // Police et taille selon la source [2]
        labelTitre.setBounds(140, 30, 400, 30);
        JPANEL1.add(labelTitre);

        // 3. Champ Identifiant (Label + Texte)
        labelIdentifiant = new JLabel("Identifiant");
        labelIdentifiant.setBounds(100, 100, 150, 30);
        JPANEL1.add(labelIdentifiant);

        texteIdentifiant = new JTextField();
        texteIdentifiant.setBounds(250, 100, 200, 30);
        JPANEL1.add(texteIdentifiant);

        // 4. Champ Mot de passe (Label + PasswordField)
        labelMotDePasse = new JLabel("Mot de passe");
        labelMotDePasse.setBounds(100, 150, 150, 30);
        JPANEL1.add(labelMotDePasse);

        texteMotDePasse = new JPasswordField(); // Pour masquer le mot de passe [3]
        texteMotDePasse.setBounds(250, 150, 200, 30);
        JPANEL1.add(texteMotDePasse);

        // 5. Choix du type d'utilisateur (Label + ComboBox)
        labelChoix = new JLabel("Utilisateur");
        labelChoix.setBounds(100, 200, 150, 30);
        JPANEL1.add(labelChoix);

        // Liste déroulante avec les trois types d'utilisateurs [4]
        String[] profils = {"etudiant", "entreprise", "admin"};
        comboUtilisateur = new JComboBox<>(profils);
        comboUtilisateur.setBounds(250, 200, 200, 30);
        JPANEL1.add(comboUtilisateur);

        // Paramètres de la fenêtre principale
        this.setTitle("Accueil");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(JPANEL1);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true); // Commenté pour test

        // 6. Bouton Valider
        boutonValider = new JButton("Valider");
        boutonValider.setBounds(210, 270, 150, 40);
        JPANEL1.add(boutonValider);

        // 7. Boutons de création de compte (placés côte à côte) [5, 6]
        boutonCreerEtudiant = new JButton("Créer étudiant");
        boutonCreerEtudiant.setBounds(80, 350, 180, 30);
        JPANEL1.add(boutonCreerEtudiant);

        boutonCreerEntreprise = new JButton("Créer entreprise");
        boutonCreerEntreprise.setBounds(310, 350, 180, 30);
        JPANEL1.add(boutonCreerEntreprise);



        // --- Gestion des Actions (Listeners) ---
        // Ces lignes lient les boutons aux méthodes que nous créerons ensuite [7, 8]

        boutonValider.addActionListener(e -> connecter());

        boutonCreerEtudiant.addActionListener(e -> {
            new CreerEtudiant().setVisible(true); // Ouvre la fenêtre de création étudiant
        });

        boutonCreerEntreprise.addActionListener(e -> {
            new CreerEntreprise().setVisible(true); // Ouvre la fenêtre de création entreprise
        });
    }

    private void connecter() {
        // 1. Récupérer les choix et saisies de l'utilisateur [1, 3]
        String choix = comboUtilisateur.getSelectedItem().toString(); // "etudiant", "entreprise" ou "admin"
        String identifiant = texteIdentifiant.getText();

        // Le mot de passe est un tableau de caractères, on le transforme en String pour SQL [4]
        String mdp = new String(texteMotDePasse.getPassword());

        // 2. Définir la table et les colonnes SQL selon le profil choisi [4]
        String table = "";
        String colEmail = "";
        String colMdp = "";

        switch (choix) {
            case "etudiant":
                table = "etudiant";
                colEmail = "email_etudiant";
                colMdp = "mot_de_passe";
                break;
            case "entreprise":
                table = "entreprise";
                colEmail = "email_entreprise";
                colMdp = "mot_de_passe";
                break;
            case "admin":
                table = "admin";
                colEmail = "email";
                colMdp = "mdp";
                break;
        }

        try {
            // 3. Préparer la requête SQL sécurisée (pour éviter les injections) [5, 6]
            String sql = "SELECT * FROM " + table + " WHERE " + colEmail + " = ? AND " + colMdp + " = ?";

            PR = con.prepareStatement(sql);
            PR.setString(1, identifiant); // Remplace le 1er point d'interrogation
            PR.setString(2, mdp);         // Remplace le 2ème point d'interrogation

            // 4. Exécuter la requête [6]
            RS = PR.executeQuery();

            // 5. Vérifier si l'utilisateur existe dans la base [2]
            if (RS.next()) {
                JOptionPane.showMessageDialog(null, "Connexion réussie !");

                // 6. Redirection vers la bonne fenêtre selon le rôle
                if (choix.equals("etudiant")) {
                    new ConsulterOffreEtudiant().setVisible(true);
                } else if (choix.equals("entreprise")) {
                    new EntrepriseCreerOffre().setVisible(true);
                } else {
                    new GestionDesOffres().setVisible(true);
                }

                // Fermer la fenêtre d'accueil
                this.dispose();

            } else {
                // Si aucune ligne n'est trouvée dans la base
                JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            // Gestion des erreurs SQL ou de connexion [7]
            JOptionPane.showMessageDialog(null, "Erreur de connexion : " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        // Lancement de l'application en affichant la fenêtre d'accueil
        SwingUtilities.invokeLater(() -> {
            Accueil accueil = new Accueil();
            accueil.setVisible(true); // Commenté pour test
        });
    }

     // Méthode pour gérer la connexion (sera appelée par le bouton Valider)

}