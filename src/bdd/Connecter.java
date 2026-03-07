package bdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connecter {

    // Méthode pour établir la connexion (statique pour l'appeler facilement)
    public static Connection connexion() {
        Connection con = null;
        Properties props = new Properties();

        try {
            // 1. Chargement du fichier config.properties que tu viens de créer
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);

            // 2. Récupération des infos (URL, utilisateur, mot de passe)
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String mdp = props.getProperty("db.password");

            // 3. Tentative de connexion via le Driver Manager
            con = DriverManager.getConnection(url, user, mdp);
            System.out.println("Connexion réussie à la base de données !");

        } catch (SQLException e) {
            // Affiche l'erreur SQL si les identifiants sont mauvais par exemple
            System.err.println("Erreur SQL lors de la connexion : " + e.getMessage());
        } catch (IOException e) {
            // Affiche l'erreur si le fichier config.properties est introuvable
            System.err.println("Erreur lors du chargement du fichier : " + e.getMessage());
        }

        return con;
    }

    // Méthode pour fermer la connexion proprement
    public static void fermerConnexion(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connexion fermée avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture : " + e.getMessage());
        }
    }
}