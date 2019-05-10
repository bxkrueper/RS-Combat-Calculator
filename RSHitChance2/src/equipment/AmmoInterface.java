package equipment;
/*
 * ammo damage is determined by its tier, not by a seperate value
 */
public interface AmmoInterface extends EquipmentInterface{

    int getDamage(int powerLevel);

}
