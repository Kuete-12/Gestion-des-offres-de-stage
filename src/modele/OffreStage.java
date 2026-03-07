package modele;

public class OffreStage {
    // Attributs spécifiques à l'offre
    private int id_offre;
    private String libelle;
    private String description;
    private String domaine;
    private String date_debut_offre;
    private String duree;
    private String chemin;
    private int statut;

    // Attributs de l'entreprise liés à l'offre
    private String nom_entreprise;
    private String email_entreprise;
    private String ville_entreprise;

    // Constructeur vide
    public OffreStage() {
    }

    // Constructeur complet (utilisé pour la récupération totale des données)
    public OffreStage(int id_offre, String libelle, String description, String domaine, String date_debut_offre, String duree, String chemin, int statut, String nom_entreprise, String email_entreprise, String ville_entreprise) {
        this.id_offre = id_offre;
        this.libelle = libelle;
        this.description = description;
        this.domaine = domaine;
        this.date_debut_offre = date_debut_offre;
        this.duree = duree;
        this.chemin = chemin;
        this.statut = statut;
        this.nom_entreprise = nom_entreprise;
        this.email_entreprise = email_entreprise;
        this.ville_entreprise = ville_entreprise;
    }

    // Getters et Setters
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

    public String getChemin() { return chemin; }
    public void setChemin(String chemin) { this.chemin = chemin; }

    public int getStatut() { return statut; }
    public void setStatut(int statut) { this.statut = statut; }

    public String getNom_entreprise() { return nom_entreprise; }
    public void setNom_entreprise(String nom_entreprise) { this.nom_entreprise = nom_entreprise; }

    public String getEmail_entreprise() { return email_entreprise; }
    public void setEmail_entreprise(String email_entreprise) { this.email_entreprise = email_entreprise; }

    public String getVille_entreprise() { return ville_entreprise; }
    public void setVille_entreprise(String ville_entreprise) { this.ville_entreprise = ville_entreprise; }

    @Override
    public String toString() {
        return "OffreStage{" +
                "libelle='" + libelle + '\'' +
                ", entreprise='" + nom_entreprise + '\'' +
                ", domaine='" + domaine + '\'' +
                '}';
    }
}