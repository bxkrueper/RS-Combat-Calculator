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
import main.ConstantHit;
import main.DamageMode;
import main.Hit;
/*
 * does 6 hits. the last 5 are bleeds
 */
public class MassacreClass extends BaseAbility{
	
	private PercentageRange rangeForFirstHit;
	
	public MassacreClass(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff) {
		super(name, category, level, type, requirement, cooldown,buff);
		this.rangeForFirstHit = PercentageRangeFlyweight.getPercentageRange(37.6, 188);
	}

	public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
		return new MassacreClass(name,category,level,type,requirement,cooldown,buff);
	}

    @Override
    public int getNumberOfHits(DamageMode damageMode) {
        return 6;
    }

    @Override
    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
        if(hitNumber==0) {
            return rangeForFirstHit.getMultiplier(damageMode);
        }else {
            return 0.6266;
        }
    }

    @Override
    public List<Hit> generateBaseDamageHitList(int baseDamage, OffensiveCombatStyle combatStyle) {
        List<Hit> hitList = new ArrayList<>(6);
        hitList.add(new AbilityHit(combatStyle,baseDamage*rangeForFirstHit.getMin(),baseDamage*rangeForFirstHit.getMax()));
        for(int i=0;i<5;i++) {
            hitList.add(new ConstantHit(combatStyle,(int) (baseDamage*0.6266)));//no range for the bleeds
        }
        return hitList;
    }

}