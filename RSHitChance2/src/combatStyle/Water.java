package combatStyle;

class Water extends Magic{
    
    private static Water instance;
    
    Water(){
        
    }
    
    public static Water getInstance(){
        if(instance==null){
            instance = new Water();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "Water";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
