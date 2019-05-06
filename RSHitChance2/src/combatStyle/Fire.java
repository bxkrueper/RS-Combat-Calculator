package combatStyle;

class Fire extends Magic{
    
    private static Fire instance;
    
    Fire(){
        
    }
    
    public static Fire getInstance(){
        if(instance==null){
            instance = new Fire();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Fire";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
