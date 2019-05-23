package main;

import combatStyle.OffensiveCombatStyle;

//standard ability hit with separate min and max damabe
public class AbilityHit extends Hit{

    private double minDamage;
    private double maxDamage;
    
    public AbilityHit(OffensiveCombatStyle cbs, double minDamage, double maxDamage) {
        super(cbs);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public double getMaxDamage() {
        return maxDamage;
    }

    @Override
    public double getAveDamage() {
        return ((minDamage+maxDamage)/2);
    }

    @Override
    public double getMinDamage() {
        return minDamage;
    }

    @Override
    public void applyEffectMultiplier(double multiplier) {
        minDamage*=multiplier;
        maxDamage*=multiplier;
    }

    @Override
    public void applyAutoAttackMultiplier(double multiplier) {//not an auto attack, so no effect
    }

    @Override
    public void applyDamageFromExtraPowerLevels(int extraPowerLevels) {
        minDamage+= extraPowerLevels*DamageCalculator.endDamageMultForExtraPowerLevels(DamageMode.MIN);
        maxDamage+= extraPowerLevels*DamageCalculator.endDamageMultForExtraPowerLevels(DamageMode.MAX);
    }

    @Override
    public void multiplyMaxHit(double maxHitMultiplier) {
        maxDamage*=maxHitMultiplier;
    }


    @Override
    public void multiplyMinHit(double minHitMultiplier) {
        minDamage*=minHitMultiplier;
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
        return "Ability Hit: min " + minDamage + ",max " + maxDamage;
    }
}