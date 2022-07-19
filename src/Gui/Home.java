/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author garbo
 */
public class Home extends Form {

    public Home(Resources res) {

        boolean admin = true;

        setTitle("Premiasport");
        setScrollableY(true);
        setLayout(new FlowLayout(LEFT, TOP));
        setLayout(new FlowLayout(CENTER, CENTER));
        if (admin) {
            this.getToolbar().addCommandToLeftSideMenu("Ajouter Terrain", null, (evt) -> {
                new AjouterTerrain(res).show();
            });

            this.getToolbar().addCommandToLeftSideMenu("Supprimer Terrain", null, (evt) -> {
                new SupprimerTerrain(res).show();

            });

            this.getToolbar().addCommandToLeftSideMenu("Modifier Terrain", null, (evt) -> {
                new ModifierTerrain(res).show();
            });
        }
        this.getToolbar().addCommandToLeftSideMenu("Afficher Terrain", null, (evt) -> {
            new ListeTerrains(res).show();

        });

        this.getToolbar().addCommandToOverflowMenu("Settings", null, (evt) -> {
        });
        this.getToolbar().addCommandToOverflowMenu("Logout", null, (evt) -> {
        });

    }
}
