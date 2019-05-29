package combatent;

import java.util.List;

import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import main.DamageMode;

public interface Attack {
	OffensiveCombatStyle getCombatStyle();
    String getName();
	List<Hit> generateBaseDamageHitList();
//	double getMultiplierToBaseHit(int hitNumber,DamageMode damageMode);
//	boolean canApplyEffects(int hitNumber);//multiplication boosts and add from boosted levels
//    boolean canApplyAutoAttackEffectMultiplier(int hitNumber);//for things that only affect auto attacks like balmung
//    int getBaseDamage();//max hit for autos, 100% ability damage for abilities
}
