/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

///MODEL FOR CUSTOM ROW IN A JLIST
public class ImgsNText
     {
        //FIELDS
        private String name;
        private Icon img;

        //CONSTRUCTOR
        public ImgsNText(String text,Icon icon)
        {
            this.name=text;
            
   
            Image img = ((ImageIcon)icon).getImage();
            Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            this.img = new ImageIcon(newimg);
        }
        
        public ImgsNText(Image img)
        {
            this.name="";
            
   
            
            Image newimg = img.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            this.img = new ImageIcon(newimg);
        }
        
        public ImgsNText(String text,Icon icon, int len, int wid)
        {
            this.name=text;
            
   
            Image img = ((ImageIcon)icon).getImage();
            Image newimg = img.getScaledInstance(len, wid,  java.awt.Image.SCALE_SMOOTH);
            this.img = new ImageIcon(newimg);
        }

        //GETTERS AND SET

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }

 }

