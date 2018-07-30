/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

/**
 *
 * @author fernanda
 */
public class MatrizCoOcorrenciaElementos {
    private double segundoMomentoAngular;
    private double medidaDeEntropia;
    private double medidaDeContraste;
    private double medidaDeHomogeneidade;
    private double medidaDeCorrelacao;
    private GrayScaleImage img;
    private double distanciaValor;
    
    public MatrizCoOcorrenciaElementos(GrayScaleImage img){
        this.img =  img;
    }
    
    public void setsegundoMomentoAngular(double sMA){
        segundoMomentoAngular = sMA;
    }
    
    public void setmedidaDeEntropia(double mDE){
        medidaDeEntropia = mDE;
    }
    
    public void setmedidaDeContraste(double mDC){
        medidaDeContraste = mDC;
    }
    
    public void setmedidaDeHomogeneidade(double mDH){
        medidaDeHomogeneidade = mDH;
    }
    
    public void setmedidaDeCorrelacao(double mDCor){
        medidaDeCorrelacao = mDCor;
    }
    
    public double getsegundoMomentoAngular(){
        return segundoMomentoAngular;
    }
    
    public double getmedidaDeEntropia(){
        return medidaDeEntropia;
    }
    
    public double getmedidaDeContraste(){
        return medidaDeContraste;
    }
    
    public double getmedidaDeHomogeneidade(){
        return medidaDeHomogeneidade;
    }
    
    public double getmedidaDeCorrelacao(){
        return medidaDeCorrelacao;
    }
    public double minmaxNormal(double max, double min, double x){
        return ( (x-min)/(max-min) );
    }
    
    public void setAllminmax(matCoElMinMax minmaxAll){
        setsegundoMomentoAngular(
                minmaxNormal(minmaxAll.getMaxsegundoMomentoAngular(),
                minmaxAll.getMinsegundoMomentoAngular(),
                this.getsegundoMomentoAngular()));
        
        setmedidaDeEntropia(
                minmaxNormal(minmaxAll.getMaxmedidaDeEntropia(),
                minmaxAll.getMinmedidaDeEntropia(),
                this.getmedidaDeEntropia()));
        
        setmedidaDeContraste(
                minmaxNormal(minmaxAll.getMaxmedidaDeContraste(),
                minmaxAll.getMinmedidaDeContraste(),
                this.getmedidaDeContraste()));
        
        setmedidaDeHomogeneidade(
                minmaxNormal(minmaxAll.getMaxmedidaDeHomogeneidade(),
                minmaxAll.getMinmedidaDeHomogeneidade(),
                this.getmedidaDeHomogeneidade()));
        
        setmedidaDeCorrelacao(
                minmaxNormal(minmaxAll.getMaxmedidaDeCorrelacao(),
                minmaxAll.getMinmedidaDeCorrelacao(),
                this.getmedidaDeCorrelacao()));
        
        
    }
    
    public void setdistanciaEucliadiana(MatrizCoOcorrenciaElementos matCoEl){
        distanciaValor = Math.pow((segundoMomentoAngular - matCoEl.getsegundoMomentoAngular()),2)+
                Math.pow((medidaDeEntropia - matCoEl.getmedidaDeEntropia()),2)+
                Math.pow((medidaDeContraste - matCoEl.getmedidaDeContraste()),2)+
                Math.pow((medidaDeHomogeneidade - matCoEl.getmedidaDeHomogeneidade()),2)+
                Math.pow((medidaDeCorrelacao - matCoEl.getmedidaDeCorrelacao()),2);
        distanciaValor = Math.sqrt(distanciaValor);
        System.out.printf("Aqui a distancia: %f", distanciaValor);
    
    }
    public void setdistanciaManhattan(MatrizCoOcorrenciaElementos matCoEl){
        distanciaValor = Math.abs(segundoMomentoAngular - matCoEl.getsegundoMomentoAngular())+
                Math.abs(medidaDeEntropia - matCoEl.getmedidaDeEntropia())+
                Math.abs(medidaDeContraste - matCoEl.getmedidaDeContraste())+
                Math.abs(medidaDeHomogeneidade - matCoEl.getmedidaDeHomogeneidade())+
                Math.abs(medidaDeCorrelacao - matCoEl.getmedidaDeCorrelacao());
        
        
    
    }
    
    public double getdistanciaValor(){
        return distanciaValor;
    }
    public int[][] getimg(){
        return img.getPixels();
    }
 
}
