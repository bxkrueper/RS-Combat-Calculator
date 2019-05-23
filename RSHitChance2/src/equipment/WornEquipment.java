package equipment;
/*
 * represents all of a player's equiped items
 */
import java.util.HashMap;
import java.util.Map;

import buff.Buff;
import buff.Buffs;
import buff.NullBuff;
import combatStyle.All;
import combatStyle.CantAttack;
import combatStyle.CombatStyle;
import combatStyle.DefensiveCombatStyle;
import combatStyle.Hybrid;
import combatStyle.Magic;
import combatStyle.None;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;

public class WornEquipment{
    
    private static final Slot[] slotsThatAffectAccuracyPenalty = {Slot.HELMET,Slot.BODY,Slot.LEGS,Slot.BOOTS,Slot.GLOVES,Slot.OFF_HAND};
    
    private Map<Slot,Equipment> slotEquipmentMap;
    
    private Buffs equipmentBuffs;
    
    public WornEquipment(){
        this.slotEquipmentMap = new HashMap<>();
        equipNullEquipment();
        this.equipmentBuffs = new Buffs();
    }

    //initially fills the map with the default Null Objects instead of null
    private void equipNullEquipment() {
        for(Slot slot:Slot.values()){
            slotEquipmentMap.put(slot, EquipmentFlyweight.getNullEquipmentForSlot(slot));
        }
    }

    

    public Equipment getEquipment(Slot slot) {
        return slotEquipmentMap.get(slot);
    }
    
    //equips the item into its slot and adds its buff
    //makes sure an off hand cannot be wielded with a two handed item and that there is only one clear primary weapon (either one handed or two handed)
    public void equip(Equipment equipment){
        Slot slot = equipment.getSlot();
        deEquip(slot);
        slotEquipmentMap.put(slot, equipment);
        
        Buff buff = equipment.getBuff();
        equipmentBuffs.addBuff(buff);
        
        if(slot == Slot.TWO_HAND){
            deEquip(Slot.OFF_HAND);
            deEquip(Slot.MAIN_HAND);
        }
        if(slot == Slot.OFF_HAND && equipment!=EquipmentFlyweight.getNullEquipmentForSlot(Slot.OFF_HAND)){
            deEquip(Slot.TWO_HAND);
        }
        if(slot == Slot.MAIN_HAND){
            deEquip(Slot.TWO_HAND);
        }
    }
    //removes the item's buffs from the player and equips the Null Object for that slot
    public void deEquip(Slot slot){
        Buff buffToRemove = slotEquipmentMap.get(slot).getBuff();
        if(buffToRemove!=NullBuff.getInstance()){
            equipmentBuffs.removeBuff(buffToRemove.getName());
        }
        
        slotEquipmentMap.put(slot, EquipmentFlyweight.getNullEquipmentForSlot(slot));
    }
    
    
    
    
    public Buffs getEquipmentBuffs(){
        return equipmentBuffs;
    }
    
    public OffensiveCombatStyle getCombatStyle(){
        PrimaryCombatStyle pcbsWeapon = (PrimaryCombatStyle) getMainWeapon().getCombatStyle();
        if(pcbsWeapon == Magic.getInstance()){//magic's combat style depends on the spell in the quiver
        	CombatStyle quiverCbs = this.getEquipment(Slot.QUIVER).getCombatStyle();
            PrimaryCombatStyle pcbsSpell;
            if(quiverCbs instanceof PrimaryCombatStyle) {
            	pcbsSpell = (PrimaryCombatStyle) quiverCbs;
            }else {
            	return CantAttack.getInstance();
            }
            if(pcbsSpell.getGeneralOffensiveStyle()==Magic.getInstance().getGeneralOffensiveStyle()){
                return pcbsSpell;
            }else{
                return pcbsWeapon;
            }
        }else{
            return pcbsWeapon;//melee and range weapons have their specific combat styles attached to them
        }
    }
    
    //returns what is in the main-hand/two hand slot. There should not be a real weapon in both. can return the default main weapon (fists)
    public WeaponInterface getMainWeapon() {
        if (getEquipment(Slot.MAIN_HAND)!=EquipmentFlyweight.getNullEquipmentForSlot(Slot.MAIN_HAND)){
            return (WeaponInterface) getEquipment(Slot.MAIN_HAND);
        }else{
            return (WeaponInterface) getEquipment(Slot.TWO_HAND);
        }
    }
    

    //returns null if the player cannot attack with the off hand slot (shield or fist)
	public WeaponInterface getOffHandWeapon() {
		Equipment offHand = getEquipment(Slot.OFF_HAND);
		if((offHand instanceof WeaponInterface) && offHand!=EquipmentFlyweight.getNullEquipmentForSlot(Slot.OFF_HAND)) {//////////excluded off hand 5-18-19
			return (WeaponInterface) offHand;
		}else {
			return null;
		}
	}

	//only gets accuracy from weapon
    public int getMainWeaponAccuracy(){
        return getMainWeapon().getAccuracy();
    }
    //only adds damage for current combat style (melee,range,magic)
    public int getDamage(){
        int total = 0;
        for(Slot slot:Slot.values()){
            Equipment equipment = getEquipment(slot);
            total+= equipment.getDamage();
        }
        return total;
    }
    //gets damage bonuses from non-weapon or ammoslots
    public int getNonWeaponStrengthBonuses() {
        int total = 0;
        for(Slot slot:Slot.values()){
            if(slot==Slot.MAIN_HAND || slot==Slot.OFF_HAND || slot==Slot.TWO_HAND || slot==Slot.QUIVER){
                continue;
            }
            Equipment equipment = getEquipment(slot);
            total+= equipment.getDamage();
        }
        return total;
    }
    //totals all armor values
    public int getArmor(){
        int total = 0;
        for(Slot slot:Slot.values()){
            Equipment equipment = getEquipment(slot);
            total+= equipment.getArmor();
        }
        return total;
    }

    /////////test
    public int getAffinityTo(PrimaryCombatStyle cbs) {
        int armor = getArmor();//total armor
        if(armor==0){
            return 55;
        }else{
        	//hybrid, none, and all are treated the same
            int weightedAffinity = (int) ((45*getArmor(cbs.getWeakAgainst())+55*getArmor(cbs)+65*getArmor(cbs.getStrongAgainst())+55*getArmor(Hybrid.getInstance())+55*getArmor(All.getInstance())+55*getArmor(None.getInstance())));
            if(weightedAffinity==0){
                return 55;
            }else{
                 return (int) (weightedAffinity/((double) armor));
            }
           
        }
        
    }
    
    //totals all armor values from equipment that matches the given combat style
    private int getArmor(DefensiveCombatStyle cbs) {
        int total = 0;
        for(Slot slot:Slot.values()){
            Equipment equipment = getEquipment(slot);
            if(equipment.getCombatStyle()==cbs){//assumes defensive styles don't have subclasses
                total+= equipment.getArmor();
            }
        }
        return total;
    }
    
    @Override
    public String toString(){
        return slotEquipmentMap.values().toString();
    }

    public int getAccuracyPenaltyFromWrongArmor() {
        PrimaryCombatStyle pcbs;
        CombatStyle cbs = getMainWeapon().getCombatStyle();
        if(cbs instanceof PrimaryCombatStyle){
            pcbs = (PrimaryCombatStyle) cbs;
        }else{
            return 0;
        }
        double total = 0;
        for(Slot slot:slotsThatAffectAccuracyPenalty){
            Equipment equipment = slotEquipmentMap.get(slot);
            CombatStyle equipmentCBS = equipment.getCombatStyle();
            if(equipmentCBS==pcbs.getStrongAgainst()){
                total+=equipment.getArmor()*1.5;
            }else if(equipmentCBS==pcbs.getWeakAgainst()){
                total+=equipment.getArmor()*0.8;
            }
        }
        return (int) total;
    }

    public double getDamageReduction() {
        double total = 0;
        for(Slot slot:Slot.values()){
            Equipment equipment = getEquipment(slot);
            if(equipment instanceof Armor){
                total+= ((Armor) equipment).getDamageReduction();
            }
        }
        return total;
    }

    
    
}
