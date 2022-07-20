package gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Util;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import model.CoachNotes;
import model.ReservationTraining;
import service.CoachNotesService;

public class DetailsCoachNotes extends Form{
    
    public DetailsCoachNotes(Resources theme, int data) throws ParseException{
        this.setLayout(BoxLayout.y());
        this.setTitle("Detail coach note");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        
		CoachNotesService ctn = new CoachNotesService();
		List<CoachNotes> coachNotesList = ctn.getCoachNote(data);

		 //button
        Button openPdf = new Button("Open PDF");
        openPdf.addActionListener((evt) -> {
        	FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
            if(!fs.exists(fileName)) {
                Util.downloadUrlToFile("http://localhost:3015/pdfGenrator/get?coach_id=11&notes_id="+data, fileName, true);
            }
            Display.getInstance().execute(fileName);
        });	
        //1
        TextField title = new TextField("", "Title");
        //2
        TextArea description = new TextArea("");
        //1
        TextField title_1 = new TextField("", "Note 1");
        //2
        TextField description_1 = new TextField("", "Description 1");
        //1
        TextField title_2 = new TextField("", "Note 2");
        //2
        TextField description_2 = new TextField("", "Description 2");
        //1
        TextField title_3 = new TextField("", "Note 3");
        //2
        TextField description_3 = new TextField("", "Description 3");
        //1
        TextField title_4 = new TextField("", "Note 4");
        //2
        TextField description_4 = new TextField("", "Description 4");
        //1
        TextField title_5 = new TextField("", "Note 5");
        //2
        TextField description_5 = new TextField("", "Description 5");


        
        
        //button
        Button btn = new Button("Update Note");
        btn.addActionListener((evt) -> {

            String url= "http://localhost:3015/notesCoach/update?id="+data;
            JSONParser json = new JSONParser();

            String dataForm = "{\"coach_id\":"+0+
                    ",\"title\":\""+title.getText()+
                    "\",\"description\":\""+description.getText()+
                    "\",\"title_1\":\""+title_1.getText()+
                    "\",\"description_1\":\""+description_1.getText()+
                    "\",\"title_2\":\""+title_2.getText()+
                    "\",\"description_2\":\""+description_2.getText()+
                    "\",\"title_3\":\""+title_3.getText()+
                    "\",\"description_3\":\""+description_3.getText()+
                    "\",\"title_4\":\""+title_4.getText()+
                    "\",\"description_4\":\""+description_4.getText()+
                    "\",\"title_5\":\""+title_5.getText()+
                    "\",\"description_5\":\""+description_5.getText()+"\""+
                 "}";
           System.out.println(dataForm); 
           Response<Map> jsonData = Rest.post(url).body(dataForm).jsonContent().getAsJsonMap();
           ToastBar.showMessage("Saved note !", FontImage.MATERIAL_INFO);

        });
        
        
        //this
        this
        .add(openPdf)
        .add("Title: ").add(title)
        .add("Description: ").add(description)
        .add("Note 1: ").add(title_1)
        .add("Description 1: ").add(description_1)
        .add("Note 2: ").add(title_2)
        .add("Description 2: ").add(description_2)
        .add("Note 3: ").add(title_3)
        .add("Description 3: ").add(description_3)
        .add("Note 4: ").add(title_4)
        .add("Description 4: ").add(description_4)
        .add("Note 5: ").add(title_5)
        .add("Description 5: ").add(description_5)
        .add(btn);
        
        
        title.setText(coachNotesList.get(0).getTitle());
        description.setText(coachNotesList.get(0).getDescription());
        title_1.setText(coachNotesList.get(0).getTitle_1());
        description_1.setText(coachNotesList.get(0).getDescription_1());
        title_2.setText(coachNotesList.get(0).getTitle_2());
        description_2.setText(coachNotesList.get(0).getDescription_2());
        title_3.setText(coachNotesList.get(0).getTitle_3());
        description_3.setText(coachNotesList.get(0).getDescription_3());
        title_4.setText(coachNotesList.get(0).getTitle_4());
        description_4.setText(coachNotesList.get(0).getDescription_4());
        title_5.setText(coachNotesList.get(0).getTitle_5());
        description_5.setText(coachNotesList.get(0).getDescription_5());
    }

    
    void deleteReservation(int id, Resources theme) {
        String url= "http://localhost:3015/notesCoach/delete?id="+id;
        JSONParser json = new JSONParser();

       Response<Map> jsonData = Rest.post(url).jsonContent().getAsJsonMap();
       ToastBar.showMessage("Cancel reservation training session", FontImage.MATERIAL_INFO);
       new ListCoachNotes(theme).show();
    }
}