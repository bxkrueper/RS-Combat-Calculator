package combatent;
import abilities.Ability;
import abilities.AbilityFlyweight;
import buff.Buff;
/*
 * stores the player's information
 */
import buff.BuffFlyweight;
import buff.BuffName;
import buff.Buffs;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;
import equipment.AmmoInterface;
import equipment.Equipment;
import equipment.EquipmentFlyweight;
import equipment.MagicSpell;
import equipment.Slot;
import equipment.WeaponInterface;
import equipment.WornEquipment;

public class Player implements Combatent{

    public static final int ABILITY = 0;
    public static final int MAIN_HAND_AUTO = 1;
    public static final int OFF_HAND_AUTO = 2;
    
    private String name;
    private PlayerStats stats;
    private WornEquipment wornEquipment;
    private Buffs allBuffs;
    private Buffs playerAbilityBuffs;
    private Buffs playerOtherBuffs;
    private Buffs playerInventionPerkBuffs;
    private Vulnerabilities vulnerabilities;
    private Ability currentAbility;////initilize with default ability
    private Attacks attacks;
    
	public Player(String name, PlayerStats stats){
        this.name = name;
        this.stats = stats;
        this.wornEquipment = new WornEquipment();
        this.vulnerabilities = new Vulnerabilities();
        this.currentAbility = AbilityFlyweight.getAbility("Sacrifice");
        
        this.allBuffs = new Buffs();
        this.playerAbilityBuffs = new Buffs();
        this.playerOtherBuffs = new Buffs();//filled with stuff from the bottom interface (potions, prayers, ext)
        this.playerInventionPerkBuffs = new Buffs();
        allBuffs.addBuff(wornEquipment.getEquipmentBuffs());//equipment buffs are automatically added and removed with equipment
        allBuffs.addBuff(playerOtherBuffs);
        allBuffs.addBuff(playerInventionPerkBuffs);
        allBuffs.addBuff(playerAbilityBuffs);//ability buffs are automatically added and removed when switching attacks
        
        this.attacks = new Attacks(new PlayerAbilityAttack(this),new PlayerMainHandAttack(this),new PlayerOffHandAttack(this));
    }
	
	public void setAttackToAbility() {
	    attacks.setIndex(ABILITY);//ability is first
	    allBuffs.addBuff(playerAbilityBuffs);
	}
	
	public void setAttackToMainHand() {
        attacks.setIndex(MAIN_HAND_AUTO);//main hand is second
        allBuffs.removeBuffs(playerAbilityBuffs);
    }
	
	public void setAttackToOffHand() {
        attacks.setIndex(OFF_HAND_AUTO);//offHand is third
        allBuffs.removeBuffs(playerAbilityBuffs);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public PlayerStats getStats() {
        return stats;
    }

    //offensive style depends on weapon
    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return wornEquipment.getCombatStyle();
    }
    
    @Override
    public Attack getAttack() {
		return attacks.getAttack();
	}

	public void setCurrentAbility(Ability currentAbility) {
	    playerAbilityBuffs.removeBuff(this.currentAbility.getBuff().getName());
		this.currentAbility = currentAbility;
		playerAbilityBuffs.addBuff(currentAbility.getBuff());
	}

    //checks if the player can actually attack with the weapon (right bolts, spell, magic level for spell).
    public boolean canAttackWithWeapon(WeaponInterface weapon,Combatent opponent) {//////don't need opponent here? opponents never affect visable accuracy levels
        if(weapon==null) {
        	return false;
        }
        
        PrimaryCombatStyle mainWeaponCombatStyle = (PrimaryCombatStyle) weapon.getCombatStyle();
        
        if(mainWeaponCombatStyle.isSameGeneralPrimaryStyleAs(Melee.getInstance()) || weapon.getBuff()==BuffFlyweight.getBuff(BuffName.Chargebow,BuffFlyweight.Owner.LEFT) | mainWeaponCombatStyle==CombatStyleFlyweight.getCombatStyle("Thrown")){
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
    public double getAffinityTo(PrimaryCombatStyle cbs) {
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
    public double getNaturalArmor() {
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
    
    public Buffs getPlayerInventionPerkBuffs() {
        return playerInventionPerkBuffs;
    }
    
    public Buffs getPlayerOtherBuffs(){
        return playerOtherBuffs;
    }

    public WornEquipment getWornEquipment() {
        return wornEquipment;
    }

    @Override
    public double getAccuracyPenaltyFromWrongArmor() {
        return wornEquipment.getAccuracyPenaltyFromWrongArmor();
    }
    
    public double getNaturalAbsorbsion() {
        double damageRecievedMultiplier = 1.0-getStats().getBaseDefenseLevel()/1000.0;//for defense level
        damageRecievedMultiplier-=getWornEquipment().getDamageReduction();//for tank armor and shield
        return damageRecievedMultiplier;
    }

    @Override
    public boolean canAttack(Combatent opponent) {
        if(attacks.getIndex()==OFF_HAND_AUTO) {
            WeaponInterface offHand = wornEquipment.getOffHandWeapon();
            if(offHand==null) {
                return false;
            }else {
                return canAttackWithWeapon(wornEquipment.getOffHandWeapon(),opponent);
            }
        }else {
            return canAttackWithWeapon(wornEquipment.getMainWeapon(),opponent);
        }
    }

    
    /*
     * level is after boosts  level is strength level for melee
     * bonuses are from stuff like jewelry
     * main hand and off hand spell are assumed to be the same
     * 
     * abilities:
     * Melee:
     * 2.5*hand*level+ weaponDamage*meleeMod + hand*bonuses
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
    
    //for abilities
	@Override
	public double getBaseDamage() {
		WornEquipment wornEquipment = getWornEquipment();
        
        WeaponInterface mainWeapon = wornEquipment.getMainWeapon();
        WeaponInterface offHand = wornEquipment.getOffHandWeapon();
        OffensiveCombatStyle mainWeaponCombatStyle = getCombatStyle();
        
        
        
        System.out.println("main hand: " + mainWeapon);
        System.out.println("off Hand Weapon: " + offHand);
        System.out.println("main hand combat style: " + mainWeaponCombatStyle);
        
        
        int currentPowerLevel = getCurrentPowerLevel();
        System.out.println("current power Level: " + currentPowerLevel);
        
        double bonuses = wornEquipment.getNonWeaponStrengthBonuses();
        System.out.println("non weapon bonuses: " + bonuses);
        
        double abilityDamage = getBaseDamageFromWeapon(currentPowerLevel,mainWeapon,bonuses,(AmmoInterface) wornEquipment.getEquipment(Slot.QUIVER),true);
        System.out.println("damage from main hand: " + abilityDamage);
        
        if(offHand!=null && offHand!=EquipmentFlyweight.getEquipment("No Off Hand") && ((OffensiveCombatStyle) offHand.getCombatStyle()).isSameGeneralOffensiveStyleAs(mainWeaponCombatStyle)){
            //add off hand damage
            double offHandDamage = getBaseDamageFromWeapon(currentPowerLevel,offHand,bonuses,(AmmoInterface) wornEquipment.getEquipment(Slot.QUIVER),true);
            System.out.println("damage from off hand: " + offHandDamage);
            abilityDamage += offHandDamage;
        }
        
        return abilityDamage;
	}
	
    
	

	public double calculateBaseAutoDamage(WeaponInterface weapon) {
		  WornEquipment wornEquipment = this.getWornEquipment();
		  
		  
		  
		  OffensiveCombatStyle weaponCombatStyle = (OffensiveCombatStyle) weapon.getCombatStyle();
		  
		  System.out.println("combat style: " + weaponCombatStyle);
		  
		  
		  int currentPowerLevel = getCurrentPowerLevel();
		  System.out.println("current power Level: " + currentPowerLevel);
		  
		  double bonuses = wornEquipment.getNonWeaponStrengthBonuses();
		  System.out.println("non weapon bonuses: " + bonuses);
		  
		  double autoDamage = getBaseDamageFromWeapon(currentPowerLevel,weapon,bonuses,(AmmoInterface) wornEquipment.getEquipment(Slot.QUIVER),false);
		  
		  return autoDamage;
    }

    
    
    
	
	//stat damage + weapon damage + bonus damage
    private double getBaseDamageFromWeapon(int level,WeaponInterface weapon, double bonuses, AmmoInterface quiverItem,boolean useingAbilities) {
        double handiness = weapon.getHandinessMultiplier();
        System.out.println("handiness mult:" + handiness);
        
        double statPart = 2.5*handiness*level;
        double weaponPart = getWeaponDamage(weapon,quiverItem,level,useingAbilities);
        double bonusPart = handiness*bonuses;
        
        if(!useingAbilities){
            double speedMod = weapon.getWeaponSpeed().speedMod();
            System.out.println("speed mult:" + speedMod);
            statPart*=speedMod;
            bonusPart*=speedMod;
        }
        
        System.out.println("stat part:" + statPart + " weapon part: " + weaponPart + " bonus part: " + bonusPart);
        
        return statPart + weaponPart + bonusPart;
    }

    
    
    private double getWeaponDamage(WeaponInterface weapon, AmmoInterface quiverItem,int powerLevel,boolean forAbilities){
        if(((OffensiveCombatStyle) weapon.getCombatStyle()).isSameGeneralOffensiveStyleAs(Melee.getInstance())){//melee
            System.out.println("Weapon damage: " + weapon.getDamage() + " Weapon speed: " + weapon.getWeaponSpeed() + " Melee Mod: " + weapon.getWeaponSpeed().meleeMod());
            if(forAbilities){
                return weapon.getDamage()*weapon.getWeaponSpeed().meleeMod();
            }else{
                return weapon.getDamage();
            }
            
        }else{//range or magic//////////////////////////////////what about typeless and can't hit?
            double ammoDamage;
            if(weapon.getCombatStyle()==CombatStyleFlyweight.getCombatStyle("Thrown")){
                ammoDamage = weapon.getDamage();
            }else{
                ammoDamage = quiverItem.getDamage(powerLevel);
            }
            System.out.println("ammo damage: " + ammoDamage);
            
            double handinessMult = weapon.getHandinessMultiplier();
            double minChoice1;
            double minChoice2;
            if(forAbilities){
                minChoice1 = 9.6*handinessMult*weapon.getLevel();
                minChoice2 = handinessMult*ammoDamage;
            }else{//same, but divide by meleeMod
                minChoice1 = 9.6*handinessMult/weapon.getWeaponSpeed().meleeMod()*weapon.getLevel();
                minChoice2 = handinessMult/weapon.getWeaponSpeed().meleeMod()*ammoDamage;
            }
            
            
            if(weapon.getBuff()==BuffFlyweight.getBuff(BuffName.Chargebow,BuffFlyweight.Owner.LEFT) && (quiverItem.getBuff()==NullBuff.getInstance() || quiverItem.getCombatStyle()!=weapon.getCombatStyle())){
                //if there is a chargebow with no usable buff-imbued ammunition    assuming only bows have the charbow buff, not staffs
                return minChoice1;
            }
            return Math.min(minChoice1, minChoice2);
        }
    }
    
    private int getCurrentPowerLevel() {
        //power level is multiplied as a double while calculating buffs, then cast to int
		return getBasePowerLevel()+(int) getBuffs().addPowerLevelsToOwner(this, null);///////opponents can't drain power level?
	}

    public Ability getAbility() {
        return currentAbility;
    }

    

    
    

}
