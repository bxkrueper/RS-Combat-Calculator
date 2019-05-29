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
import calculations.BleedHitSlantedAverage;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.DamageMode;

public class BleedAbility extends BaseAbility{

	private PercentageRange range;
	private int numberOfHits;
	private double chanceOfHittingMin;
	private double averageMultiplier;
	
	public BleedAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff,PercentageRange range,int numberOfHits) {
		super(name, category, level, type, requirement, cooldown, buff);
		this.range = range;
		this.numberOfHits = numberOfHits;
		this.chanceOfHittingMin = range.getMin()/range.getMax();
		this.averageMultiplier = getAverageMultiplier(range,chanceOfHittingMin);
	}

	public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
		int numberOfHits = Integer.parseInt(mechanicParameters[1]);
		PercentageRange range = PercentageRangeFlyweight.getPercentageRange(Double.parseDouble(mechanicParameters[2]),Double.parseDouble(mechanicParameters[3]));
		return new BleedAbility(name,category,level,type,requirement,cooldown,buff,range,numberOfHits);
	}
	
	private static double getAverageMultiplier(PercentageRange range, double minChance) {
	    double nonMinChance = 1-minChance;
	    return range.getMin()*minChance+range.getAverage()*nonMinChance;
	}

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return numberOfHits;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        return range.getMultiplier(damageMode)/numberOfHits;
    }

    @Override
    public List<Hit> generateBaseDamageHitList(double baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(numberOfHits);
        for(int i=0;i<numberOfHits;i++) {
            hitList.add(new BleedHitSlantedAverage(combatStyle,baseDamage*range.getMin()/numberOfHits,baseDamage*range.getMax()/numberOfHits));
        }
        return hitList;
    }

}