/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author fernanda
 */
public class LBP {
    private GrayScaleImage img;
    private GrayScaleImage modifiedimg;
    private double[] Histograma;
    private double distanciaValor;
    private int maxvalue;
    
    public LBP(GrayScaleImage img){
         this.img = img;
         this.modifiedimg = new GrayScaleImage(img.getHeight()-2,img.getWidth()-2);
         this.maxvalue= 0;
         System.out.printf("\nMAXx: %d e MAXy:%d\n",modifiedimg.getHeight(), modifiedimg.getWidth());
         //this.normal = (img.getHeight()-2)*(img.getWidth()-2);
         this.distanciaValor = -1.0;
         LBPfunction();
         System.out.printf("\n Setou os pixels\n");
        
         Histograma = createColorHistogram();
    }
    
    
    
    public void LBPfunction(){
       
       for (int x = 1; x < img.getWidth()-2; x++) {
            //System.out.printf("\nx: %d e y:%d\n",x, y);
        
	    for (int y = 1; y < img.getHeight()-2; y++) {
                LBPpixel(x,y);
            }
       }
       
    }
    
    public void LBPpixel(int x, int y){
       
        int middlepixel = img.getPixel(x, y);
        String arrayPixel = pixelCheck(img.getPixel(x-1, y-1),middlepixel)+
                            pixelCheck(img.getPixel(x  , y-1),middlepixel)+
                            pixelCheck(img.getPixel(x+1, y-1),middlepixel)+
                            pixelCheck(img.getPixel(x+1, y  ),middlepixel)+
                            pixelCheck(img.getPixel(x+1, y+1),middlepixel)+
                            pixelCheck(img.getPixel(x  , y+1),middlepixel)+
                            pixelCheck(img.getPixel(x-1, y+1),middlepixel)+
                            pixelCheck(img.getPixel(x-1, y  ),middlepixel);
       
        modifiedimg.setPixel(x-1, y-1, ( BinaryStringtoDecimal(arrayPixel)));
        
    }
    
    public String pixelCheck(int a, int b){
        if(a>b) return "0";
        return "1";
    }
    
    
    
    
    public int BinaryStringtoDecimal(String c){
        int result = Integer.parseInt(c, 2);
        if(result>maxvalue) maxvalue = result;
        return result;
    }
    
    public double[] createColorHistogram(){
        double[] histogram = new double[maxvalue+1];
        int normal = (modifiedimg.getHeight())*(modifiedimg.getWidth());
        //System.out.println(maxvalue);
        for (int i = 1; i < maxvalue+1; i++) {
        histogram[i] =0;
        }
       
        for (int x = 0; x < modifiedimg.getWidth(); x++) {
	    for (int y = 0; y < modifiedimg.getHeight(); y++) {
            //    System.out.println(img.getPixel(x, y));
                histogram[modifiedimg.getPixel(x, y)]++;
                
            }
       }
        //System.out.println("ok!");
        for (int i = 1; i < maxvalue+1; i++) {
        histogram[i] = histogram[i]/normal;
        }
        return histogram;
    }
    public void setdistanciaEucliadiana(double[] arrayKey){
        double valor = 0.0;
        for(int i=0; i<arrayKey.length;i++){
            valor += Math.pow(Histograma[i]-arrayKey[i],2);
        }
        this.distanciaValor = Math.sqrt(valor);
        
    }
    public void setdistanciaManhattan(double[] arrayKey){
        double valor = 0.0;
        for(int i=0; i<arrayKey.length;i++){
            valor += Math.abs(Histograma[i]-arrayKey[i]);
        }
        this.distanciaValor = Math.sqrt(valor);
        
    }
    
    public double getdistanciaValor(){
        return this.distanciaValor;
    }
    public double[] getHistograma(){
        return this.Histograma;
    }
    public int[][] getmodifiedimg(){
        return this.modifiedimg.getPixels();
    }
    
     public int[][] getimg(){
        return this.img.getPixels();
    }
    
    
    public static void main(String args[]) {
        String pathString = "/home/fernanda/NetBeansProjects/IC/src/ic/images/Brodatz/D8.gif";
        File path = new File(pathString);
       GrayScaleImage img = null;
        try {
            img = new GrayScaleImage(ImageIO.read(path));
             
        } catch (IOException ex) {
           // Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("O Problema 1 eh:");
             System.out.println(ex);
        }
        LBP test = new LBP(img);
        test.LBPfunction();
        
        double[] hist;
        hist = test.createColorHistogram();
        
    }
}
