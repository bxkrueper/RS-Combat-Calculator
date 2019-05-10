package equipment;
/*
 * each equipment csv file is formated differently.
 * these classes read a row from a equipment file and return a Equipment object from it
 */
public interface EquipmentConverter {
    EquipmentInterface getEquipment(String[] strArray);
}
