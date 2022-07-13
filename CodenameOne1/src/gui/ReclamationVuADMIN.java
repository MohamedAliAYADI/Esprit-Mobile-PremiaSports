/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import models.Categorie;
import models.Reclamation;
import services.CategoriService;
import services.ReclamationService;

public class ReclamationVuADMIN  extends Form{
     ReclamationService ts = ReclamationService.getInstance();
   
 public ReclamationVuADMIN(Resources theme, Reclamation p){
 }

    Reclamation c = new Reclamation();

    public ReclamationVuADMIN(Resources theme) {

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
     

        Image pic = theme.getImage("profil.jpg");
        Image mask1 = theme.getImage("round.png");
        mask1 = mask1.scaledHeight(mask1.getHeight() / 5 * 10);
        pic = pic.fill(mask1.getWidth(), mask1.getHeight());
        Label PicLabel = new Label("", pic, " ");
        PicLabel.setMask(mask1.createMask());

        Container sTop = BorderLayout.west(PicLabel);
        sTop.setUIID("Top");


        this.setLayout(BoxLayout.y());

         

        Button AfficherReclamation = new Button("Liste des reclamations Client  ");
        FontImage.setMaterialIcon(AfficherReclamation, FontImage.MATERIAL_BOOKMARK);
 AfficherReclamation.addActionListener((evt) -> {

   new ListReclamation(theme).show();
    });
        this.setLayout(BoxLayout.y());
        

        this.addAll(sTop,AfficherReclamation);



    }

}
