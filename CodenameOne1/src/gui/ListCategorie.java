/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;
import models.Categorie;
import models.Produits;
import services.CategoriService;
import services.ProduitService;

/**
 *
 * @author user
 */
public class ListCategorie extends Form {

  
    public ListCategorie (Resources theme){

       this.setLayout(BoxLayout.y());
        this.setTitle("Liste des Categories");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm(theme).showBack();
        });
        
        List<Categorie> cat = CategoriService.getInstance().fetchcat();
        
        for (Categorie item : cat) {
           this.add(new mCelluleCat(theme, item));
        }

    }

}
