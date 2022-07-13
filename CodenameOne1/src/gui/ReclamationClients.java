/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import models.Reclamation;
import services.ReclamationService;

/**
 *
 * @author user
 */


public class ReclamationClients extends Form{
     ReclamationService ts = ReclamationService.getInstance();
   
 public ReclamationClients(Resources theme, Reclamation p){
 }

    Reclamation c = new Reclamation();

    public ReclamationClients(Resources theme) {
        this.setLayout(BoxLayout.y());
      
        Label lab1 = new Label(c.getDescription());
        Label lab2 = new Label(c.getSujet());
          Label lab3 = new Label(c.getStatut());
        
       
            Button btn1 = new Button("Modifier l'etat de la reclamation ");
           btn1.addActionListener((evt) -> {
           
           Dialog.show("suite au changement de statut de la reclamation  ", "Un SMS sera envoyer au client ", "OK", null);
           
        });
    Button btn3 = new Button("Supprimer Cette reclamation");
           btn3.addActionListener((evt) -> {
           
           Dialog.show("etes vous sure de vouloir supprimer cette reclamation", "appu sur OK", "OK", null);
           
        });
    
           
           
           
           
           
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab1, lab2,lab3,btn1,btn3);
        
        
        this.addAll(ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new HomeForm (theme).show();
            }
        });}}
