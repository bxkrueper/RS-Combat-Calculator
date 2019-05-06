package buffSpecific;

/*
 * there are two types of dragonbane ammo. arrows and bolts. Effect only counts if owner is using the right specific combat style
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.BuffConditions;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import buff.OwnerUsingSpecificCombatStyleCondition;
import buff.TrueBuffCondition;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;
import combatent.Combatent;

public class DragonbaneAmmo extends ConstantFillInBuff{

    private BuffCondition condition;
    
    public DragonbaneAmmo(BuffName name) {
        super(name);
        this.condition = new TrueBuffCondition();//if no combat style was given, it always applies the effect
    }
    
    public DragonbaneAmmo(BuffName name,PrimaryCombatStyle pcbs) {
        super(name);
        this.condition = new BuffConditions(new OpponentWeakToBuffCondition(BuffName.Dragonbane),new OwnerUsingSpecificCombatStyleCondition(pcbs));
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.25;
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
        
    }
    
    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 30;
        }else{
            return NullBuff.getInstance().getAddToFinalHitChance(owner, opponent);
        }
        
    }

}