package calculations;

import combatStyle.OffensiveCombatStyle;

public class BleedHitSlantedAverage extends BleedHit{
    private double chanceOfHittingMin;
    
    public BleedHitSlantedAverage(OffensiveCombatStyle cbs, double minDamage, double maxDamage) {
        super(cbs,minDamage,maxDamage);
        this.chanceOfHittingMin = minDamage/maxDamage;
    }

    @Override//bleeds do not crit
    public double getAveDamageWithCap() {
        double nonMinChance = 1-chanceOfHittingMin;
        double minDamageWithCap = applyNoCritHitCap(getMinDamage());
        double maxDamageWithCap = applyNoCritHitCap(getMaxDamage());
        return (minDamageWithCap*chanceOfHittingMin+((minDamageWithCap+maxDamageWithCap)/2)*nonMinChance);
    }
    
    @Override
    public String toString() {
        return "BleedHitSlantedAverage: min " + getMinDamage() + ",max " + getMaxDamage();
    }
}
