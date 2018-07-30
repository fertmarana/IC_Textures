/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author fernanda
 */
public class MatrizCompare {
    String imgpath;
    String imgpathSelected;
    int distancia;
    int angulo;
    List<String> imgslist;
    int distanceChoice;
    
    public MatrizCompare(String imgpathSelected, int distancia, int angulo, String imgpath, List<String> imgslist, int distanceChoice){
        this.angulo = angulo;
        this.distancia = distancia;
        this.imgpathSelected = imgpathSelected;
        
        this.imgpath = imgpath;
        this.imgslist = imgslist;
        this.distanceChoice = distanceChoice;
        System.out.println(imgpath);
        System.out.println(imgpathSelected);
        System.out.println(imgslist);
        
    }
    public MatrizCompare(String imgpathSelected, String imgpath, List<String> imgslist, int distanceChoice){
        this.angulo = 0;
        this.distancia = 0;
        this.imgpathSelected = imgpathSelected;
        this.imgpath = imgpath;
        this.imgslist = imgslist;
        this.distanceChoice = distanceChoice;
        System.out.println(imgpath);
        System.out.println(imgpathSelected);
        System.out.println(imgslist);
        
    }
    
    
    public List<Image> CompareImagesMatrizCoocorrencia(){
       List<MatrizCoOcorrenciaElementos> matCoElList = new ArrayList<MatrizCoOcorrenciaElementos>();
       File path = new File(imgpathSelected);
       GrayScaleImage img = null;
        try {
            img = new GrayScaleImage(ImageIO.read(path));
             System.out.println("deu certo!");
        } catch (IOException ex) {
           // Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("O Problema 1 eh:");
             System.out.println(ex);
        }
        MatrizCoOcorrencia mCoocorrencia = new MatrizCoOcorrencia(img, distancia, angulo); 
        
        for(int i=0; i<imgslist.size();i++){
            
           File ipath = new File(imgpath+imgslist.get(i));
            try { 
                MatrizCoOcorrencia imCoocorrencia = new MatrizCoOcorrencia(new GrayScaleImage(ImageIO.read(ipath)), distancia, angulo);
                matCoElList.add(imCoocorrencia.getMatrizCoOcorrenciaElementos());
                
            } catch (IOException ex) {
                //Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("O Problema eh:");
                System.out.println(ex);
            }
            
        }
        matCoElMinMax minMaxList = new matCoElMinMax(matCoElList);
        mCoocorrencia.getMatrizCoOcorrenciaElementos().setAllminmax(minMaxList);
        for (int i=0; i<matCoElList.size();i++){
            matCoElList.get(i).setAllminmax(minMaxList);
            if(distanceChoice == 0){
            matCoElList.get(i).setdistanciaEucliadiana(mCoocorrencia.getMatrizCoOcorrenciaElementos());
            }else{
            matCoElList.get(i).setdistanciaManhattan(mCoocorrencia.getMatrizCoOcorrenciaElementos());    
            }
        }
        
        if(matCoElList.isEmpty()== false){
            Collections.sort(matCoElList,new MatrizCoocorenciaElComparator());
            for (int k = 0;k<matCoElList.size();k++){
                System.out.printf("matCoELList[%d].getDistancia(): %f", k, matCoElList.get(k).getdistanciaValor());
            }
            
            
            List<Image> imgList = new ArrayList<Image>();
            for(int j = 0; j<matCoElList.size();j++){
                imgList.add(ImageBuilder.convertToImage(matCoElList.get(j).getimg()));
            }
            return imgList;
        }
        return null;
        
    }
     public List<Image> CompareImagesLBP(){
       List<LBP> LBPlist = new ArrayList<LBP>();
       File path = new File(imgpathSelected);
       GrayScaleImage img = null;
        try {
            img = new GrayScaleImage(ImageIO.read(path));
            // System.out.println("deu certo!");
        } catch (IOException ex) {
           // Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("O Problema 1 eh:");
             System.out.println(ex);
        }
        LBP keyLBP = new LBP(img); 
      System.out.println("Carregou o LBPKey");
        for(int i=0; i<imgslist.size();i++){
            
           File ipath = new File(imgpath+imgslist.get(i));
            try { 
                LBP imLBP = new LBP(new GrayScaleImage(ImageIO.read(ipath)));
                LBPlist.add(imLBP);
                
            } catch (IOException ex) {
                //Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("O Problema eh:");
                System.out.println(ex);
            }
            
        }
        System.out.println("Calculando distancia");
        if(distanceChoice == 0){
            for (int i=0; i<LBPlist.size();i++){
            LBPlist.get(i).setdistanciaEucliadiana(keyLBP.getHistograma());
            }
        }else{
            for (int i=0; i<LBPlist.size();i++){
            LBPlist.get(i).setdistanciaManhattan(keyLBP.getHistograma());
            }
        }
        
        
        if(LBPlist.isEmpty()== false){
            Collections.sort(LBPlist,new Comparator<LBP>()
                        {
                            @Override
                            public int compare(LBP o1, LBP o2)
                            {
                                return Double.compare(o1.getdistanciaValor(), o2.getdistanciaValor());
                            }
                        });
            
            System.out.println("Imagem criada");
            List<Image> imgList = new ArrayList<Image>();
            for(int j = 0; j<LBPlist.size();j++){
                imgList.add(ImageBuilder.convertToImage(LBPlist.get(j).getimg()));
            }
            return imgList;
        }
        return null;
        
    }
    
    
}
