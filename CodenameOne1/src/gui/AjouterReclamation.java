/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import gui.HomeForm;
import java.util.ArrayList;
import models.Categorie;
import models.Reclamation;

import services.ReclamationService;

/**
 *
 * @author user
 */
public class AjouterReclamation extends Form {

    ReclamationService ts = ReclamationService.getInstance();

    public AjouterReclamation(Resources theme) {

        this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter une nouvelle reclamation");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeReclamation(theme).showBack();
        });

        //Widgets
        Label Sj = new Label("selectionner un sujet ");
        TextField sujet = new TextField("","sujet");

        Label desc = new Label("Redijer votre reclamation");
        TextField description = new TextField("","description");

        
        Label dd1 = new Label("statut");
        TextField statut = new TextField("", "statut");
       

        Button submitBtn = new Button("Ajouter");
//
        submitBtn.addActionListener((evt) -> {
           
           if( statut.getText().equals("") && sujet.getText().equals("") && description.getText().equals("") || !statut.getText().equals("en cours")) 
            
           { Dialog.show("Erreur","Veuillez remplir tout les champs","OK",null);
           
  
           Dialog.show("Erreur","Veuillez noter statut:  en cours","OK",null);
            
           } 
           else if (ts.addRec(new Reclamation(sujet.getText(), description.getText(),statut.getText()))) {
              Dialog.show("succé ", "Reclamation  ajouté avec  succé", "ok", null);
          } 
           
     });  

        this.addAll(Sj, sujet, desc, description, dd1, statut, submitBtn);
    }

}
