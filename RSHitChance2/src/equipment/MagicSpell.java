package equipment;

import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;

public class MagicSpell extends Equipment implements AmmoInterface{
    private static final EquipmentConverter equipmentConverter = new MagicSpellEquipmentConverter();

    private int maxLevel;//level where the spell stops scaling
    
    public MagicSpell(String name, String imageName, Slot slot, DefensiveCombatStyle combatStyle, int minLevel, int damage, int armor, Buff buff,int maxLevel) {
        super(name, imageName, slot, combatStyle, minLevel, damage, armor, buff);
        this.maxLevel = maxLevel;
    }
    
    public static EquipmentConverter getEquipmentConverter() {
        return equipmentConverter;
    }
    
    @Override
    public int getDamage(int playerMagicLevel) {
        
        int scaledSpellLevel;
        if(playerMagicLevel<getMinLevel()){
            scaledSpellLevel = 0;//can't cast it!
        }else if(playerMagicLevel>=maxLevel){
            scaledSpellLevel = maxLevel;
        }else{
            scaledSpellLevel = playerMagicLevel;
        }
        System.out.println("playerMagicLevel: " + playerMagicLevel + " maxSpellLevel: " + getMaxLevel() + " minSpellLevel: " + getMinLevel() + " scaledLevel: " + scaledSpellLevel);
        return (int) (9.6*scaledSpellLevel);
    }
    
    public int getMinLevel(){
        return getLevel();
    }
    
    public int getMaxLevel(){
        return maxLevel;
    }
    
    private static class MagicSpellEquipmentConverter implements EquipmentConverter{

        @Override
        public EquipmentInterface getEquipment(String[] strArray) {
            String name = strArray[0];
            String imageName = strArray[1].equals("")?name:strArray[1];
            DefensiveCombatStyle combatStyle = (DefensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[2]);
            int minLevel = Integer.parseInt(strArray[3]);
            int maxLevel = Integer.parseInt(strArray[4]);
            String buffString = strArray[5];
            Buff buff = buffString.equals("None")?NullBuff.getInstance():BuffFlyweight.getBuff(BuffName.valueOf(buffString));

            
            
            return new MagicSpell(name, imageName, Slot.QUIVER, combatStyle, minLevel, (int) (minLevel*9.6),0, buff,maxLevel);////////////damage field not needed?
        }
        
    }



}
