package equipment;

import java.util.HashMap;
import java.util.Map;

import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.Buffs;
import buff.NullBuff;
import combatStyle.All;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.DefensiveCombatStyle;
import combatStyle.Hybrid;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.None;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;

public class WornEquipment{
    
    private static final Slot[] slotsThatAffectAccuracyPenalty = {Slot.HELMET,Slot.BODY,Slot.LEGS,Slot.BOOTS,Slot.GLOVES,Slot.OFF_HAND};
    
    private Map<Slot,Equipment> slotEquipmentMap;
    
    private Buffs equipmentBuffs;
    
    public WornEquipment(){
        this.slotEquipmentMap = new HashMap<>();
        equipNullEquipment();
        this.equipmentBuffs = new Buffs();
    }

    private void equipNullEquipment() {
        for(Slot slot:Slot.values()){
            slotEquipmentMap.put(slot, EquipmentFlyweight.getNullEquipmentForSlot(slot));
        }
    }

    

    public Equipment getEquipment(Slot slot) {
        return slotEquipmentMap.get(slot);
    }
    
    public void equip(Equipment equipment){
        Slot slot = equipment.getSlot();
        deEquip(slot);
        slotEquipmentMap.put(slot, equipment);
        
        Buff buff = equipment.getBuff();
        if(buff!=NullBuff.getInstance()){
            equipmentBuffs.addBuff(buff);
        }
        
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
            PrimaryCombatStyle pcbsSpell = (PrimaryCombatStyle) this.getEquipment(Slot.QUIVER).getCombatStyle();
            if(pcbsSpell.getGeneralOffensiveStyle()==Magic.getInstance().getGeneralOffensiveStyle()){
                return pcbsSpell;
            }else{
                return pcbsWeapon;
            }
        }else{
            return pcbsWeapon;//melee and range weapons have their specific combat styles attached to them
        }
    }
    
    //returns what is in the main-hand/two hand slot. There should not be a real weapon in both
    public WeaponInterface getMainWeapon() {
        if (getEquipment(Slot.MAIN_HAND)!=EquipmentFlyweight.getNullEquipmentForSlot(Slot.MAIN_HAND)){
            return (WeaponInterface) getEquipment(Slot.MAIN_HAND);
        }else{
            return (WeaponInterface) getEquipment(Slot.TWO_HAND);
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
            if(equipment!=null){
                total+= equipment.getDamage();
            }
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
            if(equipment!=null){
                total+= equipment.getDamage();
            }
        }
        return total;
    }
    //totals all armor values
    public int getArmor(){
        int total = 0;
        for(Slot slot:Slot.values()){
            Equipment equipment = getEquipment(slot);
            if(equipment!=null){
                total+= equipment.getArmor();
            }
        }
        return total;
    }

    public int getAffinityTo(PrimaryCombatStyle cbs) {
        int armor = getArmor();//total armor
        if(armor==0){
            return 55;
        }else{
            int weightedAffinity = (int) ((45*getArmor(cbs.getWeakAgainst())+55*getArmor(cbs)+65*getArmor(cbs.getStrongAgainst())+55*getArmor(Hybrid.getInstance())+55*getArmor(All.getInstance())+55*getArmor(Hybrid.getInstance())+55*getArmor(None.getInstance())));
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
            if(equipment.getCombatStyle()==cbs){/////////////////////
                if(equipment!=null){
                    total+= equipment.getArmor();
                }
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
            if(equipment!=null){
                if(equipment instanceof Armor){
                    total+= ((Armor) equipment).getDamageReduction();
                }
            }
        }
        return total;
    }

    
    
}
