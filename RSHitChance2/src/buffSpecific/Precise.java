package buffSpecific;

import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInStackBuff;
//does not do anything direcly. the damage calculator takes the stack value for the calculations
public class Precise extends ConstantFillInStackBuff{
    //stack is expected to be between 0 and 5 (inclusive)
      
    public Precise() {
        super(BuffName.Precise);
    }
    
    @Override
    public Buff makeNew() {
        return new Precise();
    }
}
