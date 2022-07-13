/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import gui.HomeForm;
import models.Categorie;
import services.CategoriService;


/**
 *
 * @author user
 */
public class AjouterCategorie extends Form {

    CategoriService ts = CategoriService.getInstance();
    
    public AjouterCategorie(Resources theme) {

        this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter Categorie ");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
             new HomeForm(theme).showBack();
        });

        //Widgets
        TextField type = new TextField("", "type");
        TextField nom_cat = new TextField("", "nom_cat");
        


        Button submitBtn = new Button("Ajouter");
        

        submitBtn.addActionListener((evt) -> {
            if(type.getText().equals("") && nom_cat.getText().equals("")) 
            //controle de saisie
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
//        
//        
            else if (ts.addCat(new Categorie(type.getText(), nom_cat.getText()))) {
              Dialog.show("succé ", "Categories  ajouté avec  succé", "ok", null);
              new ListCategorie(theme).show();
          } 
 
     });

        this.addAll(type, nom_cat, submitBtn);

    }

}

    
