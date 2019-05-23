package combatent;
/*
 * creates a monster out of one row of Monsters.csv
 */
import java.util.ArrayList;
import java.util.List;

import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.Buffs;
import buff.NullBuff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.None;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;

public class MonsterFactory {
    
    
    public static MonsterSingle readMonster(String[] splitString){
        
        String name = splitString[0];
        String pictureName = splitString[1].equals("")? name:splitString[1];//if there is nothing in this column, the picture name is the same as the name. File extensions are found by ResourceGetter
        String wikiLink = replaceSemiColons(splitString[2]);
        
        int attackLevel = Integer.parseInt(splitString[3]);
        int rangedLevel = Integer.parseInt(splitString[4]);
        int magicLevel = Integer.parseInt(splitString[5]);
        int meleeAccuracy = Integer.parseInt(splitString[6]);
        int rangedAccuracy = Integer.parseInt(splitString[7]);
        int magicAccuracy = Integer.parseInt(splitString[8]);
        Attacks attacks = getAttacksFromString(splitString[9]);
        int defenseLevel = Integer.parseInt(splitString[10]);
        int armour = Integer.parseInt(splitString[11]);
        int meleeAffinity = Integer.parseInt(splitString[12]);
        int rangedAffinity = Integer.parseInt(splitString[13]);
        int magicAffinity = Integer.parseInt(splitString[14]);
        boolean weakToPoison = Boolean.parseBoolean(splitString[15]);
        boolean weakToReflect = Boolean.parseBoolean(splitString[16]);
        boolean weakToStun = Boolean.parseBoolean(splitString[17]);
        boolean weakToDrain = Boolean.parseBoolean(splitString[18]);
        MonsterSpecialAffinity specialAffinity =  getAffinityWeaknessFromString(splitString[19]);
        Vulnerabilities vulnerabilities = getVulnsFromString(splitString[20]);
        Buff naturalBuffs = getNaturalBuffsFromString(splitString[21]);
        
        
        MonsterAffinityWeaknesses affinityWeaknesses = new MonsterAffinityWeaknesses(meleeAffinity,rangedAffinity,magicAffinity,specialAffinity);
        
        MonsterStats monsterBaseStats = new MonsterStats(attackLevel,meleeAccuracy,rangedLevel,rangedAccuracy,magicLevel,magicAccuracy,defenseLevel,armour);
        
        if(weakToDrain){
            vulnerabilities.addBuffToStart(BuffName.Drain);
        }
        if(weakToStun){
            vulnerabilities.addBuffToStart(BuffName.Stun);
        }
        if(weakToReflect){
            vulnerabilities.addBuffToStart(BuffName.Reflect);
        }
        if(weakToPoison){
            vulnerabilities.addBuffToStart(BuffName.Poison);
        }
        
        
        return new MonsterSingle(name,pictureName,wikiLink,monsterBaseStats,attacks,affinityWeaknesses,vulnerabilities,naturalBuffs);
    }
    
    
    //string may look like this for ambassador. attacks separated by ;    Ranged 2200;Melee 3300;Magic 10000;Typeless 9000 Frag_Nuke
    private static Attacks getAttacksFromString(String string) {
        String[] attackStrings = string.split(";");
        List<Attack> attackList = new ArrayList<>();
        for(String attackString:attackStrings){
            attackList.add(getAttackFromString(attackString));
        }
        return new Attacks(attackList);
    }


    //attackString format: CombatStyle maxHit name     separated by space      name is optional    ex: Magic 10000            Typeless 9000 Frag_Nuke
    private static MonsterAttack getAttackFromString(String attackString) {
        String[] strArray = attackString.split(" ");
        OffensiveCombatStyle cbs = (OffensiveCombatStyle) CombatStyleFlyweight.getCombatStyle(strArray[0]);
        int maxHit = Integer.parseInt(strArray[1]);
        boolean isAuto = (cbs instanceof PrimaryCombatStyle);
        if(strArray.length==2){
            return new MonsterAttack(cbs,maxHit,isAuto);
        }else{//name is included, so length = 3
            return new MonsterAttack(cbs,maxHit,isAuto,strArray[2]);
        }
    }


    private static String replaceSemiColons(String string) {
        return string.replace(';', ',');
    }

    /*returns a vulnerabilities, which contains names of buffs that the monster is weak to
    if there is more than one, they are separated by spaces
    format examples:
    None
    Balmung
    SlayerMask Hexhunter
    */
    private static Vulnerabilities getVulnsFromString(String str) {
        String[] vulnStrings = str.split(" ");
        Vulnerabilities vulns = new Vulnerabilities();
        if(vulnStrings[0].equals("None")){
            return vulns;
        }
        
        for(String vulnStr:vulnStrings){
            vulns.addBuff(BuffName.valueOf(vulnStr));
        }
        
        return vulns;
    }

    //format in file: either "None" or "<weakness> affinity value" ex: "Slash 90"
    private static MonsterSpecialAffinity getAffinityWeaknessFromString(String str){
        String[] weaknessStrings = str.split(" ");
        
        CombatStyle cs = (CombatStyle) CombatStyleFlyweight.getCombatStyle(weaknessStrings[0]);
        PrimaryCombatStyle pcs;
        if(cs==None.getInstance()){
            return null;
        }else{
            pcs = (PrimaryCombatStyle) cs;
        }
        
        int affinity;
        if(weaknessStrings.length==1){
            affinity = -1;//for debugging. shouldn't happen
        }else{
            affinity = Integer.parseInt(weaknessStrings[1]);
        }
        
        return new MonsterSpecialAffinity(pcs,affinity);
    }
    
    private static Buff getNaturalBuffsFromString(String string) {
        String[] buffStrings = string.split(" ");
        
        if(buffStrings[0].equals("None")){
            return NullBuff.getInstance();
        }
        
        Buffs buffs = new Buffs();
        for(String buffStr:buffStrings){
            buffs.addBuff(BuffFlyweight.getBuff(BuffName.valueOf(buffStr)));
        }
        
        if(buffs.getListSize()==1){
            return buffs.getBuff(0);
        }else{
            return buffs;
        }
        
    }
    
    
}

