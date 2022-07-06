/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

 
/**
 *
 * @author khaledguedria
 */
public class Promotion {
    
    public int idPromotion;
    private String type;
    private int promo;
    

    public Promotion() {
        
    }

    public Promotion(String type,int promo) {
        this.type = type;
        this.promo=promo;
    }
    

    public Promotion(int idPromotion, String type,int promo) {
        this.idPromotion = idPromotion;
        this.type = type;
        this.promo=promo;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public String getType() {
        return type;
    }

    public double getPromo() {
        return promo;
    }
    

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "Promotion{" + "idPromotion=" + idPromotion + ", type=" + type + ", promo=" + promo + '}';
    }
    
    
    
    
    
}
