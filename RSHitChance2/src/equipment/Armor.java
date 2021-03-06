package equipment;
/*
 * for head, body, legs, gloves, boots, shields
 */
import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;

public class Armor extends Equipment implements ArmorInterface{
    private static final EquipmentConverter equipmentConverter = new ArmorEquipmentConverter();
    
    private double damageReduction;//0 for non-tank armor, example values: level 90 shields: 0.09, raids armor: 0.018
    
    public Armor(String name, String imageName, Slot slot, DefensiveCombatStyle combatStyle, int level, double damage, double armor, Buff buff,double damageReduction) {
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
            double damage = Double.parseDouble(strArray[5]);
            double armor = Double.parseDouble(strArray[6]);
            double damageReduction = Double.parseDouble(strArray[7]);
            String buffString = strArray[8];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new Armor(name,imageName,slot,combatStyle,level,damage,armor,buff,damageReduction);
        }
        
    }
}
