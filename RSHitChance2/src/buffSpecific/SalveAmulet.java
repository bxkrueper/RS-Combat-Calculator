package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class SalveAmulet extends ConstantFillInBuff{
    private static final BuffCondition condition = new OpponentWeakToBuffCondition(BuffName.Salve_Amulet);
    private double multiplier;
    
    public SalveAmulet(BuffName name, double multiplier) {
        super(name);
        this.multiplier = multiplier;
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
