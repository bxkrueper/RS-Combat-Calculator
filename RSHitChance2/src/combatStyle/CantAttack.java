package combatStyle;

import java.awt.Color;

//for use when you have a bow or staff with no correct ammo
public class CantAttack implements OffensiveCombatStyle{
    
    private static CantAttack instance;
    private Color color;
    
    private CantAttack(){
        this.color = Color.PINK; 
    }
    
    public static CantAttack getInstance(){
        if(instance==null){
            instance = new CantAttack();
        }
        
        return instance;
    }

    @Override
    public String getName() {
        return "CantAttack";
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public String toString(){
        return getName();
    }

    @Override
    public OffensiveCombatStyle getGeneralOffensiveStyle() {
        return CantAttack.getInstance();
    }

}