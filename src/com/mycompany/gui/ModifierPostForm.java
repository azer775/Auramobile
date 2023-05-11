/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.entities.Post;
import com.mycompany.services.ServicePost;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author walid
 */
public class ModifierPostForm extends SideMenuBaseForm{
   
    private Post post;
     String Imagecode;
   String filePath="";

    
    public ModifierPostForm(Resources theme, Post post) {
        
        super("Modifier Produit", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle("Modifier Produit");
        getContentPane().setScrollVisible(false);
        
        super.setupSideMenu(theme);
        
        tb.addSearchCommand(e -> {});
        
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label facebook = new Label("786 followers", theme.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", theme.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2, 
                            facebook, twitter
                    )
                )
        ));
        
         TextComponent nom= new TextComponent().label("Nom");
         nom.text(post.getNom());
        add(nom);
        
        TextComponent themes= new TextComponent().label("Theme");
        themes.text(post.getTheme());
        add(themes);
        
                      
       /* TextComponent prix= new TextComponent().label("Prix");
        add(prix);*/
             TextComponent description= new TextComponent().label("Contenu");
             description.text(post.getContenu());
            add(description);  
        
        TextComponent image= new TextComponent().label("Image");
        image.text(post.getImage());
        add(image);  
        
        //IMAGE
        Font materialFont = FontImage.getMaterialDesignFont();
        FontImage fntImage = FontImage.createFixed("\uE871", materialFont, 0xffffff, 100, 100);
        Button b2 = new Button(fntImage);
        Style fabStyle = b2.getAllStyles();
        fabStyle.setBorder(RoundBorder.create().color(0xf05f5f).shadowOpacity(100));
        fabStyle.setFgColor(0xf15f5f);
        fabStyle.setBgTransparency(50);
        fabStyle.setBgColor(0xf05f5f);
           
        Label lbimg = new Label();
        
        /*  if (DropTarget.isSupported() ) {
       DropTarget dnd = DropTarget.create((evt)->{
        String srcFile = (String)evt.getSource();
        System.out.println("Src file is "+srcFile);
       
               String  maChaine = srcFile;
               filePath= maChaine.substring(19,srcFile.length());
               System.out.println(filePath);
                    System.out.println("Location: "+evt.getX()+", "+evt.getY());
                    if (srcFile != null) {
                        try {
                            Image imgg = Image.createImage(FileSystemStorage.getInstance().openInputStream(srcFile));
                            imgg.scale(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth());
                                lbimg.setIcon(imgg);
                            // c3.removeComponent(imgv);
                            revalidate();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } 
                }, Display.GALLERY_IMAGE);
         }*/
         add(b2);
         add(lbimg);
         
        Button Edit = new Button("Edit");
         Button backButton = new Button("Back");
        Edit.addActionListener((evt) -> {
                              
             if (themes.getText().equals("")||(nom.getText().equals("")) || (description.getText().equals("")) || (image.getText().equals(""))){
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                return;
                        }
             if(description.getText().length()<20){
                     Dialog.show("Alert", "Please enter a content greater than 20 caracters", new Command("OK"));
                     
                return;
                }
                else
                {
                    ImageIO imgIO = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] ba = out.toByteArray();
                    Imagecode = Base64.encode(ba);
                    System.out.println(filePath);

                    ServicePost sp = new ServicePost();
                    post.setNom(nom.getText());
                     post.setTheme((themes.getText()));
           
           
            //fi.setDateexpiration(dateexpiration.getText());
            if(!filePath.equals(""))
            {
              post.setImage(filePath);
            }
            else
            {
              post.setImage((image.getText()));
            }
           
            post.setContenu(description.getText());
                
                    sp.modifierPost(post);
                    Dialog.show("Success","Produit modifier avec success",new Command("OK"));
                    new afficher_post_form(theme).showBack();
                }
        });
          backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new afficher_post_form(theme).showBack();
            }
        });
        addStringValue("", FlowLayout.encloseRightMiddle(Edit,backButton));
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
        
     /*  super("Modifier post");
        setLayout(BoxLayout.y());
        this.post = post;
        
        // Créer les champs pour modifier les détails de post
        TextField nomField = new TextField(post.getNom(), "Nom");
        TextField themeField = new TextField(post.getTheme(), "Theme");
        TextField contenuField = new TextField(post.getContenu(), "Contenu");
        TextField imageField = new TextField(post.getImage(), "Image");*/
        
        // Ajouter les champs au formulaire
    /*    add(nomField);
        add(themeField);
        add(contenuField);
        add(descriptionField);*/
        
        // Ajouter un bouton pour enregistrer les modifications
     /*  Button enregistrerButton = new Button("Update Post");
       Button backButton = new Button("Back");
           
        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if ((nomField.getText().length()==0) && (themeField.getText().length()==0) && (contenuField.getText().length()==0) && (imageField.getText().length()==0)){
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    return;
                }
                if((nomField.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the title of the post field", new Command("OK"));
                      return;
                }
                if((themeField.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the theme of the post field", new Command("OK"));
                      return;
                }
                if((contenuField.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the content of the post field", new Command("OK"));
                      return;
                }
                if((imageField.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the image of the post field", new Command("OK"));
                      return;
                }
                 if((contenuField.getText().length()<20)){
                     Dialog.show("Alert", "Please enter a content greater than 20 caracters", new Command("OK"));
                      return;
                }
                
                post.setNom(nomField.getText());
                post.setTheme(themeField.getText());
                post.setContenu(contenuField.getText());
                post.setImage(imageField.getText());
                  if(  ServicePost.getInstance().modifierPost(post))
                        {
                           Dialog.show("Success","Post updated succefully",new Command("OK"));
                           new afficher_post_form(theme).showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
               
                
                // Fermer le formulaire et retourner à la liste des posts
                new afficher_post_form(theme).showBack();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new afficher_post_form(theme).showBack();
            }
        });
         addAll(nomField,themeField,contenuField,imageField,enregistrerButton,backButton);
    */
}

