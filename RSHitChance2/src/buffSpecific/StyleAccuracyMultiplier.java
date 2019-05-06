package buffSpecific;
/*
 * multiplies the accuracy of a primary combat style
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class StyleAccuracyMultiplier extends ConstantFillInBuff{
    
    private double multiplier;
    private BuffCondition condition;
    
    public StyleAccuracyMultiplier(BuffName name,double multiplier, PrimaryCombatStyle combatStyle) {
        super(name);
        this.multiplier = multiplier;
        this.condition = new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle);
    }
    public StyleAccuracyMultiplier(BuffName name,double multiplier, PrimaryCombatStyle combatStyle,String pictureName) {
        this(name,multiplier,combatStyle);
        setPictureName(pictureName);
    }
    
    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return multiplier;
        }else{
            return NullBuff.getInstance().getAccuracyMultiplier(owner, opponent);
        }
    }
}
