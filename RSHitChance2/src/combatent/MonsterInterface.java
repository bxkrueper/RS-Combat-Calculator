package combatent;

import buff.Buff;
import buff.Buffs;
import javafx.scene.image.Image;

public interface MonsterInterface extends Combatent{
    Image getImage();
    String getLink();
    Buff getNaturalBuffs();
    public Buffs getEditableBuffs();//buffs applied by the player/interface
    MonsterAffinityWeaknesses getAffinityWeaknesses();
    Attacks getAttacks();
}
