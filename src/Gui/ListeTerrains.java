/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Service.TerrainService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import model.Terrain;

/**
 *
 * @author garbo
 */
public class ListeTerrains extends Form {

    public ListeTerrains() {
        this(Resources.getGlobalResources());
    }

    public ListeTerrains(Resources resourceObjectInstance) {
        // check if current user is admin
        boolean admin = true;

        setLayout(BoxLayout.y());
        //titre interface
        setTitle("Terrains");

        Form previous = Display.getInstance().getCurrent();
        Toolbar tb = getToolbar();

        //go back to home
        tb.setBackCommand("", new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, true, Integer.parseInt("200")));
                new Home(resourceObjectInstance).show();
            }
        });

        //list des terrains
        ArrayList<Terrain> terrainList;
        terrainList = TerrainService.getInstance().fetchTerrains();

        //affichage
        initGuiBuilderComponents(resourceObjectInstance, terrainList, previous, admin);
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance, ArrayList<Terrain> terrainsList, Form previous, boolean admin) {

        //font styles
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
 

        // si 0 terrain dans database
        if (terrainsList.isEmpty()) {
            setLayout(new FlowLayout(CENTER, CENTER));
            //if no offre found
            Label lempty = new Label("Aucun terrain");
            lempty.setUIID("CenterLabel");
            add(lempty);
            //si il ya des terrains dans database
        } else {
            for (Terrain terrain : terrainsList) {
                //init variables
                String nomTerrain = terrain.getNom();
                String infoTerrain = terrain.getInfos();
                String typeTerrain = terrain.getType();

                Container gui_Container_1 = new Container(new BorderLayout());
                Container gui_Container_2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container gui_Container_3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                Label nomTerrainL = new Label();
                nomTerrainL.setText(nomTerrain);

                Label typeTerrainL = new Label();
                typeTerrainL.setUIID("RedLabel");
                typeTerrainL.setText(typeTerrain.toUpperCase());

                SpanLabel infoTerr = new SpanLabel();
                infoTerr.setText(infoTerrain);
                infoTerr.setTextUIID("GreenLabel");

                gui_Container_1.addComponent(BorderLayout.EAST, gui_Container_2);
                gui_Container_1.setUIID("Margin2");
                gui_Container_2.addComponent(typeTerrainL);

                gui_Container_1.addComponent(BorderLayout.CENTER, gui_Container_3);
                gui_Container_3.addComponent(nomTerrainL);
                gui_Container_3.addComponent(infoTerr);

                //layout pour modifier ajouter et consulter ( sheet )
                Button dispTerrain = new Button();
                dispTerrain.addActionListener(e -> {
                 
                        new DetailleTerrain(resourceObjectInstance, terrain).show();
                
                });
                gui_Container_1.setLeadComponent(dispTerrain);
                add(gui_Container_1);
            }
        }
    }

}
