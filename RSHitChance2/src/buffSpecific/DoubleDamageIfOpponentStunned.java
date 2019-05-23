package buffSpecific;

import java.util.List;

import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.DontShowOnBuffBar;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.BleedHitSlantedAverage;
import main.Hit;

public class DoubleDamageIfOpponentStunned extends ConstantFillInBuff {///implements DontShowOnBuffBar

    public DoubleDamageIfOpponentStunned() {
        super(BuffName.Double_Damage_If_Opponent_Stunned);
    }
    
    @Override
    public void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        if(opponent.getBuffs().contains(BuffName.Stun)) {
            Hit hit = list.get(0);
            hit.applyEffectMultiplier(2.0);//this would not work on bleeds and constant damage, but this buff only applies to abilities that use normal hits
        }
    }

}
