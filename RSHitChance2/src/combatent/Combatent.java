package combatent;
import abilities.Ability;
import buff.BuffFlyweight;
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
    double getAffinityTo(PrimaryCombatStyle cbs);
    int getBaseAccuracyLevel();//attack, ranged, or magic level
    int getNaturalAccuracy();//for players, this is levelFunction(weapon)*2.5.   Monsters have this value directly
    double getAccuracyPenaltyFromWrongArmor();
    int getBasePowerLevel();//strength for melee, same as accuracy for range and mage
    int getBaseDefenseLevel();
    double getNaturalArmor();
    double getNaturalAbsorbsion();//is applied separately and before buff damage modifiers. Value for 10% Absorbsion is .9
    Vulnerabilities getVulnerabilities();
    Buffs getBuffs();
    boolean canAttack(Combatent opponent);
    double getBaseDamage();
	Attack getAttack();
}
