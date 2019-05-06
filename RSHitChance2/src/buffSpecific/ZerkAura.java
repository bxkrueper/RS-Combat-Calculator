package buffSpecific;
/*
 * for bezerker, reckless, and maniacle
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class ZerkAura extends ConstantFillInBuff{

    private BuffCondition condition;
    
    public ZerkAura(BuffName name, PrimaryCombatStyle combatStyle) {
        super(name);
        this.condition = new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle);
    }
    
    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return (owner.getBaseAccuracyLevel()*0.1);
        }else{
            return 0;
        }
        
    }

    @Override
    public double addPowerLevelsToOwner(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return (owner.getBasePowerLevel()*0.1);
        }else{
            return 0;
        }
        
    }

    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return owner.getBaseDefenseLevel()*-0.15;
    }
    
    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.1;
        }else{
            return 1.0;
        }
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.1;
        }else{
            return 1.0;
        }
    }
    
    @Override
    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
        return 1.15;
    }
    
    @Override
    public double addVisibleAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return addAccuracyLevelsToOwner(owner,opponent);
    }
}