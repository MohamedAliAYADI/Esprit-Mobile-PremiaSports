package gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import static com.codename1.io.Log.p;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import gui.HomeForm;
import gui.HomeForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Categorie;
import models.Produits;
import services.CategoriService;

import services.ProduitService;
import utils.Statics;

public class AjouterProduit extends Form {

    ProduitService ts = ProduitService.getInstance();
     CategoriService tss = CategoriService.getInstance();

    public AjouterProduit(Resources theme) {

        this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter Produit ");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm(theme).showBack();
        });

        //Widgets
        TextField nom_prod = new TextField("", "Nom de Produit");
      TextField reference_prod = new TextField("", "reference_prod");
        TextField description = new TextField("", "description	");
        TextField prix = new TextField("", "prix");
        
        TextField quantite = new TextField("", "quantite");
         //TextField Image_prod = new TextField("", "Image_prod");
         
         
         ComboBox cb=new ComboBox();
         
        CategoriService ps = new CategoriService () ;
        
        List<Categorie> cat = ps.fetchcat();
        
        for (Categorie p : cat) {
            cb.addItem(p.getType());
            cb.addItem(p.getId_catg());
        }
        cb.addSelectionListener((oldSelected, newSelected) -> {
            System.out.println("****** oldSelected = " + cat.get(oldSelected));
            System.out.println("************ newSelected = " + cat.get(newSelected));
        });
       
        
            
        Button addprod = new Button("vider");
        addprod.addActionListener((evt) -> {

   new AjouterProduit(theme).show();
    });
        

        TextField Image_prod = new TextField("", "Image name");
        Button btnUpload = new Button("telecharger l'image produit");
        btnUpload.addActionListener((evt) -> {
            if (!"".equals(Image_prod.getText())) {
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                
             cr.setUrl(Statics.BASE_URL);
                cr.setPost(true);
                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
                cr.setFilename("file", Image_prod.getText() + ".jpg");

                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                Dialog.show("Success", "Image uploaded", "OK", null);
            }else{
                Dialog.show("Error", "Invalid image name", "Ok", null);
            }
        });
       

        Button submitBtn = new Button("Ajouter prod");

        submitBtn.addActionListener((evt) -> {
            if (ts.addProd(new Produits( nom_prod.getText(), reference_prod.getText() ,description.getText(),prix.getText(),Image_prod.getText(),quantite.getText()))) {
                Dialog.show("succé ", "Produit ajouté avec  Inserted successfully", "ok", null);
            } else {
                Dialog.show("err", "Verifier", "ok", null);
            }
        });

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
        this.addAll(nom_prod,reference_prod,description,prix,quantite,cb ,Image_prod,btnUpload,submitBtn,addprod);

    }

}
