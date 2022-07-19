/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import Service.AvisService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import model.Avis;
import model.Terrain;

/**
 *
 * @author anash
 */
public class EditAvis extends Form{

    public EditAvis(Resources resourceObjectInstance, Terrain terrain, Avis avis) {
        int currentuser = 1;

        setLayout(BoxLayout.y());
        setTitle("Modifier avis");
        Toolbar tb = getToolbar();
        tb.setBackCommand("", new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
                new DetailleTerrain(resourceObjectInstance,terrain).show();

            }
        });
        
        Font poppinsRegular40 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(40, Font.STYLE_PLAIN);
        
        TextArea comnt = new TextArea(avis.getCommentaire());
        comnt.getAllStyles().setFont(poppinsRegular40);

        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        starRank.setProgress(avis.getNote());
        
        Button commentBtn = new Button("Modifier");
        commentBtn.setUIID("BlackRoundFilledBtn");
        commentBtn.addActionListener((arg0) -> {
            //ajout data
            if (comnt.getText().isEmpty() || starRank.getProgress() == 0) {
                //toast if empty
                ToastBar.showErrorMessage("Veuillez remplir le champ et donner une note", FontImage.MATERIAL_ERROR);
            } else {

                Avis newAvis = new Avis(avis.getId(),comnt.getText(), starRank.getProgress(), currentuser, terrain.getId());
                //create new evnt
                if (AvisService.getInstance().updateAvis(newAvis)) {

                    //success toast
                    ToastBar.showMessage("Avis modifié", FontImage.MATERIAL_CHECK_CIRCLE);
                    setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("0")));
                    new DetailleTerrain(resourceObjectInstance, terrain).show();
                } else {
                    //error toast
                    ToastBar.showMessage("Une erreur est survenue lors de la modification ", FontImage.MATERIAL_ERROR);
                }
            }
        });

        add(comnt);
        add(FlowLayout.encloseCenter(starRank));

        add(commentBtn);

    }
     private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
}
