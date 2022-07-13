/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import models.Categorie;
import models.Produits;
import services.CategoriService;
import services.ProduitService;

/**
 *
 * @author user
 */
public class detailswithoutbutton1prod extends Container {
 ProduitService ts = ProduitService.getInstance();
 ProduitService ps = new ProduitService ();
  Produits c = new Produits();
    public detailswithoutbutton1prod(Resources theme, Produits p) {
      
       
//        
       this.setLayout(BoxLayout.y());
   
        ImageViewer img = new ImageViewer(theme.getImage("emy.png"));
        
        Label lab1 = new Label(p.getNom_prod());
        
        
          Label lab2 = new Label(p.getReference_prod());
                    Label labref = new Label("Reference :");

         Label lab3 = new Label(p.getDescription());
       
        Label lab4 = new Label(p.getPrix());
         Label labprix = new Label("Prix :");
        
        
        
        
        
        
          Label lab5 = new Label(p.getQuantite());
           Label labquantite = new Label("quantité :");
          
             Container ctn = new Container();
              Container ctnimg = new Container();
              
              
                            ctnimg.setLayout(BoxLayout.y());
  ctnimg.add(img);
  
  
  
Container ctref = new Container();
              
              
                            ctref.setLayout(BoxLayout.x());
  ctref.addAll(labref,lab2);
  
  
  
 
Container ctprix = new Container();
              
       ctprix.setLayout(BoxLayout.x());         
                          
  ctprix.addAll(labprix,lab4);
  
  
  
  
  
Container ctquantite = new Container();
              
       ctquantite.setLayout(BoxLayout.x());         
                          
  ctquantite.addAll(labquantite,lab5);
  
  
  
  
  
  
  
  
  
  
  
  

  
  
      ctn.setLayout(BoxLayout.y());
      ctn.addAll(lab1 ,lab3);
    
      this.addAll(ctnimg,ctn,ctprix,ctref,ctquantite);

//
//      
//        Button btn1 = new Button("Supprimer la produit");
//        btn1.addActionListener((evt) -> {
//        
//     
//   // ts.Remove( c.getId_prod());
//    ps.Remove(c.getId_prod());
//      
//    
////            Dialog.show("sur T7eb tfasakh? ", "appu sur oui ken oui", "oui", null);
//
//        });



Button btn1 = new Button("Supprimer la produit");
btn1.addActionListener((evt) -> {
    
    
     ps.Remove(c.getId_prod());
     Dialog.show("succé ", " Produit supprimer avec succé", "ok", null);
    
    
    
});
    

        Container ctn1 = new Container();
        ctn1.setLayout(BoxLayout.y());
        ctn1.addAll(btn1);

        this.addAll(ctn1);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Details(theme, p).show();
            }
        });
    }
}
