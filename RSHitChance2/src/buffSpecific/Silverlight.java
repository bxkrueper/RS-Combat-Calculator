package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class Silverlight extends ConstantFillInBuff{
    
    private BuffCondition condition;
    
    public Silverlight() {
        super(BuffName.Silverlight);
        this.condition = new OpponentWeakToBuffCondition(getName());
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner,opponent)){
            return 1.25+0.01*Math.max((owner.getBaseAccuracyLevel()+owner.getBasePowerLevel())/2-opponent.getBaseDefenseLevel(), 0);
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
    }
    
    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        if(condition.passes(owner,opponent)){
            return 20;
        }else{
            return NullBuff.getInstance().getAddToFinalHitChance(owner, opponent);
        }
    }
}
