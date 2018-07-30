
package ic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImageBuilder {

    /**
     * Carregando uma imagem do arquivo
     * @param path - caminho da imagem
     * @return BinaryImage
     * @throws IOException 
     */
    public static GrayScaleImage loadImageGrayScale(File path) throws IOException {
        GrayScaleImage img = null;
        img = new GrayScaleImage(ImageIO.read(path));
        return img;
    }
    
    /**
     * Converte um objeto java.awt.Image para uma matriz de pixels
     * @param img
     * @return
     */
    public static int[][] convertToArray(Image img){
        BufferedImage image = ImageBuilder.createBufferedImage(img);
        int width = image.getWidth();
        int height = image.getHeight();
        int pixels[][] = new int[width][height];
        int rgb, r, g, b;
        for(int w=0;w<width;w++){
            for(int h=0;h<height;h++){
                rgb = image.getRGB(w,h);
                r = (int)((rgb&0x00FF0000)>>>16); // Red level
                g = (int)((rgb&0x0000FF00)>>>8); // Green level
                b = (int) (rgb&0x000000FF); // Blue level
                pixels[w][h] = (int) Math.round(.299*r + .587*g + .114*b); //convertendo para niveis de cinza
            }
        } 
        return pixels;
    }
        
    /**
     * Converte uma matriz de pixel para um objeto java.awt.Image
     * @param pixels
     * @return
     */
    public static Image convertToImage(int [][]pixels){
        int width = pixels.length;
        int height = pixels[0].length; 
        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bi.getRaster();
        for(int w=0;w<width;w++){
            for(int h=0;h<height;h++){
                raster.setSample(w,h,0,pixels[w][h]);
                raster.setSample(w,h,1,pixels[w][h]);
                raster.setSample(w,h,2,pixels[w][h]);
            }
        }
        ImageProducer producer = bi.getSource();
        int[] pix = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(producer, 0, 0, width, height, pix, 0, width);
        try {
            if (!grabber.grabPixels())
                throw new IllegalStateException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MemoryImageSource membo = new MemoryImageSource(width,height,pix,0,width);
        return Toolkit.getDefaultToolkit().createImage(membo);
    }
        
    
    /**
     * Cria um BufferedImage de uma dada imagem
     * @param image
     * @return
     */
    public static BufferedImage createBufferedImage(Image image){
        BufferedImage bi = null;
        if(image instanceof BufferedImage)
            bi = (BufferedImage)image;
        else{
            int w = image.getWidth(null);
            int h = image.getHeight(null);
            bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
        }
        return bi;
    }
    
    /**
     * salvando uma imagem
     * @param image
     * @throws IOException 
     */
    public static void saveImage(Image image, File file) {
        try {
            ImageIO.write(ImageBuilder.createBufferedImage(image),file.getName().substring(file.getName().length() - 3), file);
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }


    /**
     * salvando uma imagem
     * @param image
     * @throws IOException 
     */
    public static void saveImage(GrayScaleImage image, File file) {
        saveImage(image.createImage(), file);
    }
    
}
