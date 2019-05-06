package buffSpecific;

/*
 * for ranged and magic potions where the stat is both the accuracy level and power level
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class AccuracyPowerPotion extends ConstantFillInBuff{
    
    private double percentageOfTotalToAdd;
    private int add;
    private BuffCondition condition;
    
    public AccuracyPowerPotion(BuffName name, double percentageOfTotalToAdd, int add, PrimaryCombatStyle combatStyle) {
        super(name);
        this.percentageOfTotalToAdd = percentageOfTotalToAdd;
        this.add = add;
        this.condition = new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle);
    }
    
    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return owner.getBaseAccuracyLevel()*percentageOfTotalToAdd+add;
        }else{
            return 0.0;
        }
    }
    
    @Override
    public double addPowerLevelsToOwner(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return owner.getBaseAccuracyLevel()*percentageOfTotalToAdd+add;
        }else{
            return 0.0;
        }
    }
    
    @Override
    public double addVisibleAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return addAccuracyLevelsToOwner(owner,opponent);
    }
}
