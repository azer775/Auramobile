/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
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
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.services.ServicePost;
//import com.codename1.datatransfer.DropTarget;
//import java.awt.dnd.DropTarget;

import java.io.ByteArrayOutputStream;

/**
 *
 * @author walid
 */
public class ajouter_post_form extends SideMenuBaseForm{
    
    String Imagecode;
   String filePath="";
    public ajouter_post_form(Resources theme) {
      
      super("Ajouter Post", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
//        setTitle("Ajouter Post");
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
        add(nom);
        TextComponent themes= new TextComponent().label("Theme");
        add(themes);
                      
       /* TextComponent prix= new TextComponent().label("Prix");
        add(prix);*/
             TextComponent description= new TextComponent().label("Contenu");
        add(description);  
        
        TextComponent image= new TextComponent().label("Image");
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
       //  TextComponent prixachat= new TextComponent().label("Prixachat");
      
         add(b2);
         add(lbimg); 
         
         
         Button Edit = new Button("Ajouter");
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
            Post fi = new Post();
            fi.setNom(nom.getText());
           fi.setTheme(themes.getText());
            
           // fi.setDateexpiration(dateexpiration.getText());
           fi.setContenu(description.getText());
            if(!filePath.equals(""))
            {
              fi.setImage(filePath);
            }
            else
            {
              fi.setImage(image.getText());
            }
           
          
            sp.addPost(fi);
            Dialog.show("Success","Post ajouté avec success",new Command("OK"));
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
        add(createLineSeparator(0xeeeeee));}
        
    /*    
       setTitle("Add a new Post");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","PostNom");
        TextField tfTheme = new TextField("","PostTheme");
        TextField tfContenu = new TextField("","PostContenu");
        TextField tfImage = new TextField("","PostImage");


        Button btnValider = new Button("Add Post");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0) && (tfTheme.getText().length()==0) && (tfContenu.getText().length()==0) && (tfImage.getText().length()==0)){
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    return;
                }
                if((tfNom.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the title of the post field", new Command("OK"));
                      return;
                }
                if((tfTheme.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the theme of the post field", new Command("OK"));
                      return;
                }
                if((tfContenu.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the content of the post field", new Command("OK"));
                      return;
                }
                if((tfImage.getText().length()==0)){
                     Dialog.show("Alert", "Please fill the image of the post field", new Command("OK"));
                      return;
                }
                 if((tfContenu.getText().length()<20)){
                     Dialog.show("Alert", "Please enter a content greater than 20 caracters", new Command("OK"));
                      return;
                }
               
                        Post p = new Post(tfNom.getText(),tfTheme.getText(),tfContenu.getText(),tfImage.getText());
                        if( ServicePost.getInstance().addPost(p))
                        {
                           Dialog.show("Success","Post added succefully",new Command("OK"));
                           new afficher_post_form(theme).showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                
                
               
                    
                }
            
        });
        
        addAll(tfNom,tfTheme,tfContenu,tfImage,btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     */           
    }

