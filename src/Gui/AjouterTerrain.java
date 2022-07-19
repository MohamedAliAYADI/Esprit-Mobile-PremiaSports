/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Service.TerrainService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;

import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import model.Terrain;

/**
 *
 * @author garbo
 */
public class AjouterTerrain extends Form {

    public AjouterTerrain() {
        this(Resources.getGlobalResources());
    }

    public AjouterTerrain(Resources resourceObjectInstance) {
        //SKELETON
        Toolbar tb = getToolbar();
        setLayout(new FlowLayout(CENTER, CENTER));
        setTitle("Formulaire Terrain");
        
        Font poppinsRegular55 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(55, Font.STYLE_PLAIN);
        Font poppinsRegular40 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(40, Font.STYLE_PLAIN);
        Font poppinsRegular35 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(35, Font.STYLE_PLAIN);
        Form previous = Display.getInstance().getCurrent();

        tb.setBackCommand("", new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
                previous.showBack();
            }
        });
        Container cnt = new Container(BoxLayout.y());

        //BODY
        //nom
        Label nomTerrain = new Label("Nom");
        nomTerrain.setUIID("CustomLabel");
        
        Style snom = nomTerrain.getUnselectedStyle();
        snom.setFont(poppinsRegular40);
        
        TextField nomtf = new TextField("", "Veuillez saisir le nom");
        nomtf.getHintLabel().setUIID("CustomHint");
        Style sttit = nomtf.getHintLabel().getUnselectedStyle();
        sttit.setFont(poppinsRegular35);
        //type
        Label typeTerrain = new Label("Type");
        typeTerrain.setUIID("CustomLabel");
        Style stype = typeTerrain.getUnselectedStyle();
        stype.setFont(poppinsRegular40);
        TextField typetf = new TextField("", "Veuillez saisir le type");
        typetf.getHintLabel().setUIID("CustomHint");
        Style stypetf = typetf.getHintLabel().getUnselectedStyle();
        stypetf.setFont(poppinsRegular35);

        //address
        Label addressTerrain = new Label("Adresse");
        addressTerrain.setUIID("CustomLabel");
        Style saddressTerrain = addressTerrain.getUnselectedStyle();
        saddressTerrain.setFont(poppinsRegular40);
        TextField addresstf = new TextField("", "Veuillez saisir l'adresse");
        addresstf.getHintLabel().setUIID("CustomHint");
        Style stfaddress = addresstf.getHintLabel().getUnselectedStyle();
        stfaddress.setFont(poppinsRegular35);

        //info
        Label infoTerrain = new Label("Infos");
        infoTerrain.setUIID("CustomLabel");
        Style sinfo = infoTerrain.getUnselectedStyle();
        sinfo.setFont(poppinsRegular40);
        TextField infotf = new TextField("", "Veuillez saisir les infos");
        infotf.getHintLabel().setUIID("CustomHint");
        Style stinfo = infotf.getHintLabel().getUnselectedStyle();
        stinfo.setFont(poppinsRegular35);

        //contact
        Label contactTerrain = new Label("Contact");
        contactTerrain.setUIID("CustomLabel");
        Style scontact = contactTerrain.getUnselectedStyle();
        scontact.setFont(poppinsRegular40);
        TextField scontacttf = new TextField("", "Veuillez saisir le contact");
        scontacttf.getHintLabel().setUIID("CustomHint");
        Style sttcontactt = scontacttf.getHintLabel().getUnselectedStyle();
        sttcontactt.setFont(poppinsRegular35);

        //add btn
        Button add_btn = new Button("Ajouter");
        add_btn.setUIID("BlackRoundFilledBtn");
        Style s_add_btn = add_btn.getUnselectedStyle();
        s_add_btn.setFont(poppinsRegular55);
        cnt.addAll(nomTerrain, nomtf,
                typeTerrain, typetf,
                addressTerrain, addresstf,
                infoTerrain, infotf,
                contactTerrain, scontacttf,
                add_btn);
        //action add btn
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                //ajout data
                if (nomtf.getText().isEmpty()
                        || typetf.getText().isEmpty()
                        || addresstf.getText().isEmpty()
                        || infotf.getText().isEmpty()
                        || scontacttf.getText().isEmpty()) {
                    //toast if empty
                    ToastBar.showErrorMessage("Veuillez remplir tous les champs", FontImage.MATERIAL_ERROR);
                } else {

                    Terrain terrain = new Terrain(nomtf.getText(),typetf.getText(),addresstf.getText(),infotf.getText(),scontacttf.getText());
                    //create new evnt
                    if (TerrainService.getInstance().addTerrain(terrain)) {

                        //success toast
                        ToastBar.showMessage("Terrain ajouté", FontImage.MATERIAL_CHECK_CIRCLE);
                        setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
                        new ListeTerrains(resourceObjectInstance).show();
                    } else {
                        //error toast
                        ToastBar.showMessage("Une erreur est survenue lors de l'ajout ", FontImage.MATERIAL_ERROR);
                    }
                }
            }
        });
        addAll(cnt);
    }

}
