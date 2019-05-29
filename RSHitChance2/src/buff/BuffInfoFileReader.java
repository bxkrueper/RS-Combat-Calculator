package buff;

import java.io.FileNotFoundException;

import resources.ObjectFileReader;
//gives additional info for buffs in the form of a string array [BuffName,description]
public class BuffInfoFileReader extends ObjectFileReader{
    
    private boolean passedFirstLine;
    
    public BuffInfoFileReader(String fileName) throws FileNotFoundException {
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
        return splitString;
    }
        

}