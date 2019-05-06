package buffSpecific;
/*
 * does nothing except allow chargebows use their own ammunition instead of using a lower tier ammunition or not being able to fire at all
 */
import buff.BuffName;
import buff.ConstantFillInBuff;

public class Chargebow extends ConstantFillInBuff{

    public Chargebow() {
        super(BuffName.Chargebow);
    }

}
