package main;

import java.util.ArrayList;
import java.util.List;

import abilities.Ability;
import buff.BuffFlyweight;
import buff.BuffName;
import buff.StackBuff;
import combatStyle.HardTypeless;
import combatent.Attack;
import combatent.Combatent;
import combatent.Player;
import equipment.Slot;
import equipment.WeaponInterface;

public class DamageCalculator {

	public static List<Hit> calculateDamage(Combatent attacker, Combatent defender) {
		if(!attacker.canAttack(defender)){
            System.out.println(attacker + " Can't attack!");
            return new ArrayList<Hit>();
        }
		
		Attack attack = attacker.getAttack();
		System.out.println("attack: " + attack.getName());
		System.out.println("attacker buffs: " + attacker.getBuffs());
        System.out.println("defender buffs: " + defender.getBuffs());
		List<Hit> hitList = attack.generateBaseDamageHitList();
		System.out.println("Base Hits: " + hitList.toString());
        attacker.getBuffs().affectOwnerBaseHitList(hitList, attacker, defender);
        System.out.println("Hits after owner buffs affect Base Hit list: " + hitList.toString());
        
		
        //apply effects to the values that can be affected
		double effectMultiplier = effectMultiplier(attacker, defender);
		double autoAttackMultiplier = attacker.getBuffs().getAutoAttackDamageMultiplier(attacker, defender);
		int extraPowerLevels = ((int) attacker.getBuffs().addPowerLevelsToOwner(attacker, null));////defender does not affect?
		for(int i=0;i<hitList.size();i++) {
		    hitList.get(i).applyEffectMultiplier(effectMultiplier);
            hitList.get(i).applyAutoAttackMultiplier(autoAttackMultiplier);
        }
		System.out.println("Hits after multiplication effects: " + hitList.toString());
		for(int i=0;i<hitList.size();i++) {
		    hitList.get(i).applyDamageFromExtraPowerLevels(extraPowerLevels);
        }
		System.out.println("Hits after damage from extra power levels: " + hitList.toString());
		for(int i=0;i<hitList.size();i++) {
            int precise = ((StackBuff) BuffFlyweight.getBuff(BuffName.Precise)).getStackValue();
            int equilibrium = ((StackBuff) BuffFlyweight.getBuff(BuffName.Equilibrium)).getStackValue();
            double minBeforePE = hitList.get(i).getMinDamage();//////////this is cast to int
            double maxBeforePE = hitList.get(i).getMaxDamage();//////////this is cast to int
            double minMultiplier = minDamageWithPreciseEquilibriumMultiplier(precise,equilibrium,minBeforePE,maxBeforePE);
            double maxMultiplier = maxDamageWithPreciseEquilibriumMultiplier(precise,equilibrium,minBeforePE,maxBeforePE);
            hitList.get(i).multiplyMinHit(minMultiplier);
            hitList.get(i).multiplyMaxHit(maxMultiplier);
        }
		System.out.println("Hits after PreciseEquilibriumMultipliers: " + hitList.toString());
		defender.getBuffs().affectOpponentFinalHitList(hitList, defender, attacker);
        System.out.println("Hits after opponent buffs affect final Hit list: " + hitList.toString());
		return hitList;
	}
    

	

	

    private static double effectMultiplier(Combatent attacker, Combatent defender) {
		double multiplier = 1;
		//Apply defender natural absorbsion
		multiplier*=defender.getNaturalAbsorbsion();
        System.out.println("defender natural absorbsion: " + defender.getNaturalAbsorbsion());
        System.out.println("multiplier after defender natural absorbsion: " + multiplier);
        
        //apply multiplicative boosts
        System.out.println("damage mult from owner: " + attacker.getBuffs().getDamageMultiplier(attacker, defender));
        System.out.println("damage mult from opponent: " + defender.getBuffs().getDamageRecievedMultiplier(defender,attacker));
        multiplier*=attacker.getBuffs().getDamageMultiplier(attacker, defender);
        multiplier*=defender.getBuffs().getDamageRecievedMultiplier(defender,attacker);
        
        System.out.println("total mult: " + multiplier);
        
        return multiplier;
	}
	

	
	//bounus damage from extra levels. added at the end
	static int endDamageMultForExtraPowerLevels(DamageMode mode) {
		int multiplier;
		switch(mode) {
		case MAX:
			multiplier = 8;
			break;
		case MIN:
			multiplier = 4;
			break;
		case AVE:
			multiplier = 6;
			break;
		default:
			System.out.println("Damage Calculator: unrecognized mode! " + mode);
			multiplier = 0;
		}
		return multiplier;
	}

	
	private static double minDamageWithPreciseEquilibriumMultiplier(int pre, int equ, double baseMin,double baseMax) {
	    double minWithP = baseMin+0.015*pre*baseMax;
	    return (minWithP+(baseMax-minWithP)*0.03*equ)/baseMin;
    }
	
	private static double maxDamageWithPreciseEquilibriumMultiplier(int pre, int equ, double baseMin,double baseMax) {
	    double minWithP = baseMin+0.015*pre*baseMax;
	    return (baseMax-(baseMax-minWithP)*0.01*equ)/baseMax;
    }
	
	
	

    
}
