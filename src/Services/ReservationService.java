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
import model.Reservation;

/**
 *
 * @author bouss
 */
public class ReservationService {

    private String reservationPrefix = "/Reservation";

    static ReservationService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Reservation> reservations = new ArrayList<Reservation>();

    public boolean isResultOK() {
        return resultOK;
    }

    //constructor
    public ReservationService() {
        req = new ConnectionRequest();
    }
    
    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }
    
    public boolean addReservation(Reservation R) {

        //build URL
        String addURL = Statitics.BASE_URL + reservationPrefix + "/add";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("DateReservation", R.getDateReservation());
        req.addArgument("nombredeParticipant",String.valueOf(R.getNombredeParticipant()) );
        req.addArgument("prix",String.valueOf( R.getPrix()));
        req.addArgument("promotion",String.valueOf (R.getProm().getIdPromotion()));
    

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
    
public List<Reservation> fetchReservation() {

        //URL
        String selectURL = Statitics.BASE_URL + reservationPrefix + "/showAll";

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
                reservations = parseReservation(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }

    //PARSING JSON
    public List<Reservation> parseReservation(String jsonText) {

        //parser
        reservations = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Reservation R = new Reservation();
                R.setDateReservation((String) item.get("DateReservation"));
                R.setNombredeParticipant(((int)(double) item.get("nombredeParticipant")));
                R.setPrix(((int)(double)item.get("prix")));
                R.setProm((new Promotion(((int)(double) item.get("promotion")), "", 0)));
         
                reservations.add(R);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return reservations;
    }
        public boolean deleteReservation(Reservation R) {

        //build URL
        String addURL = Statitics.BASE_URL + reservationPrefix + "/delete";

        //2
        req.setUrl(addURL);

        //3
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

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
        
//  public boolean UpdateReservation(Reservation R) {
//        
//        String addURL = Statitics.BASE_URL + reservationPrefix + "/update" ;
//
//        req.setUrl(addURL);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
}
