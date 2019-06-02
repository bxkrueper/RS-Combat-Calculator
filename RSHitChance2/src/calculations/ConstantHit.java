package calculations;

import combatStyle.OffensiveCombatStyle;

//flat hit with no range that is not affected by any effects
public class ConstantHit extends Hit{
    
    private double damage;
    
    public ConstantHit(OffensiveCombatStyle cbs, double damage) {
        super(cbs);
        this.damage = damage;
    }

    @Override
    public double getMaxDamage() {
        return damage;
    }

    @Override//no crits here/doesn't matter
    public double getAveDamageWithCap() {
        return damage;
    }

    @Override
    public double getMinDamage() {
        return damage;
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
    public String toString() {
        return "Constant Hit: " + damage;
    }

    @Override
    public void setMinDamage(double minDamage) {
        this.damage = minDamage;
    }

    @Override
    public void setMaxDamage(double maxDamage) {
        this.damage = maxDamage;
    }
}
