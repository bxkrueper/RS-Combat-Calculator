package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class UpgradedSilverlight extends ConstantFillInBuff{
private BuffCondition condition;
    
    public UpgradedSilverlight() {
        super(BuffName.Upgraded_Silverlight);
        this.condition = new OpponentWeakToBuffCondition(BuffName.Silverlight);
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner,opponent)){
            return 1.5+0.01*Math.max((owner.getBaseAccuracyLevel()+owner.getBasePowerLevel())/2-opponent.getBaseDefenseLevel()*2, 0);
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
    }
    
    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        if(condition.passes(owner,opponent)){
            return 30;
        }else{
            return NullBuff.getInstance().getAddToFinalHitChance(owner, opponent);
        }
    }
}
