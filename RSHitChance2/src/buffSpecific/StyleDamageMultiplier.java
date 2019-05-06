package buffSpecific;
/*
 * multiplies the damage of a specific combat style
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class StyleDamageMultiplier extends ConstantFillInBuff{
    
    private BuffCondition condition;
    private double multiplier;

    public StyleDamageMultiplier(BuffName name, double multiplier,PrimaryCombatStyle combatStyle) {
        super(name);
        this.condition = new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle);
        this.multiplier = multiplier;
        
    }
    public StyleDamageMultiplier(BuffName name,double multiplier, PrimaryCombatStyle combatStyle,String pictureName) {
        this(name,multiplier,combatStyle);
        setPictureName(pictureName);
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
