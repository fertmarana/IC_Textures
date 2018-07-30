/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.util.Comparator;

/**
 *
 * @author fernanda
 */
public class MatrizCoocorenciaElComparator implements Comparator<MatrizCoOcorrenciaElementos> {
    public int compare(MatrizCoOcorrenciaElementos o1, MatrizCoOcorrenciaElementos o2)
        {
        return Double.compare(o1.getdistanciaValor(), o2.getdistanciaValor());
        }
    }

