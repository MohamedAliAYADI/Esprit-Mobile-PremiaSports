/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import Services.ReservationService;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import gui.Home;
import java.util.List;
import model.Reservation;

/**
 *
 * @author bouss
 */
public class ListReservation extends Form {

  
    public ListReservation (Resources theme){

       this.setLayout(BoxLayout.y());
        this.setTitle("Liste des Reservations");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        
        List<Reservation> res = ReservationService.getInstance().fetchReservation();
        
        for (Reservation item : res) {
           this.add(new mCelluleCat(theme, item));
        }

    }

}