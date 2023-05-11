/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MSI
 */
public abstract class SideMenuFront extends Form{
    
    public SideMenuFront(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuFront(String title) {
        super(title);
    }

    public SideMenuFront() {
    }

    public SideMenuFront(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
     public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
     
    public void setupSideMenu(Resources theme) {
       Toolbar tb = getToolbar();
        Image img = theme.getImage("dog.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl
               /* FlowLayout.encloseCenterBottom(
                        new Label(theme.getImage("azizpic.png"), "PictureWhiteBackgrond"))*/
        ));
        
        tb.addMaterialCommandToSideMenu("Posts", FontImage.MATERIAL_SETTINGS, e -> new postFront(theme).show());
       
           //     tb.addMaterialCommandToSideMenu("Show Front", FontImage.MATERIAL_SETTINGS, e -> new postFront(theme).show());

//   tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> showOtherForm(theme));

    }
    
}
