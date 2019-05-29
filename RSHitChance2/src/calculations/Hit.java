package calculations;

import combatStyle.CombatStyle;
import combatStyle.OffensiveCombatStyle;
import main.DamageMode;

public abstract class Hit {

	private OffensiveCombatStyle cbs;
	private double critChance;// ex: .02 for 2%
	
	public Hit(OffensiveCombatStyle cbs) {
		this.cbs = cbs;
		this.critChance = 0.0;
	}
	
	public CombatStyle getCombatStyle() {
    	return cbs;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }
    
    public void addToCritChance(double toAdd) {
        this.critChance += toAdd;
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
	public double getAveDamage() {
	    double minDamage = getMinDamage();
	    double maxDamage = getMaxDamage();
	    double normalAve = ((minDamage+maxDamage)/2);
	    if(critChance==0.0) {
            return normalAve;
        }else {
            double chanceOfNotCritting = 1-critChance;
            double critMin = minDamage+0.95*(maxDamage-minDamage);
            double critAve = ((critMin+maxDamage)/2);
            double aveDamage = normalAve*chanceOfNotCritting+critAve*critChance;
            return aveDamage;
        }
	}
	public abstract double getMinDamage();
	
	public abstract void setMinDamage(double minDamage);

    public abstract void setMaxDamage(double maxDamage);
	
	
	
	public abstract void applyEffectMultiplier(double multiplier);//multiplication boosts, and add from boosted levels, and precise and equilibrium
	
	public abstract void applyAutoAttackMultiplier(double multiplier);//for things that only affect auto attacks like balmung

    public abstract void applyDamageFromExtraPowerLevels(int extraPowerLevels);
    
    public abstract void multiplyMaxHit(double maxHitMultiplier);
    public abstract void multiplyMinHit(double minHitMultiplier);

}
