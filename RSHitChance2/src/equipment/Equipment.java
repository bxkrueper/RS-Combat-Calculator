package equipment;
/*
 * groups up data that all equipment has
 */
import buff.Buff;
import combatStyle.CombatStyle;
import combatStyle.CombatStyleFlyweight;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;
import javafx.scene.image.Image;
import resources.ImageFlyweight;
import resources.Imageable;

public abstract class Equipment implements EquipmentInterface{
    private static final String PATH_TO_PICTURES = "images/equipment/";
    public static final int IMAGE_WIDTH = 32;
    public static final int IMAGE_HEIGHT = 32;
    private static int nextIDToAssign = 0;
    
    private String name;
    private String imageName;
    private Image image;
    private Slot slot;
    private CombatStyle combatStyle;
    private int level;
    private double damage;
    private double armor;
    
    
    private Buff buff;
    private int sortingId;//this is the order the items were in in Equipment.csv
    public Equipment(String name, String imageName, Slot slot, CombatStyle combatStyle, int level,double damage, double armor,Buff buff) {
        this.name = name;
        this.imageName = imageName;
        this.slot = slot;
        this.combatStyle = combatStyle;
        this.level = level;
        
        this.damage = damage;
        this.armor = armor;
        this.buff = buff;
        this.sortingId = getNextIDToAssign();
    }
    private static int getNextIDToAssign(){
        int id = nextIDToAssign;
        nextIDToAssign++;
        return id;
    }
    public String getName() {
        return name;
    }
    @Override
    public Image getImage() {
        if(image==null){
            image = ImageFlyweight.getImage(PATH_TO_PICTURES+imageName,IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
        }
        return image;
    }
    public Slot getSlot() {
        return slot;
    }
    public CombatStyle getCombatStyle() {
        return combatStyle;
    }
    public int getLevel() {
        return level;
    }
    public double getDamage() {
        return damage;
    }
    public double getArmor() {
        return armor;
    }
    
    
    public Buff getBuff() {
        return buff;
    }
    public int getSortingId() {
        return sortingId;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    
    
}
