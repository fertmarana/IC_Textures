package ic;

public class ImagesUtils {
    
    //consertar
    public static boolean isIndexImage(GrayScaleImage img, int x, int y){
        if ( (x > 0 && y > 0) && (img.getWidth() > x && img.getHeight() > y))
            return true;
        else
            return false;
    }
}
