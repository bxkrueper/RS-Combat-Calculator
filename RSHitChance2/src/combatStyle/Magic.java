package combatStyle;

import java.awt.Color;

public class Magic implements PrimaryCombatStyle{
    
    private static Magic instance;
    private static final Color color = new Color(0,0,255);
    
    Magic(){
        
    }
    
    public static Magic getInstance(){
        if(instance==null){
            instance = new Magic();
        }
        return instance;
    }
    
    
    @Override
    public String getName() {
        return "Magic";
    }
    
    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public PrimaryCombatStyle getWeakAgainst() {
        return Ranged.getInstance();
    }

    @Override
    public PrimaryCombatStyle getStrongAgainst() {
        return Melee.getInstance();
    }
    
    @Override
    public final OffensiveCombatStyle getGeneralOffensiveStyle() {
        return Magic.getInstance();
    }
    
    @Override
    public PrimaryCombatStyle getGeneralPrimaryStyle() {
        return Magic.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }

    
}