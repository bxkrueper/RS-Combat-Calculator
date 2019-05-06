package buff;

/*
 * allows the stack value to be edited and passed by reference
 */
public class StackValueReference {
    
    private int value;
    
    public StackValueReference(int value){
        this.value = value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
