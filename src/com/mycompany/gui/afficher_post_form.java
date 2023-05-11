/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.entities.services.ServicePost;
import java.util.ArrayList;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Post;




/**
 *
 * @author MSI
 */
public class afficher_post_form extends SideMenuBaseForm{
     Form current;
           ImageViewer imgv;
    public afficher_post_form(Resources theme){
     /*for (Event e : ServiceEvent.getInstance().getAllEvents()) {
            SpanLabel sp = new SpanLabel();
            sp.setText("Id: " + e.getId() + "\nNom: " + e.getNom() + "\nCatégorie: " + e.getCategory() + "\nLieu: " + e.getLieu() + "\nDescription: " + e.getDescriptio() + "\n\n");
            addComponent(sp);
        }*/
     super("Posts", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       // setTitle("¨Posts");
        getContentPane().setScrollVisible(false);
     
         super.setupSideMenu(theme);
        tb.addSearchCommand(e -> {});
        
         Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, theme.getImage("Logo.png"), spacer1, "  ", "", " ");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
                                    
        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    new ajouter_post_form(theme).show();
            }
        });
        add(Ajouter);
        
         ButtonGroup barGroup = new ButtonGroup();
                  Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Post> posts = new ArrayList();
                    ServicePost sa =new ServicePost();
                    posts=sa.afficherPosts();

                 for (Post fi : posts) {
                            Container ct = new Container(BoxLayout.y());
                            String url = /*"file://C:/xampp/htdocs/produit_final/produit/public/images/product/"+ */fi.getImage();
                            int deviceWidth = Display.getInstance().getDisplayWidth();
                            Image placeholder = Image.createImage( deviceWidth/3,  deviceWidth/3, 0xbfc9d2);
                            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + fi.getImage(),url, URLImage.RESIZE_SCALE);
                                imgv = new ImageViewer();
                                imgv.setImage(i);
                                ct.add(imgv);
                            Label l = new Label("ID : "+fi.getId());
                            Label l2 = new Label("Nom : "+fi.getNom(),"SmallLabel");
                            Label l3 = new Label("Theme : "+fi.getTheme(),"SmallLabel");
                            Label l4 = new Label("Contenu : "+fi.getContenu(),"SmallLabel");
                            //Label l4 = new Label("date : "+fi.getDateexpiration(),"RedLabel");
                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l3);
                            ct.add(l4);
                           // ct.add(l4);

                            Button Modif = new Button("Modifier");
                            Button Supprimer = new Button("Supprimer");
                            Modif.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               

                    new ModifierPostForm(theme,fi).showBack();          
                }   
        });
        
        
         Supprimer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ServicePost.getInstance().supprimerPost(fi.getId());
             new afficher_post_form(theme).showBack();
            
        }
         });
                       ct.add(Modif);
                       ct.add(Supprimer);
                       Label separator = new Label("","Separator");
                       ct.add(separator);
                       add(ct);
               }
    }                    
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {     
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
     private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    
                            
    /* Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

      //  ArrayList<Post> events = ServicePost.getInstance().afficherPosts();

        for (Post e : ServicePost.getInstance().afficherPosts()) {
            Container eventContainer = new Container(new BorderLayout());

            Label nomLabel = new Label("nom: " + e.getNom());
            Label themeLabel = new Label("theme: " + e.getTheme());
            Label contenuLabel = new Label("contenu: " + e.getContenu());
            Label imageLabel = new Label("image: " + e.getImage());
              //Label date = new Label("date_Creation: " + e.getDate_Creation());
            Button supprimerButton = new Button("Supprimer");
            Button modifierButton = new Button("Modifier");
            int id = e.getId();
            
            eventContainer.addComponent(BorderLayout.NORTH, nomLabel);
            eventContainer.addComponent(BorderLayout.WEST, themeLabel);
            eventContainer.addComponent(BorderLayout.SOUTH, contenuLabel);
          
                   Container buttonContainer = new Container(new BorderLayout());
            buttonContainer.addComponent(BorderLayout.NORTH, supprimerButton);
            buttonContainer.addComponent( BorderLayout.SOUTH,modifierButton);

                
        supprimerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ServicePost.getInstance().supprimerPost(id);
             new afficher_post_form(theme).showBack();
            
        }
    });
            modifierButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            //ServicePost.getInstance().modifierPost(e);
            new ModifierPostForm(theme,e).showBack();
            
        }
    });

            container.addComponent(eventContainer);
             container.addComponent(buttonContainer);
        }

        addComponent(container);
       
        

        
    }*/
}
