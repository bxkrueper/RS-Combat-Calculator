package equipment;
/*
 * example use: if cbs is melee, filters out ranged and magic but keeps others like hybrid
 */
import combatStyle.CombatStyle;
import combatStyle.PrimaryCombatStyle;

public class NotOtherPrimaryStyleFilter implements EquipmentFilter{
    
    private PrimaryCombatStyle cbs;
    
    public NotOtherPrimaryStyleFilter(PrimaryCombatStyle combatStyle){
        this.cbs = combatStyle;
    }

    @Override
    public boolean passes(Equipment equipment) {
        CombatStyle eCbs = equipment.getCombatStyle();
        if(eCbs instanceof PrimaryCombatStyle){
            PrimaryCombatStyle peCbs = (PrimaryCombatStyle) eCbs;
            return cbs.getGeneralPrimaryStyle()==peCbs.getGeneralPrimaryStyle();
        }else{
            return true;
        }
    }

}
