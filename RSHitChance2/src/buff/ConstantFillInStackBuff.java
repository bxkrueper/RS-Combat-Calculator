package buff;

public abstract class ConstantFillInStackBuff extends ConstantFillInBuff implements StackBuff{
    
    private int stackValue;
    
    public ConstantFillInStackBuff(BuffName name) {
        super(name);
        this.stackValue = 0;
    }
    
    @Override
    public int getStackValue(){
        return stackValue;
    }

    @Override
    public void setStackValue(int newValue) {
        this.stackValue = newValue;
    }
    
    @Override
    public String toString() {
        return getName() + " Stack: " + getStackValue();
    }
    
    @Override
    public String getToolTipString() {
        return getNiceName() + ": Stack: " + stackValue + " " + getDescription();
    }

}
