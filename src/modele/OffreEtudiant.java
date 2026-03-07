package modele;

public class OffreEtudiant {
    // Attributs (on utilise int et String comme convenu)
    private int id_offre;
    private String libelle;
    private String description;
    private String domaine;
    private String date_debut_offre;
    private String duree;
    private String nom_entreprise;
    private String email_entreprise;
    private String ville_entreprise;

    // 1. Constructeur vide
    public OffreEtudiant() {
    }

    // 2. Constructeur plein (tous les champs)
    public OffreEtudiant(int id_offre, String libelle, String description, String domaine, String date_debut_offre, String duree, String nom_entreprise, String email_entreprise, String ville_entreprise) {
        this.id_offre = id_offre;
        this.libelle = libelle;
        this.description = description;
        this.domaine = domaine;
        this.date_debut_offre = date_debut_offre;
        this.duree = duree;
        this.nom_entreprise = nom_entreprise;
        this.email_entreprise = email_entreprise;
        this.ville_entreprise = ville_entreprise;
    }

    // 3. Getters et Setters
    public int getId_offre() { return id_offre; }
    public void setId_offre(int id_offre) { this.id_offre = id_offre; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDomaine() { return domaine; }
    public void setDomaine(String domaine) { this.domaine = domaine; }

    public String getDate_debut_offre() { return date_debut_offre; }
    public void setDate_debut_offre(String date_debut_offre) { this.date_debut_offre = date_debut_offre; }

    public String getDuree() { return duree; }
    public void setDuree(String duree) { this.duree = duree; }

    public String getNom_entreprise() { return nom_entreprise; }
    public void setNom_entreprise(String nom_entreprise) { this.nom_entreprise = nom_entreprise; }

    public String getEmail_entreprise() { return email_entreprise; }
    public void setEmail_entreprise(String email_entreprise) { this.email_entreprise = email_entreprise; }

    public String getVille_entreprise() { return ville_entreprise; }
    public void setVille_entreprise(String ville_entreprise) { this.ville_entreprise = ville_entreprise; }

    // 4. Méthode toString pour le debug
    @Override
    public String toString() {
        return "OffreEtudiant{" +
                "libelle='" + libelle + '\'' +
                ", entreprise='" + nom_entreprise + '\'' +
                ", ville='" + ville_entreprise + '\'' +
                '}';
    }
}