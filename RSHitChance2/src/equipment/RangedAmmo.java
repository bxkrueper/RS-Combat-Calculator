package equipment;

import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;

public class RangedAmmo extends Equipment implements AmmoInterface{
    private static final EquipmentConverter equipmentConverter = new RangedAmmoEquipmentConverter();

    public RangedAmmo(String name, String imageName, Slot slot, DefensiveCombatStyle combatStyle, int level, int damage, int armor, Buff buff) {
        super(name, imageName, slot, combatStyle, level, damage, armor, buff);
    }
    
    
    public static EquipmentConverter getEquipmentConverter() {
        return equipmentConverter;
    }
    
    @Override
    public int getDamage(int playerMagicLevel) {
        return getDamage();//arrows do not scale with level
    }
    
    private static class RangedAmmoEquipmentConverter implements EquipmentConverter{

        @Override
        public EquipmentInterface getEquipment(String[] strArray) {
            String name = strArray[0];
            String imageName = strArray[1].equals("")?name:strArray[1];
            DefensiveCombatStyle combatStyle = (DefensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[2]);
            int level = Integer.parseInt(strArray[3]);
            int damage = (int) Double.parseDouble(strArray[4]);
            String buffString = strArray[5];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new RangedAmmo(name, imageName, Slot.QUIVER, combatStyle, level, damage,0, buff);
        }
        
    }

}
