package buffSpecific;
/*
 * for buffs like guthix staff spec and status warhammer
 */
import buff.BuffName;
import buff.ConstantFillInBuff;

public class AffinityRaise extends ConstantFillInBuff{

    public AffinityRaise(BuffName name, int amount) {
        super(name);
        setOwnerAffinityDebuff(amount);
    }

}
