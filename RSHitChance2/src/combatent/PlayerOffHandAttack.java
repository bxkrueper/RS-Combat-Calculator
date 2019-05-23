package combatent;

import java.util.ArrayList;
import java.util.List;

import combatStyle.OffensiveCombatStyle;
import equipment.WeaponInterface;
import main.AutoAttackHit;
import main.DamageMode;
import main.Hit;

public class PlayerOffHandAttack implements Attack{
    
    private Player player;
    
    public PlayerOffHandAttack(Player player) {
        this.player = player;
    }

    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return player.getCombatStyle();
    }

    @Override
    public String getName() {
        WeaponInterface offHand = player.getWornEquipment().getOffHandWeapon();
        if(offHand==null) {
            return "Player can't attack with this off hand!";
        }else {
            return  offHand.getName() + " auto attack";
        }
        
    }
    
    @Override
    public List<Hit> generateBaseDamageHitList() {
        List<Hit> hitList = new ArrayList<>(1);
        WeaponInterface offHand = player.getWornEquipment().getOffHandWeapon();
        if(offHand==null) {
            return hitList;
        }
        int maxHit = player.calculateBaseAutoDamage(offHand);
        System.out.println("Player Off Hand base max hit: " + maxHit);
        hitList.add(new AutoAttackHit(player.getCombatStyle(),1,maxHit));
        return hitList;
    }

}