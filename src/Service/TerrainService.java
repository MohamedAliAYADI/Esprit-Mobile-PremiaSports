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

import model.Terrain;

/**
 *
 * @author garbo
 */
public class TerrainService {

    private String terrainPrefix = "/terrain";

    static TerrainService instance;
    boolean resultOK = false;
    ConnectionRequest req;
    ArrayList<Terrain> terrains = new ArrayList<>();

    private TerrainService() {
        req = new ConnectionRequest();
    }

    public static TerrainService getInstance() {
        if (instance == null) {
            instance = new TerrainService();
        }

        return instance;
    }

    public boolean addTerrain(Terrain terrain) {

        //build URL
        String addURL = Statitics.BASE_URL + terrainPrefix + "/add?nom=" + terrain.getNom() 
                + "&type=" + terrain.getType()
                + "&adresse=" + terrain.getAdresse()
                + "&infos=" + terrain.getInfos() 
                + "&contact=" + terrain.getContact();
    
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

    public ArrayList<Terrain> fetchTerrains() {

        //URL
        String selectURL = Statitics.BASE_URL + terrainPrefix + "/showAll";
        req.setUrl(selectURL);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains;
    }

    public ArrayList<Terrain> parseTerrains(String jsonText) {

        try {
            //parser
            terrains = new ArrayList<>();
            JSONParser jp = new JSONParser();

            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Terrain T = new Terrain();
                T.setId((int) ((double) item.get("id")));
                T.setNom((String) item.get("nom"));
                T.setType((String) item.get("type"));
                T.setAdresse((String) item.get("adresse"));
                T.setInfos((String) item.get("infos"));
                T.setContact((String) item.get("contact"));

                terrains.add(T);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return terrains;

    }

    public boolean deleteTerrain(int idterrain) {
        String url = Statitics.BASE_URL + "/terrain/delete?id=" + idterrain;
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

    public boolean updateTerrain(Terrain terrain) {
        String url = Statitics.BASE_URL
                + "/terrain/update?id=" + terrain.getId() + "&nom=" + terrain.getNom() + "&type=" + terrain.getType() + "&adresse=" + terrain.getAdresse() + "&infos=" + terrain.getInfos() + "&contact=" + terrain.getContact();
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
