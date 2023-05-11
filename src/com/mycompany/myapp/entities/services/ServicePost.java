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
public class ServicePost {
    public ArrayList<Post> events ;
    public static ServicePost instance = null;
    private ConnectionRequest req;
    public boolean resultOK;
    public boolean resultOKk;

    
    public static ServicePost getInstance(){
        if(instance == null){
            instance =new ServicePost();
        }
        return instance;
    }
    public ServicePost(){
        req=new ConnectionRequest();
    }
    public boolean addPost(Post e) {

      
        
 String url = Statics.BASE_URL + "/post/addJson?theme=" + e.getTheme() + "&nom=" + e.getNom() + "&image=" + e.getImage() +
                "&contenu=" + e.getContenu();

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
    
    
    public ArrayList<Post> afficherPosts() {
         ArrayList<Post> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000" + "/post/allpostjson";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    String strData = new String(req.getResponseData(), "UTF-8");
                    
                    Map<String, Object> mapPosts = jsonp.parseJSON(new StringReader(strData));
                    
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPosts.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps) {
                    
                        Post p = new Post(); 
                 
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String name = obj.get("nom").toString();
                       
                     
                        String image = obj.get("image").toString();
                    
                        String contenu = obj.get("contenu").toString();
                      
                        
                        String theme = obj.get("theme").toString();
                        String date = obj.get("date_Creation").toString();
                       
                        // setting the values :
                        p.setId((int) id);
                        p.setNom(name);
                        p.setContenu(contenu);
                        p.setImage(image);
                        p.setTheme(theme);
                        p.setDate_Creation(date);
                      

                        result.add(p);
                    }
                } catch (Exception ex) {
                   
                    ex.printStackTrace();
                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
       
     
     
  public void supprimerPost(int id_post) {
    // Afficher une boîte de dialogue de confirmation
    boolean confirmation = Dialog.show("Confirmation", "Êtes-vous sûr de vouloir supprimer ce post ?", "OK", "Cancel");
    if (confirmation) {
        String url = Statics.BASE_URL + "/post/deletePost/" + id_post;
        req.setUrl(url);
        req.addResponseListener((a) -> {
            String str = new String(req.getResponseData());
            System.out.println("Data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if (req.getResponseCode() == 200) {
            Dialog.show("Success", "This Post has been deleted successfully", "OK", null);
        } else {
            Dialog.show("Error", "it seems there's an error at deleting the post", "OK", null);
        }
    }
}

     
     
     public boolean modifierPost(Post post) {
         
        String url = Statics.BASE_URL + "/post/updatePost/"+post.getId()+"?nom=" +post.getNom()+ "&theme=" + post.getTheme() + "&contenu=" + post.getContenu() + "&image=" + post.getImage();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOKk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
  
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOKk;
     }
    
    
}
