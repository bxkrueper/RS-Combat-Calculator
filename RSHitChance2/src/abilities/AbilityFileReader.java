package abilities;

import java.io.FileNotFoundException;

import resources.ObjectFileReader;


public class AbilityFileReader extends ObjectFileReader{
    
    private boolean passedFirstLine;
    
    public AbilityFileReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    protected Object nextObject() {
        //first line is just labels to make the csv file more readable. skip it
        if(!passedFirstLine){
            nextLine();
            passedFirstLine = true;
        }
        
        String rowString;
        rowString = nextLine();
        if(rowString==null || rowString.equals("")){
            return null;//end of file reached
        }
        
        String[] splitString = rowString.split(",");
        Ability ability = AbilityFactory.makeAbility(splitString);
        return ability;
    }
        

}
