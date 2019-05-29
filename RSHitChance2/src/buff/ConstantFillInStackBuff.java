package buff;

public abstract class ConstantFillInStackBuff extends ConstantFillInBuff implements StackBuff{
    
    private StackValueReference stack;//this is a reference to the stack value so that interface classes can use the reference to make changes easily

    public ConstantFillInStackBuff(BuffName name) {
        super(name);
        this.stack = new StackValueReference(0);
    }

    @Override
    public StackValueReference getStackReference() {
        return stack;
    }
    
    @Override
    public int getStackValue(){
        return stack.getValue();
    }

    @Override
    public void setStackReference(StackValueReference stack) {
        this.stack = stack;
    }

    
    
    @Override
    public String toString() {
        return getName() + " Stack: " + stack.getValue();
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + ". Stack value: " + getStackValue();
    }

}
