package buffSpecific;
/*
 * for the level 70 prayers and chivalry
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class TopTierPrayer extends ConstantFillInBuff{
      
    private BuffCondition condition;
    private int levelBoost;
    private double damageMultiplier;
    
    public TopTierPrayer(BuffName name, PrimaryCombatStyle cbs,int levelBoost,double damageMultiplier) {
        super(name);
        this.condition = new OwnerUsingGeneralPrimaryCombatStyleCondition(cbs);
        this.levelBoost = levelBoost;
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return levelBoost;
        }else{
            return 0;
        }
    }

    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return levelBoost;
    }
    
    
    @Override
    public double getDamageMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return damageMultiplier;
        }else{
            return 1.0;
        }
    }
  }
