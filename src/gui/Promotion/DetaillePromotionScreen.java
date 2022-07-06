/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Promotion;
;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import model.Promotion;


/**
 *
 * @author bouss
 */
public class DetaillePromotionScreen extends Form{
    
    public DetaillePromotionScreen(Resources theme, Promotion P){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Promotion Informations");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
         //   new SigninScreen(theme).showBack();
        });
        
        this.add(new SpanLabel(P.toString()));
    }
    
}
