package combatent;

import buff.BuffFlyweight;
import buff.BuffName;
import buff.Buffs;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;
import equipment.AmmoInterface;
import equipment.MagicSpell;
import equipment.Slot;
import equipment.WeaponInterface;
import equipment.WornEquipment;

//stores the player's information
public class Player implements Combatent{

    private String name;
    private PlayerStats stats;
    private WornEquipment wornEquipment;
    private Buffs allBuffs;
    private Buffs playerOtherBuffs;
    private Vulnerabilities vulnerabilities;
    
    
    public Player(String name, PlayerStats stats){
        this.name = name;
        this.stats = stats;
        this.wornEquipment = new WornEquipment();
        this.vulnerabilities = new Vulnerabilities();
        
        this.allBuffs = new Buffs();
        this.playerOtherBuffs = new Buffs();//added with the bottom interface (potions, prayers, ext)
        allBuffs.addBuff(wornEquipment.getEquipmentBuffs());//equipment buffs are automatically added and removed with equipment
        allBuffs.addBuff(playerOtherBuffs);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public PlayerStats getStats() {
        return stats;
    }
    
    public WornEquipment weapon(){
        return wornEquipment;
    }

    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return wornEquipment.getCombatStyle();
    }

    //checks if the player can actually attack with the weapon (right bolts, spell, magic level for spell, not shield). If they can't, return Can't attack combat style
    public boolean canAttackWithWeapon(WeaponInterface weapon,Combatent opponent) {
        
        PrimaryCombatStyle mainWeaponCombatStyle = (PrimaryCombatStyle) weapon.getCombatStyle();
        
        if(mainWeaponCombatStyle.isSameGeneralPrimaryStyleAs(Melee.getInstance()) || weapon.getBuff()==BuffFlyweight.getBuff(BuffName.Chargebow) | mainWeaponCombatStyle==CombatStyleFlyweight.getCombatStyle("Thrown")){
            return true;
        }
        
        //need right ammo from here on
        
        //assign quiverStyle (must be primary style)
        AmmoInterface ammo = (AmmoInterface) wornEquipment.getEquipment((Slot.QUIVER));
        PrimaryCombatStyle quiverStyle;
        CombatStyle quiverGeneralStyle = ammo.getCombatStyle();
        if(quiverGeneralStyle instanceof PrimaryCombatStyle){
            quiverStyle = (PrimaryCombatStyle) quiverGeneralStyle;
        }else{
            return false;
        }
        
        if(mainWeaponCombatStyle.isSameGeneralPrimaryStyleAs(Ranged.getInstance())){
            
            if(quiverStyle==mainWeaponCombatStyle){
                return true;
            }else{
                return false;
            }
            
        }
        
        if(mainWeaponCombatStyle.isSameGeneralPrimaryStyleAs(Magic.getInstance())){
            if(ammo instanceof MagicSpell){
                MagicSpell spell = (MagicSpell) ammo;
                if(stats.getAccuracyLevel(Magic.getInstance())+allBuffs.addVisibleAccuracyLevelsToOwner(this, opponent)>=spell.getMinLevel()){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        
        System.out.println("Worn equipment: Main weapon style not recognized!");
        return false;
    }

    @Override
    public int getAffinityTo(PrimaryCombatStyle cbs) {
        return wornEquipment.getAffinityTo(cbs);
    }
    
    @Override
    public int getBaseAccuracyLevel() {
        return stats.getAccuracyLevel(getCombatStyle());
    }

    //natural accuracy is from main/2h only
    @Override
    public int getNaturalAccuracy() {
        return wornEquipment.getMainWeaponAccuracy();
    }

    @Override
    public int getBasePowerLevel() {
        return stats.getPowerLevel(getCombatStyle());
    }
    
    @Override
    public int getBaseDefenseLevel() {
        return stats.getBaseDefenseLevel();
    }

    //natural armor is from equipment only
    @Override
    public int getNaturalArmor() {
        return wornEquipment.getArmor();
    }
    
    @Override
    public Vulnerabilities getVulnerabilities() {
        return vulnerabilities;
    }

    @Override
    public Buffs getBuffs() {
        return allBuffs;
    }
    
    public Buffs getPlayerOtherBuffs(){
        return playerOtherBuffs;
    }

    public WornEquipment getWornEquipment() {
        return wornEquipment;
    }

    @Override
    public int getAccuracyPenaltyFromWrongArmor() {
        return wornEquipment.getAccuracyPenaltyFromWrongArmor();
    }

    /////promote to Combatent?
    public double getNaturalAbsorbsion() {
        double damageRecievedMultiplier = 1.0-getStats().getBaseDefenseLevel()/1000.0;//for defense level
        damageRecievedMultiplier-=getWornEquipment().getDamageReduction();//for tank armor and shield
        return damageRecievedMultiplier;
    }

    @Override
    public boolean canAttack(Combatent opponent) {
        return canAttackWithWeapon(wornEquipment.getMainWeapon(),opponent);
    }
    
    
    

}
