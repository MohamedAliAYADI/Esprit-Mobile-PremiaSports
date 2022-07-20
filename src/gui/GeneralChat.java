package gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;

public class GeneralChat extends Form{
    
    @SuppressWarnings("deprecation")
	public GeneralChat(Resources theme){
        
        this.setLayout(new GridLayout(1, 1));
        this.setTitle("General Chat");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });

        Form hi = new Form(new BorderLayout());
        hi.setHeight(getLayoutHeight());
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("https://permia-sport.vercel.app/");
        browser.setHeight(getLayoutHeight());
        browser.setScrollVisible(false);
        browser.setScrollableY(false);
        hi.add(BorderLayout.CENTER, browser);
        
        TextField title = new TextField("", "Title");

        
        this.add(hi);
        
    }

}	