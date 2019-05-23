package main;

import combatStyle.OffensiveCombatStyle;

public class BleedHitSlantedAverage extends BleedHit{
    private double chanceOfHittingMin;
    
    public BleedHitSlantedAverage(OffensiveCombatStyle cbs, double minDamage, double maxDamage) {
        super(cbs,minDamage,maxDamage);
        this.chanceOfHittingMin = minDamage/maxDamage;
    }

    @Override
    public double getAveDamage() {
        double nonMinChance = 1-chanceOfHittingMin;
        return (getMinDamage()*chanceOfHittingMin+((getMinDamage()+getMaxDamage())/2)*nonMinChance);
    }
    
    @Override
    public String toString() {
        return "BleedHitSlantedAverage: min " + getMinDamage() + ",max " + getMaxDamage();
    }
}
