/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import models.Categorie;
import models.Produits;

/**
 *
 * @author user
 */
public class Details extends Form{
    
    public Details(Resources theme, Produits p){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Details");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ListProduit(theme).showBack();
        });
    
    
    
      this.add(new detailswithoutbutton1prod(theme , p));
      
     
      
    }
}
