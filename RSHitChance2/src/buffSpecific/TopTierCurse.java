package buffSpecific;
/*
 * for the level 95 and 99 curses
 */
import buff.BuffCondition;
import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInStackBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;
import combatent.MonsterInterface;

public class TopTierCurse extends ConstantFillInStackBuff{
    //stack is expected to be between 0 and 4 (inclusive)
      
    PrimaryCombatStyle combatStyle;
    private int levelBoost;
    private double damageMultiplier;
    private BuffCondition buffOwnerCondition;
    private BuffCondition deBuffOpponentCondition;
    
    public TopTierCurse(BuffName name, PrimaryCombatStyle cbs,int levelBoost,double damageMultiplier) {
        super(name);
        this.combatStyle = cbs;
        this.levelBoost = levelBoost;
        this.damageMultiplier = damageMultiplier;
        this.buffOwnerCondition = new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle);
        deBuffOpponentCondition = new BuffCondition(){

            @Override
            public boolean passes(Combatent owner, Combatent opponent) {
                return opponent.getCombatStyle().isSameGeneralOffensiveStyleAs((combatStyle)) && opponent.getVulnerabilities().isVulnerableTo(BuffName.Drain);
            }
            
        };
    }

    @Override
    public double addAccuracyLevelsToOwner(Combatent owner, Combatent opponent) {
        if(buffOwnerCondition.passes(owner, opponent)){
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
        if(buffOwnerCondition.passes(owner, opponent)){
            return damageMultiplier;
        }else{
            return 1.0;
        }
    }
    
    
    @Override
    public double addAccuracyLevelsToOpponent(Combatent owner, Combatent opponent) {
        if(deBuffOpponentCondition.passes(owner, opponent)){
            return -6-getStackValue();
        }else{
            return 0;
        }
    }

    @Override
    public double addDefenseLevelsToOpponent(Combatent owner, Combatent opponent) {
        if(opponent.getVulnerabilities().isVulnerableTo(BuffName.Drain)){
            return -6-getStackValue();
        }else{
            return 0;
        }
    }
    
    @Override
    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
        if(deBuffOpponentCondition.passes(owner, opponent)){
            if(opponent instanceof MonsterInterface){
                return 0.91-getStackValue()*0.015;
            }else{//instance of player
                return 0.94-getStackValue()*0.01;
            }
            
        }else{
            return 1.0;
        }
    }

      @Override
      public Buff makeNew() {
          return new TopTierCurse(getName(),combatStyle,levelBoost,damageMultiplier);
      }
  }
