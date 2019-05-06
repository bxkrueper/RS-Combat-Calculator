package combatStyle;

class Crush extends Melee{
    
    private static Crush instance;
    
    Crush(){
        
    }
    
    public static Crush getInstance(){
        if(instance==null){
            instance = new Crush();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Crush";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
