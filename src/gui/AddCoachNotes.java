package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;
import com.codename1.components.ToastBar;
import com.codename1.io.JSONParser;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

public class AddCoachNotes extends Form{
    
    @SuppressWarnings("deprecation")
	public AddCoachNotes(Resources theme){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Add coach note");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
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
        Button btn = new Button("Add Note");
        btn.addActionListener((evt) -> {

            String url= "http://localhost:3015/notesCoach/add";
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
        
    }
    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
    
}

