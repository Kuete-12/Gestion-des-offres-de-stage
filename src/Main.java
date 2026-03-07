import bdd.Connecter;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // 1. On tente d'établir la connexion
        Connection con = Connecter.connexion();

        // 2. On vérifie si l'objet connexion n'est pas nul
        if (con != null) {
            System.out.println("Connexion OK");
            // 3. On referme la connexion après le test pour libérer les ressources
            Connecter.fermerConnexion(con);
        } else {
            System.out.println("Échec de la connexion");
        }
    }
}