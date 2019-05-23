package buffSpecific;

import buff.BuffName;
import buff.ConstantFillInBuff;
import combatent.Combatent;

public class Quake extends ConstantFillInBuff{

    public Quake() {
        super(BuffName.Quake);
        setOwnerAffinityDebuff(2);
    }
    
    @Override
    public double addDefenseLevelsToOwner(Combatent owner, Combatent opponent) {
        return owner.getBaseDefenseLevel()*-.05;
    }

}