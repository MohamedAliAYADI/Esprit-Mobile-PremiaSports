/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import model.Reservation;

/**
 *
 * @author bouss
 */

    public class mCelluleCat  extends Container{
 public mCelluleCat(Resources theme, Reservation p){
//      
//        
        this.setLayout(BoxLayout.y());
     
        
        
      
        Label lab1 = new Label(p.getDateReservation());
       Label lab2 = new Label(String.valueOf(p.getNombredeParticipant()));
            Button btn = new Button("Details");
          btn.addActionListener((evt) -> {
          
         new DetailsCatg(theme, p).show();
      });
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab1,btn);
          
        this.addAll(ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsCatg(theme, p).show();
            }
        });
           
 
    
}
}

