//package abilitiesSpecific;
//
//import abilities.Ability;
//import abilities.AbilityCategory;
//import abilities.AbilityRequirement;
//import abilities.AbilityType;
//import abilities.BaseAbility;
//import abilities.PercentageRange;
//import abilities.PercentageRangeFlyweight;
//import buff.Buff;
//import buff.BuffName;
//import combatStyle.OffensiveCombatStyle;
//import combatent.Combatent;
//import main.AbilityHit;
//import main.BleedHitSlantedAverage;
//import main.DamageMode;
//import main.HitList;
//
//public class DragonBreath extends BaseAbility{
//
//    private PercentageRange range;
//    
//    public DragonBreath(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff) {
//        super(name, category, level, type, requirement, cooldown,buff);
//        this.range = PercentageRangeFlyweight.getPercentageRange(37.6, 188);
//    }
//
//    public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
//        return new DragonBreath(name,category,level,type,requirement,cooldown,buff);
//    }
//
//    @Override
//    public void fillHitListWithBaseDamage(HitList hitlist, int baseDamage, DamageMode mode,OffensiveCombatStyle combatStyle,Combatent owner,Combatent opponent) {
//        boolean dragonRiderAmulet = owner.getBuffs().contains(BuffName.Dragon_Rider_Amulet);
//        if(dragonRiderAmulet) {
//            baseDamage*=1.1;
//        }
//        hitlist.addHit(new AbilityHit(combatStyle,(int) (baseDamage*range.getMultiplier(mode))));
//        
//        if(mode==DamageMode.MAX) {
//            hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMax()*.1)));////////does burn effect use new or old value?
//            hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMax()*.1)));////////does burn effect use new or old value?
//            hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMax()*.1)));////////does burn effect use new or old value?
//        }
//        if(mode==DamageMode.AVE) {
//            hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMax()*.01)));////////does burn effect use new or old value?
//            hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMax()*.01)));////////does burn effect use new or old value?
//            hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMax()*.01)));////////does burn effect use new or old value?
//        }
//    }
//
//    @Override
//    public int getNumberOfHits(DamageMode damageMode) {
//        if(damageMode == DamageMode.MIN) {
//            return 1;
//        }else {
//            return 4;
//        }
//    }
//
//    @Override
//    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
//        if(hitNumber == 0) {
//            return range.getMultiplier(damageMode);
//        }else {
//            if(damageMode==DamageMode.MAX) {
//                return range.getMultiplier(damageMode)*.1;////////does burn effect use new or old value? from dragon rider ammulet?
//            }else if(damageMode==DamageMode.AVE) {
//                return range.getMultiplier(damageMode)*.01;////////does burn effect use new or old value?
//            }else {
//                return 0;
//            }
//            
//        }
//    }
//
//}