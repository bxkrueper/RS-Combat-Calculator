package buffSpecific;

import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInStackBuff;
import combatent.Combatent;

public class BlackStoneArrows extends ConstantFillInStackBuff{
//stack is expected to be between 0 and 20 (inclusive)
    
    public BlackStoneArrows() {
        super(BuffName.Black_Stone_Arrows);
    }

    @Override
    public double getArmorMultiplier(Combatent owner, Combatent opponent) {
        double ratePerStack = 0.0075;
        if(owner.getNaturalArmor()>3027){
            //set rate so that the cap per stack is - 22.7 armor
            ratePerStack = 22.7/owner.getNaturalArmor();
        }
        return 1.0-(ratePerStack*getStackValue());
    }

    @Override
    public Buff makeNew() {
        return new BlackStoneArrows();
    }
}
