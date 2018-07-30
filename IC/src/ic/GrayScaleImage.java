package ic;
import java.awt.Image;
import java.io.Serializable;




/**
 * Projection: 
 * @description
 * Classe que representa uma imagem digital.   
 * 
 */
public class GrayScaleImage implements  Serializable {
    private int width; //largura da imagem
    private int height; //altura da imagem
    private int pixels[][]; //matriz de pixel da imagem
    private String className;
    /**
     * Construtor que recebe uma imagem e extrai a sua matriz de pixels
     * @param img - Image
     */
    public GrayScaleImage(Image img){
         System.out.println("entrou aqui!");
        this.setPixels(ImageBuilder.convertToArray(img));
       
    }
    public GrayScaleImage(Image img, String name){
         System.out.println("entrou aqui!");
        this.setPixels(ImageBuilder.convertToArray(img));
        this.className = name;
    }

    /**
     * Construtor para criar uma nova imagem
     * @param width - largura
     * @param height - altura
     */
    public GrayScaleImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width][height];
    }

    
    /**
     * Cria uma Image
     * @return Image
     */
    public Image createImage(){
        return ImageBuilder.convertToImage(getPixels());
    }
    
    /**
     * Inicializar todos os pixel da imagem para um dado nivel de cinza
     * @param color
     */
    public void initImage(int color){
        for (int i = 0; i < getWidth(); i++){
            for (int j = 0; j < getHeight(); j++){
                setPixel(i,j, color);
            }
        }
    }
    
    /**
     * Pega o valor do pixel (x, y)
     * @param x - largura
     * @param y - altura
     * @return float - valor do pixel
     */
    public int getPixel(int x, int y){
        if(x >= getWidth() || y >= getHeight()) throw new RuntimeException("(x="+x+", y="+y+") Nao existe essa posicao na matriz de pixels");
        return pixels[x][y];
    }
    
    
    /**
     * Modifica o valor do pixel (x, y) = value
     * @param x - largura
     * @param y - altura
     * @param value - valor do pixel
     */
    public void setPixel(int x, int y, int value){
        if(x >= getWidth() || y >= getHeight()) throw new RuntimeException("(x="+x+", y="+y+") Nao existe essa posicao na matriz de pixels");
        pixels[x][y] = value;
    }
    
    /**
     * Pega uma matriz bidimensional de pixel da imagem
     * @return int[][]
     */
    public int[][] getPixels(){
        return pixels;
    }
    
    
    public String getclassName(){
        return className;
    }
    
    
    /**
     * Modifica a matriz de pixel da imagem para os valores da matriz dada
     * @param matrix 
     */
    public void setPixels(int matrix[][]){
        this.width = matrix.length;
        this.height = matrix[0].length;
        this.pixels = new int[width][height];
        for (int i = 0; i < getWidth(); i++){
            for (int j = 0; j < getHeight(); j++){
                setPixel(i, j, matrix[i][j]);
            }
        }
    }
    
    
    /**
     * Retorna a altura da imagem
     * @return int - altura
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Retorna a largura da imagem
     * @return int - largura
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * Retorna o tamanho da imagem
     * @return
     */
    public int getSize(){
        return getHeight() * getWidth();
    }

    /**
     * Modifica o valor do pixel (i / getHeight() , i % getHeight()) = value - formato raster
     * @param i
     * @param value - valor do pixel
     */
    public void setPixel(int i, int level){
        int w = i / getHeight();
        int h = i % getHeight();
        pixels[w][h] = level;
    }

    /**
     * Pega o valor do pixel (i / getHeight() , i % getHeight())
     * @param i
     */
    public int getPixel(int i){
        int w = i / getHeight();
        int h = i % getHeight();
        return pixels[w][h];
    }
    
}
