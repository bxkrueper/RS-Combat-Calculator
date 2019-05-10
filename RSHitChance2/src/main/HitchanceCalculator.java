package main;
/*
 * takes into account all factors about that attacker and defender to determine the attacker's chance to hit.
 * use the calculateHitchance method to update the fields, then use the getter methods to get the calculation you need
 */
import buff.Buff;
import buff.Buffs;
import combatStyle.AlwaysHits;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Typeless;
import combatent.Combatent;

public class HitchanceCalculator {
    
	//intermediary calculations
    private static int attackerTotalAccuracy;
    private static int defenderTotalDefense;
    private static int defenderAffinityRaise;
    //final hit chance
    private static double hitChance;//100 and above always hits. not guaranteed to be between 0 and 100

    public static void calculateHitchance(Combatent attacker, Combatent defender) {
        if(!attacker.canAttack(defender)){
            System.out.println(attacker + " Can't attack!");
            hitChance = 0;
            return;
        }
        
        OffensiveCombatStyle attackerCombatStyle = attacker.getCombatStyle();
        System.out.println(attackerCombatStyle);
        if((attackerCombatStyle instanceof AlwaysHits)){
            attackerTotalAccuracy = 0;
            setTotalAffinityRaise(attacker,defender);
            setTotalBaseDefense(attacker,defender);
            hitChance = 100;
            return;
        }
        
        int defenderAffinity = defender.getAffinityTo((PrimaryCombatStyle) attacker.getCombatStyle());
        setTotalAffinityRaise(attacker,defender);
        defenderAffinity+=defenderAffinityRaise;
        System.out.println("defenderAffinity: " + defenderAffinity);
        
        setTotalBaseAccuracy(attacker,defender);
        System.out.println("attackerTotalBaseAccuracy: " + attackerTotalAccuracy);
        
        setTotalBaseDefense(attacker,defender);
        System.out.println("defenderTotalBaseDefense: " + defenderTotalDefense);
        
        double hitchanceAdd = getTotalHitchanceAdd(attacker,defender);
        System.out.println("hitchanceAdd: " + hitchanceAdd);
        
        hitChance = defenderAffinity*((double) attackerTotalAccuracy/defenderTotalDefense) + hitchanceAdd;
    }
    
    private static double getTotalHitchanceAdd(Combatent attacker, Combatent defender) {
        return attacker.getBuffs().getAddToFinalHitChance(attacker, defender);
    }

    private static void setTotalAffinityRaise(Combatent attacker, Combatent defender) {
        Buff defenderBuffs = defender.getBuffs();
        
        //no attacker debuffs to affinity?
        
        defenderAffinityRaise = defenderBuffs.getOwnerAffinityDebuff(defender, attacker);;
    }

    private static void setTotalBaseAccuracy(Combatent attacker, Combatent defender) {
        Buff attackerBuffs = attacker.getBuffs();
        Buff defenderBuffs = defender.getBuffs();
        int accuracyLevel = attacker.getBaseAccuracyLevel();
        System.out.println("base accuracy Level: " + accuracyLevel);
        accuracyLevel+=attackerBuffs.addAccuracyLevelsToOwner(attacker, defender);//ex: pots, prayers, zerk aura
        accuracyLevel+=defenderBuffs.addAccuracyLevelsToOpponent(defender, attacker);//ex: turmoil
        System.out.println("accuracyLevel after buffs: " + accuracyLevel);
        
        int naturalAccuracy = attacker.getNaturalAccuracy();
        System.out.println("naturalAccuracy: " + naturalAccuracy);
        System.out.println("accuracy multiplier: " + attackerBuffs.getAccuracyMultiplier(attacker, defender));
        naturalAccuracy*=attackerBuffs.getAccuracyMultiplier(attacker, defender);
        
        attackerTotalAccuracy = levelFunction(accuracyLevel)+ naturalAccuracy;
        
        int accuracyPenalty = attacker.getAccuracyPenaltyFromWrongArmor();
        System.out.println("accuracyPenalty: " + accuracyPenalty);
        
        attackerTotalAccuracy-=accuracyPenalty;
    }

    private static void setTotalBaseDefense(Combatent attacker, Combatent defender) {
        Buff attackerBuffs = attacker.getBuffs();
        Buff defenderBuffs = defender.getBuffs();
        int defenseLevel = defender.getBaseDefenseLevel();
        System.out.println("base defense Level: " + defenseLevel);
        defenseLevel+=defenderBuffs.addDefenseLevelsToOwner(defender, attacker);//ex: pots, prayers, zerk aura
        defenseLevel+=attackerBuffs.addDefenseLevelsToOpponent(attacker, defender);//ex: turmoil
        System.out.println("defenseLevel after buffs: " + defenseLevel);
        int naturalArmor = defender.getNaturalArmor();
        System.out.println("naturalArmor: " + naturalArmor);
        naturalArmor*=defenderBuffs.getArmorMultiplier(defender, attacker);
        
        defenderTotalDefense = levelFunction(defenseLevel) + naturalArmor;
    }

    

    public static int levelFunction(int level){
        return (int) (0.0008 *Math.pow(level, 3)) + 4 * level + 40;
    }

    public static int getAttackerTotalAccuracy() {
        return attackerTotalAccuracy;
    }

    public static int getDefenderTotalDefense() {
        return defenderTotalDefense;
    }

    public static int getDefenderAffinityRaise() {
        return defenderAffinityRaise;
    }

    public static double getHitChance() {
        return hitChance;
    }

}
