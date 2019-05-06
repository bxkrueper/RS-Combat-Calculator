package combatStyle;

class AlwaysHitMelee extends Melee implements AlwaysHits{
    private static AlwaysHitMelee instance;
    
    AlwaysHitMelee(){
        
    }
    
    public static AlwaysHitMelee getInstance(){
        if(instance==null){
            instance = new AlwaysHitMelee();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "AlwaysHitMelee";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
