/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;
import models.Categorie;
import models.Produits;
import models.Reclamation;
import services.CategoriService;
import services.ProduitService;
import services.ReclamationService;

/**
 *
 * @author user
 */
public class detailswithoutbuttonReclamation  extends Container {
 ReclamationService ts = ReclamationService.getInstance();
 ReclamationService ps = new ReclamationService ();
  Reclamation p = new Reclamation();
    public detailswithoutbuttonReclamation(Resources theme, Reclamation p) {
      
    this.setLayout(BoxLayout.y());
           Image profilePic = theme.getImage("reclamation.jpg");
        Image mask = theme.getImage("round.png");
        mask = mask.scaledHeight(mask.getHeight() / 3* 10);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.centerAbsolute(profilePicLabel);
        
  
     List<Reclamation> tournois = ts.fetchRec();
 CategoriService ts = CategoriService.getInstance();
//        
        this.setLayout(BoxLayout.y());
        
      
        Label lab1 = new Label("Reclamation :");
        Label lab11 = new Label(p.getDescription());
        Container c1 = new Container();
              
       c1.setLayout(BoxLayout.x());         
                          
  c1.addAll(lab1,lab11);
  
  
  
   Label lab2 = new Label("Statut de reclamation :");
        Label lab22 = new Label(p.getStatut());
        
        
        
             Container c2 = new Container();
              
       c2.setLayout(BoxLayout.x());         
                          
  c2.addAll(lab2,lab22);
        
        
        Label lab3 = new Label("Sujet de reclamation :");
        
         Label lab33 = new Label(p.getSujet());
                 
             Container c3 = new Container();
              
       c3.setLayout(BoxLayout.x());         
                          
  c2.addAll(lab3,lab33);
         
         
         
         
         
            
        
            Button btn1 = new Button("Supprimer la reclamation ");
           btn1.addActionListener((evt) -> {
           
           Dialog.show("voulez vous supprimer cette reclamation", "appuyer sur oui ", "oui", null);
           
        });
    Button btn3 = new Button("Ajouter une nouvelle reclamation");
           btn3.addActionListener((evt) -> {
           
           
           
        });
    
           
           
           
           
           
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.x());
        ctn.addAll(btn1,btn3);
        
        
        this.addAll(sidemenuTop,c1,c2,c3,ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsRec(theme, p).show();
            }
        });}}