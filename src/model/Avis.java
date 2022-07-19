/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author garbo
 */
public class Avis {

    private int id;
    private String commentaire;
    private int note;
    private int idterrain;
    private int idUser;

    public Avis() {
    }

    public Avis(String commentaire, int note) {
        this.commentaire = commentaire;
        this.note = note;
    }

    public Avis(int id, String commentaire, int note) {
        this.id = id;
        this.commentaire = commentaire;
        this.note = note;
    }

    public Avis(String commentaire, int note, int idUser, int idterrain) {
        this.commentaire = commentaire;
        this.note = note;
        this.idUser = idUser;
        this.idterrain = idterrain;

    }

    public Avis(int id, String commentaire, int note, int idUser, int idterrain) {
        this.commentaire = commentaire;
        this.note = note;
        this.idUser = idUser;
        this.idterrain = idterrain;
        this.id = id;

    }

    public int getIdterrain() {
        return idterrain;
    }

    public void setIdterrain(int idterrain) {
        this.idterrain = idterrain;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", commentaire=" + commentaire + ", note=" + note + ", idterrain=" + idterrain + ", idUser=" + idUser + '}';
    }

  
}