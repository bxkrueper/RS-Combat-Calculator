package combatent;

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
    int getPowerLevel(PrimaryCombatStyle combatStyle);
}
