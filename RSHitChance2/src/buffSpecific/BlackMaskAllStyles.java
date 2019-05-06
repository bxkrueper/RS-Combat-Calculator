package buffSpecific;
/*
 * for slayer helmets with the black mask, hexcrest, and focus sight, so it doesn't matter what style you are using to get the effect, only if the monster is weak to slayer masks
 */
import buff.BuffCondition;
import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;
import javafx.scene.image.Image;
import resources.ImageFlyweight;

public class BlackMaskAllStyles extends ConstantFillInBuff{

    private BuffCondition condition;
    private double multiplier;
    
    public BlackMaskAllStyles(BuffName name, double multiplier) {
        super(name);
        this.multiplier = multiplier;
        this.condition = new OpponentWeakToBuffCondition(BuffName.Black_Mask);
        this.setPictureName("Black_Mask");
    }
    
    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return multiplier;
        }else{
            return NullBuff.getInstance().getAccuracyMultiplier(owner, opponent);
        }
    }

    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return multiplier;
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
    }
    
    

}
