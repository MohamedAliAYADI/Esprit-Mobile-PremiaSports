/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import Service.TerrainService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import model.Terrain;

/**
 *
 * @author anash
 */
public class ModifierTerrain extends Form {

    ModifierTerrain(Resources theme) {
        this.setLayout(BoxLayout.y());

        //titre interrface
        this.setTitle("Modifier terrain ");

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).show();
        });

        // liste deroulante pour charger les Terrain a modifier + affichage
        ComboBox<Terrain> listDeroulante = new ComboBox<>();
        add(listDeroulante);

        // les Terrain stockees dans la base
        ArrayList<Terrain> terrianlist = TerrainService.getInstance().fetchTerrains();

        // si '0 Terrain
        if (terrianlist.isEmpty()) {
            listDeroulante.addItem(new Terrain("Aucun offre"));
        } // si 1 --n Terrain
        else {
            for (Terrain terrain : terrianlist) {
                //ajouter les Terrain dans la liste deroulante
                listDeroulante.addItem(terrain);
            }

            Container cnt = new Container(BoxLayout.y());

            //nom
            Label nomTerrain = new Label("Nom");
            nomTerrain.setUIID("CustomLabel");

            TextField tfNom = new TextField(listDeroulante.getSelectedItem().getNom(), "Veuillez saisir le nom");
            tfNom.getHintLabel().setUIID("CustomHint");

            //type
            Label typeTerrain = new Label("Type");
            typeTerrain.setUIID("CustomLabel");

            TextField tfType = new TextField(listDeroulante.getSelectedItem().getType(), "Veuillez saisir le type");
            tfType.getHintLabel().setUIID("CustomHint");

            //address
            Label addressTerrain = new Label("Adresse");
            addressTerrain.setUIID("CustomLabel");

            TextField tfAddr = new TextField(listDeroulante.getSelectedItem().getAdresse(), "Veuillez saisir l'adresse");
            tfAddr.getHintLabel().setUIID("CustomHint");

            //infos
            Label infTerrain = new Label("Infos");
            infTerrain.setUIID("CustomLabel");

            TextField tfInf = new TextField(listDeroulante.getSelectedItem().getInfos(), "Veuillez saisir les infos");
            tfInf.getHintLabel().setUIID("CustomHint");

            //contact
            Label conTerrain = new Label("Contact");
            conTerrain.setUIID("CustomLabel");

            TextField tfcon = new TextField(listDeroulante.getSelectedItem().getContact(), "Veuillez saisir le contact");
            tfcon.getHintLabel().setUIID("CustomHint");

            //add btn
            Button update_btn = new Button("Modifier");
            update_btn.setUIID("BlackRoundFilledBtn");

            cnt.addAll(nomTerrain, tfNom,
                    typeTerrain, tfType,
                    addressTerrain, tfAddr,
                    infTerrain, tfInf,
                    conTerrain, tfcon,
                    update_btn);

            tfNom.setText(listDeroulante.getSelectedItem().getNom());
            tfType.setText(listDeroulante.getSelectedItem().getType());
            tfAddr.setText(listDeroulante.getSelectedItem().getAdresse());
            tfInf.setText(listDeroulante.getSelectedItem().getInfos());
            tfcon.setText(listDeroulante.getSelectedItem().getContact());

            addAll(cnt);

            //action add btn
            update_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    //update data
                    if (tfNom.getText().isEmpty()
                            || tfType.getText().isEmpty()
                            || tfAddr.getText().isEmpty()
                            || tfInf.getText().isEmpty()
                            || tfcon.getText().isEmpty()) {
                        //toast if empty
                        ToastBar.showErrorMessage("Veuillez remplir tous les champs", FontImage.MATERIAL_ERROR);
                    } else {
                        Terrain terrain = new Terrain();
                        terrain.setId(listDeroulante.getSelectedItem().getId());
                        terrain.setNom(tfNom.getText());
                        terrain.setType(tfType.getText());
                        terrain.setAdresse(tfAddr.getText());
                        terrain.setInfos(tfInf.getText());
                        terrain.setContact(tfcon.getText());
                        if (TerrainService.getInstance().updateTerrain(terrain)) {
                            //success toast
                            ToastBar.showMessage("Terrain modifié", FontImage.MATERIAL_CHECK_CIRCLE);
                            setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
                            new ListeTerrains().show();
                        } else {
                            //error toast
                            ToastBar.showMessage("Une erreur est survenue lors de la modification", FontImage.MATERIAL_ERROR);
                        }
                    }
                }
            });

            listDeroulante.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    tfNom.setText(listDeroulante.getSelectedItem().getNom());
                    tfType.setText(listDeroulante.getSelectedItem().getType());
                    tfAddr.setText(listDeroulante.getSelectedItem().getAdresse());
                    tfInf.setText(listDeroulante.getSelectedItem().getInfos());
                    tfcon.setText(listDeroulante.getSelectedItem().getContact());
                }
            });

        }

    }

}
