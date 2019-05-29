package buffSpecific;

import java.util.List;

import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.DontShowOnBuffBar;
import calculations.AbilityHit;
import calculations.Hit;
import combatent.Combatent;

//makes the base dmg of slice go from 30%-120% to 80%-146%
public class Slice extends ConstantFillInBuff{

    private static final double MIN_HIT_MULT = 8.0/3;
    private static final double MAX_HIT_MULT = 146.0/120;
    
    public Slice() {
        super(BuffName.Slice);
    }
    
    @Override
    public void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        if(opponent.getBuffs().contains(BuffName.Stun)) {
            AbilityHit hit = (AbilityHit) list.get(0);
            hit.setMinDamage(hit.getMinDamage()*MIN_HIT_MULT);
            hit.setMaxDamage(hit.getMaxDamage()*MAX_HIT_MULT);
        }
    }

}
