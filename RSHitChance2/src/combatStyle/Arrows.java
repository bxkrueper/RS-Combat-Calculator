package combatStyle;

class Arrows extends Ranged{
    
    private static Arrows instance;
    
    Arrows(){
        
    }
    
    public static Arrows getInstance(){
        if(instance==null){
            instance = new Arrows();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Arrows";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
