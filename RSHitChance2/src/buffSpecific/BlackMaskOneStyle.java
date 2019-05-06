package buffSpecific;
/*
 * for black mask, hexcrest, focus sight, and original slayer helm with just the black mask
 * as opposed to BlackMaskAllStyles, owner must be using the right combat style to get the effect
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import buff.BuffConditions;
import combatent.Combatent;

public class BlackMaskOneStyle extends ConstantFillInBuff{

    private BuffCondition condition;
    private double multiplier;
    
    public BlackMaskOneStyle(BuffName name, double multiplier,PrimaryCombatStyle combatStyle) {
        super(name);
        this.multiplier = multiplier;
        this.condition = new BuffConditions(new OpponentWeakToBuffCondition(BuffName.Black_Mask),new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle));
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