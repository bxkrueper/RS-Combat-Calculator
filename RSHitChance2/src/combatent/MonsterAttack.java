package combatent;




/*
 * describes a monster's attack
 */
import java.util.ArrayList;
import java.util.List;

import calculations.AutoAttackHit;
import calculations.ConstantHit;
import calculations.Hit;
import combatStyle.OffensiveCombatStyle;

public class MonsterAttack implements Attack{
    
    private OffensiveCombatStyle combatStyle;
    private int maxHit;
    private String name;//can be null (if an attack is just a normal auto attack)
    private boolean isAuto;
    
    public MonsterAttack(OffensiveCombatStyle combatStyle, int maxHit,boolean isAuto) {
        this(combatStyle,maxHit,isAuto,null);
    }
    public MonsterAttack(OffensiveCombatStyle combatStyle, int maxHit,boolean isAuto,String name) {
        this.combatStyle = combatStyle;
        this.maxHit = maxHit;
        this.name = name;
        this.isAuto = isAuto;
    }
    
    public OffensiveCombatStyle getCombatStyle() {
        return combatStyle;
    }
    //can be null
    public String getName(){
        return name;
    }
    
    public int getMaxBaseHit() {
        return maxHit;
    }
    
    @Override
    public List<Hit> generateBaseDamageHitList() {
        System.out.println("Monster base max hit: " + maxHit);
        List<Hit> hitList = new ArrayList<>(1);
        if(isAuto) {
            hitList.add(new AutoAttackHit(combatStyle,1,maxHit));
        }else {
            hitList.add(new ConstantHit(combatStyle,maxHit));
        }
        return hitList;
    }
    
    
    
}
