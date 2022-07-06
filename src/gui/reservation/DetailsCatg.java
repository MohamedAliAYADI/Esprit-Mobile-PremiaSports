/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import model.Reservation;

/**
 *
 * @author bouss
 */
public class DetailsCatg extends Form{
     public DetailsCatg(Resources theme, Reservation p){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Details");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ListReservation(theme).showBack();
        });
    
      this.add("");
    
      this.add(new detailswithoutbutton(theme , p));
      
     
      
    }
}
