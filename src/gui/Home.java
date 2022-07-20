/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.PromotionService;
import Services.ReservationService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import gui.Promotion.AjouterPromotionScreen;
import gui.reservation.AjouterReservationScreen;
import gui.reservation.ListReservation;
import model.Reservation;
import service.PromotionServices;
import service.Reservationservices;

/**
 *
 * @author bouss
 */
public class Home extends Form {

    public Home(Resources theme) {

        this.setLayout(BoxLayout.y());
        this.setTitle("Premia Sports");

        this.getToolbar().addCommandToLeftSideMenu("Ajouter Reservation", null, (evt) -> {
            new AjouterReservationScreen(theme).show();
        });
        this.getToolbar().addCommandToLeftSideMenu("Ajouter Promotion", null, (evt) -> {
            new AjouterPromotionScreen(theme).show();
        });
        getToolbar().addMaterialCommandToSideMenu("Liste des Reservation", FontImage.MATERIAL_SELECT_ALL, e -> new ListReservation(theme).show());

        this.getToolbar().addCommandToLeftSideMenu("Liste des Promotion", null, (evt) -> {
            PromotionService ps = new PromotionService();
            this.add(new SpanLabel(ps.fetchPromotion().toString()));
        });

                this.getToolbar().addCommandToLeftSideMenu("Ajouter Reservation", null, (evt) -> {
            new AddReservation(theme).show();
        });

        this.getToolbar().addCommandToLeftSideMenu("Liste de reservation", null, (evt) -> {

            new ListeReservation(theme).show();
        });

        this.getToolbar().addCommandToLeftSideMenu("Add Notes Coach", null, (evt) -> {

            new AddCoachNotes(theme).show();
        });
        
        this.getToolbar().addCommandToLeftSideMenu("Liste Notes Coach", null, (evt) -> {

            new ListCoachNotes(theme).show();
        });
        
        this.getToolbar().addCommandToLeftSideMenu("General Chat", null, (evt) -> {

            new GeneralChat(theme).show();
        });

        this.getToolbar().addCommandToOverflowMenu("Settings", null, (evt) -> {
        });
        this.getToolbar().addCommandToOverflowMenu("Logout", null, (evt) -> {
        });
    }
}
