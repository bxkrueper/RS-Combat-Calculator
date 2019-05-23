package combatStyle;

import java.awt.Color;

public class SoftTypeless implements OffensiveCombatStyle,AlwaysHits{
    
    private static SoftTypeless instance;
    private Color color;
    
    private SoftTypeless(){
        this.color = Color.BLACK; 
    }
    
    public static SoftTypeless getInstance(){
        if(instance==null){
            instance = new SoftTypeless();
        }
        
        return instance;
    }

    @Override
    public String getName() {
        return "Soft_Typeless";
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public OffensiveCombatStyle getGeneralOffensiveStyle() {
        return SoftTypeless.getInstance();
    }
    
    @Override
    public String toString(){
        return getName();
    }

}
