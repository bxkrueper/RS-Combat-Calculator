package abilitiesSpecific;

import java.util.ArrayList;
import java.util.List;

import abilities.Ability;
import abilities.AbilityCategory;
import abilities.AbilityRequirement;
import abilities.AbilityType;
import abilities.BaseAbility;
import abilities.PercentageRange;
import abilities.PercentageRangeFlyweight;
import buff.Buff;
import calculations.AbilityHit;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.DamageMode;

public class Onslaught extends BaseAbility{

    private static final int MAX_NUMBER_OF_HITS = 26;
    private PercentageRange baseRange;
    private PercentageRange increaseRange;
    
    public Onslaught(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff) {
        super(name, category, level, type, requirement, cooldown,buff);
        this.baseRange = PercentageRangeFlyweight.getPercentageRange(33, 150);
        this.increaseRange = PercentageRangeFlyweight.getPercentageRange(11, 33);
    }

    public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
        return new Onslaught(name,category,level,type,requirement,cooldown,buff);
    }

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return MAX_NUMBER_OF_HITS;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        return baseRange.getMultiplier(damageMode)+increaseRange.getMultiplier(damageMode)*hitNumber;
    }

    @Override
    public List<Hit> generateBaseDamageHitList(double baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(MAX_NUMBER_OF_HITS);
        for(int i=0;i<MAX_NUMBER_OF_HITS;i++) {
            double minMultiplier = baseRange.getMin()+increaseRange.getMin()*i;
            double maxMultiplier = baseRange.getMax()+increaseRange.getMax()*i;
            Hit hit = new AbilityHit(combatStyle,baseDamage*minMultiplier,baseDamage*maxMultiplier);
            hit.setHitCapNoCrit(30000);
            hit.setHitCapWithCrit(30000);
            ////////////////////////////////////onslaught always visably crits
            hitList.add(hit);
      //////not affected by dmg ults
        }
        return hitList;
    }

}