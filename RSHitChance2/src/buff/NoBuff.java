package buff;

//for buffs that are there just to deselect options like potions
class NoBuff extends ConstantFillInBuff implements DontAddToBuffs{
  
  public NoBuff(BuffName name) {
      super(name);
  }
  

}
