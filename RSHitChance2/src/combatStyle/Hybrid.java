package combatStyle;

public class Hybrid implements DefensiveCombatStyle{
    
    private static Hybrid instance;
    
    private Hybrid(){
        
    }
    
    public static Hybrid getInstance(){
        if(instance==null){
            instance = new Hybrid();
        }
        return instance;
    }
    
    
    @Override
    public String getName() {
        return "Hybrid";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}