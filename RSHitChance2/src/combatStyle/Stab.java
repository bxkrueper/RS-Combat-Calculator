package combatStyle;

class Stab extends Melee{
    
    private static Stab instance;
    
    Stab(){
        
    }
    
    public static Stab getInstance(){
        if(instance==null){
            instance = new Stab();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Stab";
    }
    
    
    @Override
    public String toString(){
        return getName();
    }
}
