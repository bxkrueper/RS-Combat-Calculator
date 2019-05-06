package buff;
/*
 * 
 */
import java.util.HashMap;
import java.util.Map;

import buffSpecific.AccuracyPotion;
import buffSpecific.AccuracyPowerPotion;
import buffSpecific.AffinityRaise;
import buffSpecific.Balmung;
import buffSpecific.BlackMaskAllStyles;
import buffSpecific.BlackMaskOneStyle;
import buffSpecific.BlackStoneArrows;
import buffSpecific.Chargebow;
import buffSpecific.DamageMultiplier;
import buffSpecific.DamageRecievedMultiplier;
import buffSpecific.Defender;
import buffSpecific.DefensePotion;
import buffSpecific.DesertAmulet3;
import buffSpecific.DragonbaneAmmo;
import buffSpecific.FinalHitChanceAffect;
import buffSpecific.Hexhunter;
import buffSpecific.Keris;
import buffSpecific.Overload;
import buffSpecific.PowerPotion;
import buffSpecific.Reaper;
import buffSpecific.SalveAmulet;
import buffSpecific.Silverlight;
import buffSpecific.SpiritShieldAbsorbsion;
import buffSpecific.StyleAccuracyMultiplier;
import buffSpecific.StyleDamageMultiplier;
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
    
    private static Map<BuffName,Buff> buffMap;
    
    public static Buff getBuff(BuffName name){
        if(buffMap==null){
            makeMap();
        }
        
        Buff buff = buffMap.get(name);
        if(buff==null){
            System.out.println("Can't find buff: " + name);
            return null;
        }
        
        return buff;
    }
    public static Buff getBuff(String niceName){
        return getBuff(niceNameToBuffName(niceName));
    }
    
    private static BuffName niceNameToBuffName(String niceName) {
        String enumBuffNameStirng = niceName.replace(' ', '_');
        BuffName buffName = BuffName.valueOf(enumBuffNameStirng);
        return buffName;
    }
    
    private static void makeMap() {
        buffMap = new HashMap<>();
        
        Buff buff = NullBuff.getInstance();
        buffMap.put(buff.getName(), buff);
        
        buff = new NoBuff(BuffName.No_Potion);
        buffMap.put(buff.getName(), buff);
        
        buff = new NoBuff(BuffName.No_Prayer);
        buffMap.put(buff.getName(), buff);
        
        buff = new NoBuff(BuffName.No_Familiar);
        buffMap.put(buff.getName(), buff);
        
        buff = new AffinityRaise(BuffName.Guthix_Staff_Spec,2);
        buffMap.put(buff.getName(), buff);
        
        buff = new AffinityRaise(BuffName.Status_Warhammer_Spec,5);
        buffMap.put(buff.getName(), buff);
        
        buff = new AffinityRaise(BuffName.Quake,2);
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackStoneArrows();
        buffMap.put(buff.getName(), buff);
        
        buff = new Reaper();
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPotion(BuffName.Basic_Attack_Potion,0.08,1,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Basic_Ranging_Potion,0.08,1,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Basic_Magic_Potion,0.08,1,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPotion(BuffName.Super_Attack_Potion,0.12,2,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Super_Ranging_Potion,0.12,2,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Super_Magic_Potion,0.12,2,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPotion(BuffName.Grand_Attack_Potion,0.14,2,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Grand_Ranging_Potion,0.14,2,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Grand_Magic_Potion,0.14,2,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPotion(BuffName.Extreme_Attack_Potion,0.15,3,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Extreme_Ranging_Potion,0.15,3,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Extreme_Magic_Potion,0.15,3,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPotion(BuffName.Supreme_Attack_Potion,0.16,4,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Supreme_Ranging_Potion,0.16,4,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new AccuracyPowerPotion(BuffName.Supreme_Magic_Potion,0.16,4,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new PowerPotion(BuffName.Basic_Strength_Potion,0.08,1,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new PowerPotion(BuffName.Super_Strength_Potion,0.12,2,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new PowerPotion(BuffName.Grand_Strength_Potion,0.14,2,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new PowerPotion(BuffName.Extreme_Strength_Potion,0.15,3,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new PowerPotion(BuffName.Supreme_Strength_Potion,0.16,4,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new DefensePotion(BuffName.Basic_Defense_Potion,0.08,1);
        buffMap.put(buff.getName(), buff);
        
        buff = new DefensePotion(BuffName.Super_Defense_Potion,0.12,2);
        buffMap.put(buff.getName(), buff);
        
        buff = new DefensePotion(BuffName.Grand_Defense_Potion,0.14,2);
        buffMap.put(buff.getName(), buff);
        
        buff = new DefensePotion(BuffName.Extreme_Defense_Potion,0.15,3);
        buffMap.put(buff.getName(), buff);
        
        buff = new DefensePotion(BuffName.Supreme_Defense_Potion,0.16,4);
        buffMap.put(buff.getName(), buff);
        
        buff = new Overload(BuffName.Overload,0.15,3);
        buffMap.put(buff.getName(), buff);
        
        buff = new Overload(BuffName.Supreme_Overload,0.16,4);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierPrayer(BuffName.Chivalry,Melee.getInstance(),7,1.07);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierPrayer(BuffName.Piety,Melee.getInstance(),8,1.08);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierPrayer(BuffName.Rigour,Ranged.getInstance(),8,1.08);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierPrayer(BuffName.Augury,Magic.getInstance(),8,1.08);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierCurse(BuffName.Turmoil,Melee.getInstance(),10,1.1);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierCurse(BuffName.Anguish,Ranged.getInstance(),10,1.1);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierCurse(BuffName.Torment,Magic.getInstance(),10,1.1);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierCurse(BuffName.Malevolence,Melee.getInstance(),12,1.12);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierCurse(BuffName.Desolation,Ranged.getInstance(),12,1.12);
        buffMap.put(buff.getName(), buff);
        
        buff = new TopTierCurse(BuffName.Affliction,Magic.getInstance(),12,1.12);
        buffMap.put(buff.getName(), buff);
        
        buff = new Defender(BuffName.Defender,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new Defender(BuffName.Repriser,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new Defender(BuffName.Rebounder,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Brawler_Aura,1.03,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Sharpshooter_Aura,1.03,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Runic_Accuracy_Aura,1.03,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Greater_Brawler_Aura,1.05,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Greater_Sharpshooter_Aura,1.05,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Greater_Runic_Accuracy_Aura,1.05,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Master_Brawler_Aura,1.07,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Master_Sharpshooter_Aura,1.07,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Master_Runic_Accuracy_Aura,1.07,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supreme_Brawler_Aura,1.1,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supreme_Sharpshooter_Aura,1.1,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supreme_Runic_Accuracy_Aura,1.1,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new ZerkAura(BuffName.Berserker_Aura,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new ZerkAura(BuffName.Reckless_Aura,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new ZerkAura(BuffName.Maniacal_Aura,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Scrimshaw_of_Attack,1.02,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Scrimshaw_of_Ranging,1.02,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Scrimshaw_of_Magic,1.02,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supeior_Scrimshaw_of_Attack,1.04,Melee.getInstance(),"Scrimshaw_of_Attack");
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supeior_Scrimshaw_of_Ranging,1.04,Ranged.getInstance(),"Scrimshaw_of_Ranging");
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Supeior_Scrimshaw_of_Magic,1.04,Magic.getInstance(),"Scrimshaw_of_Magic");
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleDamageMultiplier(BuffName.Scrimshaw_of_Cruelty,1.05,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleDamageMultiplier(BuffName.Scrimshaw_of_the_Elements,1.05,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleDamageMultiplier(BuffName.Supeior_Scrimshaw_of_Cruelty,1.0666,Ranged.getInstance(),"Scrimshaw_of_Cruelty");
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleDamageMultiplier(BuffName.Supeior_Scrimshaw_of_the_Elements,1.0666,Magic.getInstance(),"Scrimshaw_of_the_Elements");
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Blood_Nihil,1.05,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Shadow_Nihil,1.05,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Smoke_Nihil,1.05,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new StyleAccuracyMultiplier(BuffName.Ice_Nihil,1.05,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskOneStyle(BuffName.Black_Mask,1.125,Melee.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskOneStyle(BuffName.Focus_Sight,1.125,Ranged.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskOneStyle(BuffName.Hexcrest,1.125,Magic.getInstance());
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskAllStyles(BuffName.Slayer_Helmet_All,1.125);
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskAllStyles(BuffName.Reinforced_Slayer_Helmet,1.13);
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskAllStyles(BuffName.Strong_Slayer_Helmet,1.135);
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskAllStyles(BuffName.Mighty_Slayer_Helmet,1.14);
        buffMap.put(buff.getName(), buff);
        
        buff = new BlackMaskAllStyles(BuffName.Corrupted_Slayer_Helmet,1.145);
        buffMap.put(buff.getName(), buff);
        
        buff = new Hexhunter();
        buffMap.put(buff.getName(), buff);
        
        buff = new Keris();
        buffMap.put(buff.getName(), buff);
        
        buff = new Balmung(BuffName.Balmung,1.125,1.075,30,1.25,1.15);
        buffMap.put(buff.getName(), buff);
        
        buff = new Balmung(BuffName.Upgraded_Balmung,1.225,1.175,45,1.45,1.35);
        buffMap.put(buff.getName(), buff);
        
        buff = new Silverlight();
        buffMap.put(buff.getName(), buff);
        
        buff = new UpgradedSilverlight();
        buffMap.put(buff.getName(), buff);
        
        buff = new SalveAmulet(BuffName.Salve_Amulet,1.15);
        buffMap.put(buff.getName(), buff);
        
        buff = new SalveAmulet(BuffName.Salve_Amulet_E,1.2);
        buffMap.put(buff.getName(), buff);
        
        buff = new DragonbaneAmmo(BuffName.Dragonbane);
        buffMap.put(buff.getName(), buff);
        
        buff = new DragonbaneAmmo(BuffName.Dragonbane_Arrows,(PrimaryCombatStyle) CombatStyleFlyweight.getCombatStyle("Arrows"));
        buffMap.put(buff.getName(), buff);
        
        buff = new DragonbaneAmmo(BuffName.Dragonbane_Bolts,(PrimaryCombatStyle) CombatStyleFlyweight.getCombatStyle("Bolts"));
        buffMap.put(buff.getName(), buff);
        
        buff = new Chargebow();
        buffMap.put(buff.getName(), buff);
        
        buff = new DamageRecievedMultiplier(BuffName.Vulnerability,1.1);
        buffMap.put(buff.getName(), buff);
        
        buff = new DamageMultiplier(BuffName.Enfeeble,0.9);
        buffMap.put(buff.getName(), buff);
        
        buff = new FinalHitChanceAffect(BuffName.Stagger,-10);
        buffMap.put(buff.getName(), buff);
        
        buff = new DamageMultiplier(BuffName.Mahjarrat_Aura,1.05);
        buffMap.put(buff.getName(), buff);
        
        buff = new DesertAmulet3();
        buffMap.put(buff.getName(), buff);
        
        buff = new SpiritShieldAbsorbsion();
        buffMap.put(buff.getName(), buff);
        
        //these are just here to make monster weaknesses show. The buff effects do nothing
        buff = new ConstantFillInBuff(BuffName.Poison);
        buffMap.put(buff.getName(), buff);
        
        buff = new DamageRecievedMultiplier(BuffName.Reflect,0.5);
        buffMap.put(buff.getName(), buff);
        
        buff = new ConstantFillInBuff(BuffName.Stun);
        buffMap.put(buff.getName(), buff);
        
        buff = new ConstantFillInBuff(BuffName.Drain);
        buffMap.put(buff.getName(), buff);
        
        
        
    }






}
