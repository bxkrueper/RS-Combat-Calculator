package buffSpecific;

import java.util.List;

import buff.Buff;
import buff.BuffName;
import buff.ConstantFillInStackBuff;
import buff.StackBuff;
import calculations.Hit;
import combatent.Combatent;
//stack value is the tier of biting. 1 means add 2%, 2 means add 4%...
public class Biting extends ConstantFillInStackBuff{

    private final double stackMultiplier;//biting adds 2% crit chance per stack
    
    public Biting() {
        super(BuffName.Biting);
        this.stackMultiplier = 2.0;
    }

    @Override
    public StackBuff makeNew() {
        ConstantFillInStackBuff buff = new Biting();
        buff.setDescription(getDescription());
        return buff;
    }
    
    public void affectOwnerBaseHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        for(Hit hit: list) {
            hit.addToCritChance(this.getStackValue()/100.0*stackMultiplier);
        }
    }

}
