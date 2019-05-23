package buffSpecific;

import java.util.List;

import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.DontShowOnBuffBar;
import combatStyle.CombatStyle;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.BleedHit;
import main.BleedHitSlantedAverage;
import main.Hit;

public class Dismember extends ConstantFillInBuff implements DontShowOnBuffBar{

    public Dismember() {
        super(BuffName.Dismember);
    }
    
    @Override
    public void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        if(owner.getBuffs().contains(BuffName.Strength_Cape_Perk)) {
            double minHit = list.get(0).getMinDamage();
            double maxHit = list.get(0).getMaxDamage();
            OffensiveCombatStyle cbs = owner.getCombatStyle();
            list.add(new BleedHitSlantedAverage(cbs,minHit,maxHit));
            list.add(new BleedHitSlantedAverage(cbs,minHit,maxHit));
            list.add(new BleedHitSlantedAverage(cbs,minHit,maxHit));
        }
    }

}
