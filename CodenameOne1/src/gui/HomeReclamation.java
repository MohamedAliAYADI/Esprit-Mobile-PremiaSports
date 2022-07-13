/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import models.Reclamation;
import services.ProduitService;
import services.ReclamationService;

public class HomeReclamation extends Form {

    ReclamationService ts = ReclamationService.getInstance();
    ProduitService ps = new ProduitService();
    Reclamation c = new Reclamation();

    public HomeReclamation(Resources theme) {

        this.setTitle("Reclamtion");

        Image profilePic = theme.getImage("profil.jpg");
        Image mask = theme.getImage("round.png");
        mask = mask.scaledHeight(mask.getHeight() / 5 * 10);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.north(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        getToolbar().addComponentToSideMenu(sidemenuTop);

        this.getToolbar().addCommandToLeftSideMenu("Profil", null, (evt) -> {
            new HomeFormClient(theme).show();
        });
        getToolbar().addMaterialCommandToSideMenu("Store", FontImage.MATERIAL_STORE, e -> new ListProduit(theme).show());

        getToolbar().addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_PAGES, e -> new HomeForm(theme).show());

        getToolbar().addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_MAIL, e -> new HomeForm(theme).show());
        getToolbar().addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SELECT_ALL, e -> new ListCategorie(theme).show());
        getToolbar().addMaterialCommandToSideMenu(" Parametres de compte", FontImage.MATERIAL_SETTINGS, e -> new HomeForm(theme).show());
        getToolbar().addMaterialCommandToSideMenu(" se deconnecter ", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(theme).show());

        Image pic = theme.getImage("profil.jpg");
        Image mask1 = theme.getImage("round.png");
        mask1 = mask1.scaledHeight(mask1.getHeight() / 5 * 10);
        pic = pic.fill(mask1.getWidth(), mask1.getHeight());
        Label PicLabel = new Label("", pic, " ");
        PicLabel.setMask(mask1.createMask());

        Container sTop = BorderLayout.west(PicLabel);
        sTop.setUIID("Top");

        Button AjouterReclamation = new Button("Deposer une nouvelle  reclamation");
        FontImage.setMaterialIcon(AjouterReclamation, FontImage.MATERIAL_EDIT);
 AjouterReclamation.addActionListener((evt) -> {

   new AjouterReclamation(theme).show();
    });
        this.setLayout(BoxLayout.y());
        
        
        
        
        
         Image pic1 = theme.getImage("profil.jpg");
        Image mask11 = theme.getImage("round.png");
        mask11 = mask11.scaledHeight(mask11.getHeight() / 5 * 10);
        pic1 = pic1.fill(mask11.getWidth(), mask11.getHeight());
        Label PicLabel2 = new Label("", pic1, "Gestionnaire Store");
        PicLabel2.setMask(mask11.createMask());

        Container sTop1 = BorderLayout.west(PicLabel2);
        sTop1.setUIID("Top");

        Button AfficherReclamation = new Button("Mes reclamations");
        FontImage.setMaterialIcon(AfficherReclamation, FontImage.MATERIAL_ADD_ALERT);
 AfficherReclamation.addActionListener((evt) -> {

   new ListReclamation(theme).show();
   
    });
        this.setLayout(BoxLayout.y());
        
        
        
        
        
        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        this.addAll(sTop, AjouterReclamation,sTop1,AfficherReclamation);



    }

}
