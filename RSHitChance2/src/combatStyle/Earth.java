package combatStyle;

class Earth extends Magic{
    
    private static Earth instance;
    
    Earth(){
        
    }
    
    public static Earth getInstance(){
        if(instance==null){
            instance = new Earth();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Earth";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
