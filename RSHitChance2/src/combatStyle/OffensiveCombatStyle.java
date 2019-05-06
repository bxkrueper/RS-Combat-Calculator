package combatStyle;

import java.awt.Color;

public interface OffensiveCombatStyle extends CombatStyle{
    
    OffensiveCombatStyle getGeneralOffensiveStyle();//melee, ranged, magic,typeless,dragonfire,can't hit    not stab, crush, ect
    Color getColor();
    default boolean isSameGeneralOffensiveStyleAs(OffensiveCombatStyle offensiveCombatStyle){
        return getGeneralOffensiveStyle()==offensiveCombatStyle.getGeneralOffensiveStyle();
    }
}
