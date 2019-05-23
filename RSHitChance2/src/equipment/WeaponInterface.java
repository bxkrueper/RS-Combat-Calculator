package equipment;

import combatStyle.OffensiveCombatStyle;

public interface WeaponInterface extends EquipmentInterface{
    int getAccuracy();
    WeaponSpeed getWeaponSpeed();
    
	public default double getHandinessMultiplier() {
		switch(getSlot()){
        case MAIN_HAND:
            return 1;
        case OFF_HAND:
            return 0.5;
        case TWO_HAND:
            return 1.5;
        default:
            System.out.println("unrecognized weapon handiness!");
            return 0;
        }
	}
	public default OffensiveCombatStyle getOffensiveCombatStyle() {
		return (OffensiveCombatStyle) getCombatStyle();
	}
}
