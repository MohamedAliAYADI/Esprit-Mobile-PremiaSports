/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utilities.Statitics;
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
import model.Promotion;


/**
 *
 * @author bouss
 */
public class PromotionService {

    private String promotionPrefix = "/promotion";

    static PromotionService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Promotion> promotions = new ArrayList<Promotion>();

    public boolean isResultOK() {
        return resultOK;
    }

    //constructor
    public PromotionService() {
        req = new ConnectionRequest();
    }
    
    public static PromotionService getInstance() {
        if (instance == null) {
           instance=new PromotionService();
        }
        return instance;
    }
    
    public boolean addPromotion(Promotion P) {

        //build URL
        String addURL = Statitics.BASE_URL + promotionPrefix + "/add";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("type_promotion", P.getType());
        req.addArgument("promo",String.valueOf(P.getPromo()) );
       
    

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
    
public List<Promotion> fetchPromotion() {

        //URL
        String selectURL = Statitics.BASE_URL + promotionPrefix + "/showAll";

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
                promotions = parsePromotion(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return promotions;
    }

    //PARSING JSON
    public List<Promotion> parsePromotion(String jsonText) {

        //parser
        promotions = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Promotion P = new Promotion();
                P.setType((String) item.get("type_promotion"));
                P.setPromo((int)((double) item.get("promo")));
               
         
                promotions.add(P);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return promotions;
    }
}
