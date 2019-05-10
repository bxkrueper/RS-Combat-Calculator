package combatStyle;
/*
 * maps strings to the combat style instances. Each combat style is a singleton,
 * so you can access them from their class's get instance method or here
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombatStyleFlyweight {
    
    private static Map<String,CombatStyle> cbsMap;
    
    public static CombatStyle getCombatStyle(String str){
        if(cbsMap==null){
            makeMap();
        }
        
        CombatStyle cbs = cbsMap.get(str);
        if(cbs==null){
            System.out.println("unrecognized combat style: " + str + " " + " Using None instead");
        }
        return cbs;
    }
    
    private static void makeMap() {
        cbsMap = new HashMap<>();
        
        CombatStyle cbs;
        
        cbs = Melee.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Ranged.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Magic.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Typeless.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Dragonfire.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = CantAttack.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = None.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = All.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Hybrid.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Stab.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Slash.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Crush.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Arrows.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Bolts.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Thrown.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Air.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Water.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Earth.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = Fire.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = AlwaysHitMelee.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = AlwaysHitRanged.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
        cbs = AlwaysHitMagic.getInstance();
        cbsMap.put(cbs.getName(),cbs);
        
    }
}
