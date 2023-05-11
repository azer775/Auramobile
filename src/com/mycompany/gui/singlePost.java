/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Post;

/**
 *
 * @author MSI
 */
public class singlePost extends SideMenuBaseForm{
    
    
    public singlePost(Resources theme,Post post){
         super("Single Post", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle("Modifier Produit");
        getContentPane().setScrollVisible(false);
        
        super.setupSideMenu(theme);
        
        tb.addSearchCommand(e -> {});
        
        TextComponent nom= new TextComponent().label("Nom");
         nom.text(post.getNom());
        add(nom);
        
        TextComponent themes= new TextComponent().label("Theme");
        themes.text(post.getTheme());
        add(themes);
        
        String url = /*"file://C:/xampp/htdocs/produit_final/produit/public/images/product/"+ */post.getImage();
                            int deviceWidth = Display.getInstance().getDisplayWidth();
                            Image placeholder = Image.createImage( deviceWidth/3,  deviceWidth/3, 0xbfc9d2);
                            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + post.getImage(),url, URLImage.RESIZE_SCALE);
                            
                            
        
        
    }
}
