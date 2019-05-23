package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class TokKulZo extends ConstantFillInBuff{

    private BuffCondition condition;
    
    public TokKulZo() {
        super(BuffName.TokKulZo);
        this.condition = new OpponentWeakToBuffCondition(BuffName.TokKulZo);
        this.setPictureName("TokKul-Zo");
    }
    

    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.1;
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
    }
    
    

}