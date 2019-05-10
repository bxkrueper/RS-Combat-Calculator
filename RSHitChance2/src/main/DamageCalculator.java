package main;

import buff.BuffFlyweight;
import buff.BuffName;
import buff.NullBuff;
import combatStyle.CombatStyleFlyweight;
import combatStyle.Melee;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatent.Combatent;
import combatent.Monster;
import combatent.Player;
import equipment.AmmoInterface;
import equipment.Equipment;
import equipment.EquipmentFlyweight;
import equipment.Slot;
import equipment.WeaponInterface;
import equipment.WeaponSpeed;
import equipment.WornEquipment;
/////////simplify
public class DamageCalculator {
    
    /*
     * level is after boosts  level is strength level for melee
     * bonuses are from stuff like jewelry
     * main hand and off hand spell are assumed to be the same
     * 
     * abilities:
     * Melee:
     * hand*2.5*level+ weaponDamage*meleeMod + hand*bonuses
     * 
     * Ranged,Magic:
     * 2.5*hand*level + min(9.6*hand*tier,hand*ammoDamage) + hand*bonuses
     * 
     * 
     * auto attacks:
     * 
     * Melee:
     * 2.5*hand*speedMod*level+ weaponDamage + hand*speedMod*bonuses
     * 
     * Ranged,Magic:
     * 2.5*hand*speedMod*level + min(9.6*hand/meleeMod*tier,hand/meleeMod*ammoDamage) + hand*speedMod*bonuses
     */
    public static int calculateAbilityDamage(Player attacker, Combatent defender){
        
        if(!attacker.canAttack(defender)){
            System.out.println(attacker + " Can't attack!");
            return 0;
        }
        
        WornEquipment wornEquipment = attacker.getWornEquipment();
        
        
        
        WeaponInterface mainWeapon = wornEquipment.getMainWeapon();
        Equipment offHand = wornEquipment.getEquipment(Slot.OFF_HAND);
        OffensiveCombatStyle mainWeaponCombatStyle = attacker.getCombatStyle();
        OffensiveCombatStyle offHandCombatStyle = (OffensiveCombatStyle) offHand.getCombatStyle();
        
        
        
        System.out.println("\nmain hand: " + mainWeapon);
        System.out.println("off Hand: " + offHand);
        System.out.println("main hand combat style: " + mainWeaponCombatStyle);
//        System.out.println("offHandCombatStyle: " + offHandCombatStyle);
        
        
        
        
        int basePowerLevel = attacker.getBasePowerLevel();
        System.out.println("base power Level: " + basePowerLevel);
        int extraPowerLevels = getAttackerPowerLevelBuffAdd(attacker, defender);
        System.out.println("extra power Levels: " + extraPowerLevels);
        int boostedPowerLevel = basePowerLevel+extraPowerLevels;
        System.out.println("boosted Power Level: " + boostedPowerLevel);
        
        int bonuses = wornEquipment.getNonWeaponStrengthBonuses();
        System.out.println("non weapon bonuses: " + bonuses);
        
        int abilityDamage = (int) getBaseDamage(boostedPowerLevel,mainWeapon,bonuses,(AmmoInterface) wornEquipment.getEquipment(Slot.QUIVER),true);
        System.out.println("damage from main hand: " + abilityDamage);
        
        if(offHand!=EquipmentFlyweight.getEquipment("No Off Hand") && offHandCombatStyle.isSameGeneralOffensiveStyleAs(mainWeaponCombatStyle)){
            //add off hand damage
            int offHandDamage;
            if(offHand instanceof WeaponInterface){
                offHandDamage = getBaseDamage(boostedPowerLevel,(WeaponInterface) offHand,bonuses,(AmmoInterface) wornEquipment.getEquipment(Slot.QUIVER),true);
            }else{
                offHandDamage = 0;
            }
            System.out.println("damage from off hand: " + offHandDamage);
            abilityDamage += offHandDamage;
        }
        
        //apply extra levels bonus
        System.out.println("extra power level bonus: " + extraPowerLevels*8);
        abilityDamage+=extraPowerLevels*8;
        
        System.out.println("ability damage before damage absorbsion: " + abilityDamage);
        
        //Apply monster natural absorbsion
        abilityDamage*=defender.getNaturalAbsorbsion();
        System.out.println("monster natural absorbsion: " + defender.getNaturalAbsorbsion());
        System.out.println("ability damage before mult buffs: " + abilityDamage);
        
        //apply multiplicative boosts
        System.out.println("damage mult from owner: " + attacker.getBuffs().getDamageMultiplier(attacker, defender));
        System.out.println("damage mult from opponent: " + defender.getBuffs().getDamageRecievedMultiplier(defender,attacker));
        abilityDamage*=attacker.getBuffs().getDamageMultiplier(attacker, defender);
        abilityDamage*=defender.getBuffs().getDamageRecievedMultiplier(defender,attacker);
        
        return abilityDamage;
    }
    
    //stat damage + weapon damage + bonus damage
    private static int getBaseDamage(int boostedLevel,WeaponInterface weapon, int bonuses, AmmoInterface quiverItem,boolean useingAbilities) {
        double handiness = getHandinessMult(weapon);
        System.out.println("handiness mult:" + handiness);
        
        int statPart = (int) (2.5*handiness*boostedLevel);
        int weaponPart = (int) getWeaponDamage(weapon,quiverItem,boostedLevel,useingAbilities);
        int bonusPart = (int) (handiness*bonuses);
        
        if(!useingAbilities){
            double speedMod = weapon.getWeaponSpeed().speedMod();
            System.out.println("speed mult:" + speedMod);
            statPart*=speedMod;
            bonusPart*=speedMod;
        }
        
        System.out.println("stat part:" + statPart + " weapon part: " + weaponPart + " bonus part: " + bonusPart);
        
        return statPart + weaponPart + bonusPart;
    }

    
    
    private static double getWeaponDamage(WeaponInterface weapon, AmmoInterface quiverItem,int powerLevel,boolean forAbilities){
        if(((OffensiveCombatStyle) weapon.getCombatStyle()).isSameGeneralOffensiveStyleAs(Melee.getInstance())){//melee
            System.out.println("Weapon damage: " + weapon.getDamage() + " Weapon speed: " + weapon.getWeaponSpeed() + " Melee Mod: " + weapon.getWeaponSpeed().meleeMod());
            if(forAbilities){
                return weapon.getDamage()*weapon.getWeaponSpeed().meleeMod();
            }else{
                return weapon.getDamage();
            }
            
        }else{//range or magic//////////////////////////////////what about typeless and can't hit?
            int ammoDamage;
            if(weapon.getCombatStyle()==CombatStyleFlyweight.getCombatStyle("Thrown")){
                ammoDamage = weapon.getDamage();
            }else{
                ammoDamage = quiverItem.getDamage(powerLevel);
            }
            System.out.println("ammo damage: " + ammoDamage);
            
            double minChoice1;
            double minChoice2;
            if(forAbilities){
                minChoice1 = 9.6*getHandinessMult(weapon)*weapon.getLevel();
                minChoice2 = getHandinessMult(weapon)*ammoDamage;
            }else{//same, but divide by meleeMod
                minChoice1 = 9.6*getHandinessMult(weapon)/weapon.getWeaponSpeed().meleeMod()*weapon.getLevel();
                minChoice2 = getHandinessMult(weapon)/weapon.getWeaponSpeed().meleeMod()*ammoDamage;
            }
            
            
            if(weapon.getBuff()==BuffFlyweight.getBuff(BuffName.Chargebow) && (quiverItem.getBuff()==NullBuff.getInstance() || quiverItem.getCombatStyle()!=weapon.getCombatStyle())){
                //if there is a chargebow with no usable buff-imbued ammunition    assuming only bows have the charbow buff, not staffs
                return minChoice1;
            }
            return Math.min(minChoice1, minChoice2);
        }
    }

    

    private static double getHandinessMult(WeaponInterface weapon) {
        switch(weapon.getSlot()){
        case MAIN_HAND:
            return 1;
        case OFF_HAND:
            return 0.5;
        case TWO_HAND:
            return 1.5;
        default:
            System.out.println("AbilityDamageCalculator: unrecognized weapon handiness!");
            return 0;
        }
    }

    private static int getAttackerPowerLevelBuffAdd(Combatent attacker, Combatent defender) {
        int extraLevels = 0;
        extraLevels+=attacker.getBuffs().addPowerLevelsToOwner(attacker, defender);//ex: pots, prayers, zerk aura
        extraLevels+=defender.getBuffs().addPowerLevelsToOpponent(defender, attacker);//ex: turmoil
        System.out.println("extra Levels from boosts: " + extraLevels);
        return extraLevels;
    }
    
    
    
    
    
    
    public static int calculateAutoDamage(Player player, Monster currentMonster, boolean mainHand) {
        if(mainHand){
            return calculateAutoDamage(player, currentMonster, player.getWornEquipment().getMainWeapon());
        }else{
            Equipment offHand = player.getWornEquipment().getEquipment(Slot.OFF_HAND);
            if(offHand instanceof WeaponInterface){
                return calculateAutoDamage(player, currentMonster, (WeaponInterface) offHand);
            }else{
                return 0;
            }
            
        }
    }

    
    
    private static int calculateAutoDamage(Player attacker, Monster defender, WeaponInterface weapon) {
        if(!attacker.canAttackWithWeapon(weapon,defender)){
            System.out.println(attacker + " Can't attack with " + weapon);
            return 0;
        }
        
        WornEquipment wornEquipment = attacker.getWornEquipment();
        
        
        
        OffensiveCombatStyle weaponCombatStyle = (OffensiveCombatStyle) weapon.getCombatStyle();
        
        System.out.println("\ncalculating auto attack damage for " + weapon);
        System.out.println("combat style: " + weaponCombatStyle);
        
        
        
        
        int basePowerLevel = attacker.getBasePowerLevel();
        System.out.println("base power Level: " + basePowerLevel);
        int extraPowerLevels = getAttackerPowerLevelBuffAdd(attacker, defender);
        System.out.println("extra power Levels: " + extraPowerLevels);
        int boostedPowerLevel = basePowerLevel+extraPowerLevels;
        System.out.println("boosted Power Level: " + boostedPowerLevel);
        
        int bonuses = wornEquipment.getNonWeaponStrengthBonuses();
        System.out.println("non weapon bonuses: " + bonuses);
        
        int autoDamage = (int) getBaseDamage(boostedPowerLevel,weapon,bonuses,(AmmoInterface) wornEquipment.getEquipment(Slot.QUIVER),false);
        System.out.println("base damage: " + autoDamage);
        
        
        //apply extra levels bonus
        System.out.println("extra power level bonus: " + extraPowerLevels*8);
        autoDamage+=extraPowerLevels*8;
        
        System.out.println("Auto damage before natural absorbsion: " + autoDamage);
        
        //Apply monster natural absorbsion
        autoDamage*=defender.getNaturalAbsorbsion();
        System.out.println("monster natural absorbsion: " + defender.getNaturalAbsorbsion());
        System.out.println("auto damage before mult buffs: " + autoDamage);
        
        //apply multiplicative boosts
        System.out.println("damage mult from owner: " + attacker.getBuffs().getDamageMultiplier(attacker, defender));
        System.out.println("damage mult from opponent: " + defender.getBuffs().getDamageRecievedMultiplier(defender,attacker));
        autoDamage*=attacker.getBuffs().getDamageMultiplier(attacker, defender);
        autoDamage*=defender.getBuffs().getDamageRecievedMultiplier(defender,attacker);
        
        //apply auto attack multiplicative boosts
        System.out.println("auto damage mult from owner: " + attacker.getBuffs().getAutoAttackDamageMultiplier(attacker, defender));
        autoDamage*=attacker.getBuffs().getAutoAttackDamageMultiplier(attacker, defender);
        
        return autoDamage;
    }
    
    
    
    
    
    
    //monster damage is simpler
    public static int calculateAbilityDamage(Monster attacker, Player defender) {
        int damage = attacker.getAttacks().getAttack().getMaxHit();
        System.out.println("monster base damage: " + damage);
        
        //apply natural absorbsion
        damage*=defender.getNaturalAbsorbsion();
        System.out.println("player natural absorbsion: " + defender.getNaturalAbsorbsion());
        System.out.println("monster damage after player natural absorbsion: " + damage);
        
        //apply multiplicative boosts from buffs
        damage*=attacker.getBuffs().getDamageMultiplier(attacker, defender);
        damage*=defender.getBuffs().getDamageRecievedMultiplier(defender,attacker);
        System.out.println("monster buffs multiplier: " + attacker.getBuffs().getDamageMultiplier(attacker, defender));
        System.out.println("player buffs multiplier: " + defender.getBuffs().getDamageRecievedMultiplier(defender,attacker));
        System.out.println("monster damage after boosts: " + damage);
        
        return damage;
    }

    
}
