package combatent;
/*
 * //anyone that plans on fighting. Intended subclasses are player and monster
 */
import buff.Buffs;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;


public interface Combatent {
    String getName();
    Stats getStats();
    OffensiveCombatStyle getCombatStyle();
    int getAffinityTo(PrimaryCombatStyle cbs);
    int getBaseAccuracyLevel();
    int getNaturalAccuracy();//for players, this is levelFunction(weapon)*2.5.   Monsters have this value directly
    int getAccuracyPenaltyFromWrongArmor();
    int getBasePowerLevel();//strength for melee, same as accuracy for range and mage
    int getBaseDefenseLevel();
    int getNaturalArmor();
    Vulnerabilities getVulnerabilities();
    Buffs getBuffs();
    boolean canAttack(Combatent opponent);
}
