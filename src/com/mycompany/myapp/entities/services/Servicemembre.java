/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.WalkthruForm;
import com.mycompany.myapp.entities.Membre;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azerb
 */
public class Servicemembre {
    
     //singleton 
    public static Servicemembre instance = null ;
    
    
    
     public static boolean resultOk = true;
    String json;
    
    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static Servicemembre getInstance() {
        if(instance == null )
            instance = new Servicemembre();
        return instance ;
    }
    
       public Servicemembre() {
        req = new ConnectionRequest();
        
    }
       public Membre parseProduits(String jsonText) {
    Membre membre=new Membre();
        
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        if (tasksListJson != null) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit produit = new Produit();

                membre.setId((int) Float.parseFloat(obj.get("id").toString()));
                membre.setNom(obj.get("nom").toString());
                membre.setPrenom(obj.get("prenom").toString());
                membre.setEmail(obj.get("email").toString());
                membre.setAdresse(obj.get("adresse").toString());
                membre.setTel(obj.get("tel").toString());
               // produit.setDescription(obj.get("description").toString());
                //produit.setImage(obj.get("image").toString());
                //produit.setPrix(Float.parseFloat(obj.get("prix").toString()));
                //produit.setNbr_Prods((int) Float.parseFloat(obj.get("nbr_Prods").toString()));
                //produit.setCategorie((int) Float.parseFloat(obj.get("categorie").toString()));

               // produits.add(produit);
            }
        }
    } catch (IOException ex) {
    }
    return membre;
}
        //SignIn
    
    public void signin(TextField Email,TextField Password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/membre/signinjson?Email="+Email.getText().toString()+"&Password="+Password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                 new WalkthruForm(rs).show();
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                //Membre membre = parseProduits(j.parseJSON(new CharArrayReader(json.toCharArray())));
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
                SessionManager.setPassowrd(user.get("Password").toString());
                SessionManager.setUserName(user.get("nom").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setRole(user.get("role").toString());
               
                
                //photo 
                
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());
                
                System.out.println("currnt user ==>"+SessionManager.getEmail()+","+SessionManager.getPassowrd()+","+SessionManager.getUserName());
                
                
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    //Signup
    public void signup(TextField CIN,TextField UserName,TextField Email,TextField Adresse ,TextField Password, Resources res  ) {
        
     
        
        String url = Statics.BASE_URL+"/membre/signupjson?UserPrenom="+CIN.getText()+"&UserName="+UserName.getText().toString()+"&Email="+Email.getText().toString()+"&Adresse="+Adresse.getText().toString()+"&Password="+Password.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(UserName.getText().equals(" ") && Password.getText().equals(" ") && Email.getText().equals(" ") && CIN.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData(); 
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
}
