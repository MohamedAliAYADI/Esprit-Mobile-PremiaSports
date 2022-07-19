/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import Service.TerrainService;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.Date;
import model.Terrain;

/**
 *
 * @author anash
 */
public class EditTerrain extends Form {

    public EditTerrain(Terrain terrain, Resources resourceObjectInstance) {
        Toolbar tb = getToolbar();
        setLayout(new FlowLayout(CENTER, CENTER));
        //titre interface
        setTitle("Modifier le terrain");
        
        //fontstyle
        Font poppinsRegular55 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(55, Font.STYLE_PLAIN);
        Font poppinsRegular40 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(40, Font.STYLE_PLAIN);
        Font poppinsRegular35 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(35, Font.STYLE_PLAIN);
        
        //back to list
        tb.setBackCommand("", e -> {
            setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
            new ListeTerrains().show();
        });
        
        Container cnt = new Container(BoxLayout.y());
        
        //nom
        Label nomTerrain = new Label("Nom");
        nomTerrain.setUIID("CustomLabel");
        Style s_lnom = nomTerrain.getUnselectedStyle();
        s_lnom.setFont(poppinsRegular40);

        TextField tfNom = new TextField(terrain.getNom(), "Veuillez saisir le nom");
        tfNom.getHintLabel().setUIID("CustomHint");
        Style s_tnomHint = tfNom.getHintLabel().getUnselectedStyle();
        s_tnomHint.setFont(poppinsRegular35);

        //type
        Label typeTerrain = new Label("Type");
        typeTerrain.setUIID("CustomLabel");
        Style s_ltype = typeTerrain.getUnselectedStyle();
        s_ltype.setFont(poppinsRegular40);

        TextField tfType = new TextField(terrain.getType(), "Veuillez saisir le type");
        tfType.getHintLabel().setUIID("CustomHint");
        Style s_ttypeHint = tfType.getHintLabel().getUnselectedStyle();
        s_ttypeHint.setFont(poppinsRegular35);

        //address
        Label addressTerrain = new Label("Adresse");
        addressTerrain.setUIID("CustomLabel");
        Style s_ltadd = addressTerrain.getUnselectedStyle();
        s_ltadd.setFont(poppinsRegular40);

        TextField tfAddr = new TextField(terrain.getAdresse(), "Veuillez saisir l'adresse");
        tfAddr.getHintLabel().setUIID("CustomHint");
        Style s_addressHint = tfAddr.getHintLabel().getUnselectedStyle();
        s_addressHint.setFont(poppinsRegular35);

        //infos
        Label infTerrain = new Label("Infos");
        infTerrain.setUIID("CustomLabel");
        Style s_ltinf = infTerrain.getUnselectedStyle();
        s_ltinf.setFont(poppinsRegular40);

        TextField tfInf = new TextField(terrain.getInfos(), "Veuillez saisir les infos");
        tfInf.getHintLabel().setUIID("CustomHint");
        Style s_infHint = tfInf.getHintLabel().getUnselectedStyle();
        s_infHint.setFont(poppinsRegular35);

        //contact
        Label conTerrain = new Label("Contact");
        conTerrain.setUIID("CustomLabel");
        Style s_ltcon = conTerrain.getUnselectedStyle();
        s_ltcon.setFont(poppinsRegular40);

        TextField tfcon = new TextField(terrain.getContact(), "Veuillez saisir le contact");
        tfcon.getHintLabel().setUIID("CustomHint");
        Style s_conHint = tfcon.getHintLabel().getUnselectedStyle();
        s_conHint.setFont(poppinsRegular35);

        //add btn
        Button update_btn = new Button("Modifier");
        update_btn.setUIID("BlackRoundFilledBtn");
        Style s_update_btn = update_btn.getUnselectedStyle();
        s_update_btn.setFont(poppinsRegular55);
        cnt.addAll(nomTerrain, tfNom,
                typeTerrain, tfType,
                addressTerrain, tfAddr,
                infTerrain, tfInf,
                conTerrain, tfcon,
                update_btn);
        tfNom.setText(terrain.getNom());
        tfType.setText(terrain.getType());
        tfAddr.setText(terrain.getAdresse());
        tfInf.setText(terrain.getInfos());
        tfcon.setText(terrain.getContact());

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
                    //create new doc
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
        addAll(cnt);
    }

}
