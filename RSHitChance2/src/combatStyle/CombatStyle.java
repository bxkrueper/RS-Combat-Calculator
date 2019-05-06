package combatStyle;

import resources.ImageFlyweight;
import resources.Imageable;
import javafx.scene.image.Image;

public interface CombatStyle extends Imageable{
    public static final int IMAGE_WIDTH = 50;
    public static final int IMAGE_HEIGHT = 50;
    
    String getName();
    public default Image getImage(){
        return ImageFlyweight.getImage("images/Combat Styles/" + getName(),IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
    }
}
