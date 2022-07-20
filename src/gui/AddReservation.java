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
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

public class AddReservation extends Form{
    
    @SuppressWarnings("deprecation")
	public AddReservation(Resources theme){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Add reservation");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        
        String[] characters = { "Mohamed Ali AYADI", "Aymen MECHERGUI", "Cersei Lannister", "Daenerys Targaryen"};
        int[] characters_price = { 40, 42, 54, 12 };
        
        //1
        TextField title = new TextField("", "Title");


        //4
        TextField description = new TextField("", "Description");

        // price 
        TextField price = new TextField(Integer.toString(characters_price[0]), "Price");
        price.setConstraint(TextField.NON_PREDICTIVE);
        
        //5 
        Button btnQr = new Button ("Scan code Qr");
        TextField codeQrInput = new TextField ("","code Qr");
        Label codeQRText = new Label("Enter codeQR to get promo");
        codeQrInput.addDataChangeListener((i1, i2) -> { // <2>
            if(codeQrInput.getText().length() > 4) {
                String url= "http://localhost:3015/reservationTrainning/useCodeQr?code="+codeQrInput.getText();
                JSONParser json = new JSONParser();
                Response<String> jsonData = Rest.get(url).getAsString();
                System.out.print(jsonData.getResponseData());
                if(jsonData.getResponseCode() == 201){
                	codeQRText.setText("Error code qr try another time");
                }
                else {
                	codeQRText.setText("Good one you have promo of "+jsonData.getResponseData()+"%");
                	double x = Integer.parseInt(jsonData.getResponseData())*Integer.parseInt(price.getText());
                	price.setText(Double.toString(x/100));
                }
            }            
        });
        btnQr.addActionListener((evt) -> {
        	/*String image = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
        	System.out.print(image);*/
        	CodeScanner.getInstance().scanQRCode(new ScanResult() {
                public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                    System.out.print(contents);
                }

                public void scanCanceled() {
                }

                public void scanError(int errorCode, String message) {
                }
            });
        });
        

        
        Form codeQr = new Form("", new GridLayout(2, 2));
        codeQr.add(codeQrInput).add(btnQr);
        // ComboBox
        
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

        //button
        Button btn = new Button("Reserve now");
        btn.addActionListener((evt) -> {
        	ArrayList<String> data = new ArrayList<String>();
        	data.add(title.getText());
        	data.add(description.getText());
        	data.add(description.getText());
        	data.add(description.getText());

            String url= "http://localhost:3015/reservationTrainning/add";
            JSONParser json = new JSONParser();
            String qr;
            if(codeQrInput.getText().length()>0) {
            	qr = codeQrInput.getText();
            }
            else {
            	qr= "-";
            }
            String dataForm = "{\"user_id\":"+22+
            		",\"coach_id\":"+2+
                    ",\"title\":\""+title.getText()+
                    "\",\"state\":\""+qr+
                    "\",\"type\":\""+description.getText()+
                    "\",\"start_time\":\""+dateStartPicker.getText()+
                    "\",\"end_time\":\""+dateEndPicker.getText()+
                    "\",\"price\":"+price.getText()+
                 "}";
           System.out.println(dataForm); 
           Response<Map> jsonData = Rest.post(url).body(dataForm).jsonContent().getAsJsonMap();
           ToastBar.showMessage("Saved reservation training session", FontImage.MATERIAL_INFO);

        });
        
        
        //this
        this.add("Title: ").add(title)
        .add("Description: ").add(description)
        .add("Choose coach: ").add(p)
        .add("Pick start day").add(dateStartPicker)
        .add("Pick start day").add(dateEndPicker)
        .add("Code QR").add(codeQr).add(codeQRText)
        .add("Price").add(price)
        .add(btn);
        
    }
    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
    
}

