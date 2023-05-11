/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.services.ServiceCommentaire;
import com.mycompany.myapp.entities.services.ServicePost;

/**
 *
 * @author MSI
 */
public class ajouter_comment_form extends SideMenuBaseForm{
    
    
      public ajouter_comment_form(Resources theme) {
        setTitle("Add a new Comment");
        setLayout(BoxLayout.y());
        
        TextField tfText = new TextField("","Comment");
        


        Button btnValider = new Button("Add Comment");
         Button backButton = new Button("Back");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                if((tfText.getText().length()==0)){
                     Dialog.show("Alert", "Please write the comment content", new Command("OK"));
                      return;}
                 Commentaire c = new Commentaire(tfText.getText());
                        if( ServiceCommentaire.getInstance().ajouterCommentaire(c))
                        {
                           Dialog.show("Success","Comment added succefully",new Command("OK"));
                           new afficher_commentaire_form(theme).showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                      
                }
             });
            backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                /* Dialog.show("Success","Comment showed succefully",new Command("OK"));
                 ServiceCommentaire.getInstance().afficherCommentaires();*/
                 
               //new afficher_commentaire_form(theme).showBack();
               new WalkthruForm(theme).show();
            }
        });
                       
                
                
                addAll(tfText,btnValider,backButton);
                    
                }
            
        
        
       
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

