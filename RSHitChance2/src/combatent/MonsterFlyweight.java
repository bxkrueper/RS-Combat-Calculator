package combatent;
/*
 * data base for all monsters
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import resources.ObjectFileReader;

public class MonsterFlyweight {
    
    private static final String pathToMonsters = "data/monsters/";
    
    private static Map<String,Monster> monsterMap;
    private static List<Monster> monsterList;
    
    private static void makeMaps() {
        monsterMap = new HashMap<>();
        monsterList = new ArrayList<>();
        
        ObjectFileReader obr;
        try {
            obr = new MonsterFileReader(pathToMonsters+"Monsters.csv");
            for(Object o:obr){
                Monster m = (Monster) o;
                
                
                if(m instanceof MonsterGroup){
                    //add all names of monster to map
                    MonsterGroup mg = (MonsterGroup) m;
                    monsterMap.put(mg.getGroupName(), m);
                    for(int i=0;i<mg.getNumberOfMonsters();i++){
                        mg.setSelected(i);
                        monsterMap.put(mg.getName(), m);
                    }
                    mg.setSelected(0);
                }else{
                    monsterMap.put(m.getName(), m);
                }
                
                monsterList.add(m);
            }
        } catch (FileNotFoundException e1) {
            System.out.println("Monster file not found");
        }
        
        addNullMonster();
    }
    
    
    
    private static void addNullMonster() {
        Monster m = new NullMonster();
        monsterMap.put(m.getName(), m);
        monsterList.add(m);
    }



    public static Monster getMonster(String name){
        if(monsterMap==null){
            makeMaps();
        }
        return monsterMap.get(name);//////////////what if name is Araxxor (melee) and Araxxor group is on another selected version? shouldn't happen with current ui
    }
    
    public static List<Monster> getListOfAllMonsters(){
        if(monsterList==null){
            makeMaps();
        }
        return monsterList;
    }

}
