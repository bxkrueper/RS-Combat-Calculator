package buffSpecific;

import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import buff.OpponentWeakToBuffCondition;
import combatent.Combatent;

public class Balmung extends ConstantFillInBuff{

    private static final BuffCondition condition = new OpponentWeakToBuffCondition(BuffName.Balmung);
    private double dmgVSNormal;
    private double dmgVSSupreme;
    
    //the damage calculator applies normal damage boosts and then separatly applies the auto attack multiplier. Together they should multiply to the auto attack multiplier
    private double aditionalAutoDmgVSNormal;
    private double aditionalAutoDmgVSSupreme;
    
    private double hitchanceAdd;
    
    public Balmung(BuffName name, double dmgVSNormal, double dmgVSSupreme, double hitchanceAdd,double autoDmgVSNormal,double autoDmgVSSupreme) {
        super(name);
        this.dmgVSNormal = dmgVSNormal;
        this.dmgVSSupreme = dmgVSSupreme;
        this.aditionalAutoDmgVSNormal = autoDmgVSNormal/dmgVSNormal;
        this.aditionalAutoDmgVSSupreme = autoDmgVSSupreme/dmgVSSupreme;
        this.hitchanceAdd = hitchanceAdd;
    }
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            if(opponent.getName().equals("Dagannoth Supreme")){
                return dmgVSSupreme;
            }else{
                return dmgVSNormal;
            }
        }else{
            return NullBuff.getInstance().getDamageMultiplier(owner, opponent);
        }
        
    }
    
    @Override
    public double getAutoAttackDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            if(opponent.getName().equals("Dagannoth Supreme")){
                return aditionalAutoDmgVSSupreme;
            }else{
                return aditionalAutoDmgVSNormal;
            }
        }else{
            return NullBuff.getInstance().getAutoAttackDamageMultiplier(owner, opponent);
        }
    }
    
    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return hitchanceAdd;
        }else{
            return NullBuff.getInstance().getAddToFinalHitChance(owner, opponent);
        }
        
    }

}
