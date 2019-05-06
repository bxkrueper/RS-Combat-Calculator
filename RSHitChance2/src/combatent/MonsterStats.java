package combatent;

/*
 * monster stats are not editable, their strength level is 0 as their strength is determined by other means
 */
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;

public class MonsterStats implements Stats{
    
    private final int baseAttackLevel;
    private final int baseMeleeAccuracy;
    private final int baseRangedLevel;
    private final int baseRangedAccuracy;
    private final int baseMagicLevel;
    private final int baseMagicAccuracy;
    private final int baseDefenseLevel;
    private final int baseArmour;
    
    public MonsterStats(int attackLevel,int meleeAccuracy,int rangedLevel,int rangedAccuracy, int magicLevel,int magicAccuracy, int defenceLevel,int armour){
        this.baseAttackLevel = attackLevel;
        this.baseMeleeAccuracy = meleeAccuracy;
        this.baseRangedLevel = rangedLevel;
        this.baseRangedAccuracy = rangedAccuracy;
        this.baseMagicLevel = magicLevel;
        this.baseMagicAccuracy = magicAccuracy;
        this.baseDefenseLevel = defenceLevel;
        this.baseArmour = armour;
    }
    
    @Override
    public int getBaseAttackLevel(){
        return baseAttackLevel;
    }
    @Override
    public int getBaseMeleeAccuracy(){
        return baseMeleeAccuracy;
    }
    @Override
    public int getBaseRangedLevel(){
        return baseRangedLevel;
    }
    @Override
    public int getBaseRangedAccuracy(){
        return baseRangedAccuracy;
    }
    @Override
    public int getBaseMagicLevel(){
        return baseMagicLevel;
    }
    @Override
    public int getBaseMagicAccuracy(){
        return baseMagicAccuracy;
    }
    @Override
    public int getBaseStrengthLevel() {
        return 0;
    }
    @Override
    public int getBaseDefenseLevel(){
        return baseDefenseLevel;
    }
    @Override
    public int getBaseArmour(){
        return baseArmour;
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
        
        System.out.println("unrecognized combat type for accuracyLevel in Monster Stats class!");
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
        if(cbs.isSameGeneralPrimaryStyleAs(Melee.getInstance())){
            return baseMeleeAccuracy;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Ranged.getInstance())){
            return baseRangedAccuracy;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Magic.getInstance())){
            return baseMagicAccuracy;
        }
        
        System.out.println("unrecognized combat type for accuracy in Monster Stats class!");
        return -1;
    }
    public int getAccuracy(OffensiveCombatStyle combatStyle) {
        if(combatStyle instanceof PrimaryCombatStyle){
            return getAccuracy((PrimaryCombatStyle) combatStyle);
        }else{
            return 0;
        }
    }

    

    @Override
    public int getPowerLevel(PrimaryCombatStyle combatStyle) {
        return 0;
    }

    

    
    
    
}
