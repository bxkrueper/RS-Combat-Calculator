package buff;
/*
 * BuffConditions test whether a buff can be applied (ex: if the opponent is weak to slayer masks)
 */
import combatent.Combatent;

public interface BuffCondition {
    boolean passes(Combatent owner, Combatent opponent);
}
