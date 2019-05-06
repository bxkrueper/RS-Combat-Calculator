package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;
import combatent.Combatent;

public class Overload extends ConstantFillInBuff{

    private double percentageOfTotalToAdd;
    private int add;
    
    public Overload(BuffName name, double percentageOfTotalToAdd, int add) {
        super(name);
        this.percentageOfTotalToAdd = percentageOfTotalToAdd;
        this.add = add;
    }
    
    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return owner.getBaseAccuracyLevel()*percentageOfTotalToAdd+add;
    }
    
    @Override
    public double addPowerLevelsToOwner(Combatent owner, Combatent opponent) {
        return owner.getBasePowerLevel()*percentageOfTotalToAdd+add;
    }

    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return owner.getBaseDefenseLevel()*percentageOfTotalToAdd+add;
    }
    
    @Override
    public double addVisibleAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        return addAccuracyLevelsToOwner(owner,opponent);
    }

}
