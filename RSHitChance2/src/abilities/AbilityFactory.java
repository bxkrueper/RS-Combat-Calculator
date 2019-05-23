package abilities;

import abilitiesSpecific.BleedAbility;
import abilitiesSpecific.BloodTendrils;
import abilitiesSpecific.ChanneledAbility;
import abilitiesSpecific.Corruption;
import abilitiesSpecific.DoubleHitAbility;
import abilitiesSpecific.MassacreClass;
import abilitiesSpecific.Onslaught;
import abilitiesSpecific.SimpleAbility;
import buff.Buff;
import buff.BuffFlyweight;
import buff.BuffName;
import buff.Buffs;
import buff.NullBuff;

/*
 * makes an ability given a row of Abilities.csv
 */
public class AbilityFactory {
	
	
	
	public static Ability makeAbility(String[] row) {
		String name = row[0];
		AbilityCategory category = AbilityCategory.valueOf(row[1]);
		int level = Integer.parseInt(row[2]);
		AbilityType type = AbilityType.valueOf(row[3]);
		AbilityRequirement requirement = AbilityRequirementFlyweight.getRequirement(row[4]);
		int cooldown = Integer.parseInt(row[5]);
		String[] mechanicParameters = row[6].split(" ");
		Buff buff = getBuff(row[7].split(" "));
		
		
		String mechanicName = mechanicParameters[0];
		switch(mechanicName) {
//		case "BoostIfStunnedBound":
//			return BoostIfStunnedBoundAbility.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);//the first entry of mechanic parameters is just the mechanic name, which will be ignored by the classes method
		case "Simple":
			return SimpleAbility.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "Bleed":
			return BleedAbility.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "Corruption":
            return Corruption.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "BloodTendrils":
            return BloodTendrils.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "Channeled":
			return ChanneledAbility.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "DoubleHit":
			return DoubleHitAbility.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "MassacreClass":
			return MassacreClass.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
//		case "DragonBreath":
//            return DragonBreath.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
//		case "Dismember":
//            return Dismember.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		case "Onslaught":
            return Onslaught.makeAbility(name, category, level, type, requirement, cooldown, buff, mechanicParameters);
		default:
			System.out.println("AbilityFactory: unrecognized ability mechanic name! " + mechanicName);
			return null;
		}
		
	}

    private static Buff getBuff(String[] split) {
        if(split[0].contentEquals("None")) {
            return NullBuff.getInstance();
        }else {
            return BuffFlyweight.getBuff(BuffName.valueOf(split[0]));
        }
        //what if you try to delete a buffs by name?
//        else {
//            Buffs buffs = new Buffs();
//            for(int i=0;i<split.length;i++) {
//                buffs.addBuff(BuffFlyweight.getBuff(split[i]));
//            }
//            return buffs;
//        }
    }
}
