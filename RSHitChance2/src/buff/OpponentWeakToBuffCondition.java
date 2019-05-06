package buff;

/*
 * return true if the opponent is weak to the given buff
 */
import combatent.Combatent;

public class OpponentWeakToBuffCondition implements BuffCondition{
    
    private BuffName buffName;//no spaces, buff that the opponent needs to be weak to for the condition to pass
    
    public OpponentWeakToBuffCondition(BuffName buffName) {
        this.buffName = buffName;
    }

    

    @Override
    public boolean passes(Combatent owner, Combatent opponent) {
        return opponent.getVulnerabilities().isVulnerableTo(buffName);
    }
    
}
