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
import models.Reclamation;
import services.CategoriService;
import services.ProduitService;
import services.ReclamationService;

/**
 *
 * @author user
 */
public class ListReclamation extends Form {

  
    public ListReclamation (Resources theme){

       this.setLayout(BoxLayout.y());
        this.setTitle("Liste des Reclamation");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeFormClient(theme).showBack();
        });
        
        List<Reclamation> cat = ReclamationService.getInstance().fetchRec();
        
        for (Reclamation item : cat) {
       this.add(new McelluleRec (theme, item));
        }

    }

}
