package buff;
/*
 * composite form of BuffCondition. All conditions must pass to return true
 */
import java.util.ArrayList;
import java.util.List;

import combatent.Combatent;


public class BuffConditions implements BuffCondition{

    private List<BuffCondition> conditionList;
    
    public BuffConditions(List<BuffCondition> conditionList) {
        this.conditionList = conditionList;
    }
    public BuffConditions(BuffCondition ... conditions) {
        conditionList = new ArrayList<>();
        for(BuffCondition condition: conditions){
            conditionList.add(condition);
        }
    }

    @Override
    public boolean passes(Combatent owner, Combatent opponent) {
        for(int i=0;i<conditionList.size();i++){
            if(conditionList.get(i).passes(owner, opponent)){
                continue;
            }else{
                return false;
            }
        }
        
        return true;
    }

}
