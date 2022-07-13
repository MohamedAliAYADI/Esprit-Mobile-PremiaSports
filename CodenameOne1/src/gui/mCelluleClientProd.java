
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.sun.javafx.iio.ImageLoader;
import gui.DetailsCatg;
import java.io.IOException;
import models.Categorie;
import models.Produits;
import utils.Statics;

/**
 *
 * @author user
 */
public class mCelluleClientProd  extends Container{
 public mCelluleClientProd(Resources theme, Produits p){
//      
//        


        this.setLayout(BoxLayout.y());
       Image pic = theme.getImage("emy.png");
        Image mask1 = theme.getImage("round.png");
        mask1 = mask1.scaledHeight(mask1.getHeight() / 5 * 10);
        pic = pic.fill(mask1.getWidth(), mask1.getHeight());
        Label PicLabel = new Label("", pic, "Gestionnaire Store");
        PicLabel.setMask(mask1.createMask());
        Container sTop = BorderLayout.center(PicLabel);
        sTop.setUIID("Top");
        
        
     
Label lab1 = new Label(p.getNom_prod());

 
          Label lab2= new Label(p.getPrix());
           Label labprix = new Label("Prix :");
           
           
           
           Container ctprix = new Container();
              
       ctprix.setLayout(BoxLayout.y());         
                          
  ctprix.addAll(labprix,lab2);
           
           
           
           
           
           
   
            Button btn = new Button("Information sur le produit");
          btn.addActionListener((evt) -> {
         new DetailsProduitClient(theme, p).show();
      });
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(sTop,lab1,ctprix,btn);
        
        this.addAll(ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsProduitClient(theme, p).show();
            }
        });
//
}
}