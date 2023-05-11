/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceProduit {

    public ArrayList<Produit> produits;
    public boolean resultOKk;
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    


    
    public ArrayList<Produit> parseProduits(String jsonText) {
    ArrayList<Produit> produits;
        produits = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        if (tasksListJson != null) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Produit produit = new Produit();

                produit.setId((int) Float.parseFloat(obj.get("id").toString()));
                produit.setNom_Prod(obj.get("nom_Prod").toString());
                produit.setDescription(obj.get("description").toString());
                produit.setImage(obj.get("image").toString());
                produit.setPrix(Float.parseFloat(obj.get("prix").toString()));
                //produit.setNbr_Prods((int) Float.parseFloat(obj.get("nbr_Prods").toString()));
                //produit.setCategorie((int) Float.parseFloat(obj.get("categorie").toString()));

                produits.add(produit);
            }
        }
    } catch (IOException ex) {
    }
    return produits;
}
    public ArrayList<Produit> getAllProduits(){
        String url = "http://127.0.0.1:8000/produit/list";
        req.setUrl(url);
        req.setPost(false); 
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }

    public boolean addProduit(Produit p) {

        String name = p.getNom_Prod();
        String description = p.getDescription();
        String image = p.getImage();
        float prix = p.getPrix();
        int nbr_Prods = p.getNbr_Prods();
        int categorie = p.getCategorie();
        
        String url = "http://127.0.0.1:8000/produitmobile/addp?"+"nom_prod="+name+"&image="+image+"&nbr_prods="+nbr_Prods+"&description="+description+"&prix="+prix;
        //String url = Statics.BASE_URL + "/produitjson/addprodjson?categorie_id=" + produit.getCategorie() + "&libelle=" + produit.getLibelle() + "&ref=" + produit.getRef() + "&prix=" + produit.getPrix() + "&stock=" + produit.getStock();
        //String url = "http://127.0.0.1:8000/produit/addp/"+name+"/"+description+"/"+image+"/"+description+"/"+Float.toString(prix)+"/"+Integer.toString(nbr_Prods);/*"http://127.0.0.1:8000/produit/addp/eolienne/kuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu/r/77/7"*/;
        //String url = "http://127.0.0.1:8000/produit/addp";
    
        req.setUrl(url);
        req.setPost(true);
        
        // Ajout des paramètres POST
        req.addArgument("nom_prod", name);
        req.addArgument("description", description);
        req.addArgument("image", image);
        req.addArgument("prix", Float.toString(prix));
        req.addArgument("nbr_prods", Integer.toString(nbr_Prods));
        req.addArgument("categorie", Integer.toString(categorie));
        
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

    public boolean modifierPost(Produit p) {
         
        String url =  "http://127.0.0.1:8000/produitmobile/updateproduit/"+p.getId()+"?nom_prod="+p.getNom_Prod()+"&image="+p.getImage()+"&nbr_prods="+p.getNbr_Prods()+"&description="+p.getDescription()+"&prix="+p.getPrix();
        
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

        public boolean editProduit(Produit p) {
            String name = p.getNom_Prod();
            String description = p.getDescription();
            String image = p.getImage();
            float prix = p.getPrix();
            int nbr_Prods = p.getNbr_Prods();
            int categorie = p.getCategorie();
            String url = "http://127.0.0.1:8000/produitmobile/updateproduit/"+p.getId()+"?nom_prod="+name+"&image="+image+"&nbr_prods="+nbr_Prods+"&description="+description+"&prix="+prix;         
                  req.setUrl(url);
               req.setPost(false); 
                req.addArgument("nom_prod", name);
        req.addArgument("description", description);
        req.addArgument("image", image);
        req.addArgument("prix", Float.toString(prix));
        req.addArgument("nbr_prods", Integer.toString(nbr_Prods));
        req.addArgument("categorie", Integer.toString(categorie));
        req.addArgument("categorie", Integer.toString(p.getId()));
               System.out.println(url);
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

    public boolean deleteProduit(int id) {
        String url =  "http://127.0.0.1:8000/produitmobile/deleteproduit/"+id/*"http://127.0.0.1:8000/produit/deleteProduitJSON?id="+id*/;
               req.setUrl(url);
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

}

