package buffSpecific;

import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInStackBuff;
import buff.StackBuff;
//does not do anything direcly. the damage calculator takes the stack value for the calculations
public class Equilibrium extends ConstantFillInStackBuff{
    //stack is expected to be between 0 and 3 (inclusive)
      
    public Equilibrium() {
        super(BuffName.Equilibrium);
    }
    
    @Override
    public StackBuff makeNew() {
        ConstantFillInStackBuff buff = new Equilibrium();
        buff.setDescription(getDescription());
        return buff;
    }
}