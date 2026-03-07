package modele;

public class Etudiant {
    // Attributs de l'étudiant (types primitifs int et String)
    private int id_etudiant;
    private String nom_etudiant;
    private String prenom_etudiant;
    private String date_de_naissance;
    private String niveau_etude;
    private String email_etudiant;
    private String mot_de_passe;

    // Constructeur vide (généré par l'IDE)
    public Etudiant() {
    }

    // Constructeur plein (utilisé lors de la création d'un compte)
    public Etudiant(int id_etudiant, String nom_etudiant, String prenom_etudiant, String date_de_naissance, String niveau_etude, String email_etudiant, String mot_de_passe) {
        this.id_etudiant = id_etudiant;
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.date_de_naissance = date_de_naissance;
        this.niveau_etude = niveau_etude;
        this.email_etudiant = email_etudiant;
        this.mot_de_passe = mot_de_passe;
    }

    // Getters et Setters
    public int getId_etudiant() { return id_etudiant; }
    public void setId_etudiant(int id_etudiant) { this.id_etudiant = id_etudiant; }

    public String getNom_etudiant() { return nom_etudiant; }
    public void setNom_etudiant(String nom_etudiant) { this.nom_etudiant = nom_etudiant; }

    public String getPrenom_etudiant() { return prenom_etudiant; }
    public void setPrenom_etudiant(String prenom_etudiant) { this.prenom_etudiant = prenom_etudiant; }

    public String getDate_de_naissance() { return date_de_naissance; }
    public void setDate_de_naissance(String date_de_naissance) { this.date_de_naissance = date_de_naissance; }

    public String getNiveau_etude() { return niveau_etude; }
    public void setNiveau_etude(String niveau_etude) { this.niveau_etude = niveau_etude; }

    public String getEmail_etudiant() { return email_etudiant; }
    public void setEmail_etudiant(String email_etudiant) { this.email_etudiant = email_etudiant; }

    public String getMot_de_passe() { return mot_de_passe; }
    public void setMot_de_passe(String mot_de_passe) { this.mot_de_passe = mot_de_passe; }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id_etudiant=" + id_etudiant +
                ", nom_etudiant='" + nom_etudiant + '\'' +
                ", prenom_etudiant='" + prenom_etudiant + '\'' +
                ", email_etudiant='" + email_etudiant + '\'' +
                '}';
    }
}