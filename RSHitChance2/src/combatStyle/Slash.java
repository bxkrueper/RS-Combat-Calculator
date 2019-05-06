package combatStyle;

class Slash extends Melee{
    
    private static Slash instance;
    
    Slash(){
        
    }
    
    public static Slash getInstance(){
        if(instance==null){
            instance = new Slash();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Slash";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
