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
    
    private static Map<String,MonsterInterface> monsterMap;
    private static List<MonsterInterface> monsterList;
    
    private static void makeMaps() {
        monsterMap = new HashMap<>();
        monsterList = new ArrayList<>();
        
        ObjectFileReader obr;
        try {
            obr = new MonsterFileReader(pathToMonsters+"Monsters.csv");
            for(Object o:obr){
                MonsterInterface m = (MonsterInterface) o;
                
                
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
        MonsterInterface m = new NullMonster();
        monsterMap.put(m.getName(), m);
        monsterList.add(m);
    }



    public static MonsterInterface getMonster(String name){
        if(monsterMap==null){
            makeMaps();
        }
        return monsterMap.get(name);//////////////what if name is Araxxor (melee) and Araxxor group is on another selected version? shouldn't happen with current ui
    }
    
    public static List<MonsterInterface> getListOfAllMonsters(){
        if(monsterList==null){
            makeMaps();
        }
        return monsterList;
    }

}
