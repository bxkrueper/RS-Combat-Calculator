package combatent;

import java.util.ArrayList;
import java.util.List;

import abilities.Ability;
import calculations.AbilityHit;
import calculations.AutoAttackHit;
import calculations.ConstantHit;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;
import main.DamageMode;

public class PlayerMainHandAttack implements Attack{
    
    private Player player;
    
    public PlayerMainHandAttack(Player player) {
        this.player = player;
    }

    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return player.getCombatStyle();
    }

    @Override
    public String getName() {
        return player.getWornEquipment().getMainWeapon().getName() + " auto attack";
    }

    @Override
    public List<Hit> generateBaseDamageHitList() {
        List<Hit> hitList = new ArrayList<>(1);
        double maxHit = player.calculateBaseAutoDamage(player.getWornEquipment().getMainWeapon());
        System.out.println("Player Main Hand base max hit: " + maxHit);
        hitList.add(new AutoAttackHit(player.getCombatStyle(),1,maxHit));
        return hitList;
    }

}