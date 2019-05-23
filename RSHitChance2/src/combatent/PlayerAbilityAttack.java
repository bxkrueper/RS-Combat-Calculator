package combatent;

import java.util.ArrayList;
import java.util.List;

import abilities.Ability;
import combatStyle.OffensiveCombatStyle;
import main.AbilityHit;
import main.AutoAttackHit;
import main.DamageMode;
import main.Hit;

/*
 * uses the player's ability to fill the hit list
 */
public class PlayerAbilityAttack implements Attack{
    
    private Player player;
    
    public PlayerAbilityAttack(Player player) {
        this.player = player;
    }

    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return player.getCombatStyle();
    }

    @Override
    public String getName() {
        return player.getAbility().getName();
    }
    
    @Override
    public List<Hit> generateBaseDamageHitList() {
        Ability ability = player.getAbility();
        int baseDamage = player.getBaseDamage();
        System.out.println("base ability damage: " + baseDamage);
        return ability.generateBaseDamageHitList(baseDamage,player.getCombatStyle());
    }

}
