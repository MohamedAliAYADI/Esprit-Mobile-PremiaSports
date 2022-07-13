
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.sun.javafx.iio.ImageLoader;
import gui.DetailsCatg;
import models.Categorie;
import models.Produits;

/**
 *
 * @author user
 */
public class mCelluleCat  extends Container{
 public mCelluleCat(Resources theme, Categorie p){
//      
//      
   
        Label lab2 = new Label(p.getNom_cat());
            Button btn = new Button("Details");
          btn.addActionListener((evt) -> {
          
         new DetailsCatg(theme, p).show();
      });
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab2,btn);
        
        this.addAll(ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsCatg(theme, p).show();
            }
        });
//
//  
//        
//         Container ctnLogo = new Container(BoxLayout.y());
//         
//         Container ctnLogo1 = new Container(BoxLayout.y());
//         
//          Container ctnLogo2 = new Container(BoxLayout.y());
//     
//            ImageViewer ImageBtn = new ImageViewer(theme.getImage("emy.jpg"));
//            Button btn = new Button("Get users");
//            btn.addActionListener((evt) -> {
//           
//           new DetailsCatg(theme, p).show();
//        });
//            
//            ImageViewer ImageBtn1 = new ImageViewer(theme.getImage("emy.jpg"));
//            Button btn1 = new Button("Remove Users");
//
//            
//            
//            ImageViewer ImageBtn2 = new ImageViewer(theme.getImage("emy.jpg"));
//            Button btn2 = new Button("Remove Users");
//            
//            ctnLogo.addAll(ImageBtn,btn);
//            ctnLogo.setLayout(BoxLayout.y());
//            ctnLogo1.addAll(ImageBtn1,btn1);
//            ctnLogo1.setLayout(BoxLayout.y());
//            ctnLogo2.addAll(ImageBtn2,btn2);
//          ctnLogo2.setLayout(BoxLayout.y());
//
//        this.addAll(ctnLogo,ctnLogo1,ctnLogo2);
//        
//    }
//    
    
}
}