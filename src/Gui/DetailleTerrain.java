/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Service.AvisService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import static com.codename1.ui.Image.createImage;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import model.Avis;
import model.Terrain;

/**
 *
 * @author garbo
 */
public class DetailleTerrain extends Form {

    public DetailleTerrain(Resources resourceObjectInstance, Terrain terrain) {
        int currentuser = 1;
        boolean admin=true;

        setLayout(BoxLayout.y());
        // form title
        setTitle(terrain.getNom().toUpperCase());
        Toolbar tb = getToolbar();
        tb.setBackCommand("", new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
                new ListeTerrains(resourceObjectInstance).show();

            }
        });
       
        Font poppinsRegular40 = Font.createTrueTypeFont("regular", "Poppins-Regular.ttf").
                derive(40, Font.STYLE_PLAIN);
        

        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label type = new Label(terrain.getType());
        type.setTextPosition(RIGHT);
        type.getStyle().setFgColor(0xFF0000);

        SpanLabel info = new SpanLabel("Infos: " + terrain.getInfos());
        info.getStyle().setFont(poppinsRegular40);

        SpanLabel contact = new SpanLabel("Contact: " + terrain.getContact());
        contact.getStyle().setFont(poppinsRegular40);

        SpanLabel addres = new SpanLabel("Adresse: " + terrain.getAdresse());
        addres.getStyle().setFont(poppinsRegular40);

        /*
            Container header
         */
        Container cardTitle = new Container();
        cardTitle.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Style cardTitleStyles = cardTitle.getUnselectedStyle();
        
        cardTitleStyles.setBgColor(0xf3f3f3);
        cardTitleStyles.setBgTransparency(255);
        cardTitleStyles.setBorder(RoundRectBorder.create().
                bottomOnlyMode(true).
                strokeColor(0x858585).
                strokeOpacity(999999));
        /*
            Container content
         */
        Container cardContent = new Container();
        cardContent.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Container cardInfo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Style cardContentStyles = cardContent.getUnselectedStyle();
        cardContentStyles.setBgColor(0xf3f3f3);
        cardContentStyles.setBgTransparency(255);
        cardContentStyles.setMarginBottom(30);

        cardContentStyles.setMarginLeft(10);
        cardContentStyles.setPadding(10, 10, 20, 20);


        Font materialFont = FontImage.getMaterialDesignFont();
        materialFont = materialFont.derive(60, Font.STYLE_PLAIN);

        Container terraininfo = new Container();
        terraininfo.getStyle().setMarginTop(10);
        terraininfo.setLayout(new BorderLayout());
        
        ImageViewer n = null;
        try {
            if (terrain.getType().toLowerCase().contains("foot")) {
                n = new ImageViewer(Image.createImage("/foot.jpg").fill(400, 400));
            }  else if (terrain.getType().toLowerCase().contains("tennis")) {
                n = new ImageViewer(Image.createImage("/tennis.jpg").fill(400, 400));
            } else if (terrain.getType().toLowerCase().contains("basket")) {
                n = new ImageViewer(Image.createImage("/basket.jpg").fill(400, 400));
            } else if (terrain.getType().toLowerCase().contains("golf")) {
                n = new ImageViewer(Image.createImage("/golf.jpg").fill(400, 400));
            } else if (terrain.getType().toLowerCase().contains("hand")) {
                n = new ImageViewer(Image.createImage("/hand.jpg").fill(400, 400));
            } else {
                n = new ImageViewer(Image.createImage("/random.jpg").fill(400, 400));
            }

        } catch (IOException ex) {
        }
        Container tis = new Container();
        tis.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tis.add(n);

        terraininfo.add(BorderLayout.WEST, tis);
        Container t = new Container();
        t.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        t.add(type);
        t.add(addres);
        t.add(info);
        t.add(contact);

        terraininfo.add(BorderLayout.CENTER, t);


        cardInfo.add(terraininfo);
        cardContent.add(cardInfo);
        cnt.add(cardTitle);
        cnt.add(cardContent);
        add(cnt);
        
        
        //avis
        TextArea comnt = new TextArea("Ajouter un avis");
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

        Button commentBtn = new Button("Ajouter");
        commentBtn.setUIID("BlackRoundFilledBtn");
        commentBtn.addActionListener((arg0) -> {
            //ajout data
            if (comnt.getText().isEmpty() || starRank.getProgress() == 0) {
                //toast if empty
                ToastBar.showErrorMessage("Veuillez remplir le champ et donner une note", FontImage.MATERIAL_ERROR);
            } else {

                Avis avis = new Avis(comnt.getText(), starRank.getProgress(), currentuser, terrain.getId());
                //create new evnt
                if (AvisService.getInstance().addAvis(avis)) {

                    //success toast
                    ToastBar.showMessage("Avis ajouté", FontImage.MATERIAL_CHECK_CIRCLE);
                    setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("0")));
                    new DetailleTerrain(resourceObjectInstance, terrain).show();
                } else {
                    //error toast
                    ToastBar.showMessage("Une erreur est survenue lors de l'ajout ", FontImage.MATERIAL_ERROR);
                }
            }
        });

        add(comnt);
        add(FlowLayout.encloseCenter(starRank));

        add(commentBtn);

        
        ArrayList<Avis> avisList;
        avisList = AvisService.getInstance().fetchAvis(terrain.getId());
        
        for (Avis avis : avisList) {
            /**
             * * card
             */
            Container card = new Container(BoxLayout.y());
            card.getStyle().setBorder(RoundRectBorder.create().shadowOpacity(100));
            card.getStyle().setMarginTop(10);
            /**
             * * card header
             */
            Container cardTitleAvis = new Container();
            cardTitleAvis.setLayout(new BorderLayout());
            Style cardTitleStyless = cardTitleAvis.getUnselectedStyle();
            cardTitleStyless.setBgColor(0x45B1E7);
            cardTitleStyless.setBgTransparency(255);
            cardTitleStyless.setMarginRight(30);
            cardTitleStyless.setMarginLeft(30);
            cardTitleStyless.setBorder(RoundRectBorder.create().
                    bottomOnlyMode(false).
                    strokeColor(0).
                    strokeOpacity(120));
            cardTitleStyless.setPadding(20, 20, 10, 10);
            
            /**
             * username
             */
            
            Label l_name = new Label(avis.getIdUser() + "nom");
            Font poppinsname = Font.createTrueTypeFont("dss", "Poppins-Regular.ttf").
                    derive(50, Font.STYLE_PLAIN);
            Style l_nameStyles = l_name.getUnselectedStyle();
            l_nameStyles.setFgColor(0xffffff);
            l_nameStyles.setFont(poppinsname);
            cardTitleAvis.add(BorderLayout.CENTER, l_name);

            /**
             * user mig
             */
            Image pic;
            try {
                pic = createImage("/userimg.png").fill(100, 100);
                Image roundMask = Image.createImage(pic.getWidth(), pic.getHeight(), 0x000000);
                Graphics gr = roundMask.getGraphics();
                gr.fillArc(0, 0, pic.getWidth(), pic.getWidth(), 0, 360);
                Object mask = roundMask.createMask();
                pic = pic.applyMask(mask);
                ImageViewer iv_user = new ImageViewer(pic);
                cardTitleAvis.add(BorderLayout.WEST, iv_user);

            } catch (Exception ex) {
            }

            /**
             * delete & add icons
             */
            if (avis.getIdUser() == currentuser || admin) {
                Font del = FontImage.getMaterialDesignFont();
                del = del.derive(80, Font.STYLE_PLAIN);
              
                
                Button btn_del = new Button();
                btn_del.setUIID("WLabelRight");
                btn_del.setIcon(FontImage.create("\ue92e", btn_del.getUnselectedStyle(), del));
               
                
                Button btn_edit = new Button();
                btn_edit.setUIID("wLabel");
                btn_edit.setIcon(FontImage.create("\ue3c9", btn_edit.getUnselectedStyle(), del));
                Container con = new Container();
                con.setLayout(new BoxLayout(BoxLayout.X_AXIS));
                con.add(btn_edit).add(btn_del);
                cardTitleAvis.add(BorderLayout.EAST, con);

                /**
                 * delete pub
                 */
                btn_del.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        /*  try {
                        Thread.sleep(2000l);
                    } catch (InterruptedException ex) {
                    }*/
                        Dialog dialog = new Dialog(BoxLayout.y());
                        dialog.setUIID("Container"); // this line has no effect, the outside dialog component is still visible
                        Style style = dialog.getDialogStyle();
                        style.setMargin(5, 5, 5, 5); // adding some margin between contentpane and Dailog container, to be more obvious
                        dialog.setDisposeWhenPointerOutOfBounds(true);
                        // body
                        SpanLabel bodyLabel = new SpanLabel("Êtes-vous sûr de vouloir supprimer cet avis?");
                        dialog.add(bodyLabel);
                        // confirm supp button
                        Button confirm_btn = new Button("Oui");
                        confirm_btn.setUIID("IndianredRoundFilledBtn"); //change
                        confirm_btn.addActionListener(e2 -> {
                            //delete method
                            AvisService.getInstance().deleteAvis(avis.getId());
                            // card.setHidden(true);
                            ToastBar.Status deltStat = ToastBar.getInstance().createStatus();
                            deltStat.setMessage("Avis supprimé");
                            deltStat.show();
                            deltStat.setExpires(2000);
                            dialog.dispose();
                            try {
                                setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("0")));
                                new DetailleTerrain(resourceObjectInstance, terrain).show();
                            } catch (Exception ex) {
                                System.out.println(ex.toString());
                            }
                        });
                        // deny button
                        Button deny_btn = new Button("Non");
                        deny_btn.setUIID("IndianredRoundBtn");
                        deny_btn.addActionListener(e3 -> {
                            dialog.dispose();
                        });
                        dialog.add(GridLayout.encloseIn(2, confirm_btn, deny_btn));
                        dialog.show();

                    }
                });

                /**
                 * edit pub
                 */
                btn_edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        new EditAvis(resourceObjectInstance, terrain, avis).show();

                    }
                });
            }

            /**
             * * card body
             */
            Container cardBody = new Container();
            cardBody.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            Style cardBodyStyles = cardBody.getUnselectedStyle();
            cardBodyStyles.setBgColor(0x45B1E7);
            cardBodyStyles.setBgTransparency(255);

            cardBodyStyles.setPadding(20, 20, 10, 10);
            /**
             * comment description
             */
            TextArea contentCom = new TextArea(
                    avis.getCommentaire()
            );
            contentCom.setEditable(false);
            contentCom.setEnabled(false);

            Stroke borderStrokepubdesc = new Stroke(0, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            contentCom.getAllStyles().setBorder(RoundRectBorder.create().
                    strokeColor(2).
                    strokeOpacity(120).
                    stroke(borderStrokepubdesc));

            contentCom.getAllStyles().setFgColor(0xffffff);
            cardBody.add(contentCom);
            Font poppinsdesc = Font.createTrueTypeFont("dss", "Poppins-Regular.ttf").
                    derive(40, Font.STYLE_PLAIN);
            contentCom.getAllStyles().setFont(poppinsdesc);

            
            Container blacklines = new Container();
            blacklines.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            Style blacklinesStyles = blacklines.getUnselectedStyle();
            blacklinesStyles.setBgColor(0xffffff);
            blacklinesStyles.setBgTransparency(255);
            blacklinesStyles.setMarginRight(30);
            blacklinesStyles.setMarginLeft(30);
            blacklinesStyles.setPadding(1, 1, 0, 0);

            blacklines.add(cardBody);

            /**
             * * card footer
             */
            Container cardFooter = new Container();
            cardFooter.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            Style cardFooterStyles = cardFooter.getUnselectedStyle();
            cardFooterStyles.setBgColor(0x45B1E7);
            cardFooterStyles.setBgTransparency(255);
            cardFooterStyles.setMarginRight(30);
            cardFooterStyles.setMarginLeft(30);
            cardFooterStyles.setMarginBottom(30);
            cardFooterStyles.setBorder(RoundRectBorder.create().
                    topOnlyMode(false).
                    strokeColor(0).
                    strokeOpacity(120));
            cardFooterStyles.setPadding(20, 20, 10, 10);
            /**
             * com rate
             */
            Slider starRank2 = new Slider();
            starRank2.setEditable(false);
            starRank2.setMinValue(0);
            starRank2.setMaxValue(5);
            Font fnts = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                    derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
            Style ss = new Style(0xffff33, 0, fnts, (byte) 0);
            Image fullStarss = FontImage.createMaterial(FontImage.MATERIAL_STAR, ss).toImage();
            ss.setOpacity(100);
            ss.setFgColor(0);
            Image emptyStars = FontImage.createMaterial(FontImage.MATERIAL_STAR, ss).toImage();
            initStarRankStyle(starRank2.getSliderEmptySelectedStyle(), emptyStars);
            initStarRankStyle(starRank2.getSliderEmptyUnselectedStyle(), emptyStars);
            initStarRankStyle(starRank2.getSliderFullSelectedStyle(), fullStarss);
            initStarRankStyle(starRank2.getSliderFullUnselectedStyle(), fullStarss);
            starRank2.setPreferredSize(new Dimension(fullStarss.getWidth() * 5, fullStarss.getHeight()));
            starRank2.setProgress(avis.getNote());
            cardFooter.add(FlowLayout.encloseCenter(starRank2));
            card.add(cardTitleAvis);
            card.add(blacklines);
            card.add(cardFooter);
            add(card);
        }

    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

}
