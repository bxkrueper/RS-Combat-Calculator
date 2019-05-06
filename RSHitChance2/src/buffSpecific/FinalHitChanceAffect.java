package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;

public class FinalHitChanceAffect extends ConstantFillInBuff{

    public FinalHitChanceAffect(BuffName name, double toAdd) {
        super(name);
        this.setAddToFinalHitChance(toAdd);
    }

}