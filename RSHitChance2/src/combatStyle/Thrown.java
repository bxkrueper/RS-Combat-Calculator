package combatStyle;

class Thrown extends Ranged{
    
    private static Thrown instance;
    
    Thrown(){
        
    }
    
    public static Thrown getInstance(){
        if(instance==null){
            instance = new Thrown();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Thrown";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
