package combatStyle;

public class All implements DefensiveCombatStyle{
    private static All instance;
    
    private All(){
        
    }
    
    public static All getInstance(){
        if(instance==null){
            instance = new All();
        }
        return instance;
    }
    
    
    @Override
    public String getName() {
        return "All";
    }
    
    @Override
    public String toString(){
        return getName();
    }
    
}
