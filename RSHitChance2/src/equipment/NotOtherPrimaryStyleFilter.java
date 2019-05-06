package equipment;

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
            return cbs.getGeneralOffensiveStyle()==peCbs.getGeneralOffensiveStyle();
        }else{
            return true;
        }
    }

}
