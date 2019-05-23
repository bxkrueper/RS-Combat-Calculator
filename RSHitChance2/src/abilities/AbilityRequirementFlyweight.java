package abilities;

import java.util.HashMap;
import java.util.Map;

import combatent.Player;
import equipment.ArmorInterface;
import equipment.Equipment;
import equipment.EquipmentFlyweight;
import equipment.Slot;
import equipment.WeaponInterface;

/*
 * Access all ability requirements by name from here
 * names are from the Requirement column in Abilities.csv
 */
public class AbilityRequirementFlyweight {
	
	private static Map<String,AbilityRequirement> reqMap;

	public static AbilityRequirement getRequirement(String string) {
		if(reqMap==null) {
			makeMap();
		}
		
		AbilityRequirement req = reqMap.get(string);
        if(req==null){
            System.out.println("unrecognized ability requirement name: " + string);
            return null;
        }else{
            return req;
        }
	}

	private static void makeMap() {
		reqMap = new HashMap<>();
		
		AbilityRequirement req;
		req = new MainHandCombatStyle();
		reqMap.put(req.getName(), req);
		
		req = new DuelWield();
		reqMap.put(req.getName(), req);
		
		req = new TwoHanded();
		reqMap.put(req.getName(), req);
		
		req = new Shield();
		reqMap.put(req.getName(), req);
		
		req = new MainHand();
		reqMap.put(req.getName(), req);
		
		req = new NoRequirement();
		reqMap.put(req.getName(), req);
	}
	
	
	
	
	
	
	//player must have a main hand weapon (not fists) of the same combat style as the ability
	private static class MainHandCombatStyle implements AbilityRequirement{

		@Override
		public String getName() {
			return "MHCBS";
		}

		@Override
		public boolean playerCanUseAbility(Player player, Ability ability) {
			WeaponInterface mainWeapon = player.getWornEquipment().getMainWeapon();
			return mainWeapon!=EquipmentFlyweight.getNullEquipmentForSlot(Slot.MAIN_HAND) && mainWeapon.getOffensiveCombatStyle().isSameGeneralOffensiveStyleAs(ability.getCombatStyle());
		}
		
	}
	
	//player must have a main hand weapon and off hand weapon (not fists), both of the same combat style
	private static class DuelWield implements AbilityRequirement{

		@Override
		public String getName() {
			return "DW";
		}

		@Override
		public boolean playerCanUseAbility(Player player, Ability ability) {
			WeaponInterface mainWeapon = player.getWornEquipment().getMainWeapon();
			WeaponInterface offHand = player.getWornEquipment().getOffHandWeapon();
			if(offHand==null) {
				return false;
			}
			if(mainWeapon==EquipmentFlyweight.getNullEquipmentForSlot(Slot.MAIN_HAND) || offHand==EquipmentFlyweight.getNullEquipmentForSlot(Slot.OFF_HAND)) {
				return false;
			}
			return mainWeapon.getOffensiveCombatStyle().isSameGeneralOffensiveStyleAs(ability.getCombatStyle()) && offHand.getOffensiveCombatStyle().isSameGeneralOffensiveStyleAs(ability.getCombatStyle());
		}
		
	}
	
	//player must have a two handed weapon of the same combat style
	private static class TwoHanded implements AbilityRequirement{

		@Override
		public String getName() {
			return "2H";
		}

		@Override
		public boolean playerCanUseAbility(Player player, Ability ability) {
			WeaponInterface mainWeapon = player.getWornEquipment().getMainWeapon();
			return mainWeapon.getSlot()==Slot.TWO_HAND && mainWeapon.getOffensiveCombatStyle().isSameGeneralOffensiveStyleAs(ability.getCombatStyle());
		}
		
	}
	
	//player must have an off hand that counts as a shield or a shieldbow
	private static class Shield implements AbilityRequirement{

		@Override
		public String getName() {
			return "SHIELD";
		}

		@Override
		public boolean playerCanUseAbility(Player player, Ability ability) {
			return player.getWornEquipment().getEquipment(Slot.OFF_HAND) instanceof ArmorInterface || player.getWornEquipment().getMainWeapon() instanceof ArmorInterface;
		}
		
	}
	
	//player must have some sort of weapon (not fists)
	private static class MainHand implements AbilityRequirement{

		@Override
		public String getName() {
			return "MH";
		}

		@Override
		public boolean playerCanUseAbility(Player player, Ability ability) {
			return player.getWornEquipment().getMainWeapon()!=EquipmentFlyweight.getNullEquipmentForSlot(Slot.MAIN_HAND);
		}
		
	}
	
	//no requirement, player can even use with firsts
	private static class NoRequirement implements AbilityRequirement{

		@Override
		public String getName() {
			return "NONE";
		}

		@Override
		public boolean playerCanUseAbility(Player player, Ability ability) {
			return true;
		}
		
	}

}
