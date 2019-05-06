package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;

public class DamageMultiplier extends ConstantFillInBuff{

    public DamageMultiplier(BuffName name, double multiplier) {
        super(name);
        this.setDamageMultiplier(multiplier);
    }

}
