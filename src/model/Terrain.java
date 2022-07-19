/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author garbo
 */
public class Terrain {
    
    
    
    public int id;
    public String nom;
    public String type ;
    public String  adresse ;
    public String infos ;
    public String contact ;

    public Terrain() {
    }
    
    

    public Terrain(int id, String nom, String type, String adresse, String infos, String contact) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.infos = infos;
        this.contact = contact;
    }

    public Terrain(String nom, String type, String adresse, String infos, String contact) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.infos = infos;
        this.contact = contact;
    }
    public Terrain(String nom) {
        this.nom = nom;
      
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getInfos() {
        return infos;
    }

    public String getContact() {
        return contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return nom ;
    }

    public Terrain show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}

   

    
   