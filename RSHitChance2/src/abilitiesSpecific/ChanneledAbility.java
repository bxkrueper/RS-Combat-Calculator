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
import combatStyle.OffensiveCombatStyle;
import combatent.Combatent;
import main.AbilityHit;
import main.BleedHitSlantedAverage;
import main.DamageMode;
import main.Hit;

public class ChanneledAbility extends BaseAbility{

	private PercentageRange[] ranges;
	
	public ChanneledAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff,PercentageRange[] ranges) {
		super(name, category, level, type, requirement, cooldown,buff);
		this.ranges = ranges;
	}

	public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
		int numberOfHitsChanneled = Integer.parseInt(mechanicParameters[1]);
		PercentageRange[] rangesChanneled = new PercentageRange[numberOfHitsChanneled];
		for(int i=0;i<numberOfHitsChanneled;i++) {
			rangesChanneled[i] = PercentageRangeFlyweight.getPercentageRange(Double.parseDouble(mechanicParameters[2+2*i]),Double.parseDouble(mechanicParameters[3+2*i]));
		}
		return new ChanneledAbility(name,category,level,type,requirement,cooldown,buff,rangesChanneled);
	}

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return ranges.length;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        return ranges[hitNumber].getMultiplier(damageMode);
    }

    @Override
    public List<Hit> generateBaseDamageHitList(int baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(ranges.length);
        for(int i=0;i<ranges.length;i++) {
            hitList.add(new AbilityHit(combatStyle,baseDamage*ranges[i].getMin(),baseDamage*ranges[i].getMax()));
        }
        return hitList;
    }

}