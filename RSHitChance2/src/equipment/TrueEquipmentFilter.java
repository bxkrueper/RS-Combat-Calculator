package equipment;

public class TrueEquipmentFilter implements EquipmentFilter{

    @Override
    public boolean passes(Equipment equipment) {
        return true;
    }

}
