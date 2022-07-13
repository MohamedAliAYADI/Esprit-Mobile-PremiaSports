
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
import models.Reclamation;
import utils.Statics;

public class ReclamationService {
 
    private String recc= "/reclamation";

    static ReclamationService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Reclamation> cat = new ArrayList<Reclamation>();

  
    public ReclamationService() {
        req = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }

        return instance;
    }

    //Ajout
    public boolean addRec(Reclamation p) {

        String addURL = Statics.BASE_URL + recc + "/add";

        req.setUrl(addURL);

        req.setPost(true);

        req.addArgument("sujet", p.getSujet());
        req.addArgument("description", p.getDescription());
        
        req.addArgument("statut", p.getStatut());
        
  

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
    public List<Reclamation> fetchRec() {

        //URL
        String selectURL = Statics.BASE_URL + recc + "/showAll";
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
                cat = parseReclamation(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }

        public boolean Remove(int id) {
       

        String url = Statics.BASE_URL + "/removeReclamation/" + id; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    //Update 
    public boolean modifierReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId()+"&objet="+reclamation.getSujet()+"&description="+reclamation.getDescription()+"&etat="+reclamation.getStatut();
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
    

    //PARSING JSON
    public List<Reclamation> parseReclamation(String jsonText) {

        //parser cat = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Reclamation p = new Reclamation();

                
                
                p.setSujet((String) item.get("sujet"));
                p.setDescription((String) item.get("description"));
                p.setStatut((String) item.get("statut"));
           

                cat.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cat;
    }
    
    
}
