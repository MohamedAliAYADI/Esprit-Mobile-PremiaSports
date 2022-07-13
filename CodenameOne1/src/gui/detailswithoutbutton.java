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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import models.Categorie;
import services.CategoriService;

/**
 *
 * @author user
 */
public class detailswithoutbutton extends Container{
 public detailswithoutbutton(Resources theme, Categorie p){
 CategoriService ts = CategoriService.getInstance();
//        
        this.setLayout(BoxLayout.y());
      
        Label lab1 = new Label(p.getType());
        Label lab2 = new Label(p.getNom_cat());
            
        
            Button btn1 = new Button("Supprimer la categories");
           btn1.addActionListener((evt) -> {
           
           Dialog.show("sur T7eb tfasakh? ", "appu sur oui ken oui", "oui", null);
           
        });
    Button btn3 = new Button("Modifier la categories");
           btn3.addActionListener((evt) -> {
           
           Dialog.show("sur T7eb tfasakh? ", "appu sur oui ken oui", "oui", null);
           
        });
    
           
           
           
           
           
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab1, lab2,btn1,btn3);
        
        
        this.addAll(ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsCatg(theme, p).show();
            }
        });}}