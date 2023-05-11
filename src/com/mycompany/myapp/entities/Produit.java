/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author msi
 */
/**
 *
 * @author azerb
 */
public class Produit {
    private int id;
    private String nom_Prod;
    private String description;
    private String image;
    private float prix;
    private int nbr_Prods;
    private int categorie;

    public Produit(int id, String nom_Prod, String description, String image, float prix, int nbr_Prods, int categorie) {
        this.id = id;
        this.nom_Prod = nom_Prod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.nbr_Prods = nbr_Prods;
        this.categorie = categorie;
    }

    public Produit() {
    }

    public Produit(int id, String nom_Prod, String description, String image, float prix, int nbr_Prods) {
        this.id = id;
        this.nom_Prod = nom_Prod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.nbr_Prods = nbr_Prods;
    }

    public Produit(String nom_Prod, String description, String image, float prix, int nbr_Prods, int categorie) {
        this.nom_Prod = nom_Prod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.nbr_Prods = nbr_Prods;
        this.categorie = categorie;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_Prod() {
        return nom_Prod;
    }

    public void setNom_Prod(String nom_Prod) {
        this.nom_Prod = nom_Prod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbr_Prods() {
        return nbr_Prods;
    }

    public void setNbr_Prods(int nbr_Prods) {
        this.nbr_Prods = nbr_Prods;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_Prod=" + nom_Prod + ", description=" + description + ", image=" + image + ", prix=" + prix + ", nbr_Prods=" + nbr_Prods + ", categorie=" + categorie + '}';
    }
    
    
}
