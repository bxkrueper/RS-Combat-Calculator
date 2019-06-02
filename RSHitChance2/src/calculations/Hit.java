package calculations;

import combatStyle.CombatStyle;
import combatStyle.OffensiveCombatStyle;
import main.DamageMode;

public abstract class Hit {

	private OffensiveCombatStyle cbs;
	private double forceCritChance;// ex: .02 for 2%
	private int hitCapNoCrit;
	private int hitCapWithCrit;
	
	public Hit(OffensiveCombatStyle cbs) {
		this.cbs = cbs;
		this.forceCritChance = 0.0;
		this.hitCapNoCrit = 10000;
		this.hitCapWithCrit = 12000;
	}
	
	public int getHitCapNoCrit() {
        return hitCapNoCrit;
    }

    public void setHitCapNoCrit(int hitCapNoCrit) {
        this.hitCapNoCrit = hitCapNoCrit;
    }

    public int getHitCapWithCrit() {
        return hitCapWithCrit;
    }

    public void setHitCapWithCrit(int hitCapWithCrit) {
        this.hitCapWithCrit = hitCapWithCrit;
    }

    public CombatStyle getCombatStyle() {
    	return cbs;
    }

    public double getCritChance() {
        return forceCritChance;
    }

    public void setCritChance(double critChance) {
        this.forceCritChance = critChance;
    }
    
    public void addToCritChance(double toAdd) {
        this.forceCritChance += toAdd;
    }

    public final double getDamage(DamageMode mode) {
	    switch(mode) {
        case MAX:
            return getMaxDamageWithCap();
        case AVE:
            return getAveDamageWithCap();
        case MIN:
            return getMinDamageWithCap();
        default:
            System.out.println("Hit: unrecognized damage mode!");
            return -1;
        }
	}
	public double getAveDamageWithCap() {
        double chanceOfNotForceCritting = 1-forceCritChance;
        
        //ave damage if force crit
        double minDamageIfForceCrit = applyCritHitCap(getMinDamage()+0.95*(getMaxDamage()-getMinDamage()));
        double maxDamageIfForceCrit = applyCritHitCap(getMaxDamage());
        double aveDamageIfForceCrit = (minDamageIfForceCrit+maxDamageIfForceCrit)/2.0;
        
        double aveDamageIfNotForceCrit = getAveDamageIfNotForceCrit();
        
        return aveDamageIfForceCrit*forceCritChance+aveDamageIfNotForceCrit*chanceOfNotForceCritting;
    }
	
	private double getAveDamageIfNotForceCrit() {
	    double chanceOfNaturallyHittingCritRange = 0.05;
	    double chanceOfNotNaturallyHittingCritRange = 1-chanceOfNaturallyHittingCritRange;
	    double damageAtCritRangeThreshold = getMinDamage()+0.95*(getMaxDamage()-getMinDamage());
	    
	    //ave if hit crit range naturally
	    double minDamageIfNaturallyHitCritRange = applyCritHitCap(damageAtCritRangeThreshold);
	    double maxDamageIfNaturallyHitCritRange = applyCritHitCap(getMaxDamage());
	    double aveDamageIfNaturallyHitCritRange = (minDamageIfNaturallyHitCritRange+maxDamageIfNaturallyHitCritRange)/2.0;
	    
	    //ave if did not hit crit range naturally
        double minDamageIfNotNaturallyHitCritRange = applyNoCritHitCap(getMinDamage());
        double maxDamageIfNotNaturallyHitCritRange = applyNoCritHitCap(damageAtCritRangeThreshold);
        double aveDamageIfNotNaturallyHitCritRange = (minDamageIfNotNaturallyHitCritRange+maxDamageIfNotNaturallyHitCritRange)/2.0;
        
        return aveDamageIfNaturallyHitCritRange*chanceOfNaturallyHittingCritRange+aveDamageIfNotNaturallyHitCritRange*chanceOfNotNaturallyHittingCritRange;
    }

    protected double applyCritHitCap(double damage) {
	    if(damage>hitCapWithCrit) {
	        return hitCapWithCrit;
	    }else {
	        return damage;
	    }
	}
	
    protected double applyNoCritHitCap(double damage) {
        if(damage>hitCapNoCrit) {
            return hitCapNoCrit;
        }else {
            return damage;
        }
    }

	public abstract double getMaxDamage();//without hit caps
//	public double getAveDamage() {//bleed abilities override this
//	    double chanceOfNotForceCritting = 1-forceCritChance;
//	    double chanceOfVisibleCrit = forceCritChance+0.05*chanceOfNotForceCritting;
//	    double minDamage = getMinDamage();////////////////////////////does not take into account hit caps
//	    double maxDamage = getMaxDamage();////////////////////////////does not take into account hit caps
//	    double normalAve = ((minDamage+maxDamage)/2);
//        
//        double critMin = minDamage+0.95*(maxDamage-minDamage);
//        double critAve = ((critMin+maxDamage)/2);
//        double aveDamage = normalAve*chanceOfNotForceCritting+critAve*forceCritChance;
//        return aveDamage;
//	}
	public abstract double getMinDamage();//without hit caps
	
	public abstract void setMinDamage(double minDamage);

    public abstract void setMaxDamage(double maxDamage);
	
	
	
	public abstract void applyEffectMultiplier(double multiplier);//multiplication boosts, and add from boosted levels, and precise and equilibrium
	
	public abstract void applyAutoAttackMultiplier(double multiplier);//for things that only affect auto attacks like balmung

    public abstract void applyDamageFromExtraPowerLevels(int extraPowerLevels);
    
    public abstract void multiplyMaxHit(double maxHitMultiplier);
    public abstract void multiplyMinHit(double minHitMultiplier);

    
    public double getMaxDamageWithCap() {
        if(getMaxDamage()>this.getHitCapWithCrit()) {
            return this.getHitCapWithCrit();
        }else {
            return getMaxDamage();
        }
    }

    
    public double getMinDamageWithCap() {
        if(getMinDamage()>this.getHitCapNoCrit()) {
            return this.getHitCapNoCrit();
        }else {
            return getMinDamage();
        }
    }

}
