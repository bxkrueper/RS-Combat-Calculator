package combatStyle;

import java.awt.Color;

public class Melee implements PrimaryCombatStyle{
    
    private static Melee instance;
    private static final Color color = new Color(255,0,0);
    
    Melee(){
        
    }
    
    public static Melee getInstance(){
        if(instance==null){
            instance = new Melee();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Melee";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public PrimaryCombatStyle getWeakAgainst() {
        return Magic.getInstance();
    }

    @Override
    public PrimaryCombatStyle getStrongAgainst() {
        return Ranged.getInstance();
    }
    
    @Override
    public final OffensiveCombatStyle getGeneralOffensiveStyle() {
        return Melee.getInstance();
    }
    
    @Override
    public PrimaryCombatStyle getGeneralPrimaryStyle() {
        return Melee.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }

    

}
