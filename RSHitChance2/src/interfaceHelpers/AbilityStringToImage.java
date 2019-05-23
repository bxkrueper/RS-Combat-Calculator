package interfaceHelpers;

import abilities.Ability;
import abilities.AbilityFlyweight;
import javafx.scene.image.Image;

public class AbilityStringToImage implements StringToImage{
    
    @Override
    public int getWidth() {
        return Ability.IMAGE_WIDTH;
    }

    @Override
    public int getHeight() {
        return Ability.IMAGE_HEIGHT;
    }

    @Override
    public Image getImage(String string) {
        return AbilityFlyweight.getAbility(string).getImage();
    }

}