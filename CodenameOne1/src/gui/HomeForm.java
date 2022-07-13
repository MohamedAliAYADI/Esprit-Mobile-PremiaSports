/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import gui.AjouterCategorie;
import gui.ListCategorie;
import gui.ListProduit;

public class HomeForm extends Form {

    public HomeForm(Resources theme) {
        
 this.setTitle("Profil");

         
  Image profilePic = theme.getImage("emy.jpg");
        Image mask = theme.getImage("round.png");
        mask = mask.scaledHeight(mask.getHeight() / 5 * 10);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
          getToolbar().addComponentToSideMenu(sidemenuTop);
       
       
 this.getToolbar().addCommandToLeftSideMenu("Profil", null, (evt) -> {
            new HomeForm(theme).show();
        });
 
 getToolbar().addMaterialCommandToSideMenu("Commandes Client", FontImage.MATERIAL_PAGES,  e -> new HomeForm(theme).show());
        getToolbar().addMaterialCommandToSideMenu("reclamations client", FontImage.MATERIAL_MAIL,  e -> new ReclamationVuADMIN (theme).show());
        getToolbar().addMaterialCommandToSideMenu("Lists Categories", FontImage.MATERIAL_SELECT_ALL,  e ->new ListCategorie(theme).show());
        getToolbar().addMaterialCommandToSideMenu("Lists Produits", FontImage.MATERIAL_MENU,  e ->new ListProduit(theme).show());
        getToolbar().addMaterialCommandToSideMenu(" Parametres de compte", FontImage.MATERIAL_SETTINGS,  e -> new HomeForm(theme).show());
        getToolbar().addMaterialCommandToSideMenu(" se deconnecter ", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(theme).show());
 
 
 
 
 
        //custom


        //widgets
           
    Image pic = theme.getImage("emy.jpg");
        Image mask1 = theme.getImage("round.png");
        mask1 = mask1.scaledHeight(mask1.getHeight() / 5 * 10);
        pic = pic.fill(mask1.getWidth(), mask1.getHeight());
        Label PicLabel = new Label("", pic, "Gestionnaire Store");
        PicLabel.setMask(mask1.createMask());

        Container sTop = BorderLayout.centerAbsolute(PicLabel);
        sTop.setUIID("Top");
        
        Button addprod = new Button("Ajouter Produit");
         FontImage.setMaterialIcon(addprod, FontImage.MATERIAL_EDIT);
        Button addcat = new Button("Ajouter Categorie");
         FontImage.setMaterialIcon(addcat, FontImage.MATERIAL_EDIT);
        //Button showcat = new Button("List des Categories");
      //Button showprod = new Button("List des Produits");

       addprod.addActionListener((evt) -> {

   new AjouterProduit(theme).show();
    });
//        //..
//     showprod.addActionListener((evt) -> {
//
//          new ListProduit(theme).show();
//     });

        addcat.addActionListener((evt) -> {

            new AjouterCategorie(theme).show();
        });
//
//        showcat.addActionListener((evt) -> {
//
//            new ListCategorie(theme).show();
//        });

  
        this.addAll( sTop,addcat,addprod);

    }

}
