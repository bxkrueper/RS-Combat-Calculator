package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;
import combatent.Combatent;

public class DamageRecievedMultiplier extends ConstantFillInBuff{

    public DamageRecievedMultiplier(BuffName name, double multiplier) {
        super(name);
        this.setDamageRecievedMultiplier(multiplier);
        
    }

}