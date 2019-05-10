package equipment;
/*
 * includes main hand, two handed, and off handed weapons. all weapons have a speed and accuracy, though off hand accuracy is not used
 */
import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyleFlyweight;
import combatStyle.OffensiveCombatStyle;

public class Weapon extends Equipment implements WeaponInterface{
    private static final EquipmentConverter equipmentConverter = new WeaponEquipmentConverter();
    
    private int accuracy;
    private WeaponSpeed weaponSpeed;
    
    public Weapon(String name, String imageName, Slot slot, OffensiveCombatStyle combatStyle, int level, int damage, Buff buff,int accuracy,WeaponSpeed weaponSpeed) {
        super(name, imageName, slot, combatStyle, level, damage, 0, buff);
        this.accuracy = accuracy;
        this.weaponSpeed = weaponSpeed;
    }

    public int getAccuracy() {
        return accuracy;
    }
    
    public WeaponSpeed getWeaponSpeed(){
        return weaponSpeed;
    }
    
    
    public static EquipmentConverter getEquipmentConverter() {
        return equipmentConverter;
    }
    private static class WeaponEquipmentConverter implements EquipmentConverter{

        @Override
        public EquipmentInterface getEquipment(String[] strArray) {
            String name = strArray[0];
            String imageName = strArray[1].equals("")?name:strArray[1];
            Slot slot = Slot.valueOf(strArray[2]);
            OffensiveCombatStyle combatStyle = (OffensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[3]);
            int level = Integer.parseInt(strArray[4]);
            int accuracy = Integer.parseInt(strArray[5]);
            int damage = (int) Double.parseDouble(strArray[6]);
            WeaponSpeed speed = WeaponSpeed.valueOf(strArray[7]);
            String buffString = strArray[8];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new Weapon(name, imageName, slot, combatStyle, level, damage, buff,accuracy,speed);
        }
        
    }
    
}
