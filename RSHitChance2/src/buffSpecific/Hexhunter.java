package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class Hexhunter extends ConstantFillInBuff{

    private BuffCondition condition;
    
    public Hexhunter() {
        super(BuffName.Hexhunter);
        this.condition = new OpponentWeakToBuffCondition(getName());
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.125;
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
        
    }
    
    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 10;
        }else{
            return NullBuff.getInstance().getAddToFinalHitChance(owner, opponent);
        }
        
    }

}