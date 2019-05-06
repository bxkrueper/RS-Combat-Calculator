package resources;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageFlyweight {
    
    private static Map<String,Image> imageMap = new HashMap<>();
    private static String[] acceptableExtensions = {".jpg",".png",".gif"};
    private static String defaultImageString = "images/MissingImage";
    
    
    public static Image getImage(String urlNoExtension, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth){
        Image image = null;
        
        image = imageMap.get(urlNoExtension);
        if(image!=null){
            return image;
        }
        
        for(int i=0;i<acceptableExtensions.length;i++){
            image = new Image("file:" + urlNoExtension + acceptableExtensions[i], requestedWidth, requestedHeight, preserveRatio, smooth);
            
            if(!image.isError()){
                break;
            }
        }
        
        if(image.isError()){
            System.out.println("can't find image at " + urlNoExtension + " with any valid extension!");
            image = getDefaultImage();
            if(image.isError()){
                System.out.println("can't find default image " + "at " +  defaultImageString);
            }else{
                imageMap.put(urlNoExtension, image);//if the image wasn't there the first time, assume it won't be there the second time
            }
            return image;
        }else{
            imageMap.put(urlNoExtension, image);
            System.out.println("loaded image at " + urlNoExtension);
            return image;
        }
        
        
    }
    
    
    public static Image getDefaultImage(){
        return getImage(defaultImageString,100,100,true,true);
    }
}
