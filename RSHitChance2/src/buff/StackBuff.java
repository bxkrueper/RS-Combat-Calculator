package buff;
/*
 * for buffs with a variable effect like curses and reaper necklace. Uses a reference so interface classes can easily modify the stack value
 */

public interface StackBuff extends Buff{
    StackValueReference getStackReference();
    int getStackValue();
    void setStackReference(StackValueReference stack);
    Buff makeNew();
}
