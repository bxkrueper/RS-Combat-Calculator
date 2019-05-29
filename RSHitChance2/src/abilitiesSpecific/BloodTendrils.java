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
import calculations.BleedHitSlantedAverage;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.DamageMode;

public class BloodTendrils extends BaseAbility{

    private PercentageRange range;
    
    public BloodTendrils(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff) {
        super(name, category, level, type, requirement, cooldown, buff);
        this.range = PercentageRangeFlyweight.getPercentageRange(36, 180);
    }

    public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
        return new BloodTendrils(name,category,level,type,requirement,cooldown,buff);
    }

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return 4;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        if(hitNumber==0) {
            return range.getMultiplier(damageMode);
        }else {
            return range.getMultiplier(damageMode)/2;
        }
    }

    @Override
    public List<Hit> generateBaseDamageHitList(double baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(4);
        hitList.add(new BleedHitSlantedAverage(combatStyle,(baseDamage*range.getMin()),(baseDamage*range.getMax())));
        for(int i=0;i<3;i++) {
            hitList.add(new BleedHitSlantedAverage(combatStyle,(baseDamage*range.getMin()/2),(baseDamage*range.getMax()/2)));
        }
        return hitList;
    }

}