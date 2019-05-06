package equipment;

import buff.Buff;
import combatStyle.CombatStyle;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;
import javafx.scene.image.Image;
import resources.ImageFlyweight;
import resources.Imageable;

public interface EquipmentInterface extends Imageable{
    String getName();
    Image getImage();
    Slot getSlot();
    CombatStyle getCombatStyle();
    public int getLevel();
    int getDamage();
    int getArmor();
    Buff getBuff();
    int getSortingId();
    
    
}
