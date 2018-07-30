/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.util.List;

/**
 *
 * @author fernanda
 */
public class matCoElMinMax {
    private double segundoMomentoAngularMin;
    private double medidaDeEntropiaMin;
    private double medidaDeContrasteMin;
    private double medidaDeHomogeneidadeMin;
    private double medidaDeCorrelacaoMin;
    private double segundoMomentoAngularMax;
    private double medidaDeEntropiaMax;
    private double medidaDeContrasteMax;
    private double medidaDeHomogeneidadeMax;
    private double medidaDeCorrelacaoMax;
    
    public matCoElMinMax(List<MatrizCoOcorrenciaElementos> matList){
        minMaxsegundoMomentoAngular(matList);
        minMaxmedidaDeEntropia(matList);
        minMaxmedidaDeContraste(matList);
        minMaxmedidaDeHomogeneidade(matList);
        minMaxmedidaDeCorrelacao(matList);
        
        
    }
    
    
    public void minMaxsegundoMomentoAngular(List<MatrizCoOcorrenciaElementos> matList){
        double min = matList.get(0).getsegundoMomentoAngular();
        double max = min;
        int size = matList.size();
        for(int i=1; i<size; i++){
            if(min > matList.get(i).getsegundoMomentoAngular()){
                min = matList.get(i).getsegundoMomentoAngular();
            } else if (max< matList.get(i).getsegundoMomentoAngular()){
                 max = matList.get(i).getsegundoMomentoAngular();
            }
            segundoMomentoAngularMin = min;
            segundoMomentoAngularMax = max;
          
        }
    }
    public void minMaxmedidaDeEntropia(List<MatrizCoOcorrenciaElementos> matList){
        double min = matList.get(0).getmedidaDeEntropia();
        double max = min;
        int size = matList.size();
        for(int i=1; i<size; i++){
            if(min > matList.get(i).getmedidaDeEntropia()){
                min = matList.get(i).getmedidaDeEntropia();
            } else if (max< matList.get(i).getmedidaDeEntropia()){
                 max = matList.get(i).getmedidaDeEntropia();
            }
            medidaDeEntropiaMin = min;
            medidaDeEntropiaMax = max;
          
        }
    }
    
    public void minMaxmedidaDeContraste(List<MatrizCoOcorrenciaElementos> matList){
        double min = matList.get(0).getmedidaDeContraste();
        double max = min;
        int size = matList.size();
        for(int i=1; i<size; i++){
            if(min > matList.get(i).getmedidaDeContraste()){
                min = matList.get(i).getmedidaDeContraste();
            } else if (max< matList.get(i).getmedidaDeContraste()){
                 max = matList.get(i).getmedidaDeContraste();
            }
            medidaDeContrasteMin = min;
            medidaDeContrasteMax = max;
        }
    }
    public void minMaxmedidaDeHomogeneidade(List<MatrizCoOcorrenciaElementos> matList){
        double min = matList.get(0).getmedidaDeHomogeneidade();
        double max = min;
        int size = matList.size();
        for(int i=1; i<size; i++){
            if(min > matList.get(i).getmedidaDeHomogeneidade()){
                min = matList.get(i).getmedidaDeHomogeneidade();
            } else if (max< matList.get(i).getmedidaDeHomogeneidade()){
                 max = matList.get(i).getmedidaDeHomogeneidade();
            }
            medidaDeHomogeneidadeMin = min;
            medidaDeHomogeneidadeMax = max;
        }
    }   
    
    public void minMaxmedidaDeCorrelacao(List<MatrizCoOcorrenciaElementos> matList){
        double min = matList.get(0).getmedidaDeCorrelacao();
        double max = min;
        int size = matList.size();
        for(int i=1; i<size; i++){
            if(min > matList.get(i).getmedidaDeCorrelacao()){
                min = matList.get(i).getmedidaDeCorrelacao();
            } else if (max< matList.get(i).getmedidaDeCorrelacao()){
                 max = matList.get(i).getmedidaDeCorrelacao();
            }
            medidaDeCorrelacaoMin = min;
            medidaDeCorrelacaoMax = max;
        }
    }
    public double getMinsegundoMomentoAngular(){
        return segundoMomentoAngularMin;
    }
    
    public double getMinmedidaDeEntropia(){
        return medidaDeEntropiaMin;
    }
    
    public double getMinmedidaDeContraste(){
        return medidaDeContrasteMin;
    }
    
    public double getMinmedidaDeHomogeneidade(){
        return medidaDeHomogeneidadeMin;
    }
    
    public double getMinmedidaDeCorrelacao(){
        return medidaDeCorrelacaoMin;
    }
    public double getMaxsegundoMomentoAngular(){
        return segundoMomentoAngularMax;
    }
    
    public double getMaxmedidaDeEntropia(){
        return medidaDeEntropiaMax;
    }
    
    public double getMaxmedidaDeContraste(){
        return medidaDeContrasteMax;
    }
    
    public double getMaxmedidaDeHomogeneidade(){
        return medidaDeHomogeneidadeMax;
    }
    
    public double getMaxmedidaDeCorrelacao(){
        return medidaDeCorrelacaoMax;
    }
        
        
    }
    
    
    
    
    

