package interfaceHelpers;

import buff.BuffFlyweight;
import buff.BuffName;
import javafx.scene.image.Image;
import resources.ImageFlyweight;

public class BuffStringToImage implements StringToImage{

    private int width;
    private int height;
    
    public BuffStringToImage(int width,int height){
        this.width = width;
        this.height = height;
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public Image getImage(String string) {
        return BuffFlyweight.getBuff(string,BuffFlyweight.Owner.LEFT).getImage();//left or right doesn't matter. its just a picture
    }

    

}
