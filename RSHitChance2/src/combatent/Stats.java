package combatent;

/*
 * Total Accuracy is calculated by = levelFunction(AccuracyLevel)+ naturalAccuracy. Similarly for defense
 * Copies of these values may be modified separately by buffs beforehand
 */
import combatStyle.PrimaryCombatStyle;

public interface Stats {
    int getBaseAttackLevel();
    int getBaseMeleeAccuracy();
    int getBaseRangedLevel();
    int getBaseRangedAccuracy();
    int getBaseMagicLevel();
    int getBaseMagicAccuracy();
    int getBaseStrengthLevel();
    int getBaseDefenseLevel();
    int getBaseArmour();
    
    int getAccuracyLevel(PrimaryCombatStyle cbs);
    int getAccuracy(PrimaryCombatStyle cbs);
    int getPowerLevel(PrimaryCombatStyle combatStyle);//players only for monsters, this just returns 0
}
