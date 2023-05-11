/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author azerb
 */
public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String date_nais;
    private String Email;
    private String photo;
    private String tel;
    private String Adresse;
    private String Password;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_nais() {
        return date_nais;
    }

    public void setDate_nais(String date_nais) {
        this.date_nais = date_nais;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Membre(int id, String nom, String prenom, String date_nais, String Email, String photo, String tel, String Adresse, String Password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_nais = date_nais;
        this.Email = Email;
        this.photo = photo;
        this.tel = tel;
        this.Adresse = Adresse;
        this.Password = Password;
        this.role = role;
    }

    public Membre(String nom, String prenom, String date_nais, String Email, String photo, String tel, String Adresse, String Password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_nais = date_nais;
        this.Email = Email;
        this.photo = photo;
        this.tel = tel;
        this.Adresse = Adresse;
        this.Password = Password;
        this.role = role;
    }

    public Membre() {
    }
    
    
}
