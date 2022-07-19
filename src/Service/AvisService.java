/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utilite.Statitics;
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
import model.Avis;

/**
 *
 * @author garbo
 */
public class AvisService {

    private String avisPrefix = "/avis";

    static AvisService instance;
    boolean resultOK = false;
    ConnectionRequest req;
    ArrayList<Avis> avis = new ArrayList<>();

    private AvisService() {
        req = new ConnectionRequest();
    }

    public static AvisService getInstance() {
        if (instance == null) {
            instance = new AvisService();
        }

        return instance;
    }

    public boolean addAvis(Avis a) {

        //build URL
        String addURL = Statitics.BASE_URL + avisPrefix + "/add?commentaire=" + a.getCommentaire()
                + "&note=" + a.getNote()
                + "&idUser=" + a.getIdUser()
                + "&idterrain=" + a.getIdterrain();

        req.setUrl(addURL);

        //3
        req.setPost(false);

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

    public ArrayList<Avis> fetchAvis(int terrainId) {

        //URL
        String selectURL = Statitics.BASE_URL + avisPrefix + "/showAll?id=" + terrainId;
        req.setUrl(selectURL);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String response = new String(req.getResponseData());

                avis = parseAvises(response);
                req.removeResponseListener(this);

            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return avis;
    }

    public ArrayList<Avis> parseAvises(String jsonText) {

        try {
            //parser
            avis = new ArrayList<>();
            JSONParser jp = new JSONParser();

            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Avis A = new Avis();
                A.setId((int) ((double) item.get("id")));
                A.setCommentaire((String) item.get("commentaire"));
                A.setNote(Integer.parseInt(((String) item.get("note"))));
                A.setIdUser((int) ((double) item.get("idUser")));
                A.setIdterrain((int) ((double) item.get("idterrain")));
                avis.add(A);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return avis;

    }

    public boolean deleteAvis(int id) {
        String url = Statitics.BASE_URL + "/avis/delete?id=" + id;
        req.setUrl(url);
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

    public boolean updateAvis(Avis newAvis) {
        String url = Statitics.BASE_URL
                + "/avis/update?id=" + newAvis.getId() + "&commentaire=" + newAvis.getCommentaire()
                + "&note=" + newAvis.getNote()
                + "&idUser=" + newAvis.getIdUser()
                + "&idterrain=" + newAvis.getIdterrain();
        req.setUrl(url);
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
}
