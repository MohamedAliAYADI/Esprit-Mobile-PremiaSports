/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.JSONParser;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import model.Personne;
import model.ReservationTraining;

/**
 *
 * @author dali
 */
public class DetailsScreen extends Form{
    
    public DetailsScreen(Resources theme, int data) throws ParseException{
        this.setLayout(BoxLayout.y());
        this.setTitle("Detail reservation");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        
        String myUrl = "http://localhost:3015/reservationTrainning/getById?id="+data;
        Response<Map> jsonDataRes = Rest.get(myUrl).acceptJson().jsonContent().getAsJsonMap();
        
		List<Map<String, Object>> listTraining = (List<Map<String, Object>>) jsonDataRes.getResponseData().get("root");
		List<ReservationTraining> reservations = new ArrayList<>();
		for (Map<String, Object> item : listTraining) {
			ReservationTraining rt = new ReservationTraining();
			rt.setTitle((String) item.get("title"));
			rt.setType((String) item.get("type"));
			rt.setStart_time((String) item.get("start_time"));
			rt.setEnd_time((String) item.get("end_time"));
			rt.setState((String) item.get("state"));
			reservations.add(rt);
		}
		System.out.print(reservations);
		
        //1
        TextField title = new TextField("", "Title");


        //4
        TextField description = new TextField("", "Description");

        //5 
       
        // ComboBox
        
        String[] characters = { "Tyrion Lannister", "Jaime Lannister", "Cersei Lannister", "Daenerys Targaryen"};
        int[] characters_price = { 40, 42, 54, 12 };
        
        // price 
        TextField price = new TextField(Integer.toString(characters_price[0]), "Price");
        price.setConstraint(TextField.NON_PREDICTIVE);
        
        Picker p = new Picker();
        p.setStrings(characters);
        p.setSelectedString(characters[0]);
        p.addActionListener(e -> price.setText(Integer.toString(characters_price[p.getTextPosition()-1])));
        
        // dateStart
        Picker dateStartPicker = new Picker();
        dateStartPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        // dateEnd
        Picker dateEndPicker = new Picker();
        dateEndPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        
        
        //button Update
        Button btn = new Button("Update");
        btn.addActionListener((evt) -> {
        	ArrayList<String> dataUpdate = new ArrayList<String>();
        	dataUpdate.add(title.getText());
        	dataUpdate.add(description.getText());
        	dataUpdate.add(description.getText());
        	dataUpdate.add(description.getText());

            String url= "http://localhost:3015/reservationTrainning/update?id="+data;
            JSONParser json = new JSONParser();

            String dataForm = "{\"user_id\":"+22+
            		",\"coach_id\":"+2+
                    ",\"title\":\""+title.getText()+
                    "\",\"state\":\""+reservations.get(0).getState()+
                    "\",\"type\":\""+description.getText()+
                    "\",\"start_time\":\""+dateStartPicker.getText()+
                    "\",\"end_time\":\""+dateEndPicker.getText()+
                    "\",\"price\":"+Integer.parseInt(price.getText())+
                 "}";
           System.out.println(dataForm); 
           Response<Map> jsonData = Rest.post(url).body(dataForm).jsonContent().getAsJsonMap();
           ToastBar.showMessage("Updated reservation training session", FontImage.MATERIAL_INFO);

        });
        
        // Delete button
        Button btn2 = new Button("Delete");
        btn2.addActionListener((evt) -> {
        	this.deleteReservation(data, theme);
        });
        
        //this
        this.add("Title: ").add(title)
        .add("Description: ").add(description)
        .add("Choose coach: ").add(p)
        .add("Pick start day").add(dateStartPicker)
        .add("Pick start day").add(dateEndPicker)
        .add("Price").add(price)
        .add(btn).add(btn2);
        
        
        title.setText(reservations.get(0).getTitle());
        description.setText(reservations.get(0).getType());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Date start = sdf.parse(reservations.get(0).getStart_time());
        //Date end = sdf.parse(reservations.get(0).getEnd_time());

        //dateStartPicker.setDate(start);
        //dateEndPicker.setDate(end);
    }
    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
    
    void deleteReservation(int id, Resources theme) {

        String url= "http://localhost:3015/reservationTrainning/delete?id="+id;
        JSONParser json = new JSONParser();

       Response<Map> jsonData = Rest.post(url).jsonContent().getAsJsonMap();
       ToastBar.showMessage("Cancel reservation training session", FontImage.MATERIAL_INFO);
       new ListeReservation(theme).show();
    }
}
