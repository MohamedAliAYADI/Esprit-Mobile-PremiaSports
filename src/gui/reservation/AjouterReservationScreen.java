/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reservation;

import Services.PromotionService;
import Services.ReservationService;
import com.codename1.components.Switch;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import gui.Home;
import java.util.ArrayList;
import java.util.List;
import model.Promotion;
import model.Reservation;


/**
 *
 * @author bouss
 */
public class AjouterReservationScreen extends Form {

    public AjouterReservationScreen(Resources theme) {

        Reservation R = new Reservation();

        this.setLayout(BoxLayout.y());
        this.setTitle("Reservation");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        Picker piker = new Picker();
        Label n1 = new Label("Date Rservation :");
        Picker DateRes = new Picker();
        TextField nbrP = new TextField("", "Nombre de Participant");
        Label n2 = new Label("Nombre de Participant :");
        Label n3 = new Label("Prix :");
        TextField Prix = new TextField("", "Prix");
        Label n4 = new Label("promotion:");
        
        ComboBox cb=new ComboBox();
        PromotionService ps = new PromotionService();
        List<Promotion> promotions = ps.fetchPromotion();
        for (Promotion p : promotions) {
            cb.addItem(p.getType() + " - " + p.getPromo() + "%");
        }
        cb.addSelectionListener((oldSelected, newSelected) -> {
            System.out.println("****** oldSelected = " + promotions.get(oldSelected));
            System.out.println("************ newSelected = " + promotions.get(newSelected));
        });
        
        Button btn1 = new Button("Valider");
        btn1.addActionListener((evt) -> {

            //create prom
            Promotion pr = new Promotion();
         
            //create reservation
            Reservation reservation = new Reservation(DateRes.getText(),
                    Integer.parseInt(nbrP.getText()), Integer.parseInt(Prix.getText()), pr);
            if (ReservationService.getInstance().addReservation(reservation)) {
                new DetailleReservationScreen(theme, reservation).show();
            }

        });

        this.addAll(n1,DateRes,n2, nbrP, n3, Prix, n4, cb ,btn1);

    }
}
