package modele;

public class Candidature {
    // Attributs privés
    private int id_offre;
    private int id_etudiant;
    private String nom_entreprise;

    // Constructeur avec paramètres (appelé lors d'une nouvelle candidature)
    public Candidature(int id_offre, int id_etudiant, String nom_entreprise) {
        this.id_offre = id_offre;
        this.id_etudiant = id_etudiant;
        this.nom_entreprise = nom_entreprise;
    }

    // Getters pour récupérer les données
    public int getId_offre() {
        return id_offre;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }
}