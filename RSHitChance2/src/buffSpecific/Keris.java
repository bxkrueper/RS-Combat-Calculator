package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class Keris extends ConstantFillInBuff{

    private BuffCondition condition;
    
    public Keris() {
        super(BuffName.Keris);
        this.condition = new OpponentWeakToBuffCondition(getName());
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.333;
            //keris has a random chance to to triple damage that the calculator won't bother with
//            if(owner.getBuffs().contains(BuffName.Desert_Amulet_3)){
//                return 1.333;
//                if(Math.random()<0.05){
//                    return 3.0;
//                }else{
//                    return 1.333;
//                }
//            }else{
//                return 1.333;
//                if(Math.random()<0.025){
//                    return 3.0;
//                }else{
//                    return 1.333;
//                }
//            }
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
        
    }
    
    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            if(owner.getBuffs().contains(BuffName.Desert_Amulet_3)){
                return 25;
            }else{
                return 15;
            }
        }else{
            return NullBuff.getInstance().getAddToFinalHitChance(owner, opponent);
        }
        
    }

}
