package buff;
import java.io.FileNotFoundException;
/*
 * 
 */
import java.util.HashMap;
import java.util.Map;

import abilities.Ability;
import abilities.AbilityFileReader;
import buffSpecific.AccuracyPotion;
import buffSpecific.AccuracyPowerPotion;
import buffSpecific.AffinityRaise;
import buffSpecific.Balmung;
import buffSpecific.BlackMaskAllStyles;
import buffSpecific.BlackMaskOneStyle;
import buffSpecific.BlackStoneArrows;
import buffSpecific.Chargebow;
import buffSpecific.Biting;
import buffSpecific.DamageMultiplier;
import buffSpecific.DamageRecievedMultiplier;
import buffSpecific.Defender;
import buffSpecific.DefensePotion;
import buffSpecific.DesertAmulet3;
import buffSpecific.Dismember;
import buffSpecific.DoubleDamageIfOpponentStunned;
import buffSpecific.DragonBreath;
import buffSpecific.DragonbaneAmmo;
import buffSpecific.Equilibrium;
import buffSpecific.FinalHitChanceAffect;
import buffSpecific.Hexhunter;
import buffSpecific.Keris;
import buffSpecific.Overload;
import buffSpecific.PowerPotion;
import buffSpecific.Precise;
import buffSpecific.Quake;
import buffSpecific.Reaper;
import buffSpecific.SalveAmulet;
import buffSpecific.Silverlight;
import buffSpecific.Slice;
import buffSpecific.SoftDamageCap;
import buffSpecific.SpiritShieldAbsorbsion;
import buffSpecific.StyleAccuracyMultiplier;
import buffSpecific.StyleDamageMultiplier;
import buffSpecific.TokKulZo;
import buffSpecific.TopTierCurse;
import buffSpecific.TopTierPrayer;
import buffSpecific.UpgradedSilverlight;
import buffSpecific.ZerkAura;
import combatStyle.CombatStyleFlyweight;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;

public class BuffFlyweight {
    
    public enum Owner{LEFT,RIGHT};
    
    private static Map<BuffName,Buff> buffMap;//contains an instance of all buffs, but the stack buffs here are not used. Instead, use the personal stack buffs
    private static Map<BuffName,StackBuff> leftStackBuffMap;//contains buffs with local fields that are unique to the owner on the left side
    private static Map<BuffName,StackBuff> rightStackBuffMap;//contains buffs with local fields that are unique to the owner on the right side
    
//    public BuffFlyweight() {
//        if(buffMap==null){
//            initilizeStaticStuff();
//        }
//        
//        //fill personal stack map
//        personalStackBuffMap = new HashMap<>();
//        for(Buff buff:buffMap.values()) {
//            if(buff instanceof StackBuff) {
//                personalStackBuffMap.put(buff.getName(), ((StackBuff) buff).makeNew());
//            }
//        }
//    }
//    
//    //if buff is a stack buff, return the owner's personal stack buff, otherwise return the buff from the static buff map
//    public Buff getPersonalBuff(BuffName name){
//        Buff buff = buffMap.get(name);
//        if(buff==null){
//            System.out.println("Can't find buff: " + name);
//            return null;
//        }
//        
//        if(buff instanceof StackBuff) {
//            return personalStackBuffMap.get(buff.getName());
//        }else {
//            return buff;
//        }
//    }
//    
//    public Buff getPersonalBuff(String niceName){
//        return getPersonalBuff(niceNameToBuffName(niceName));
//    }
    
    //there is only one instance of non-stack buffs
    //if the buff is a stack buff, it will return the owner's stack buff
    public static Buff getBuff(BuffName name,Owner owner){
        if(buffMap==null){
            initilizeStaticStuff();
        }
        
        Buff buff = buffMap.get(name);
        if(buff==null){
            System.out.println("Can't find buff: " + name);
            return null;
        }
        
        if(buff instanceof StackBuff) {
            switch(owner) {
            case LEFT:
                return leftStackBuffMap.get(buff.getName());
            case RIGHT:
                return rightStackBuffMap.get(buff.getName());
            default:
                System.out.println("BuffFlyweight: Unrecognized owner!: " + owner);
                return null;
            }
        }else {
            return buff;
        }
    }
    
    
    
    public static Buff getBuff(String niceName,Owner owner){
        return getBuff(niceNameToBuffName(niceName),owner);
    }
    
    private static BuffName niceNameToBuffName(String niceName) {
        String enumBuffNameStirng = niceName.replace(' ', '_');
        BuffName buffName = BuffName.valueOf(enumBuffNameStirng);
        return buffName;
    }
    
    private static void initilizeStaticStuff() {
        makeMainMap();
        addMoreInfo();
        makePersonalMaps();
    }
    



    private static void makeMainMap() {
        buffMap = new HashMap<>();
        
        Buff buff = NullBuff.getInstance();
        addBuff(buff);
        
        buff = new NoBuff(BuffName.No_Potion);
        addBuff(buff);
        
        buff = new NoBuff(BuffName.No_Prayer);
        addBuff(buff);
        
        buff = new NoBuff(BuffName.No_Familiar);
        addBuff(buff);
        
        buff = new AffinityRaise(BuffName.Guthix_Staff_Spec,2);
        addBuff(buff);
        
        buff = new AffinityRaise(BuffName.Status_Warhammer_Spec,5);
        addBuff(buff);
        
        buff = new Quake();
        addBuff(buff);
        
        buff = new AccuracyPotion(BuffName.Basic_Attack_Potion,0.08,1,Melee.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Basic_Ranging_Potion,0.08,1,Ranged.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Basic_Magic_Potion,0.08,1,Magic.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPotion(BuffName.Super_Attack_Potion,0.12,2,Melee.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Super_Ranging_Potion,0.12,2,Ranged.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Super_Magic_Potion,0.12,2,Magic.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPotion(BuffName.Grand_Attack_Potion,0.14,2,Melee.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Grand_Ranging_Potion,0.14,2,Ranged.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Grand_Magic_Potion,0.14,2,Magic.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPotion(BuffName.Extreme_Attack_Potion,0.15,3,Melee.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Extreme_Ranging_Potion,0.15,3,Ranged.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Extreme_Magic_Potion,0.15,3,Magic.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPotion(BuffName.Supreme_Attack_Potion,0.16,4,Melee.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Supreme_Ranging_Potion,0.16,4,Ranged.getInstance());
        addBuff(buff);
        
        buff = new AccuracyPowerPotion(BuffName.Supreme_Magic_Potion,0.16,4,Magic.getInstance());
        addBuff(buff);
        
        buff = new PowerPotion(BuffName.Basic_Strength_Potion,0.08,1,Melee.getInstance());
        addBuff(buff);
        
        buff = new PowerPotion(BuffName.Super_Strength_Potion,0.12,2,Melee.getInstance());
        addBuff(buff);
        
        buff = new PowerPotion(BuffName.Grand_Strength_Potion,0.14,2,Melee.getInstance());
        addBuff(buff);
        
        buff = new PowerPotion(BuffName.Extreme_Strength_Potion,0.15,3,Melee.getInstance());
        addBuff(buff);
        
        buff = new PowerPotion(BuffName.Supreme_Strength_Potion,0.16,4,Melee.getInstance());
        addBuff(buff);
        
        buff = new DefensePotion(BuffName.Basic_Defense_Potion,0.08,1);
        addBuff(buff);
        
        buff = new DefensePotion(BuffName.Super_Defense_Potion,0.12,2);
        addBuff(buff);
        
        buff = new DefensePotion(BuffName.Grand_Defense_Potion,0.14,2);
        addBuff(buff);
        
        buff = new DefensePotion(BuffName.Extreme_Defense_Potion,0.15,3);
        addBuff(buff);
        
        buff = new DefensePotion(BuffName.Supreme_Defense_Potion,0.16,4);
        addBuff(buff);
        
        buff = new Overload(BuffName.Overload,0.15,3);
        addBuff(buff);
        
        buff = new Overload(BuffName.Supreme_Overload,0.16,4);
        addBuff(buff);
        
        buff = new TopTierPrayer(BuffName.Chivalry,Melee.getInstance(),7,1.07);
        addBuff(buff);
        
        buff = new TopTierPrayer(BuffName.Piety,Melee.getInstance(),8,1.08);
        addBuff(buff);
        
        buff = new TopTierPrayer(BuffName.Rigour,Ranged.getInstance(),8,1.08);
        addBuff(buff);
        
        buff = new TopTierPrayer(BuffName.Augury,Magic.getInstance(),8,1.08);
        addBuff(buff);
        
        buff = new Defender(BuffName.Defender,Melee.getInstance());
        addBuff(buff);
        
        buff = new Defender(BuffName.Repriser,Ranged.getInstance());
        addBuff(buff);
        
        buff = new Defender(BuffName.Rebounder,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Brawler_Aura,1.03,Melee.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Sharpshooter_Aura,1.03,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Runic_Accuracy_Aura,1.03,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Greater_Brawler_Aura,1.05,Melee.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Greater_Sharpshooter_Aura,1.05,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Greater_Runic_Accuracy_Aura,1.05,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Master_Brawler_Aura,1.07,Melee.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Master_Sharpshooter_Aura,1.07,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Master_Runic_Accuracy_Aura,1.07,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supreme_Brawler_Aura,1.1,Melee.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supreme_Sharpshooter_Aura,1.1,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supreme_Runic_Accuracy_Aura,1.1,Magic.getInstance());
        addBuff(buff);
        
        buff = new ZerkAura(BuffName.Berserker_Aura,Melee.getInstance());
        addBuff(buff);
        
        buff = new ZerkAura(BuffName.Reckless_Aura,Ranged.getInstance());
        addBuff(buff);
        
        buff = new ZerkAura(BuffName.Maniacal_Aura,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Scrimshaw_of_Attack,1.02,Melee.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Scrimshaw_of_Ranging,1.02,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Scrimshaw_of_Magic,1.02,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supeior_Scrimshaw_of_Attack,1.04,Melee.getInstance(),"Scrimshaw_of_Attack");
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supeior_Scrimshaw_of_Ranging,1.04,Ranged.getInstance(),"Scrimshaw_of_Ranging");
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supeior_Scrimshaw_of_Magic,1.04,Magic.getInstance(),"Scrimshaw_of_Magic");
        addBuff(buff);
        
        buff = new StyleDamageMultiplier(BuffName.Scrimshaw_of_Cruelty,1.05,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleDamageMultiplier(BuffName.Scrimshaw_of_the_Elements,1.05,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleDamageMultiplier(BuffName.Supeior_Scrimshaw_of_Cruelty,1.0666,Ranged.getInstance(),"Scrimshaw_of_Cruelty");
        addBuff(buff);
        
        buff = new StyleDamageMultiplier(BuffName.Supeior_Scrimshaw_of_the_Elements,1.0666,Magic.getInstance(),"Scrimshaw_of_the_Elements");
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Blood_Nihil,1.05,Melee.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Shadow_Nihil,1.05,Ranged.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Smoke_Nihil,1.05,Magic.getInstance());
        addBuff(buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Ice_Nihil,1.05,Magic.getInstance());
        addBuff(buff);
        
        buff = new BlackMaskOneStyle(BuffName.Black_Mask,1.125,Melee.getInstance());
        addBuff(buff);
        
        buff = new BlackMaskOneStyle(BuffName.Focus_Sight,1.125,Ranged.getInstance());
        addBuff(buff);
        
        buff = new BlackMaskOneStyle(BuffName.Hexcrest,1.125,Magic.getInstance());
        addBuff(buff);
        
        buff = new BlackMaskAllStyles(BuffName.Slayer_Helmet_All,1.125);
        addBuff(buff);
        
        buff = new BlackMaskAllStyles(BuffName.Reinforced_Slayer_Helmet,1.13);
        addBuff(buff);
        
        buff = new BlackMaskAllStyles(BuffName.Strong_Slayer_Helmet,1.135);
        addBuff(buff);
        
        buff = new BlackMaskAllStyles(BuffName.Mighty_Slayer_Helmet,1.14);
        addBuff(buff);
        
        buff = new BlackMaskAllStyles(BuffName.Corrupted_Slayer_Helmet,1.145);
        addBuff(buff);
        
        buff = new Hexhunter();
        addBuff(buff);
        
        buff = new Keris();
        addBuff(buff);
        
        buff = new Balmung(BuffName.Balmung,1.125,1.075,30,1.25,1.15);
        addBuff(buff);
        
        buff = new Balmung(BuffName.Upgraded_Balmung,1.225,1.175,45,1.45,1.35);
        addBuff(buff);
        
        buff = new Silverlight();
        addBuff(buff);
        
        buff = new UpgradedSilverlight();
        addBuff(buff);
        
        buff = new SalveAmulet(BuffName.Salve_Amulet,1.15);
        addBuff(buff);
        
        buff = new SalveAmulet(BuffName.Salve_Amulet_E,1.2);
        addBuff(buff);
        
        buff = new DragonbaneAmmo(BuffName.Dragonbane);
        addBuff(buff);
        
        buff = new DragonbaneAmmo(BuffName.Dragonbane_Arrows,(PrimaryCombatStyle) CombatStyleFlyweight.getCombatStyle("Arrows"));
        addBuff(buff);
        
        buff = new DragonbaneAmmo(BuffName.Dragonbane_Bolts,(PrimaryCombatStyle) CombatStyleFlyweight.getCombatStyle("Bolts"));
        addBuff(buff);
        
        buff = new Chargebow();
        addBuff(buff);
        
        buff = new DamageRecievedMultiplier(BuffName.Vulnerability,1.1);
        addBuff(buff);
        
        buff = new DamageMultiplier(BuffName.Enfeeble,0.9);
        addBuff(buff);
        
        buff = new FinalHitChanceAffect(BuffName.Stagger,-10);
        addBuff(buff);
        
        buff = new DamageMultiplier(BuffName.Mahjarrat_Aura,1.05);
        addBuff(buff);
        
        buff = new DesertAmulet3();
        addBuff(buff);
        
        buff = new SpiritShieldAbsorbsion();
        addBuff(buff);
        
        buff = new TokKulZo();
        addBuff(buff);
        
        buff = new FinalHitChanceAffect(BuffName.Ultimate_Ability_Hit_Chance_Boost,25);
        addBuff(buff);
        
        buff = new DragonBreath();
        addBuff(buff);
        
        buff = new Dismember();
        addBuff(buff);
        
        
        
        buff = new ConstantFillInBuff(BuffName.Dragon_Rider_Amulet);//does nothing in this calculator but acts like a tag
        addBuff(buff);
        
        buff = new ConstantFillInBuff(BuffName.Strength_Cape_Perk);//does nothing in this calculator but acts like a tag
        addBuff(buff);
        
        buff = new DoubleDamageIfOpponentStunned();
        addBuff(buff);
        
        buff = new Slice();
        addBuff(buff);
        
        buff = new SoftDamageCap(BuffName.Nex_Soft_Damage_Cap_Phase1234,5000,0.5,"Soft_Damage_Cap");
        addBuff(buff);
        
        buff = new SoftDamageCap(BuffName.Nex_Soft_Damage_Cap_Phase5,2000,0.25,"Soft_Damage_Cap");
        addBuff(buff);
        
        buff = new ConstantFillInBuff(BuffName.Poison);//does nothing in this calculator but acts like a tag to tell if the monster is weak to poison
        addBuff(buff);
        
        buff = new DamageRecievedMultiplier(BuffName.Reflect,0.5);
        addBuff(buff);
        
        buff = new ConstantFillInBuff(BuffName.Stun);//does nothing in this calculator but acts like a tag
        addBuff(buff);
        
        buff = new ConstantFillInBuff(BuffName.Drain);//does nothing in this calculator but acts like a tag to tell if the monster is weak to drain
        addBuff(buff);
        
        
        
        //stack buffs
        buff = new BlackStoneArrows();
        addBuff(buff);
        
        buff = new Reaper();
        addBuff(buff);
        
        buff = new Precise();
        addBuff(buff);
        
        buff = new Equilibrium();
        addBuff(buff);
        
        buff = new Biting();
        addBuff(buff);
        
        buff = new TopTierCurse(BuffName.Turmoil,Melee.getInstance(),10,1.1);
        addBuff(buff);
        
        buff = new TopTierCurse(BuffName.Anguish,Ranged.getInstance(),10,1.1);
        addBuff(buff);
        
        buff = new TopTierCurse(BuffName.Torment,Magic.getInstance(),10,1.1);
        addBuff(buff);
        
        buff = new TopTierCurse(BuffName.Malevolence,Melee.getInstance(),12,1.12);
        addBuff(buff);
        
        buff = new TopTierCurse(BuffName.Desolation,Ranged.getInstance(),12,1.12);
        addBuff(buff);
        
        buff = new TopTierCurse(BuffName.Affliction,Magic.getInstance(),12,1.12);
        addBuff(buff);
                
    }
    
    private static void addBuff(Buff buff) {
        buffMap.put(buff.getName(), buff);
    }
    
    private static void addMoreInfo() {
        String fileName = "data/buffs/Buff Info.csv";
        BuffInfoFileReader buffInfoReader;
        try {
            buffInfoReader = new BuffInfoFileReader(fileName);
            for(Object o:buffInfoReader){
                String[] info = (String[]) o;
                
                BuffName buffName = BuffName.valueOf(info[0]);
                String description = info[1];
                
                Buff buff = buffMap.get(buffName);
                if(buff instanceof ConstantFillInBuff) {
                    ConstantFillInBuff cfiBuff = (ConstantFillInBuff) buff;
                    cfiBuff.setDescription(description);
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println("file not found: " + fileName);
        }
    }


    private static void makePersonalMaps() {
        leftStackBuffMap = new HashMap<>();
        for(Buff buff:buffMap.values()) {
            if(buff instanceof StackBuff) {
                leftStackBuffMap.put(buff.getName(), ((StackBuff) buff).makeNew());
            }
        }
        
        rightStackBuffMap = new HashMap<>();
        for(Buff buff:buffMap.values()) {
            if(buff instanceof StackBuff) {
                rightStackBuffMap.put(buff.getName(), ((StackBuff) buff).makeNew());
            }
        }
    }



}
