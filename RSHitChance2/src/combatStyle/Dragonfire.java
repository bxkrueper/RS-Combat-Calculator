package combatStyle;

import java.awt.Color;

public class Dragonfire implements OffensiveCombatStyle,AlwaysHits{
    
    private static Dragonfire instance;
    private Color color;
    
    private Dragonfire(){
        this.color = Color.ORANGE; 
    }
    
    public static Dragonfire getInstance(){
        if(instance==null){
            instance = new Dragonfire();
        }
        
        return instance;
    }

    @Override
    public String getName() {
        return "Dragonfire";
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public OffensiveCombatStyle getGeneralOffensiveStyle() {
        return Dragonfire.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }

}