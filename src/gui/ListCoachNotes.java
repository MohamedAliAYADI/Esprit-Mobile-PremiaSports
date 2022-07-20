package gui;

import java.util.List;
import java.util.Map;

import com.codename1.components.MultiButton;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import model.CoachNotes;
import model.ReservationTraining;
import service.CoachNotesService;
import service.ReservationTrainingService;

public class ListCoachNotes extends Form{
    
    public ListCoachNotes(Resources theme){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Liste Notes Coach");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
   
        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 3), false); 
		Image roundMask = Image.createImage(placeholder.getWidth(), placeholder.getHeight(), 0xff000000);
		Graphics gr = roundMask.getGraphics();
		gr.setColor(0xffffff);
		gr.fillArc(0, 0, placeholder.getWidth(), placeholder.getHeight(), 0, 360);
		
		URLImage.ImageAdapter ada = URLImage.createMaskAdapter(roundMask);
		Image imgg = URLImage.createToStorage(placeholder, "fileNameInStorage", "https://i.ibb.co/jb41qjN/takses.png", ada);

		CoachNotesService ctn = new CoachNotesService();
		List<CoachNotes> coachNotesList = ctn.fetchCoachNotes(0);

		for(int i=0; i<coachNotesList.size();i++) {
        	MultiButton mb = new MultiButton(coachNotesList.get(i).getTitle());
        	int idd = i;
        	mb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
						new DetailsCoachNotes(theme, coachNotesList.get(idd).getId()).show();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            });
        	mb.setIcon(imgg);
        	mb.setTextLine2("Details");
        	list.add(mb);
        }
        
        this.add(list);
    }
    
}
