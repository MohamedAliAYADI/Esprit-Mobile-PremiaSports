
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.sun.javafx.iio.ImageLoader;
import gui.DetailsCatg;
import models.Categorie;
import models.Produits;
import models.Reclamation;


public class McelluleRec  extends Container{
 public McelluleRec(Resources theme, Reclamation p){
    Image profilePic = theme.getImage("rec.png");
        Image mask = theme.getImage("round.png");
        mask = mask.scaledHeight(mask.getHeight() / 5* 10);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.centerAbsolute(profilePicLabel);
        Label lab2 = new Label(p.getDescription());
            Button btn = new Button("Details");
          btn.addActionListener((evt) -> {
          
         new DetailsRec(theme,p).show();
      });
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab2,btn);
        
        this.addAll(sidemenuTop,ctn);
        this.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsRec(theme, p).show();
            }
        });

    
}
}