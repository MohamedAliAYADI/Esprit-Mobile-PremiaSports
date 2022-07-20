package service;

import utilities.Statitics;
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
import model.ReservationTraining;

public class ReservationTrainingService {


	//prefix
    private String reservationTrainningPrefix = "/reservationTrainning";

    //var
    static ReservationTrainingService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<ReservationTraining> reservationTraining = new ArrayList<ReservationTraining>();

    //constructor
    public ReservationTrainingService() {
        req = new ConnectionRequest();
    }

    //Get
    public static ReservationTrainingService getInstance() {
        if (instance == null) {
            instance = new ReservationTrainingService();
        }

        return instance;
    }

    //Ajoute ReservationTraining
    public boolean addReservationTraining(ReservationTraining p) {

        //build URL
        String addURL = Statitics.BASE_URL + reservationTrainningPrefix + "/add";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("coach_id", String.valueOf(p.getCoach_id()));
        req.addArgument("user_id", String.valueOf(p.getUser_id()));
        req.addArgument("title", p.getTitle());
        req.addArgument("state", p.getState());
        req.addArgument("type", p.getType());
        req.addArgument("start_time", p.getStart_time());
        req.addArgument("end_time", p.getEnd_time());
        req.addArgument("price", String.valueOf(p.getPrice()));
 
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

    //List ReservationTraining
    public List<ReservationTraining> fetchReservationTraining() {

        //URL
        String selectURL = Statitics.BASE_URL + reservationTrainningPrefix + "/getListClient?clientId=22";

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
                reservationTraining = parseReservationTraining(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationTraining;
    }

    //PARSING JSON
    public List<ReservationTraining> parseReservationTraining(String jsonText) {

        //parser
    	reservationTraining = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
            	ReservationTraining p = new ReservationTraining();
    			Double d=new Double((Double) item.get("id"));
    			int id= d.intValue();
                p.setId(id);
                p.setTitle((String) item.get("title"));
                p.setType((String) item.get("type"));
                p.setState((String) item.get("state"));
                p.setStart_time((String) item.get("start_time"));
                p.setEnd_time((String) item.get("end_time"));
                //p.setPrice((Float) item.get("price"));

                reservationTraining.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return reservationTraining;
    }

}
