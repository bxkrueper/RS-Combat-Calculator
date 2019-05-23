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
//import main.BleedHitSlantedAverage;
//import main.DamageMode;
//import main.HitList;
//
//public class Dismember extends BaseAbility{
//
//    private static final int baseNumHits = 5;
//            
//    private PercentageRange range;
//    private int numberOfHits;
//    private double chanceOfHittingMin;
//    private double averageMultiplier;
//    
//    public Dismember(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff) {
//        super(name, category, level, type, requirement, cooldown, buff);
//        this.range = PercentageRangeFlyweight.getPercentageRange(100, 188);
//        this.numberOfHits = 5;/////
//        this.chanceOfHittingMin = range.getMin()/range.getMax();
//        this.averageMultiplier = getAverageMultiplier(range,chanceOfHittingMin);
//    }
//
//    public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
//        return new Dismember(name,category,level,type,requirement,cooldown,buff);
//    }
//    
//    @Override
//    public void fillHitListWithBaseDamage(HitList hitlist, int baseDamage, DamageMode mode,OffensiveCombatStyle combatStyle,Combatent owner,Combatent opponent) {
//        int numHits = baseNumHits;
//        if(owner.getBuffs().contains(BuffName.Strength_Cape_Perk)) {
//            numHits+=3;
//        }
//        
//        if(mode==DamageMode.AVE) {//bleeds have a abnormally high chance of hitting their min hit, do don't use range.getMultiplier(AVE)
//            for(int i=0;i<numHits;i++) {
//                hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*averageMultiplier/baseNumHits)));
//            }
//        }else {
//            for(int i=0;i<numHits;i++) {
//                hitlist.addHit(new BleedHitSlantedAverage(combatStyle,(int) (baseDamage*range.getMultiplier(mode)/baseNumHits)));
//            }
//        }
//        
//    }
//    
//    private static double getAverageMultiplier(PercentageRange range, double minChance) {
//        double nonMinChance = 1-minChance;
//        return range.getMin()*minChance+range.getAverage()*nonMinChance;
//    }
//
//    @Override
//    public int getNumberOfHits(DamageMode damageMode) {
//        return 5;/////could be 8 if player has strength cape perk, but this is not affected by precise or equilibrium anyway
//    }
//
//    @Override
//    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
//        if(damageMode==DamageMode.AVE) {//bleeds have a abnormally high chance of hitting their min hit, do don't use range.getMultiplier(AVE)
//            return averageMultiplier/baseNumHits;
//        }else {
//            return range.getMultiplier(damageMode)/baseNumHits;
//        }
//    }
//
//}