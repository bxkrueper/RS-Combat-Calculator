package combatStyle;

public interface PrimaryCombatStyle extends OffensiveCombatStyle, DefensiveCombatStyle{
    PrimaryCombatStyle getWeakAgainst();
    PrimaryCombatStyle getStrongAgainst();
    PrimaryCombatStyle getGeneralPrimaryStyle();//returns Melee,Ranged,or Magic
    default boolean isSameGeneralPrimaryStyleAs(PrimaryCombatStyle otherStyle){
        return this.getGeneralPrimaryStyle()==otherStyle.getGeneralPrimaryStyle();
    }
}
