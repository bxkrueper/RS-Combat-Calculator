package combatStyle;

class AlwaysHitRanged extends Ranged implements AlwaysHits{
    private static AlwaysHitRanged instance;
    
    AlwaysHitRanged(){
        
    }
    
    public static AlwaysHitRanged getInstance(){
        if(instance==null){
            instance = new AlwaysHitRanged();
        }
        return instance;
    }
    
    
    
    @Override
    public String getName() {
        return "AlwaysHitRanged";
    }
    
    @Override
    public String toString(){
        return getName();
    }
}