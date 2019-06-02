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
            Hit autoHit = new AutoAttackHit(combatStyle,1,maxHit);
            autoHit.setHitCapNoCrit(Integer.MAX_VALUE);//monster hits do not have a hit cap
            autoHit.setHitCapWithCrit(Integer.MAX_VALUE);
            hitList.add(autoHit);
        }else {
            Hit constantHit = new ConstantHit(combatStyle,maxHit);
            constantHit.setHitCapNoCrit(Integer.MAX_VALUE);
            constantHit.setHitCapWithCrit(Integer.MAX_VALUE);
            hitList.add(constantHit);
        }
        return hitList;
    }
    
    
    
}
