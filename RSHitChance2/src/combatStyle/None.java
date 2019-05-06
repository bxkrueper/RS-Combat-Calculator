package combatStyle;

public class None implements DefensiveCombatStyle{
    private static None instance;
    
    private None(){
        
    }
    
    public static None getInstance(){
        if(instance==null){
            instance = new None();
        }
        return instance;
    }
    
    
    @Override
    public String getName() {
        return "None";
    }
    
    @Override
    public String toString(){
        return getName();
    }
    
}
