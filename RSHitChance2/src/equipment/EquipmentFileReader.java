package equipment;

import java.io.FileNotFoundException;

import resources.ObjectFileReader;

public class EquipmentFileReader extends ObjectFileReader{
    
    private static EquipmentConverter converter;//this can't be set in the constructor, so it must be set before using
    
    private boolean passedFirstLine;
    
    public EquipmentFileReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }
    
    public static void setConverter(EquipmentConverter equipmentConverter){
        converter = equipmentConverter;
    }

    @Override
    protected Object nextObject() {
        //first line is just labels to make the csv file more readable
        if(!passedFirstLine){
            nextLine();
            passedFirstLine = true;
        }
        
        
        String rowString;
        rowString = nextLine();
        if(rowString==null || rowString.equals("")){
            return null;//end of file reached
        }
        
        return converter.getEquipment(rowString.split(","));
        
    }
}
