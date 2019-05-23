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
import main.DamageMode;
import main.Hit;

public class SimpleAbility extends BaseAbility{

	private PercentageRange range;
	
	public SimpleAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff,PercentageRange range) {
		super(name, category, level, type, requirement, cooldown,buff);
		this.range = range;
	}

	public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
		double min = Double.parseDouble(mechanicParameters[1]);
		double max = Double.parseDouble(mechanicParameters[2]);
		return new SimpleAbility(name,category,level,type,requirement,cooldown,buff,PercentageRangeFlyweight.getPercentageRange(min,max));
	}

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        return range.getMultiplier(damageMode);
    }
    
    @Override
    public List<Hit> generateBaseDamageHitList(int baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(1);
        hitList.add(new AbilityHit(combatStyle,baseDamage*range.getMin(),baseDamage*range.getMax()));
        return hitList;
    }

}
