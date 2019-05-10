package equipment;
/*
 * for defenders and shildbows, which are both weapons and armor
 */
import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;

public class WeaponArmor extends Equipment implements WeaponInterface, ArmorInterface{
    private static final EquipmentConverter equipmentConverter = new WeaponArmorEquipmentConverter();

    private int accuracy;
    private WeaponSpeed weaponSpeed;
    private double damageReduction;
    
    public WeaponArmor(String name, String imageName, Slot slot, OffensiveCombatStyle combatStyle, int level, int damage, int armor,Buff buff, int accuracy,WeaponSpeed weaponSpeed,double damageReduction) {
        super(name, imageName, slot, combatStyle, level, damage, armor, buff);
        this.accuracy = accuracy;
        this.weaponSpeed = weaponSpeed;
        this.damageReduction = damageReduction;
    }
    
    public int getAccuracy() {
        return accuracy;
    }
    public WeaponSpeed getWeaponSpeed(){
        return weaponSpeed;
    }
    
    public double getDamageReduction() {
        return damageReduction;
    }
    
    public static EquipmentConverter getEquipmentConverter() {
        return equipmentConverter;
    }
    
    private static class WeaponArmorEquipmentConverter implements EquipmentConverter{

        @Override
        public EquipmentInterface getEquipment(String[] strArray) {
            String name = strArray[0];
            String imageName = strArray[1].equals("")?name:strArray[1];
            Slot slot = Slot.valueOf(strArray[2]);
            OffensiveCombatStyle combatStyle = (OffensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[3]);
            int level = Integer.parseInt(strArray[4]);
            int accuracy = Integer.parseInt(strArray[5]);
            int damage = (int) Double.parseDouble(strArray[6]);
            int armor = (int) Double.parseDouble(strArray[7]);
            double damageReduction = Double.parseDouble(strArray[8]);
            WeaponSpeed speed = WeaponSpeed.valueOf(strArray[9]);
            String buffString = strArray[10];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new WeaponArmor(name,imageName,slot,combatStyle,level,damage,armor,buff,accuracy,speed,damageReduction);
        }
        
    }

}
