package buffSpecific;
/*
 * reaper necklace effect
 */
import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInStackBuff;
import combatent.Combatent;

public class Reaper extends ConstantFillInStackBuff{
    //stack is expected to be between 0 and 30 (inclusive)
    
    public Reaper() {
        super(BuffName.Reaper_Necklace);
    }

    @Override
    public double getAddToFinalHitChance(Combatent owner, Combatent opponent) {
        return 0.1*getStackValue();
    }

    @Override
    public Buff makeNew() {
        return new Reaper();
    }
}
