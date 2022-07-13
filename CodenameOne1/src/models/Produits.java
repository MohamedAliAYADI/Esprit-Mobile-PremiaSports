package models;

public class Produits {

    private int id_prod;
    
    private String nom_prod;
   
    private String reference_prod;
    
    private String description;
    
    private String prix;
    
     private String Image_prod;
     private String Image_prodd;
     
      private String quantite;
      
      private Categorie id_catg;

    public Categorie getId_catg() {
        return id_catg;
    }

    public void setId_catg(Categorie id_catg) {
        this.id_catg = id_catg;
    }

    public Produits(String nom_prod, String reference_prod, String description, String prix, String Image_prod, String quantite, Categorie id_catg) {
        this.nom_prod = nom_prod;
        this.reference_prod = reference_prod;
        this.description = description;
        this.prix = prix;
        this.Image_prod = Image_prod;
        this.quantite = quantite;
        this.id_catg = id_catg;
    }
      
      

    public Produits(String nom_prod, String reference_prod, String description, String prix, String quantite) {
        this.nom_prod = nom_prod;
        this.reference_prod = reference_prod;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getReference_prod() {
        return reference_prod;
    }

    public void setReference_prod(String reference_prod) {
        this.reference_prod = reference_prod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImage_prod() {
        return Image_prod;
    }

    public void setImage_prod(String Image_prod) {
        this.Image_prod = Image_prod;
    }

    public String getImage_prodd() {
        return Image_prodd;
    }

    public void setImage_prodd(String Image_prodd) {
        this.Image_prodd = Image_prodd;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produits{" + "id_prod=" + id_prod + ", nom_prod=" + nom_prod + ", reference_prod=" + reference_prod + ", description=" + description + ", prix=" + prix + ", Image_prod=" + Image_prod + ", Image_prodd=" + Image_prodd + ", quantite=" + quantite + '}';
    }

    public Produits(int id_prod, String nom_prod, String reference_prod, String description, String prix, String Image_prod, String Image_prodd, String quantite) {
        this.id_prod = id_prod;
        this.nom_prod = nom_prod;
        this.reference_prod = reference_prod;
        this.description = description;
        this.prix = prix;
        this.Image_prod = Image_prod;
        this.Image_prodd = Image_prodd;
        this.quantite = quantite;
    }

    public Produits(String nom_prod, String reference_prod, String description, String prix, String Image_prod, String quantite) {
        this.nom_prod = nom_prod;
        this.reference_prod = reference_prod;
        this.description = description;
        this.prix = prix;
        this.Image_prod = Image_prod;
        this.quantite = quantite;
    }

    public Produits() {
    }

    public Produits(String nom_prod, String reference_prod, String Image_prod) {
        this.nom_prod = nom_prod;
        this.reference_prod = reference_prod;
        this.Image_prod = Image_prod;
    }
}