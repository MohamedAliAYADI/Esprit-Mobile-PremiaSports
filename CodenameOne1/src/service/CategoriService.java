
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Categorie;
import utils.Statics;

public class CategoriService {
 
    private String catg= "/categorie";

    static CategoriService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Categorie> cat = new ArrayList<Categorie>();

  
    public CategoriService() {
        req = new ConnectionRequest();
    }

    public static CategoriService getInstance() {
        if (instance == null) {
            instance = new CategoriService();
        }

        return instance;
    }

    //Ajout
    public boolean addCat(Categorie p) {

        String addURL = Statics.BASE_URL + catg + "/add";

        req.setUrl(addURL);

        req.setPost(true);

        req.addArgument("type", p.getType());
        req.addArgument("nom_cat", p.getNom_cat());
  

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    //Select
    public List<Categorie> fetchcat() {

        //URL
        String selectURL = Statics.BASE_URL + catg + "/showAll";
        req = new ConnectionRequest();
        //1
        req.setPost(false);
        //2
        req.setUrl(selectURL);
        //3
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String response = new String(req.getResponseData());
                //parsing
                //..
                cat = parseCategorie(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }

    //PARSING JSON
    public List<Categorie> parseCategorie(String jsonText) {

        //parser
        cat = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Categorie p = new Categorie();
                p.setType((String) item.get("type"));
                p.setNom_cat((String) item.get("nom_cat"));
        

                cat.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cat;
    }

    
    public boolean deleteCategorie(int id ) {
        String url = Statics.BASE_URL +"/deleteCategorie?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
         
    }
    
    public boolean modifierCategorie(Categorie reclamation) {
        String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId_catg()+"&objet="+reclamation.getType()+"&description="+reclamation.getNom_cat();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ; 
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    return resultOK;
        
    }
    
    
    
    
    
    
}
