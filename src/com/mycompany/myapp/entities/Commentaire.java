/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author MSI
 */
public class Commentaire {
          private int id;
          private String text,date;
          private Post p;
          
          

    public Post getP() {
        return p;
    }

    public void setP(Post p) {
        this.p = p;
    }

    public Commentaire() {
    }

     public Commentaire(String text) {
          this.text = text;
    }
    public Commentaire(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public Commentaire(int id, String text, String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", text=" + text + ", date=" + date + '}';
    }
    
}
