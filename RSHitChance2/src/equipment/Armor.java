package equipment;

import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;

public class Armor extends Equipment implements ArmorInterface{
    private static final EquipmentConverter equipmentConverter = new ArmorEquipmentConverter();
    
    private double damageReduction;
    
    public Armor(String name, String imageName, Slot slot, DefensiveCombatStyle combatStyle, int level, int damage, int armor, Buff buff,double damageReduction) {
        super(name, imageName, slot, combatStyle, level, damage, armor, buff);
        this.damageReduction = damageReduction;
    }

    
    
    public double getDamageReduction() {
        return damageReduction;
    }



    public static EquipmentConverter getEquipmentConverter() {
        return equipmentConverter;
    }
    
    private static class ArmorEquipmentConverter implements EquipmentConverter{

        @Override
        public EquipmentInterface getEquipment(String[] strArray) {
            String name = strArray[0];
            String imageName = strArray[1].equals("")?name:strArray[1];
            Slot slot = Slot.valueOf(strArray[2]);
            DefensiveCombatStyle combatStyle = (DefensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[3]);
            int level = Integer.parseInt(strArray[4]);
            int damage = (int) Double.parseDouble(strArray[5]);
            int armor = (int) Double.parseDouble(strArray[6]);
            double damageReduction = Double.parseDouble(strArray[7]);
            String buffString = strArray[8];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new Armor(name,imageName,slot,combatStyle,level,damage,armor,buff,damageReduction);
        }
        
    }
}
