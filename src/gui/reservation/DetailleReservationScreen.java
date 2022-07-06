/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import model.Reservation;

/**
 *
 * @author bouss
 */
public class DetailleReservationScreen extends Form{
    
    public DetailleReservationScreen(Resources theme, Reservation r){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Revservation Informations");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
         //   new SigninScreen(theme).showBack();
        });
        
        this.add(new SpanLabel(r.toString()));
    }
    
}
