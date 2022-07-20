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

import model.CoachNotes;
import model.ReservationTraining;

public class CoachNotesService {


    //prefix
    private String reservationTrainningPrefix = "/notesCoach";

    //var
    static CoachNotesService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<CoachNotes> coachNotesTraining = new ArrayList<CoachNotes>();

    //constructor
    public CoachNotesService() {
        req = new ConnectionRequest();
    }

    //Get
    public static CoachNotesService getInstance() {
        if (instance == null) {
            instance = new CoachNotesService();
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
    public List<CoachNotes> fetchCoachNotes(int id) {

        //URL
        String selectURL = Statitics.BASE_URL + reservationTrainningPrefix + "/getNotesCoach?coachId="+id;

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
                coachNotesTraining = parseCoachNotes(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return coachNotesTraining;
    }

    //List ReservationTraining
    public List<CoachNotes> getCoachNote(int id) {

        //URL
        String selectURL = Statitics.BASE_URL + reservationTrainningPrefix + "/getNoteCoach?id="+id;

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
                coachNotesTraining = parseCoachNotes(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return coachNotesTraining;
    }
    
    //PARSING JSON
    public List<CoachNotes> parseCoachNotes(String jsonText) {

        //parser
    	coachNotesTraining = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
            	CoachNotes p = new CoachNotes();
    			Double d=new Double((Double) item.get("id"));
    			int id= d.intValue();
                p.setId(id);
                p.setTitle((String) item.get("title"));
                p.setDescription((String) item.get("description"));
                
                p.setTitle_1((String) item.get("title_1"));
                p.setDescription_1((String) item.get("description_1"));                
                p.setTitle_2((String) item.get("title_2"));
                p.setDescription_2((String) item.get("description_2"));
                p.setTitle_3((String) item.get("title_3"));
                p.setDescription_3((String) item.get("description_3"));
                p.setTitle_4((String) item.get("title_4"));
                p.setDescription_4((String) item.get("description_4"));
                p.setTitle_5((String) item.get("title_5"));
                p.setDescription_5((String) item.get("description_5"));
               
                coachNotesTraining.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return coachNotesTraining;
    }
}
