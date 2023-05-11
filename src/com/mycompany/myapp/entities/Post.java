/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Post {
    private int id, nbr_Vue;
    private String theme,nom,contenu,image;
    private String date_Creation;

    public Post(int id, int nbr_Vue, String theme, String nom, String contenu, String image, String date_Creation) {
        this.id = id;
        this.nbr_Vue = nbr_Vue;
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.image = image;
        this.date_Creation = date_Creation;
    }

    public Post(int nbr_Vue, String theme, String nom, String contenu, String image, String date_Creation) {
        this.nbr_Vue = nbr_Vue;
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.image = image;
        this.date_Creation = date_Creation;
    }
    public Post( String nom, String theme,String contenu, String image) {
      
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.image = image;
       
    }
    
    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_Vue() {
        return nbr_Vue;
    }

    public void setNbr_Vue(int nbr_Vue) {
        this.nbr_Vue = nbr_Vue;
        
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_Creation() {
        return date_Creation;
    }

    public void setDate_Creation(String date_Creation) {
        this.date_Creation = date_Creation;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", nbr_Vue=" + nbr_Vue + ", theme=" + theme + ", nom=" + nom + ", contenu=" + contenu + ", image=" + image + ", date_Creation=" + date_Creation + '}';
    }

    public int getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
