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
import models.Produits;
import utils.Statics;

public class ProduitService {

    private String prodd = "/produit";

    static ProduitService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Produits> cat = new ArrayList<Produits>();

     public ProduitService() {
        req = new ConnectionRequest();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }

        return instance;
    }

    //Ajout
    public boolean addProd(Produits p) {

        String addURL = Statics.BASE_URL + prodd + "/add";

        req.setUrl(addURL);

        req.setPost(true);

        req.addArgument("nom_prod", p.getNom_prod());
    req.addArgument("reference_prod", p.getReference_prod());
      req.addArgument("description", p.getDescription());
        req.addArgument("prix", p.getPrix());
       req.addArgument("quantite", p.getQuantite());
       // req.addArgument("id_catg", p.getId_catg());
     

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
    public List<Produits> fetchProd() {

        //URL
        String selectURL = Statics.BASE_URL + prodd + "/showAll";
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
                cat = parseProd(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }

    
    
    
    
     public boolean Remove(int id) {
       

        String url = Statics.BASE_URL  + prodd + "/delete/:id_prod" + id; //création de l'URL
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
    //PARSING JSON
    public List<Produits> parseProd(String jsonText) {

        //parser
        cat = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Produits p = new Produits();

                
                p.setNom_prod((String) item.get("nom_prod"));
                p.setReference_prod((String) item.get("reference_prod "));
                p.setDescription((String) item.get("description"));
                p.setPrix((String) item.get("prix"));
                p.setQuantite((String) item.get("quantite"));

                cat.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return cat;
    }
    
    
}
