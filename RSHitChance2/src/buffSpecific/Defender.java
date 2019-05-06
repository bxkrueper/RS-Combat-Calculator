package buffSpecific;
/*
 * defenders only boost accuracy for their own combat style
 */
import buff.BuffCondition;
import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.OwnerUsingGeneralPrimaryCombatStyleCondition;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class Defender extends ConstantFillInBuff{

    private BuffCondition condition;
    
    public Defender(BuffName name, PrimaryCombatStyle combatStyle) {
        super(name);
        this.condition = new OwnerUsingGeneralPrimaryCombatStyleCondition(combatStyle);
        this.setPictureName("Defender");
    }
    
    @Override
    public double getAccuracyMultiplier(Combatent owner, Combatent opponent) {
        if(condition.passes(owner, opponent)){
            return 1.03;
        }else{
            return 1.0;
        }
    }
    
    //defenders also have a chance to block damage, but this calculator won't use randomness to avoid confusion
//    @Override
//    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
//        final double oneIn15 = 1.0/15.0;
//        if(Math.random()<oneIn15){/////also increases accuracy of next attack by 20%
//            return Math.random()/2;//reduce damage by 50%-100%
//        }else{
//            return 1.0;
//        }
//    }
}
