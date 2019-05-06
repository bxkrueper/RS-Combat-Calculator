package combatent;

/*
 * describes a monster's attack
 */
import combatStyle.OffensiveCombatStyle;

public class Attack {
    
    private OffensiveCombatStyle combatStyle;
    private int maxHit;
    private String name;//can be null (if an attack is just a normal auto attack)
    ////add a buff to each attack?
    
    public Attack(OffensiveCombatStyle combatStyle, int maxHit) {
        this(combatStyle,maxHit,null);
    }
    public Attack(OffensiveCombatStyle combatStyle, int maxHit,String name) {
        this.combatStyle = combatStyle;
        this.maxHit = maxHit;
        this.name = name;
    }
    
    public OffensiveCombatStyle getCombatStyle() {
        return combatStyle;
    }
    public int getMaxHit() {
        return maxHit;
    }
    //can be null
    public String getName(){
        return name;
    }
    
}
