package combatent;
/*
 * this describes a monster's special affinity weakness to a certain type of a combat style
 * for example, Ahrim's affinity is 90 when using arrows, but only 65 when using other ranged styles
 * player affinities are calculated from equipment
 */

import combatStyle.PrimaryCombatStyle;


public class MonsterSpecialAffinity{
    
    private PrimaryCombatStyle combatStyle;
    private int affinityValue;
    
    public MonsterSpecialAffinity(PrimaryCombatStyle combatStyle,int affinityValue){
        this.combatStyle = combatStyle;
        this.affinityValue = affinityValue;
    }
    public MonsterSpecialAffinity(){
        this(null,-1);
    }
    
    public PrimaryCombatStyle getCombatStyle(){
        return combatStyle;
    }
    public int getAffinity() {
        return affinityValue;
    }

    
    
    @Override
    public String toString(){
        return "combatStyle: " + combatStyle + " affinityValue: " + affinityValue;
    }

    
}
