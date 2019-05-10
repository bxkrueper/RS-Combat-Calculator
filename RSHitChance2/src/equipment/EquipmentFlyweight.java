package equipment;
/*
 * data base for all equipment
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import buff.Buff;
import buff.NullBuff;
import combatStyle.CombatStyleFlyweight;
import combatStyle.None;
import combatStyle.OffensiveCombatStyle;

public class EquipmentFlyweight {
    
    private static final String pathToEquipment = "data/equipment/";

    private static Map<String,Equipment> equipmentMap;
    private static Map<Slot,List<Equipment>> slotListMap;
    private static Map<Slot,Equipment> nullEquipmentMap;//equipment here represents having nothing in that slot
    
    private static void makeMaps() {
        equipmentMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);//case insensitive to make naming pictures easier. doesn't work with hash map
        nullEquipmentMap = new HashMap<>();
        makeEmptySlotListMap();
        
        addNullEquipment();
        readInAllEquipmentFiles();
    }

	private static void addNullEquipment() {
		//add null equipment
        ///////rangedammo?
        nullEquipmentMap.put(Slot.HELMET, new Armor("No Helmet", "No Helmet", Slot.HELMET, None.getInstance(), 0, 0,0,NullBuff.getInstance(),0));
        nullEquipmentMap.put(Slot.AURA, new OtherEquipment("No Aura", "No Aura", Slot.AURA, None.getInstance(), 0,0,NullBuff.getInstance()));
        nullEquipmentMap.put(Slot.POCKET, new OtherEquipment("No Pocket", "No Pocket", Slot.POCKET, None.getInstance(), 0,0,NullBuff.getInstance()));
        nullEquipmentMap.put(Slot.SIGIL, new OtherEquipment("No Sigil", "No Sigil", Slot.SIGIL, None.getInstance(), 0,0,NullBuff.getInstance()));
        nullEquipmentMap.put(Slot.QUIVER, new RangedAmmo("No Ammo", "No Ammo", Slot.QUIVER, None.getInstance(), 0, 0,0,NullBuff.getInstance()));
        nullEquipmentMap.put(Slot.AMULET, new OtherEquipment("No Amulet", "No Amulet", Slot.AMULET, None.getInstance(), 0,0,NullBuff.getInstance()));
        nullEquipmentMap.put(Slot.MAIN_HAND, new Weapon("No Main Hand", "No Main Hand", Slot.MAIN_HAND, (OffensiveCombatStyle) CombatStyleFlyweight.getCombatStyle("Crush"), 0, 0, NullBuff.getInstance(),0,WeaponSpeed.Fastest));
        nullEquipmentMap.put(Slot.OFF_HAND, new Weapon("No Off Hand", "No Off Hand", Slot.OFF_HAND, (OffensiveCombatStyle) CombatStyleFlyweight.getCombatStyle("Crush"), 0, 0, NullBuff.getInstance(),0,WeaponSpeed.Fastest));
        nullEquipmentMap.put(Slot.BODY, new Armor("No Body", "No Body", Slot.BODY, None.getInstance(), 0, 0,0,NullBuff.getInstance(),0));
        nullEquipmentMap.put(Slot.LEGS, new Armor("No Legs", "No Legs", Slot.LEGS, None.getInstance(), 0, 0,0,NullBuff.getInstance(),0));
        nullEquipmentMap.put(Slot.GLOVES, new Armor("No Gloves", "No Gloves", Slot.GLOVES, None.getInstance(), 0, 0,0,NullBuff.getInstance(),0));
        nullEquipmentMap.put(Slot.BOOTS, new Armor("No Boots", "No Boots", Slot.BOOTS, None.getInstance(), 0, 0,0,NullBuff.getInstance(),0));
        nullEquipmentMap.put(Slot.RING, new OtherEquipment("No Ring", "No Ring", Slot.RING, None.getInstance(), 0,0,NullBuff.getInstance()));
        nullEquipmentMap.put(Slot.CAPE, new OtherEquipment("No Cape", "No Cape", Slot.CAPE, None.getInstance(), 0,0,NullBuff.getInstance()));
        for(Equipment nullEquipment:nullEquipmentMap.values()){
            addEquipment(nullEquipment);
        }
	}

	private static void readInAllEquipmentFiles() {
		readInFile(pathToEquipment+"Weapons.csv",Weapon.getEquipmentConverter());
        readInFile(pathToEquipment+"WeaponArmor.csv",WeaponArmor.getEquipmentConverter());
        readInFile(pathToEquipment+"Armor.csv",Armor.getEquipmentConverter());
        readInFile(pathToEquipment+"RangedAmmo.csv",RangedAmmo.getEquipmentConverter());
        readInFile(pathToEquipment+"MagicSpells.csv",MagicSpell.getEquipmentConverter());
        readInFile(pathToEquipment+"OtherEquipment.csv",OtherEquipment.getEquipmentConverter());
	}

    private static void readInFile(String fileName,EquipmentConverter converter) {
        EquipmentFileReader efr;
        try {
            EquipmentFileReader.setConverter(converter);
            efr = new EquipmentFileReader(fileName);
            for(Object o:efr){
                Equipment equipment = (Equipment) o;
                
                addEquipment(equipment);
            }
        } catch (FileNotFoundException e1) {
            System.out.println("file not found: " + fileName);
        }
    }
    
    private static void addEquipment(Equipment equipment){
        equipmentMap.put(equipment.getName(), equipment);
        addToLists(equipment.getSlot(),equipment);
    }
    
    private static void makeEmptySlotListMap() {
        slotListMap = new HashMap<>();
        for(Slot slot: Slot.values()){
            slotListMap.put(slot, new ArrayList<>());
        }
    }

    private static void addToLists(Slot slot, Equipment equipment) {
        List<Equipment> list = slotListMap.get(slot);
        list.add(equipment);
    }

    
    
    
    
    
    public static Equipment getEquipment(String str){
        if(equipmentMap==null){
            makeMaps();
        }
        
        Equipment equipment = equipmentMap.get(str);
        if(equipment==null){
            System.out.println("unrecognized equipment name: " + str);
            return null;
        }else{
            return equipment;
        }
    }
    
    
    
    
    
    
    public static List<Equipment> getListOfAllEquipmentInSlot(Slot slot){
        if(slotListMap==null){
            makeMaps();
        }
        
        return slotListMap.get(slot);
    }
    


    public static Equipment getNullEquipmentForSlot(Slot slot) {
        if(equipmentMap==null){
            makeMaps();
        }
        if(slot==Slot.TWO_HAND){
            return nullEquipmentMap.get(Slot.MAIN_HAND);
        }else{
            return nullEquipmentMap.get(slot);
        }
    }
}
