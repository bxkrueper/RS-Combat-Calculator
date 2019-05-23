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
//import main.DamageMode;
//import main.HitList;
//////////stun boost disabled
//public class BoostIfStunnedBoundAbility extends BaseAbility{
//
//	private PercentageRange rangeIfNotStunned;
//	private PercentageRange rangeIfStunned;
//	
//	public BoostIfStunnedBoundAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff,PercentageRange rangeIfNotStunned,PercentageRange rangeIfStunned) {
//		super(name, category, level, type, requirement, cooldown,buff);
//		this.rangeIfNotStunned = rangeIfNotStunned;
//		this.rangeIfStunned = rangeIfStunned;
//	}
//
//	public static Ability makeAbility(String name, AbilityCategory category, int level, AbilityType type,AbilityRequirement requirement, int cooldown,Buff buff, String[] mechanicParameters) {
//		double minNoStun = Double.parseDouble(mechanicParameters[1]);
//		double maxNoStun = Double.parseDouble(mechanicParameters[2]);
//		double minStun = Double.parseDouble(mechanicParameters[3]);
//		double maxStun = Double.parseDouble(mechanicParameters[4]);
//		return new BoostIfStunnedBoundAbility(name,category,level,type,requirement,cooldown,buff,PercentageRangeFlyweight.getPercentageRange(minNoStun,maxNoStun),PercentageRangeFlyweight.getPercentageRange(minStun,maxStun));
//	}
//	
//	@Override
//    public void fillHitListWithBaseDamage(HitList hitlist, int baseDamage, DamageMode mode,OffensiveCombatStyle combatStyle,Combatent owner,Combatent opponent) {
//	    if(opponent.getBuffs().contains(BuffName.Stun)) {
//	        hitlist.addHit(new AbilityHit(combatStyle,(int) (baseDamage*rangeIfStunned.getMultiplier(mode))));
//	    }else {
//	        hitlist.addHit(new AbilityHit(combatStyle,(int) (baseDamage*rangeIfNotStunned.getMultiplier(mode))));
//	    }
//    }
//
//    @Override
//    public int getNumberOfHits(DamageMode damageMode) {
//        return 1;
//    }
//
//    @Override
//    public double getMultiplierToBaseHit(int hitNumber, DamageMode damageMode) {
//        return rangeIfNotStunned.getMultiplier(damageMode);//////what if stunned?
//    }
//}