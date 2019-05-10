package equipment;
/*
 * composite form of EquipmentFilter
 */
import java.util.LinkedList;
import java.util.List;

public class EquipmentFilters implements EquipmentFilter{

    private List<EquipmentFilter> filterList;
    
    public EquipmentFilters(){
        this.filterList = new LinkedList<>();
    }
    
    public void addFilter(EquipmentFilter filter){
        filterList.add(filter);
    }
    
    public void removeFilter(EquipmentFilter filter){
        filterList.remove(filter);
    }
    
    public void clear(){
        filterList.clear();
    }
    
    @Override
    public boolean passes(Equipment equipment) {
        for(EquipmentFilter filter: filterList){
            if(!(filter.passes(equipment))){
                return false;
            }
        }
        return true;
    }

}
