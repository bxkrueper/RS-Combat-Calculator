package combatStyle;
/*
 * these are the 3 main combat styles (Melee, Ranged, and Magic) and their subclasses like Crush or Bolts
 */
public interface PrimaryCombatStyle extends OffensiveCombatStyle, DefensiveCombatStyle{
    PrimaryCombatStyle getWeakAgainst();//Combat triangle weakness
    PrimaryCombatStyle getStrongAgainst();//Combat triangle strength
    PrimaryCombatStyle getGeneralPrimaryStyle();//returns Melee,Ranged,or Magic
    default boolean isSameGeneralPrimaryStyleAs(PrimaryCombatStyle otherStyle){
        return this.getGeneralPrimaryStyle()==otherStyle.getGeneralPrimaryStyle();
    }
}
