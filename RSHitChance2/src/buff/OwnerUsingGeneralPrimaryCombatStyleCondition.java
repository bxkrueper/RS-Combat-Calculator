package buff;

/*
 * returns true if the owner is using the same general primary style as given. Ex of owner is using stab and given is melee, return true
 */
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;

public class OwnerUsingGeneralPrimaryCombatStyleCondition implements BuffCondition{
    
    private PrimaryCombatStyle generalCombatStyleOwnerShouldBeUsing;//Melee,Ranged,or Magic
    
    public OwnerUsingGeneralPrimaryCombatStyleCondition(PrimaryCombatStyle generalCombatStyleOwnerShouldBeUsing) {
        this.generalCombatStyleOwnerShouldBeUsing = generalCombatStyleOwnerShouldBeUsing.getGeneralPrimaryStyle();
    }

    

    @Override
    public boolean passes(Combatent owner, Combatent opponent) {
        return owner.getCombatStyle().isSameGeneralOffensiveStyleAs((generalCombatStyleOwnerShouldBeUsing));
    }

}
