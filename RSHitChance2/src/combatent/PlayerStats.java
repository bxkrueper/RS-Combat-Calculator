package combatent;

import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;

public class PlayerStats implements Stats{
    
    private int baseAttackLevel;
    private int baseRangedLevel;
    private int baseMagicLevel;
    private int baseStrengthLevel;
    private int baseDefenseLevel;
    
    public PlayerStats(int attackLevel,int rangedLevel, int magicLevel,int baseStrengthLevel, int defenceLevel){
        this.baseAttackLevel = attackLevel;
        this.baseRangedLevel = rangedLevel;
        this.baseMagicLevel = magicLevel;
        this.baseStrengthLevel = baseStrengthLevel;
        this.baseDefenseLevel = defenceLevel;
    }
    
    @Override
    public int getBaseAttackLevel(){
        return baseAttackLevel;
    }
    @Override
    public int getBaseMeleeAccuracy(){
        return 0;
    }
    @Override
    public int getBaseRangedLevel(){
        return baseRangedLevel;
    }
    @Override
    public int getBaseRangedAccuracy(){
        return 0;
    }
    @Override
    public int getBaseMagicLevel(){
        return baseMagicLevel;
    }
    @Override
    public int getBaseMagicAccuracy(){
        return 0;
    }
    @Override
    public int getBaseStrengthLevel() {
        return baseStrengthLevel;
    }
    @Override
    public int getBaseDefenseLevel(){
        return baseDefenseLevel;
    }
    @Override
    public int getBaseArmour(){
        return 0;
    }

    public void setBaseAttackLevel(int baseAttackLevel) {
        this.baseAttackLevel = baseAttackLevel;
    }
    public void setBaseRangedLevel(int baseRangedLevel) {
        this.baseRangedLevel = baseRangedLevel;
    }
    public void setBaseMagicLevel(int baseMagicLevel) {
        this.baseMagicLevel = baseMagicLevel;
    }
    public void setBaseStrengthLevel(int baseStrengthLevel) {
        this.baseStrengthLevel = baseStrengthLevel;
    }
    public void setBaseDefenseLevel(int baseDefenseLevel) {
        this.baseDefenseLevel = baseDefenseLevel;
    }
    
    
    @Override
    public int getAccuracyLevel(PrimaryCombatStyle cbs){
        if(cbs.isSameGeneralPrimaryStyleAs(Melee.getInstance())){
            return baseAttackLevel;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Ranged.getInstance())){
            return baseRangedLevel;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Magic.getInstance())){
            return baseMagicLevel;
        }
        
        System.out.println("unrecognized combat type for Accuracy Level in Player Stats class!");
        return -1;
    }
    public int getAccuracyLevel(OffensiveCombatStyle combatStyle) {
        if(combatStyle instanceof PrimaryCombatStyle){
            return getAccuracyLevel((PrimaryCombatStyle) combatStyle);
        }else{
            return 0;
        }
    }
    @Override
    public int getAccuracy(PrimaryCombatStyle cbs){
        return 0;//player accuracy determined from weapon
    }

    @Override
    public int getPowerLevel(PrimaryCombatStyle cbs) {
        if(cbs.isSameGeneralPrimaryStyleAs(Melee.getInstance())){
            return baseStrengthLevel;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Ranged.getInstance())){
            return baseRangedLevel;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Magic.getInstance())){
            return baseMagicLevel;
        }
        
        System.out.println("unrecognized combat type for Power Level in Player Stats class!");
        return -1;
    }

    public int getPowerLevel(OffensiveCombatStyle combatStyle) {
        if(combatStyle instanceof PrimaryCombatStyle){
            return getPowerLevel((PrimaryCombatStyle) combatStyle);
        }else{
            return 0;
        }
    }

    

    

    
    
    
}
