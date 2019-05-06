package combatStyle;

import java.awt.Color;

public class Typeless implements OffensiveCombatStyle,AlwaysHits{
    
    private static Typeless instance;
    private Color color;
    
    private Typeless(){
        this.color = Color.BLACK; 
    }
    
    public static Typeless getInstance(){
        if(instance==null){
            instance = new Typeless();
        }
        
        return instance;
    }

    @Override
    public String getName() {
        return "Typeless";
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public OffensiveCombatStyle getGeneralOffensiveStyle() {
        return Typeless.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }

}
