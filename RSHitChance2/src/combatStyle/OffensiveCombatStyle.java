package combatStyle;
/*
 * these include the primary combat styles as well as stuff like typeless and dragonfire
 */
import java.awt.Color;

public interface OffensiveCombatStyle extends CombatStyle{
    
    OffensiveCombatStyle getGeneralOffensiveStyle();//melee, ranged, magic,typeless,dragonfire,can't hit    not stab, crush, ect
    Color getColor();
    default boolean isSameGeneralOffensiveStyleAs(CombatStyle combatStyle){
    	if(combatStyle instanceof OffensiveCombatStyle) {
    		return getGeneralOffensiveStyle()==((OffensiveCombatStyle) combatStyle).getGeneralOffensiveStyle();
    	}else {
    		return false;
    	}
    }
}
