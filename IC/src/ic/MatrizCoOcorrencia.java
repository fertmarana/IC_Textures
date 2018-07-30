package ic;


public class MatrizCoOcorrencia {

    private GrayScaleImage img;
    private int distancia;
    private int angulo;
    private double[][] matCoOcorrencia = new double[256][256];
    private MatrizCoOcorrenciaElementos matCoEl;

    public MatrizCoOcorrencia(GrayScaleImage img, int distancia, int angulo) {
	this.img = img;
	this.distancia = distancia;
        this.angulo = angulo;

	getMatriz();
        matCoEl = new MatrizCoOcorrenciaElementos(img);
        segundoMomentoAngular();
        medidaDeEntropia();
        medidaDeHomogeneidade();
        medidaDeContraste();
        medidaDeCorrelacao();
    }

    public MatrizCoOcorrencia() {
	super();
    }

    public void getMatriz() {

	int soma = 0;

	for (int x = 0; x < img.getWidth(); x++) {
	    for (int y = 0; y < img.getHeight(); y++) {

		switch (angulo) {
		case 0:
		    if (ImagesUtils.isIndexImage(img, x + distancia, y)) {
			matCoOcorrencia[img.getPixel(x, y)][img.getPixel(x
				+ distancia, y)] += 2;
                        
			soma++;
		    }
		    if (ImagesUtils.isIndexImage(img, x - distancia, y)) {
			matCoOcorrencia[img.getPixel(x - distancia, y)][img
				.getPixel(x, y)]+= 2;
			soma++;
		    }
		    break;

		case 45:
		    if (ImagesUtils.isIndexImage(img, x + distancia, y
			    + distancia)) {
			matCoOcorrencia[img.getPixel(x, y)][img.getPixel(x
				+ distancia, y + distancia)]+= 2;
			soma++;
		    }
		    if (ImagesUtils.isIndexImage(img, x - distancia, y
			    - distancia)) {
			matCoOcorrencia[img.getPixel(x - distancia, y
				- distancia)][img.getPixel(x, y)]+=2;
			soma++;
		    }
		    break;

		case 90:
		    if (ImagesUtils.isIndexImage(img, x, y + distancia)) {
			matCoOcorrencia[img.getPixel(x, y)][img.getPixel(x, y
				+ distancia)]+= 2;
			soma++;
		    }
		    if (ImagesUtils.isIndexImage(img, x, y - distancia)) {
			matCoOcorrencia[img.getPixel(x, y)][img.getPixel(x, y
				- distancia)]+= 2 ;
			soma++;
		    }
		    break;

		case 135:
		    if (ImagesUtils.isIndexImage(img, x - distancia, y
			    + distancia)) {
			matCoOcorrencia[img.getPixel(x - distancia, y
				+ distancia)][img.getPixel(x, y)]+=2;
			soma++;
		    }
		    if (ImagesUtils.isIndexImage(img, x + distancia, y
			    - distancia)) {
			matCoOcorrencia[img.getPixel(x, y)][img.getPixel(x
				+ distancia, y - distancia)]+=2;
			soma++;
		    }
		    break;
		}
	    }
	}

	// Normalizacao
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {
		matCoOcorrencia[i][j] = matCoOcorrencia[i][j] / soma;
	    }
	}
        
    }

  

    /**
     * M�todo responsav�l em calcular o Segundo Momento Angular, conhecido
     * tamb�m como energia, expressa a uniformidade de uma textura
     * 
     * @param matCoOcorrencia
     *            Matriz de co-ocorr�ncia normalizada.
     * @return Retorna um <code>double</code> com o valor do segundo momento
     *         angular
     */
    public void segundoMomentoAngular() {
	double fSMA = 0;
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {
		fSMA += Math.pow(matCoOcorrencia[i][j], 2);
	    }
	}
        matCoEl.setsegundoMomentoAngular(fSMA);
	
    }

    /**
     * A medida de entropia expressa a desordem contida na textura. Quando uma
     * imagem n�o � uniforme, as entradas P m,n apresent�o valores pr�ximos de
     * zero.
     * 
     * @param matCoOcorrencia
     *            Matriz de co-ocorr�ncia normalizada.
     * @return Valor da entropia da imagem.
     */
    public void medidaDeEntropia() {
	double fENT = 0;
	final double E = 0.000000000000000001;
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {
		// fENT += ( (Math.log( matCoOcorrencia[i][j] ) / Math.log( 2 ))
		// * -1 );
		fENT += ((Math.log(matCoOcorrencia[i][j] + E) / Math.log(2)) * -1);

	    }
	}
	matCoEl.setmedidaDeEntropia(fENT);
    }

    /**
     * Contraste caracteriza-se pela diferen�a entre os tons de cinza. Baixo
     * contraste ocorre quando h� pequena diferen�a entre os niv�is de cinza dos
     * pixels localizados em uma regi�o cont�gua da imagem.
     * 
     * @param matCoOcorrencia
     *            Matriz de co-ocorr�ncia normalizada.
     * @return Valor do contrate da imagem.
     */
    public void medidaDeContraste() {
	double fCON = 0;
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {
		fCON += (Math.pow(i - j, 2) * matCoOcorrencia[i][j]);

	    }
	}
        matCoEl.setmedidaDeContraste(fCON);
    }

    public void medidaDeHomogeneidade() {
	double fHOM = 0;
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {

		fHOM += ((1 / (1 + Math.pow((i - j), 2))) * matCoOcorrencia[i][j]);

	    }
	}
        matCoEl.setmedidaDeHomogeneidade(fHOM);
	
    }

    public void medidaDeCorrelacao() {
	double fVARi = 0;
	double fVARj = 0;

	double correlacao = 0;
	double SIGMAi = 0;
	double SIGMAj = 0;

	double[] MIi = new double[256];
	double[] MIj = new double[256];
	// media i
	for (int i = 0; i < MIi.length; i++) {
	    for (int j = 0; j < matCoOcorrencia.length; j++) {
		MIi[i] = i * (matCoOcorrencia[i][j]);
	    }
	}
	// media j
	for (int j = 0; j < MIj.length; j++) {
	    for (int i = 0; i < matCoOcorrencia.length; i++) {
		MIj[j] = j * (matCoOcorrencia[i][j]);
	    }
	}

	// variancia i, j
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {

		fVARi += (Math.pow(i - MIi[i], 2) * matCoOcorrencia[i][j]);
		fVARj += (Math.pow(j - MIj[j], 2) * matCoOcorrencia[i][j]);

	    }
	}

	// desvio padr�o
	SIGMAi = Math.sqrt(fVARi);
	SIGMAj = Math.sqrt(fVARj);

	// correlacao
	for (int i = 0; i < matCoOcorrencia.length; i++) {
	    for (int j = 0; j < matCoOcorrencia[i].length; j++) {

		correlacao += ((i - MIi[i]) * (j - MIj[j]) * matCoOcorrencia[i][j]);

	    }
	}

	correlacao *= (1 / (SIGMAi * SIGMAj));

        matCoEl.setmedidaDeCorrelacao(correlacao);
	
    }
   
    
    public MatrizCoOcorrenciaElementos getMatrizCoOcorrenciaElementos(){
        return matCoEl;
    }
}
