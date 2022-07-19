/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import Service.TerrainService;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import java.util.ArrayList;
import model.Terrain;

/**
 *
 * @author anash
 */
public class SupprimerTerrain extends Form {

    SupprimerTerrain(Resources theme) {
        this.setLayout(BoxLayout.y());

        // titre de l'interface 
        this.setTitle("Supprimer terrain ");

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).show();
        });

        //init liste deroulante et affichage
        ComboBox<Terrain> listeDeroulante = new ComboBox<>();
        add(listeDeroulante);
        //list des Terrain
        ArrayList<Terrain> tarrainlist = TerrainService.getInstance().fetchTerrains();

        // si 0 Terrain
        if (tarrainlist.isEmpty()) {
            listeDeroulante.addItem(new Terrain("Aucun terrain"));

            // si il ya des Terrain
        } else {
            for (Terrain terrain : tarrainlist) {
                listeDeroulante.addItem(terrain);
            }

            Container gui_Container_2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label nomTerrainL = new Label();
            Label typeTerrainL = new Label();
            SpanLabel infoTerr = new SpanLabel();
            Label addressTerrain = new Label();
            Label contactTerrainL = new Label();

            gui_Container_2.addComponent(nomTerrainL);
            gui_Container_2.addComponent(typeTerrainL);
            gui_Container_2.addComponent(addressTerrain);
            gui_Container_2.addComponent(infoTerr);
            gui_Container_2.addComponent(contactTerrainL);

            //charger data dans les strings pour les afficher
            String nomT = "Nom :" + listeDeroulante.getSelectedItem().getNom();
            String typeT = "Type :" + listeDeroulante.getSelectedItem().getType();
            String adressT = "Adresse :" + listeDeroulante.getSelectedItem().getAdresse();
            String infoT = "Infos :" + listeDeroulante.getSelectedItem().getInfos();
            String contacT = "Contact :" + listeDeroulante.getSelectedItem().getContact();

            // affichier les valeurs 
            nomTerrainL.setText(nomT);
            typeTerrainL.setText(typeT);
            infoTerr.setText(infoT);
            addressTerrain.setText(adressT);
            contactTerrainL.setText(contacT);
            add(gui_Container_2);

            listeDeroulante.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {

                    //charger data dans les strings pour les afficher
                    String nomT = "Nom :" + listeDeroulante.getSelectedItem().getNom();
                    String typeT = "Type :" + listeDeroulante.getSelectedItem().getType();
                    String adressT = "Adresse :" + listeDeroulante.getSelectedItem().getAdresse();
                    String infoT = "Infos :" + listeDeroulante.getSelectedItem().getInfos();
                    String contacT = "Contact :" + listeDeroulante.getSelectedItem().getContact();

                    // affichier les valeurs 
                    nomTerrainL.setText(nomT);
                    typeTerrainL.setText(typeT);
                    infoTerr.setText(infoT);
                    addressTerrain.setText(adressT);
                    contactTerrainL.setText(contacT);
                }
            });
        }

        //btn supprimer
        Button supbtn = new Button("Supprimer");
        supbtn.setUIID("BlackRoundFilledBtn");

        //afichage du boutton
        add(supbtn);

        //evenemnt on click
        supbtn.addActionListener((arg0) -> {
            if (tarrainlist.isEmpty()) {
                ToastBar.showErrorMessage("Pas de terrain à supprimer", FontImage.MATERIAL_ERROR);
            } else {
                if (TerrainService.getInstance().deleteTerrain(listeDeroulante.getSelectedItem().getId())) {
                    ToastBar.showMessage("Suppression", FontImage.MATERIAL_ERROR);
                    setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("0")));
                    new ListeTerrains(theme).show();
                } else {
                    //error toast
                    ToastBar.showMessage("Une erreur est survenue lors de la suppression ", FontImage.MATERIAL_ERROR);
                }
            }
        });
    }

}
