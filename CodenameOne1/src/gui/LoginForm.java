/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;


public class LoginForm extends Form {
    public LoginForm(Resources theme) {
        this.setLayout(BoxLayout.y());
     
   TextField pseudo = new TextField("", "Pseudo");
        TextField password = new TextField("", "password");
        


        Button loginButton = new Button("Admin");
        FontImage.setMaterialIcon(loginButton, FontImage.MATERIAL_ACCOUNT_CIRCLE);
        
       loginButton.addActionListener((evt) -> {

   new HomeForm(theme).show();
    });
       
        
   
       
         Button Client = new Button("Client");
        FontImage.setMaterialIcon(Client, FontImage.MATERIAL_ACCOUNT_CIRCLE);
        
       Client.addActionListener((evt) -> {

   new HomeFormClient(theme).show();
    });
       
       
       
       
       
       
       
       
       
       
       
       
       
         this.addAll(pseudo,password,loginButton,Client);
         
      
        
        
        
    }
}
