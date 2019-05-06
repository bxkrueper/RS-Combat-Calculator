package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;
import buff.NullBuff;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class SpiritShieldAbsorbsion extends ConstantFillInBuff{

    public SpiritShieldAbsorbsion() {
        super(BuffName.Spirit_Shield_Absorbsion);
    }
    
    //////wiki says spirit shield does not reduce typeless damage, but it works on Telos' SMP
    @Override
    public double getDamageRecievedMultiplier(Combatent owner, Combatent opponent) {
//        if(opponent.getCombatStyle() instanceof PrimaryCombatStyle){
            return 0.7;
//        }else{
//            return NullBuff.getInstance().getDamageRecievedMultiplier(owner, opponent);
//        }
    }

}
