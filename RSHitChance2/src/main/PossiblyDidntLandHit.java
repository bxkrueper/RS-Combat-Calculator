package main;

import combatStyle.OffensiveCombatStyle;
//min damage is 0
//for dragon breath's combust effect with the dragon rider ammulet
public class PossiblyDidntLandHit extends Hit{
    private double chanceOfLanding;
    private double maxDamage;
    
    public PossiblyDidntLandHit(OffensiveCombatStyle cbs, double chanceOfLanding, double maxDamage) {
        super(cbs);
        this.chanceOfLanding = chanceOfLanding;
        this.maxDamage = maxDamage;
    }

    @Override
    public double getMaxDamage() {
        return maxDamage;
    }

    @Override
    public double getAveDamage() {
        return maxDamage*chanceOfLanding;
    }

    @Override
    public double getMinDamage() {
        return 0;
    }

    @Override
    public void applyEffectMultiplier(double multiplier) {
    }

    @Override
    public void applyAutoAttackMultiplier(double multiplier) {//not an auto attack, so no effect
    }

    @Override
    public void applyDamageFromExtraPowerLevels(int extraPowerLevels) {
    }

    @Override
    public void multiplyMaxHit(double maxHitMultiplier) {
    }


    @Override
    public void multiplyMinHit(double minHitMultiplier) {
    }
    
    @Override
    public void setMinDamage(double minDamage) {
        //min damage is always 0
    }

    @Override
    public void setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return "PossiblyDidntLandHit [chanceOfLanding=" + chanceOfLanding + ", maxDamage=" + maxDamage + "]";
    }
    
}
