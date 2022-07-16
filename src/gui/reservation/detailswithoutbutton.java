/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import Services.ReservationService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
public class detailswithoutbutton extends Container{
 public detailswithoutbutton(Resources theme, Reservation p){
     ReservationService ts = ReservationService.getInstance();
//        
        this.setLayout(BoxLayout.y());

        Label lab1 = new Label(p.getDateReservation());
       Label lab2 = new Label(String.valueOf(p.getNombredeParticipant()));
             Label lab3 = new Label(String.valueOf(p.getPrix()));
        
            Button btn1 = new Button("Supprimer la Reservation");
           btn1.addActionListener((evt) -> {
           
           Dialog.show("Voulez vous supprimer cette reservation ", "", "oui", null);
           ts.DeleteReservation(p);
        });
    Button btn3 = new Button("Modifier la reservation");
           btn3.addActionListener((evt) -> {
           
           Dialog.show("Voulez vous modifier cette reservation ", "", "oui", null);
          
        });
    
           
           
           
           
           
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab1,lab2,lab3,btn1,btn3);
        
        
        this.addAll(ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //new DetailsCatg()theme, p).show();
            }
        });}}

