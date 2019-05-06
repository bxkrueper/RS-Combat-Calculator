package combatStyle;

class Air extends Magic{
    
    private static Air instance;
    
    Air(){
        
    }
    
    public static Air getInstance(){
        if(instance==null){
            instance = new Air();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Air";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
