package equipment;
/*
 * for auras, pocket slot items, amulets, rings, capes, and sigils
 */
import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;

public class OtherEquipment extends Equipment{
    private static final EquipmentConverter equipmentConverter = new OtherEquipmentConverter();
    
    public OtherEquipment(String name, String imageName, Slot slot, CombatStyle combatStyle, double damage,double armor, Buff buff) {
        super(name, imageName, slot, combatStyle, 0, damage, armor, buff);
    }



    
    
    
    public static EquipmentConverter getEquipmentConverter() {
        return equipmentConverter;
    }
    
    private static class OtherEquipmentConverter implements EquipmentConverter{

        @Override
        public EquipmentInterface getEquipment(String[] strArray) {
            String name = strArray[0];
            String imageName = strArray[1].equals("")?name:strArray[1];
            Slot slot = Slot.valueOf(strArray[2]);
            DefensiveCombatStyle combatStyle = (DefensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[3]);
            double damage = Double.parseDouble(strArray[4]);
            double armor = Double.parseDouble(strArray[5]);
            String buffString = strArray[6];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new OtherEquipment(name, imageName, slot, combatStyle, damage,armor, buff);
        }
        
    }

}
