package calculations;

import combatStyle.OffensiveCombatStyle;

public class BleedHit extends Hit{
    private double minDamage;
    private double maxDamage;
    private double chanceOfHittingMin;
    
    public BleedHit(OffensiveCombatStyle cbs, double minDamage, double maxDamage) {
        super(cbs);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.chanceOfHittingMin = minDamage/maxDamage;
    }

    @Override
    public double getMaxDamage() {
        return maxDamage;
    }
    
    //bleeds only use the normal hit cap
    public double getMaxDamageWithCap() {
        if(getMaxDamage()>this.getHitCapNoCrit()) {
            return this.getHitCapNoCrit();
        }else {
            return getMaxDamage();
        }
    }

    
    @Override//bleeds do not crit
    public double getAveDamageWithCap() {
        return (applyNoCritHitCap(minDamage)+applyNoCritHitCap(maxDamage))/2;
    }

    @Override
    public double getMinDamage() {
        return minDamage;
    }
    
    @Override
    public void setCritChance(double critChance) {
        //bleeds do not crit
    }
    
    @Override
    public void addToCritChance(double toAdd) {
      //bleeds do not crit
    }

    @Override
    public void applyEffectMultiplier(double multiplier) {//no effect
    }

    @Override
    public void applyAutoAttackMultiplier(double multiplier) {//no effect
    }

    @Override
    public void applyDamageFromExtraPowerLevels(int extraPowerLevels) {//no effect
    }

    @Override
    public void multiplyMaxHit(double maxHitMultiplier) {//no effect
    }

    @Override
    public void multiplyMinHit(double minHitMultiplier) {//no effect
    }
    
    @Override
    public void setMinDamage(double minDamage) {
        this.minDamage = minDamage;
    }

    @Override
    public void setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
    }
    
    @Override
    public String toString() {
        return "Bleed Hit: min " + minDamage + ",max " + maxDamage;
    }
}
