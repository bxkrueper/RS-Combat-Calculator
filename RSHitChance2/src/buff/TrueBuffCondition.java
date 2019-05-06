package buff;
/*
 * BuffCondition that simply returns true
 */
import combatent.Combatent;

public class TrueBuffCondition implements BuffCondition{

    @Override
    public boolean passes(Combatent owner, Combatent opponent) {
        return true;
    }

}
