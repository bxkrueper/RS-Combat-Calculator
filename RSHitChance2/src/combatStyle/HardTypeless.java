package combatStyle;

import java.awt.Color;

public class HardTypeless implements OffensiveCombatStyle,AlwaysHits{
    
    private static HardTypeless instance;
    private Color color;
    
    private HardTypeless(){
        this.color = Color.BLACK; 
    }
    
    public static HardTypeless getInstance(){
        if(instance==null){
            instance = new HardTypeless();
        }
        
        return instance;
    }

    @Override
    public String getName() {
        return "Hard_Typeless";
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public OffensiveCombatStyle getGeneralOffensiveStyle() {
        return HardTypeless.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }

}
