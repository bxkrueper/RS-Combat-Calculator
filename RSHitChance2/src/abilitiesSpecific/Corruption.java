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
import calculations.BleedHit;
import calculations.BleedHitSlantedAverage;
import calculations.ConstantHit;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.DamageMode;

public class Corruption extends BaseAbility{
    
    private PercentageRange range;
    
    public Corruption(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff) {
        super(name, category, level, type, requirement, cooldown, buff);
        this.range = PercentageRangeFlyweight.getPercentageRange(33, 100);
    }

    public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
        return new Corruption(name,category,level,type,requirement,cooldown,buff);
    }

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return 5;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        return range.getMultiplier(damageMode)*(1-hitNumber*.2);
    }

    @Override
    public List<Hit> generateBaseDamageHitList(double baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(5);
        for(int i=0;i<5;i++) {
            hitList.add(new BleedHit(combatStyle,baseDamage*range.getMin()*(1-i*.2),baseDamage*range.getMax()*(1-i*.2)));
        }
        return hitList;
    }

}