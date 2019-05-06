package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;
import combatent.Combatent;

public class DefensePotion extends ConstantFillInBuff{
    
    private double percentageOfTotalToAdd;
    private int add;
    
    public DefensePotion(BuffName name, double percentageOfTotalToAdd, int add) {
        super(name);
        this.percentageOfTotalToAdd = percentageOfTotalToAdd;
        this.add = add;
    }
    
    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return owner.getBaseAccuracyLevel()*percentageOfTotalToAdd+add;
    }
}
