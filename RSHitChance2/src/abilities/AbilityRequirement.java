package abilities;

import combatent.Player;

public interface AbilityRequirement {
	
	String getName();
	boolean playerCanUseAbility(Player player,Ability ability);
	
}
