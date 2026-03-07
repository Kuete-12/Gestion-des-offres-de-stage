package modele;

public class Admin {

    // Attributs statiques pour un administrateur global
    public static String id_admin;
    public static String mdp_admin;
    public static boolean is_admin = false;

    // Constructeur par défaut vide
    public Admin() {
    }

    // Getters et Setters pour modifier et récupérer les infos
    public static String getId_admin() {
        return id_admin;
    }

    public static void setId_admin(String id_admin) {
        Admin.id_admin = id_admin;
    }

    public static String getMdp_admin() {
        return mdp_admin;
    }

    public static void setMdp_admin(String mdp_admin) {
        Admin.mdp_admin = mdp_admin;
    }

    public static boolean isIs_admin() {
        return is_admin;
    }

    public static void setIs_admin(boolean is_admin) {
        Admin.is_admin = is_admin;
    }
}