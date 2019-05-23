package abilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import equipment.Equipment;
import equipment.EquipmentFileReader;

public class AbilityFlyweight {
	
	private static final String pathToAbilities = "data/abilities/";
	
	private static Map<String,Ability> abilityMap;
	private static Map<AbilityCategory,List<Ability>> abilityCategoryMap;
	private static List<Ability> abilityList;
    
    public static Ability getAbility(String str){
        if(abilityMap==null){
            makeMaps();
        }
        
        Ability ability = abilityMap.get(str);
        if(ability==null){
            System.out.println("unrecognized ability: " + str);
        }
        return ability;
    }
    
    public static List<Ability> getAbilityCategoryList(AbilityCategory ac){
    	if(abilityMap==null){
            makeMaps();
        }
    	return abilityCategoryMap.get(ac);
    }
    
    public static List<Ability> getAllAbilitiesList(){
    	if(abilityMap==null){
            makeMaps();
        }
    	return abilityList;
    }
    
    private static void makeMaps() {
    	abilityMap = new HashMap<>();
    	abilityCategoryMap= new HashMap<>();
    	abilityCategoryMap.put(AbilityCategory.ATTACK, new ArrayList<Ability>());
    	abilityCategoryMap.put(AbilityCategory.CONSTITUTION, new ArrayList<Ability>());
    	abilityCategoryMap.put(AbilityCategory.DEFENSE, new ArrayList<Ability>());
    	abilityCategoryMap.put(AbilityCategory.MAGIC, new ArrayList<Ability>());
    	abilityCategoryMap.put(AbilityCategory.RANGED, new ArrayList<Ability>());
    	abilityCategoryMap.put(AbilityCategory.STRENGTH, new ArrayList<Ability>());
    	abilityList = new ArrayList<>();
        
        
        readAbilitiesFromFile(pathToAbilities+"Abilities.csv");
    }

	private static void readAbilitiesFromFile(String fileName) {
		AbilityFileReader ar;
        try {
            ar = new AbilityFileReader(fileName);
            for(Object o:ar){
            	Ability ability = (Ability) o;
                
                addAbility(ability);
            }
        } catch (FileNotFoundException e1) {
            System.out.println("file not found: " + fileName);
        }
	}

	private static void addAbility(Ability ability) {
		abilityMap.put(ability.getName(),ability);
		
		abilityCategoryMap.get(ability.getCategory()).add(ability);
		
		abilityList.add(ability);
	}
}
