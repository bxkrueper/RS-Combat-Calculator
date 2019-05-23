package main;

import combatStyle.CombatStyle;
import combatStyle.OffensiveCombatStyle;

public abstract class Hit {

	private OffensiveCombatStyle cbs;
	
	public Hit(OffensiveCombatStyle cbs) {
		this.cbs = cbs;
	}
	
	public CombatStyle getCombatStyle() {
		return cbs;
	}
	
	
	public final double getDamage(DamageMode mode) {
	    switch(mode) {
        case MAX:
            return getMaxDamage();
        case AVE:
            return getAveDamage();
        case MIN:
            return getMinDamage();
        default:
            System.out.println("Hit: unrecognized damage mode!");
            return -1;
        }
	}
	public abstract double getMaxDamage();
	public abstract double getAveDamage();
	public abstract double getMinDamage();
	
	public abstract void setMinDamage(double minDamage);

    public abstract void setMaxDamage(double maxDamage);
	
	
	
	public abstract void applyEffectMultiplier(double multiplier);//multiplication boosts, and add from boosted levels, and precise and equilibrium
	
	public abstract void applyAutoAttackMultiplier(double multiplier);//for things that only affect auto attacks like balmung

    public abstract void applyDamageFromExtraPowerLevels(int extraPowerLevels);
    
    public abstract void multiplyMaxHit(double maxHitMultiplier);
    public abstract void multiplyMinHit(double minHitMultiplier);

}
