/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.services.ServicePost;
import com.mycompany.myapp.entities.services.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author walid
 */
public class afficher_commentaire_form extends Form{
    
    public afficher_commentaire_form(Resources theme){
        
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

       

        for (Commentaire c : ServiceCommentaire.getInstance().afficherCommentaires()) {
            Container eventContainer = new Container(new BorderLayout());

            Label textLabel = new Label("Text: " + c.getText());
         //   Label dateLabel = new Label("Date: " + c.getDate());
            
            
              //Label date = new Label("date_Creation: " + e.getDate_Creation());
            Button supprimerButton = new Button("Supprimer");
         
            int id = c.getId();
            
            eventContainer.addComponent(BorderLayout.NORTH, textLabel);
           // eventContainer.addComponent(BorderLayout.WEST, dateLabel);
        
          
            Container buttonContainer = new Container(new BorderLayout());
            buttonContainer.addComponent(BorderLayout.NORTH, supprimerButton);
            

                
        supprimerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ServiceCommentaire.getInstance().supprimerComment(id);
             new afficher_commentaire_form(theme).showBack();
            
        }
    });

            container.addComponent(eventContainer);
             container.addComponent(buttonContainer);
        }

        addComponent(container);
}
}
