/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import Produit.gui.AllProduits;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Post;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
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
        Image img = theme.getImage("user-picture.jpg");
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
        
   
    
        tb.addMaterialCommandToSideMenu("Gestion Posts", FontImage.MATERIAL_SETTINGS, e -> new afficher_post_form(theme).show());
        tb.addMaterialCommandToSideMenu("Gestion Commentaires", FontImage.MATERIAL_SETTINGS, e -> new afficher_commentaire_form(theme).show());
        tb.addMaterialCommandToSideMenu("Ajouter Post", FontImage.MATERIAL_SETTINGS, e ->  new ajouter_post_form(theme).show());
        tb.addMaterialCommandToSideMenu("Ajouter Commentaire", FontImage.MATERIAL_SETTINGS, e -> new ajouter_comment_form(theme).show());
         tb.addMaterialCommandToSideMenu("Gestion Produits", FontImage.MATERIAL_SETTINGS, e -> new AllProduits(theme).show());
           //     tb.addMaterialCommandToSideMenu("Show Front", FontImage.MATERIAL_SETTINGS, e -> new postFront(theme).show());

//   tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> showOtherForm(theme));

    }
    
    
    //protected abstract void showOtherForm(Resources res);
}
