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
import models.Reclamation;

/**
 *
 * @author user
 */
public class DetailsRec extends Form{
    
    public DetailsRec(Resources theme, Reclamation p){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Detailsss");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ListReclamation(theme).showBack();
        });
    
      
    
    this.add(new detailswithoutbuttonReclamation (theme , p));
      
     
      
    }
}
