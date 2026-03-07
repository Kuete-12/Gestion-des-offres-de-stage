package modele;

import java.util.LinkedList;
import java.util.List;

public class Entreprise {
    // Liste des offres de stage proposées par l'entreprise
    private List<OffreStage> lesOffres = new LinkedList<>();

    // Attributs spécifiques
    private String nom;
    private String ville;
    private String rue;
    private String code_postal;
    private String telephone;
    private String email;
    private String secteur;

    // Constructeur vide (utile pour créer une entreprise sans données de départ)
    public Entreprise() {
    }

    // Constructeur plein (utilisé pour enregistrer une nouvelle entreprise)
    // On n'inclut pas la liste d'offres au moment de la création initiale
    public Entreprise(String nom, String ville, String rue, String code_postal, String email, String telephone, String secteur) {
        this.nom = nom;
        this.ville = ville;
        this.rue = rue;
        this.code_postal = code_postal;
        this.email = email;
        this.telephone = telephone;
        this.secteur = secteur;
    }

    // Getters et Setters pour accéder et modifier les données
    public List<OffreStage> getLesOffres() { return lesOffres; }
    public void setLesOffres(List<OffreStage> lesOffres) { this.lesOffres = lesOffres; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getCode_postal() { return code_postal; }
    public void setCode_postal(String code_postal) { this.code_postal = code_postal; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSecteur() { return secteur; }
    public void setSecteur(String secteur) { this.secteur = secteur; }

    // Méthode pour afficher l'objet sous forme de texte (exclut la liste d'offres)
    @Override
    public String toString() {
        return "Entreprise{" +
                "nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", rue='" + rue + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", secteur='" + secteur + '\'' +
                '}';
    }
}