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
import buff.BuffName;
import calculations.AbilityHit;
import calculations.BleedHitSlantedAverage;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.DamageMode;

public class DoubleHitAbility extends BaseAbility{

	private PercentageRange range1;
	private PercentageRange range2;
	
	public DoubleHitAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff,PercentageRange range1,PercentageRange range2) {
		super(name, category, level, type, requirement, cooldown,buff);
		this.range1 = range1;
		this.range2 = range2;
	}

	public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
		double minHit1 = Double.parseDouble(mechanicParameters[1]);
		double maxHit1 = Double.parseDouble(mechanicParameters[2]);
		double minHit2 = Double.parseDouble(mechanicParameters[3]);
		double maxHit2 = Double.parseDouble(mechanicParameters[4]);
		return new DoubleHitAbility(name,category,level,type,requirement,cooldown,buff,PercentageRangeFlyweight.getPercentageRange(minHit1,maxHit1),PercentageRangeFlyweight.getPercentageRange(minHit2,maxHit2));
	}

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return 2;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        if(hitNumber==0) {
            return range1.getMultiplier(damageMode);
        }else {
            return range2.getMultiplier(damageMode);
        }
    }

    @Override
    public List<Hit> generateBaseDamageHitList(double baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(2);
        hitList.add(new AbilityHit(combatStyle,baseDamage*range1.getMin(),baseDamage*range1.getMax()));
        hitList.add(new AbilityHit(combatStyle,baseDamage*range2.getMin(),baseDamage*range2.getMax()));
        return hitList;
    }
    
    

}