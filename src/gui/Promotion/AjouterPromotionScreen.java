/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Promotion;

import Services.PromotionService;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BASELINE;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import gui.Home;
import model.Promotion;


/**
 *
 * @author bouss
 */
public class AjouterPromotionScreen extends Form {

    public AjouterPromotionScreen(Resources theme) {
        this.setLayout(BoxLayout.y());
        this.setTitle("Promotion");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        Label n1 = new Label("Type Promotion :");
        TextField TypeP = new TextField("", "Type Promotion");
        Label n2 = new Label("Promotion :");
        TextField Promo = new TextField("", "Promotion");

        Button btn1 = new Button("Valider");
        btn1.addActionListener((evt2) -> {
            
            Promotion promotion = new Promotion(TypeP.getText(),
                    Integer.parseInt(Promo.getText()));
     
             if (PromotionService.getInstance().addPromotion(promotion)) {
                new DetaillePromotionScreen(theme, promotion).show();
            }
                
        });

        this.addAll(n1, TypeP, n2, Promo, btn1);
    }

}