/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commentaire;
import com.mycompany.entities.Post;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author walid
 */
public class ServiceCommentaire {
    public ArrayList<Commentaire> commentaires ;

    public static ServiceCommentaire instance = null;
    private ConnectionRequest req;
    public boolean resultOK;

    
    public static ServiceCommentaire getInstance(){
        if(instance == null){
            instance =new ServiceCommentaire();
        }
        return instance;
    }
    public ServiceCommentaire(){
        req=new ConnectionRequest();
    }
    public boolean ajouterCommentaire(Commentaire c){
        String text = c.getText();
        String date = c.getDate();
        
        String url = Statics.BASE_URL + "/commentaire/addCommentJson?text=" +c.getText();
        //String url = Statics.BASE_URL + "create/" + status + "/" + name;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
    
    public ArrayList<Commentaire> afficherCommentaires() {
         ArrayList<Commentaire> result = new ArrayList<>();
        
        String url = Statics.BASE_URL  + "/commentaire/listeeCmntsJson";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    String strData = new String(req.getResponseData(), "UTF-8");
                    
                    Map<String, Object> mapCommentaires = jsonp.parseJSON(new StringReader(strData));
                    
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCommentaires.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps) {
                    
                        Commentaire c = new Commentaire(); 
                 
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String text = obj.get("text").toString();
                       
                        
                        String date = obj.get("date").toString();
                                          
                     
                       
                        // setting the values :
                        c.setId((int) id);
                        c.setText(text);
                        c.setDate(date);
                        c.setP(null);

                        result.add(c);
                    }
                } catch (Exception ex) {
                   
                    ex.printStackTrace();
                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
     public void supprimerComment(int id_comm) {

         // Afficher une boîte de dialogue de confirmation
    boolean confirmation = Dialog.show("Confirmation", "Êtes-vous sûr de vouloir supprimer ce commentaire ?", "OK", "Cancel");
        String url = Statics.BASE_URL + "/commentaire/deleteComment/" + id_comm;
       if (confirmation) {
        req.setUrl(url);
        req.addResponseListener((a)
                -> {
            String str = new String(req.getResponseData());
            System.out.println("Data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (req.getResponseCode() == 200) {
            Dialog.show("Success", "This comment has been deleted successfully", "OK", null);
            
        } else {
            Dialog.show("Error", "it seems there's an error at deleting the comment", "OK", null);
        }
        
       }
    }
     
}
     

