package interfaceHelpers;

import equipment.Equipment;
import equipment.EquipmentFlyweight;
import javafx.scene.image.Image;

public class EquipmentStringToImage implements StringToImage{
    
    @Override
    public int getWidth() {
        return Equipment.IMAGE_WIDTH;
    }

    @Override
    public int getHeight() {
        return Equipment.IMAGE_HEIGHT;
    }

    @Override
    public Image getImage(String string) {
        return EquipmentFlyweight.getEquipment(string).getImage();
    }

}
