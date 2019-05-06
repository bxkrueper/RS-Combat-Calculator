package combatStyle;

class Bolts extends Ranged{
    
    private static Bolts instance;
    
    Bolts(){
        
    }
    
    public static Bolts getInstance(){
        if(instance==null){
            instance = new Bolts();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Bolts";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
