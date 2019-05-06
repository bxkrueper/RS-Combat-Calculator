package buff;

/*
 * returns true if owner combat style and given are exactly equal (Arrows == Arrows)
 */
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class OwnerUsingSpecificCombatStyleCondition implements BuffCondition{
    
    private PrimaryCombatStyle specificCombatStyleOwnerShouldBeUsing;//ex: stab, air, thrown
    
    public OwnerUsingSpecificCombatStyleCondition(PrimaryCombatStyle specificCombatStyleOwnerShouldBeUsing) {
        this.specificCombatStyleOwnerShouldBeUsing = specificCombatStyleOwnerShouldBeUsing;
    }

    

    @Override
    public boolean passes(Combatent owner, Combatent opponent) {
        return owner.getCombatStyle()==specificCombatStyleOwnerShouldBeUsing;
    }

}
