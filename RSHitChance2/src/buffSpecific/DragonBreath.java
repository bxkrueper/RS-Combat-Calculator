package buffSpecific;

import java.util.List;

import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.DontShowOnBuffBar;
import calculations.BleedHit;
import calculations.Hit;
import calculations.PossiblyDidntLandHit;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;

public class DragonBreath extends ConstantFillInBuff implements DontShowOnBuffBar{

    public DragonBreath() {
        super(BuffName.Dragon_Breath);
    }
    
    @Override
    public void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        if(owner.getBuffs().contains(BuffName.Dragon_Rider_Amulet)) {
            list.get(0).applyEffectMultiplier(1.1);///////bleed hits based on original or multiplied damage?
            double minHit = list.get(0).getMinDamage();
            double maxHit = list.get(0).getMaxDamage();
            OffensiveCombatStyle cbs = owner.getCombatStyle();
            list.add(new PossiblyDidntLandHit(cbs,.1,maxHit*.1));
            list.add(new PossiblyDidntLandHit(cbs,.1,maxHit*.1));
            list.add(new PossiblyDidntLandHit(cbs,.1,maxHit*.1));
        }
    }

}
