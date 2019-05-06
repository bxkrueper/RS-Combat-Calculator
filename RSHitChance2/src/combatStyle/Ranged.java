package combatStyle;

import java.awt.Color;

public class Ranged implements PrimaryCombatStyle{
    
    private static Ranged instance;
    private static final Color color = new Color(0,255,0);
    
    Ranged(){
        
    }
    
    public static Ranged getInstance(){
        if(instance==null){
            instance = new Ranged();
        }
        return instance;
    }
    
    
    @Override
    public String getName() {
        return "Ranged";
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public PrimaryCombatStyle getWeakAgainst() {
        return Melee.getInstance();
    }

    @Override
    public PrimaryCombatStyle getStrongAgainst() {
        return Magic.getInstance();
    }
    
    @Override
    public final OffensiveCombatStyle getGeneralOffensiveStyle() {
        return Ranged.getInstance();
    }
    
    @Override
    public PrimaryCombatStyle getGeneralPrimaryStyle() {
        return Ranged.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
