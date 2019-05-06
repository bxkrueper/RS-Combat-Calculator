package combatStyle;

class AlwaysHitMagic extends Magic implements AlwaysHits{
    private static AlwaysHitMagic instance;
    
    AlwaysHitMagic(){
        
    }
    
    public static AlwaysHitMagic getInstance(){
        if(instance==null){
            instance = new AlwaysHitMagic();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "AlwaysHitMagic";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}