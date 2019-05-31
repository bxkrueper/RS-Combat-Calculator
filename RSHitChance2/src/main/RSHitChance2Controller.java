package main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import abilities.Ability;
import abilities.AbilityCategory;
import abilities.AbilityFlyweight;
import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.Buffs;
import buff.DontShowOnBuffBar;
import buff.NullBuff;
import buff.StackBuff;
import buffSpecific.BlackStoneArrows;
import buffSpecific.Reaper;
import calculations.DamageCalculator;
import calculations.Hit;
import calculations.HitchanceCalculator;
import combatStyle.CantAttack;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.None;
import combatStyle.Ranged;
import combatent.Combatent;
import combatent.MonsterSingle;
import combatent.MonsterFlyweight;
import combatent.MonsterGroup;
import combatent.Monster;
import combatent.MonsterAttack;
import combatent.Player;
import combatent.PlayerStats;
import combatent.MonsterSpecialAffinity;
import combatent.MonsterStats;
import combatent.NullMonster;
import equipment.NotOtherPrimaryStyleFilter;
import equipment.AmmoInterface;
import equipment.Equipment;
import equipment.EquipmentFilter;
import equipment.EquipmentFilters;
import equipment.EquipmentFlyweight;
import equipment.Slot;
import equipment.WeaponInterface;
import interfaceHelpers.AbilityStringToImage;
import interfaceHelpers.BuffStringToImage;
import interfaceHelpers.EquipmentStringToImage;
import interfaceHelpers.PictureListCell;
import interfaceHelpers.StringToImage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Slider;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.image.ImageView;
import resources.ImageFlyweight;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class RSHitChance2Controller implements Initializable{//put a space between name and {  (scene builder included {
    //drag fxml file into main package to hook up. these fxml fields get initialized automatically
    @FXML
    private ComboBox<String> helmetComboBox;
    @FXML
    private ComboBox<String> auraComboBox;
    @FXML
    private ComboBox<String> pocketComboBox;
    @FXML
    private ComboBox<String> sigilComboBox;
    @FXML
    private ComboBox<String> amuletComboBox;
    @FXML
    private ComboBox<String> quiverComboBox;
    @FXML
    private ComboBox<String> mainHandComboBox;
    @FXML
    private ComboBox<String> bodyComboBox;
    @FXML
    private ComboBox<String> offHandComboBox;
    @FXML
    private ComboBox<String> legsComboBox;
    @FXML
    private ComboBox<String> glovesComboBox;
    @FXML
    private ComboBox<String> bootsComboBox;
    @FXML
    private ComboBox<String> ringComboBox;
    @FXML
    private ComboBox<String> capeComboBox;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private ComboBox<String> potionComboBox;
    @FXML
    private ComboBox<String> statPrayerComboBox;
    @FXML
    private ComboBox<String> familiarComboBox;
    @FXML
    private Label prayerSliderLabel;
    @FXML
    private Slider prayerSlider;
    @FXML
    private Label playerTotalAccuracyValueLabel;
    @FXML
    private Label playerToatlDefenseValueLabel;
    @FXML
    private Label monsterSpecialAffinityValueLabel;
    @FXML
    private ImageView monsterSpecialAffinityImageView;
    @FXML
    private ImageView monsterImageView;
    @FXML
    private ComboBox<String> monsterChoiceComboBox;
    @FXML
    private ImageView playerCombatStyleImageView;
    @FXML
    private ImageView monsterCombatStyleImageView;
    @FXML
    private ImageView playerMeleeAffinityImageView;
    @FXML
    private ImageView playerRangedAffinityImageView;
    @FXML
    private ImageView playerMagicAffinityImageView;
    @FXML
    private Button wikiLinkButton;
    @FXML
    private Label playerMeleeAffinityValueLabel;
    @FXML
    private Label playerRangedAffinityValueLabel;
    @FXML
    private Label playerMagicAffinityValueLabel;
    @FXML
    private Label playerHitChanceValueLabel;
    @FXML
    private Label playerAbilityDamageValue;//total
    @FXML
    private Label playerMHDamageValueLabel;
    @FXML
    private Label playerOHDamageValueLabel;
    @FXML
    private Label monsterHitChanceValueLabel;
    @FXML
    private Label monsterDamageValue;
    @FXML
    private Label monsterTotalAccuracyValueLabel;
    @FXML
    private Label monsterTotalDefenseValueLabel;
    @FXML
    private Label monsterMeleeAffinityValueLabel;
    @FXML
    private Label monsterRangedAffinityValueLabel;
    @FXML
    private Label monsterMagicAffinityValueLabel;
    @FXML
    private ImageView monsterMeleeAffinityImageView;
    @FXML
    private ImageView monsterRangedAffinityImageView;
    @FXML
    private ImageView monsterMagicAffinityImageView;
    @FXML
    private CheckBox quakeCheckBox;
    @FXML
    private CheckBox guthixStaffSpecCheckBox;
    @FXML
    private CheckBox swhSpecCheckBox;
    @FXML
    private CheckBox vulnerabilityCheckBox;
    @FXML
    private CheckBox enfeebleCheckBox;
    @FXML
    private CheckBox staggerCheckBox;
    @FXML
    private Button monsterSubTypeLeftButton;
    @FXML
    private Button monsterSubTypeRightButton;
    @FXML
    private Button monsterAttackLeftButton;
    @FXML
    private Button monsterAttackRightButton;
    @FXML
    private Label monsterAttackNameLabel;
    @FXML
    private Label monsterAttackMaxHitLabel;
    @FXML
    private Slider blackstoneArrowSlider;
    @FXML
    private Slider reaperSlider;
    @FXML
    private Label blackstoneArrowStackValueLabel;
    @FXML
    private Label reaperSliderLabel;
    @FXML
    private Label reaperStackValueLabel;
    @FXML
    private TextField playerAttackLevelTextField;
    @FXML
    private TextField playerStrengthLevelTextField;
    @FXML
    private TextField playerDefenseLevelTextField;
    @FXML
    private TextField playerRangedLevelTextField;
    @FXML
    private TextField playerMagicLevelTextField;
    @FXML
    private HBox monsterWeaknessesHBox;
    @FXML
    private HBox playerBuffHBox;
    @FXML
    private HBox monsterBuffHBox;
    @FXML
    private ComboBox<String> AbilityComboBox;
    @FXML
    private ToggleGroup abilityDamageToggleGroup;
    @FXML
    private RadioButton maxAbilityRadioButton;
    @FXML
    private RadioButton aveAbilityRadioButton;
    @FXML
    private RadioButton minAbilityRadioButton;
    @FXML
    private VBox abilityDamageVBox;
    @FXML
    private ComboBox<String> preciseComboBox;
    @FXML
    private ComboBox<String> equilibriumComboBox;
    @FXML
    private ComboBox<String> bitingComboBox;
    
    
    private Player player;
    private Monster currentMonster;
    private Map<Slot,ComboBox<String>> equipmentComboBoxMap;
    private EquipmentFilters equipmentFilters;
    
    private boolean disableComboBoxTrigger;
    
    private Buff potionBuff;
    private Buff prayerBuff;
    private Buff familiarBuff;
    
    private EquipmentFilter meleeFilter;
    private EquipmentFilter rangedFilter;
    private EquipmentFilter magicFilter;
    
    
    
    //this optional method is called when the FXML file is loaded
    public void initialize(URL arg0, ResourceBundle arg1){
        System.out.println("initializing");
        makeCombatents();
        fillInterfaces();
        setSupportFields();
        
    }
    
    private void makeCombatents() {
        this.player = makePlayer();
        setMonster("Null Monster");
    }

    private void fillInterfaces() {
        fillInitialImageViews();
        makeEquipmentComboBoxMaps();
        fillEquipmentComboBoxes();
        fillBuffComboBoxes();
        fillFilterComboBox();
        fillAbilityComboBox();
        fillPreciseEquilibriumBitingComboBoxs();
        setUpMonsterSelectionComboBox();
        fillTextFields();
        setSliderMethods();
        setInitialSliderVisablility();
    }

    

	private void setSupportFields() {
        this.disableComboBoxTrigger = false;
        
        potionBuff = NullBuff.getInstance();
        prayerBuff = NullBuff.getInstance();
        familiarBuff = NullBuff.getInstance();
        
        
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Malevolence,BuffFlyweight.Owner.LEFT)).setStackValue((int) prayerSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Desolation,BuffFlyweight.Owner.LEFT)).setStackValue((int) prayerSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Affliction,BuffFlyweight.Owner.LEFT)).setStackValue((int) prayerSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Turmoil,BuffFlyweight.Owner.LEFT)).setStackValue((int) prayerSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Anguish,BuffFlyweight.Owner.LEFT)).setStackValue((int) prayerSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Torment,BuffFlyweight.Owner.LEFT)).setStackValue((int) prayerSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Reaper_Necklace,BuffFlyweight.Owner.LEFT)).setStackValue((int) reaperSlider.getValue());
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Precise,BuffFlyweight.Owner.LEFT)).setStackValue(Integer.parseInt(preciseComboBox.getValue()));
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Equilibrium,BuffFlyweight.Owner.LEFT)).setStackValue(Integer.parseInt(equilibriumComboBox.getValue()));
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Biting,BuffFlyweight.Owner.LEFT)).setStackValue(Integer.parseInt(bitingComboBox.getValue()));
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Black_Stone_Arrows,BuffFlyweight.Owner.RIGHT)).setStackValue((int) blackstoneArrowSlider.getValue());

    }

    private Player makePlayer() {
        int attackLevel = Integer.parseInt(playerAttackLevelTextField.getText());
        int rangedLevel = Integer.parseInt(playerRangedLevelTextField.getText());
        int magicLevel = Integer.parseInt(playerMagicLevelTextField.getText());
        int strengthLevel = Integer.parseInt(playerStrengthLevelTextField.getText());
        int defenseLevel = Integer.parseInt(playerDefenseLevelTextField.getText());
        Player player = new Player("World Guardian", new PlayerStats(attackLevel,rangedLevel,magicLevel,strengthLevel,defenseLevel));
        
        return player;
    }

    private void fillInitialImageViews() {
        playerMeleeAffinityImageView.setImage(Melee.getInstance().getImage());
        playerRangedAffinityImageView.setImage(Ranged.getInstance().getImage());
        playerMagicAffinityImageView.setImage(Magic.getInstance().getImage());
        monsterMeleeAffinityImageView.setImage(Melee.getInstance().getImage());
        monsterRangedAffinityImageView.setImage(Ranged.getInstance().getImage());
        monsterMagicAffinityImageView.setImage(Magic.getInstance().getImage());
        wikiLinkButton.setGraphic(new ImageView(ImageFlyweight.getImage("images/Wiki", 50, 50, true, true)));
        playerCombatStyleImageView.setImage(player.getCombatStyle().getImage());
    }

    private void makeEquipmentComboBoxMaps() {
        this.equipmentComboBoxMap = new HashMap<>();
        equipmentComboBoxMap.put(Slot.AMULET, amuletComboBox);
        equipmentComboBoxMap.put(Slot.AURA, auraComboBox);
        equipmentComboBoxMap.put(Slot.BODY, bodyComboBox);
        equipmentComboBoxMap.put(Slot.BOOTS, bootsComboBox);
        equipmentComboBoxMap.put(Slot.CAPE, capeComboBox);
        equipmentComboBoxMap.put(Slot.GLOVES, glovesComboBox);
        equipmentComboBoxMap.put(Slot.HELMET, helmetComboBox);
        equipmentComboBoxMap.put(Slot.LEGS, legsComboBox);
        equipmentComboBoxMap.put(Slot.MAIN_HAND, mainHandComboBox);
        equipmentComboBoxMap.put(Slot.OFF_HAND, offHandComboBox);
        equipmentComboBoxMap.put(Slot.POCKET, pocketComboBox);
        equipmentComboBoxMap.put(Slot.QUIVER, quiverComboBox);
        equipmentComboBoxMap.put(Slot.RING, ringComboBox);
        equipmentComboBoxMap.put(Slot.SIGIL, sigilComboBox);
        equipmentComboBoxMap.put(Slot.TWO_HAND, mainHandComboBox);
        
    }

    private void fillEquipmentComboBoxes() {
        List<Equipment> mainHandOneTwoList = new ArrayList<>();
        mainHandOneTwoList.addAll(EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.MAIN_HAND));
        mainHandOneTwoList.addAll(EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.TWO_HAND));
        Collections.sort(mainHandOneTwoList, (Equipment o1, Equipment o2) -> o1.getSortingId() - o2.getSortingId());
    
        StringToImage stringToImage = new EquipmentStringToImage();
        fillEquipmentComboBox(Slot.HELMET,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.HELMET),stringToImage);
        fillEquipmentComboBox(Slot.AURA,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.AURA),stringToImage);
        fillEquipmentComboBox(Slot.POCKET,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.POCKET),stringToImage);
        fillEquipmentComboBox(Slot.SIGIL,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.SIGIL),stringToImage);
        fillEquipmentComboBox(Slot.AMULET,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.AMULET),stringToImage);
        fillEquipmentComboBox(Slot.QUIVER,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.QUIVER),stringToImage);
        fillEquipmentComboBox(Slot.MAIN_HAND,mainHandOneTwoList,stringToImage);
        fillEquipmentComboBox(Slot.BODY,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.BODY),stringToImage);
        fillEquipmentComboBox(Slot.OFF_HAND,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.OFF_HAND),stringToImage);
        fillEquipmentComboBox(Slot.LEGS,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.LEGS),stringToImage);
        fillEquipmentComboBox(Slot.GLOVES,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.GLOVES),stringToImage);
        fillEquipmentComboBox(Slot.BOOTS,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.BOOTS),stringToImage);
        fillEquipmentComboBox(Slot.RING,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.RING),stringToImage);
        fillEquipmentComboBox(Slot.CAPE,EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.CAPE),stringToImage);
    }

    private void fillBuffComboBoxes() {
        List<Buff> potionBuffs = makeListOfPotions();
        List<Buff> prayerBuffs = makeListOfPrayers();
        List<Buff> familiarBuffs = makeListOffamiliars();
        
        BuffStringToImage buffStringToImage = new BuffStringToImage(Buff.IMAGE_WIDTH,Buff.IMAGE_HEIGHT);
        fillBuffComboBox(potionComboBox,potionBuffs,buffStringToImage);
        fillBuffComboBox(this.statPrayerComboBox,prayerBuffs,buffStringToImage);
        fillBuffComboBox(this.familiarComboBox,familiarBuffs,buffStringToImage);
    }

    private void fillEquipmentComboBox(Slot slot, List<Equipment> equipmentList,StringToImage stringToImage) {
        ComboBox<String> comboBox = equipmentComboBoxMap.get(slot);
        ObservableList<String> options = comboBox.getItems();
        for(Equipment equipment: equipmentList){
            options.add(equipment.getName());
        }
        comboBox.setCellFactory(c -> new PictureListCell(stringToImage));
        comboBox.setButtonCell(new PictureListCell(stringToImage));
        comboBox.setItems(options);
        comboBox.getSelectionModel().select(player.getWornEquipment().getEquipment(slot).getName());
    }

    private void fillFilterComboBox() {
        equipmentFilters = new EquipmentFilters();
        meleeFilter = new NotOtherPrimaryStyleFilter(Melee.getInstance());
        rangedFilter = new NotOtherPrimaryStyleFilter(Ranged.getInstance());
        magicFilter = new NotOtherPrimaryStyleFilter(Magic.getInstance());
        
        ObservableList<String> options = this.filterComboBox.getItems();
        options.add("None");
        options.add("Melee");
        options.add("Ranged");
        options.add("Magic");
        filterComboBox.getSelectionModel().select("None");
    }

    private void fillAbilityComboBox() {
        ObservableList<String> options = AbilityComboBox.getItems();
        for(Ability ability: AbilityFlyweight.getAllAbilitiesList()){
        	if(ability.canBeUsedByPlayer(player)) {
        		options.add(ability.getName());
        	}
        }
        AbilityStringToImage abilityStringToImage = new AbilityStringToImage();
        AbilityComboBox.setCellFactory(c -> new PictureListCell(abilityStringToImage));
        AbilityComboBox.setButtonCell(new PictureListCell(abilityStringToImage));
        AbilityComboBox.setItems(options);
        AbilityComboBox.getSelectionModel().select(player.getAbility().getName());//sacrfice is the default, as it requires no weapon and has a max of 100% ability damage
    }

    private void fillPreciseEquilibriumBitingComboBoxs() {
        //precise
	    ObservableList<String> pOptions = preciseComboBox.getItems();
        for(int i=0;i<=5;i++){
            pOptions.add(Integer.toString(i));
        }
        preciseComboBox.setItems(pOptions);
        preciseComboBox.getSelectionModel().select("0");
        
        //equilibrium
        ObservableList<String> eOptions = equilibriumComboBox.getItems();
        for(int i=0;i<=3;i++){
            eOptions.add(Integer.toString(i));
        }
        equilibriumComboBox.setItems(eOptions);
        equilibriumComboBox.getSelectionModel().select("0");
        
        //biting
        ObservableList<String> bOptions = bitingComboBox.getItems();
        for(int i=0;i<=3;i++){
            bOptions.add(Integer.toString(i));
        }
        bitingComboBox.setItems(bOptions);
        bitingComboBox.getSelectionModel().select("0");
    }

    private void setUpMonsterSelectionComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
        for(Monster monster:MonsterFlyweight.getListOfAllMonsters()){
            options.add(monster.getName());
        }
        options.remove("Null Monster");
        monsterChoiceComboBox.setItems(options);
    }

    private void fillTextFields() {
        playerAttackLevelTextField.textProperty().addListener((obj, oldVal, newVal) -> {
            player.getStats().setBaseAttackLevel(getInputIntegerFromText(playerAttackLevelTextField.getText()));
            reCalculate();
        });
        
        playerStrengthLevelTextField.textProperty().addListener((obj, oldVal, newVal) -> {
            player.getStats().setBaseStrengthLevel(getInputIntegerFromText(playerStrengthLevelTextField.getText()));
            reCalculate();
        });
        
        playerDefenseLevelTextField.textProperty().addListener((obj, oldVal, newVal) -> {
            player.getStats().setBaseDefenseLevel(getInputIntegerFromText(playerDefenseLevelTextField.getText()));
            reCalculate();
        });
        
        playerRangedLevelTextField.textProperty().addListener((obj, oldVal, newVal) -> {
            player.getStats().setBaseRangedLevel(getInputIntegerFromText(playerRangedLevelTextField.getText()));
            reCalculate();
        });
        
        playerMagicLevelTextField.textProperty().addListener((obj, oldVal, newVal) -> {
            player.getStats().setBaseMagicLevel(getInputIntegerFromText(playerMagicLevelTextField.getText()));
            reCalculate();
        });
    }
    
    //scene builder methods not behaving how I want. Used this instead
    private void setSliderMethods() {
        blackstoneArrowSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                blackStoneArrowSliderChanged();
            }
        });
        
        prayerSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                curseSliderChanged();
            }
        });
        
        reaperSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                reaperSliderChanged();
            }
        });
    }
    
    //////put in csv file?
    private List<Buff> makeListOffamiliars() {
        List<Buff> list = new ArrayList<>();
        list.add(BuffFlyweight.getBuff(BuffName.No_Familiar,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Blood_Nihil,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Shadow_Nihil,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Smoke_Nihil,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Ice_Nihil,BuffFlyweight.Owner.LEFT));
        
        return list;
    }

    //////put in csv file?
    private List<Buff> makeListOfPrayers() {
        List<Buff> list = new ArrayList<>();
        list.add(BuffFlyweight.getBuff(BuffName.No_Prayer,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Malevolence,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Desolation,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Affliction,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Turmoil,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Anguish,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Torment,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Piety,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Rigour,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Augury,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Chivalry,BuffFlyweight.Owner.LEFT));
        
        return list;
    }

    //////put in csv file?
    private List<Buff> makeListOfPotions() {
        List<Buff> list = new ArrayList<>();
        list.add(BuffFlyweight.getBuff(BuffName.No_Potion,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Supreme_Overload,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Overload,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Attack_Potion,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Strength_Potion,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Defense_Potion,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Ranging_Potion,BuffFlyweight.Owner.LEFT));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Magic_Potion,BuffFlyweight.Owner.LEFT));
        
        return list;
    }

    void setInitialSliderVisablility() {
        setPrayerSliderVisible(false);
        setReaperSliderVisible(false);
    }
    
    
/*_____________________________________________________________________________________________________________________
    updater methods
    ___________________________________________________________________________________________________________________
*/

    
    
    

    private void setPrayerSliderVisible(boolean visable) {
        prayerSlider.setVisible(visable);
        prayerSliderLabel.setVisible(visable);
    }

    private void setReaperSliderVisible(boolean visable) {
        reaperSlider.setVisible(visable);
        reaperSliderLabel.setVisible(visable);
        reaperStackValueLabel.setVisible(visable);
    }

    private void setMonster(String string) {
        currentMonster = MonsterFlyweight.getMonster(string);
        
        //add or delete sub monster arrows
        if(currentMonster instanceof MonsterGroup){
            monsterSubTypeLeftButton.setVisible(true);
            monsterSubTypeRightButton.setVisible(true);
        }else{
            monsterSubTypeLeftButton.setVisible(false);
            monsterSubTypeRightButton.setVisible(false);
        }
        
        //update info
        updateMonsterAttackViews();
        monsterImageView.setImage(currentMonster.getImage());
        updateMonsterWeaknessesView();
        
        if(currentMonster.getAffinityWeaknesses().hasSpecialAffinity()){
            monsterSpecialAffinityImageView.setVisible(true);
            monsterSpecialAffinityValueLabel.setVisible(true);
            monsterSpecialAffinityImageView.setImage(currentMonster.getAffinityWeaknesses().getSpecialAffinityPicture());
        }else{
            monsterSpecialAffinityImageView.setVisible(false);
            monsterSpecialAffinityValueLabel.setVisible(false);
        }
    }
    
    private void updatePlayerCombatStyleImageView() {
        if(player.canAttack(currentMonster)){
            playerCombatStyleImageView.setImage(player.getCombatStyle().getImage());
        }else{
            playerCombatStyleImageView.setImage(CantAttack.getInstance().getImage());
        }
    }

    private void updateMonsterWeaknessesView() {
        ObservableList<Node> list = monsterWeaknessesHBox.getChildren();
        list.clear();
        for(BuffName name:currentMonster.getVulnerabilities().getVulnList()){
            Buff buff = BuffFlyweight.getBuff(name,BuffFlyweight.Owner.RIGHT);
            Label label = new Label();
            label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            label.setTooltip(new Tooltip(buff.getNiceName()));
            ImageView imageView = new ImageView(buff.getImage());
            label.setGraphic(imageView);
            list.add(label);
        }
    }
    
    private void updateBuffsView(Buffs buffs, HBox hBox) {
        ObservableList<Node> list = hBox.getChildren();
        list.clear();
        for(Buff buff:buffs){
            if(!(buff instanceof DontShowOnBuffBar)){
                Label label = new Label();
                label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                String toolTipText = buff.getToolTipString();
                label.setTooltip(new Tooltip(toolTipText));
                ImageView imageView = new ImageView(buff.getImage());
                label.setGraphic(imageView);
                list.add(label);
            }
        }
    }
    
    

    private void updateMonsterAttackViews() {
        monsterCombatStyleImageView.setImage(currentMonster.getCombatStyle().getImage());
        String attackName = currentMonster.getAttacks().getAttack().getName();
        if(attackName==null){
            monsterAttackNameLabel.setVisible(false);
        }else{
            monsterAttackNameLabel.setText(attackName);
            monsterAttackNameLabel.setVisible(true);
        }
        monsterAttackMaxHitLabel.setText(Integer.toString((int) currentMonster.getBaseDamage()));
        if(currentMonster.getAttacks().numberOfAttacks()>1){
            monsterAttackLeftButton.setVisible(true);
            monsterAttackRightButton.setVisible(true);
        }else{
            monsterAttackLeftButton.setVisible(false);
            monsterAttackRightButton.setVisible(false);
        }
    }

    private int getInputIntegerFromText(String text) {
        int num;
        try{
            num = Integer.parseInt(text);
        }catch(NumberFormatException e){
            return 0;
        }
        
        return num;
    }

    private void fillBuffComboBox(ComboBox<String> comboBox, List<Buff> buffList,StringToImage stringToImage) {
        ObservableList<String> options = FXCollections.observableArrayList();
        for(Buff buff: buffList){
            options.add(buff.getNiceName());
        }
        comboBox.setCellFactory(c -> new PictureListCell(stringToImage));
        comboBox.setButtonCell(new PictureListCell(stringToImage));
        comboBox.setItems(options);
        comboBox.getSelectionModel().select(buffList.get(0).getNiceName());
    }

    private void updateAbilityDamageLabels(List<Hit> hitList,DamageMode mode) {
        ObservableList<Node> list = abilityDamageVBox.getChildren();
        list.clear();
        int textSize = getAbilityLabelTextSize(hitList.size());
        for(int i=0;i<hitList.size();i++){
            double damage = hitList.get(i).getDamage(mode);
            if(damage<=0) {//this if block was originally put here for the extra hits of dragon breath with the dragon rider amulet during minimum hit mode when they didn't land
                continue;//don't even show
            }
            Label label = new Label(String.format("%,d",(int) damage));//round down
            label.setFont(new Font(label.getFont().getName(),textSize));
            list.add(label);
        }
        
        if(hitList.size()==0) {
            playerAbilityDamageValue.setVisible(true);
            playerAbilityDamageValue.setText("-");
        }else if(hitList.size()==1) {
            //hide
            playerAbilityDamageValue.setVisible(false);
        }else {
            playerAbilityDamageValue.setVisible(true);
            playerAbilityDamageValue.setText(String.format("(%,d)", (int) getTotalDamage(hitList,mode)));
        }
    }

    private void setLabelWithSingleHit(Label label,List<Hit> hitList,DamageMode mode) {
        if(hitList.size()==1) {
            label.setText(String.format("%,d", (int) hitList.get(0).getDamage(mode)));//round down
        }else {
            label.setText("-");
        }
    }
    
    private void monsterSubTypeButtonPressed(boolean left){
        if(currentMonster instanceof MonsterGroup){
            MonsterGroup mg = (MonsterGroup) currentMonster;
            
            int indexOfMonster = monsterChoiceComboBox.getItems().indexOf(currentMonster.getName());
            
            if(left){
                mg.prevMonster();
            }else{
                mg.nextMonster();
            }
            
            monsterChoiceComboBox.getItems().set(indexOfMonster, currentMonster.getName());
            
            selectedMonster();
        }
    }
    
    private void playerEquip(String toEquip){
        playerEquip(EquipmentFlyweight.getEquipment(toEquip));
    }
    
    private void playerEquip(Equipment toEquip) {
        player.getWornEquipment().equip(toEquip);
        updateBuffsView(player.getBuffs(),playerBuffHBox);
    }
    
    private void applyEquipmentFilters(){
        for(Slot slot:Slot.values()){
            System.out.println(slot);
            if(slot==Slot.TWO_HAND){
                continue;
            }
            
            ComboBox<String> equipmentComboBox = equipmentComboBoxMap.get(slot);
            ObservableList<String> options = equipmentComboBox.getItems();
            Equipment oldEquipment = EquipmentFlyweight.getEquipment(equipmentComboBox.getValue());
            
            this.disableComboBoxTrigger = true;
            options.clear();//calls selected method for combo boxes disableComboBoxTrigger to stop boxes sending null while they are being edited
            this.disableComboBoxTrigger = false;
            
            List<Equipment> equipmentList = EquipmentFlyweight.getListOfAllEquipmentInSlot(slot);
            if(slot==Slot.MAIN_HAND){
                
                List<Equipment> originalList = equipmentList;
                equipmentList = new ArrayList<>();
                equipmentList.addAll(originalList);
                equipmentList.addAll(EquipmentFlyweight.getListOfAllEquipmentInSlot(Slot.TWO_HAND));
                
                Collections.sort(equipmentList, (Equipment o1, Equipment o2) -> o1.getSortingId() - o2.getSortingId());
            }
            
            for(Equipment equipment: equipmentList){
                if(equipmentFilters.passes(equipment) || equipment==EquipmentFlyweight.getNullEquipmentForSlot(slot)){
                    options.add(equipment.getName());
                }
            }
            
            System.out.println("old equipment: " + oldEquipment);
            //select something. Try to keep old equipment if it passes, other wise select first option which should be No ___
            if(equipmentFilters.passes(oldEquipment)){
                System.out.println("Selecting: " + oldEquipment);
                this.disableComboBoxTrigger = true;
                equipmentComboBox.getSelectionModel().select(oldEquipment.getName());
                this.disableComboBoxTrigger = false;
                playerEquip(oldEquipment);
            }else{
                this.disableComboBoxTrigger = true;
                System.out.println("Selecting: " + EquipmentFlyweight.getNullEquipmentForSlot(slot).getName());
                equipmentComboBox.getSelectionModel().select(EquipmentFlyweight.getNullEquipmentForSlot(slot).getName());
                this.disableComboBoxTrigger = false;
                playerEquip(EquipmentFlyweight.getNullEquipmentForSlot(slot));
            }
            
            
            
            
        }
        reCalculate();
    }
    
    //if player can't attack with current ammo, finds the first ammo in the list that works and selects it
    private void updateQuiver() {
        if(player.canAttack(currentMonster)) {
            return;
        }
        
        Equipment originalAmmo = player.getWornEquipment().getEquipment(Slot.QUIVER);
        
        //find an ammo that works
        Equipment ammoThatWorks = null;
        ObservableList<String> options = quiverComboBox.getItems();
        for(String ammoString:options) {
            Equipment ammo = EquipmentFlyweight.getEquipment(ammoString);
            player.getWornEquipment().equip(ammo);
            if(player.canAttack(currentMonster)) {
                ammoThatWorks = ammo;
                break;
            }
        }
        
        if(ammoThatWorks==null) {
            //couldn't find anything. Re-equip original ammo
            player.getWornEquipment().equip(originalAmmo);
            return;
        }
        
        quiverComboBox.getSelectionModel().select(ammoThatWorks.getName());
    }
    
    //deletes and re-adds abilities that pass the player can use
    private void updateAbilityComboBox() {
    	
    	ObservableList<String> options = AbilityComboBox.getItems();
        Ability oldAbility = AbilityFlyweight.getAbility(AbilityComboBox.getValue());
        
        //delete everything
        this.disableComboBoxTrigger = true;
        options.clear();//calls selected method for combo boxes disableComboBoxTrigger to stop boxes sending null while they are being edited
        this.disableComboBoxTrigger = false;
        
        //re add everything
        for(Ability ability: AbilityFlyweight.getAllAbilitiesList()){
        	if(ability.canBeUsedByPlayer(player)) {
        		options.add(ability.getName());
        	}
        }
        
        
        //select something. Try to keep old equipment if it passes, other wise select first option which should be No ___
        if(oldAbility.canBeUsedByPlayer(player)){
            System.out.println("Selecting: " + oldAbility);
            this.disableComboBoxTrigger = true;
            AbilityComboBox.getSelectionModel().select(oldAbility.getName());
            this.disableComboBoxTrigger = false;
            player.setCurrentAbility(oldAbility);
        }else{
            this.disableComboBoxTrigger = true;
            System.out.println("Selecting: " + "Sacrifice");
            AbilityComboBox.getSelectionModel().select("Sacrifice");
            this.disableComboBoxTrigger = false;
            player.setCurrentAbility(AbilityFlyweight.getAbility(AbilityComboBox.getValue()));
        }
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
    }
    
    
    
    
    

    void reCalculate(){
        updatePlayerCombatStyleImageView();//////////////////////move
        
        System.out.println("\n\n_____________________________________________________________________________________________\nrecalculating player attack");
        System.out.println("player buffs: " + player.getBuffs());
        System.out.println("monster buffs: " + currentMonster.getBuffs());
        System.out.println("monster vulnerabilities: " + currentMonster.getVulnerabilities());
        
        
        
        //calculate hitchance
        System.out.println();
        System.out.println("Calculating player hit chance");
        HitchanceCalculator.calculateHitchance(player,currentMonster);
        playerTotalAccuracyValueLabel.setText(String.format("%.1f", HitchanceCalculator.getAttackerTotalAccuracy()));
        monsterTotalDefenseValueLabel.setText(String.format("%.1f", HitchanceCalculator.getDefenderTotalDefense()));
        monsterMeleeAffinityValueLabel.setText(String.format("%.1f", currentMonster.getAffinityTo(Melee.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterRangedAffinityValueLabel.setText(String.format("%.1f", currentMonster.getAffinityTo(Ranged.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterMagicAffinityValueLabel.setText(String.format("%.1f", currentMonster.getAffinityTo(Magic.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterSpecialAffinityValueLabel.setText(Integer.toString(currentMonster.getAffinityWeaknesses().getSpecialAffinityWeaknessValue()+HitchanceCalculator.getDefenderAffinityRaise()));
        playerHitChanceValueLabel.setText(String.format("%.2f%%",HitchanceCalculator.getHitChance()));
        
        //main hand auto
        System.out.println();
        System.out.println("Calculating player main hand damage");
        player.setAttackToMainHand();
        List<Hit> hitList = DamageCalculator.calculateDamage(player, currentMonster);
        setLabelWithSingleHit(playerMHDamageValueLabel,hitList,getMode());
        
        //off hand auto
        System.out.println();
        System.out.println("Calculating player off hand damage");
        player.setAttackToOffHand();
        hitList = DamageCalculator.calculateDamage(player, currentMonster);
        setLabelWithSingleHit(playerOHDamageValueLabel,hitList,getMode());
        	
        //ability damage
        System.out.println();
        System.out.println("Calculating player ability damage");
    	player.setAttackToAbility();
    	hitList = DamageCalculator.calculateDamage(player, currentMonster);
    	updateAbilityDamageLabels(hitList,getMode());
        
        
        
        
        //monster hit chance
        System.out.println();
        System.out.println("recalculating monster hit chance");
        HitchanceCalculator.calculateHitchance(currentMonster,player);
        monsterTotalAccuracyValueLabel.setText(String.format("%.1f", HitchanceCalculator.getAttackerTotalAccuracy()));
        playerToatlDefenseValueLabel.setText(String.format("%.1f", HitchanceCalculator.getDefenderTotalDefense()));
        playerMeleeAffinityValueLabel.setText(String.format("%.1f", player.getAffinityTo(Melee.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        playerRangedAffinityValueLabel.setText(String.format("%.1f", player.getAffinityTo(Ranged.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        playerMagicAffinityValueLabel.setText(String.format("%.1f", player.getAffinityTo(Magic.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterHitChanceValueLabel.setText(String.format("%.2f%%",HitchanceCalculator.getHitChance()));
        //monster damage
        System.out.println();
        System.out.println("recalculating monster damage");
        hitList = DamageCalculator.calculateDamage(currentMonster,player);
        setLabelWithSingleHit(monsterDamageValue,hitList,getMode());
        
    }
    
/*___________________________________________________________________________________________________________________
 * support methods
 * ___________________________________________________________________________________________________________________
 */

    private DamageMode getMode() {
        if(maxAbilityRadioButton.isSelected()) {
            return DamageMode.MAX;
        }else if(aveAbilityRadioButton.isSelected()) {
            return DamageMode.AVE;
        }else if(minAbilityRadioButton.isSelected()) {
            return DamageMode.MIN;
        }else {
            System.out.println("unknown radio button selected for hit mode!");
            return DamageMode.MAX;
        }
    }

    private int getAbilityLabelTextSize(int numberOfHits) {
        if(numberOfHits<=5) {
            return 26;
        }else if(numberOfHits<=8) {
            return 18;
        }else if(numberOfHits<=12) {
            return 13;
        }else {
            return 8;
        }
    }

    private double getTotalDamage(List<Hit> hitList,DamageMode mode) {
        double total = 0;
        for(int i=0;i<hitList.size();i++) {
            total+=hitList.get(i).getDamage(mode);
        }
        return total;
    }
    
    
/*___________________________________________________________________________________________________________________
 * methods called by the interfaces
 * ___________________________________________________________________________________________________________________
 */

    public void selectedMonster(){
        setMonster(monsterChoiceComboBox.getValue());
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }

    public void monsterSubTypeLeftButtonPressed(){
        monsterSubTypeButtonPressed(true);
    }
    
    public void monsterSubTypeRightButtonPressed(){
        monsterSubTypeButtonPressed(false);
    }
    
    public void monsterAttackLeftButtonPressed(){
        currentMonster.getAttacks().previous();
        updateMonsterAttackViews();
        reCalculate();
    }
    
    public void monsterAttackRightButtonPressed(){
        currentMonster.getAttacks().next();
        updateMonsterAttackViews();
        reCalculate();
    }
    
    public void wikiButtonPressed(){
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(currentMonster.getLink()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectedAura(){
        
        System.out.println("selectedAura");
        playerEquip(this.auraComboBox.getValue());
        if(!disableComboBoxTrigger){
            reCalculate();  
        }
        
    }
    public void selectedHelmet(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedHelmet");
        playerEquip(this.helmetComboBox.getValue());
        reCalculate();
    }
    public void selectedPocket(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedPocket");
        playerEquip(this.pocketComboBox.getValue());
        reCalculate();
    }
    public void selectedSigil(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedSigil");
        playerEquip(this.sigilComboBox.getValue());
        reCalculate();
    }
    public void selectedAmulet(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedAmulet");
        if(amuletComboBox.getValue().equalsIgnoreCase("Reaper necklace (or)") || amuletComboBox.getValue().equalsIgnoreCase("Reaper necklace")){
            setReaperSliderVisible(true);
        }else{
            setReaperSliderVisible(false);
        }
        playerEquip(this.amuletComboBox.getValue());
        reCalculate();
    }
    public void selectedQuiver(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedQuiver");
        playerEquip(this.quiverComboBox.getValue());
        
        
        reCalculate();
    }

    
    public void selectedMainHand(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedMainHand");
        playerEquip(this.mainHandComboBox.getValue());
        
        if(EquipmentFlyweight.getEquipment(mainHandComboBox.getValue()).getSlot()==Slot.TWO_HAND){
            disableComboBoxTrigger = true;
            offHandComboBox.getSelectionModel().select(player.getWornEquipment().getEquipment(Slot.OFF_HAND).getName());
            //this slot may have gotten de-equiped due to two handed weapons not being able to be wielded with duel wield weapons worn equpiment has already taken care of this, so just update the box with what it has
            disableComboBoxTrigger = false;
        }
        updateQuiver();
        updateAbilityComboBox();
        
        
        
        reCalculate();
    }

    public void selectedBody(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedBody");
        playerEquip(this.bodyComboBox.getValue());
        reCalculate();
    }
    public void selectedOffHand(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedOffHand");
        playerEquip(this.offHandComboBox.getValue());
        
        if(EquipmentFlyweight.getEquipment(mainHandComboBox.getValue()).getSlot()==Slot.TWO_HAND && EquipmentFlyweight.getEquipment(offHandComboBox.getValue())!=EquipmentFlyweight.getEquipment("No Off Hand")){
            disableComboBoxTrigger = true;
            mainHandComboBox.getSelectionModel().select(player.getWornEquipment().getMainWeapon().getName());
            //this slot may have gotten de-equiped due to two handed weapons not being able to be wielded with duel wield weapons worn equpiment has already taken care of this, so just update the box with what it has
            disableComboBoxTrigger = false;
        }
        updateQuiver();
        updateAbilityComboBox();
        
        
        reCalculate();
    }
    public void selectedLegs(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedLegs");
        playerEquip(this.legsComboBox.getValue());
        reCalculate();
    }
    public void selectedGloves(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedGloves");
        playerEquip(this.glovesComboBox.getValue());
        reCalculate();
    }
    public void selectedBoots(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedBoots");
        playerEquip(this.bootsComboBox.getValue());
        reCalculate();
    }
    public void selectedRing(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedRing");
        playerEquip(this.ringComboBox.getValue());
        reCalculate();
    }
    public void selectedCape(){
        if(disableComboBoxTrigger){
            return;
        }
        System.out.println("selectedCape");
        playerEquip(this.capeComboBox.getValue());
        reCalculate();
    }
    
    
    
    
    public void selectedPlayerPotion(){
        if(disableComboBoxTrigger){
            return;
        }
        Buffs playerOtherBuffs = player.getPlayerOtherBuffs();
        Buff selectedPotion = BuffFlyweight.getBuff(potionComboBox.getValue(),BuffFlyweight.Owner.LEFT);
        
        playerOtherBuffs.removeBuff(potionBuff.getName());
        potionBuff = selectedPotion;
        playerOtherBuffs.addBuff(potionBuff);
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
        reCalculate();
    }
    
    public void selectedPlayerPrayer(){
        if(disableComboBoxTrigger){
            return;
        }
        Buffs playerOtherBuffs = player.getPlayerOtherBuffs();
        Buff selectedPrayer = BuffFlyweight.getBuff(statPrayerComboBox.getValue(),BuffFlyweight.Owner.LEFT);
        
        playerOtherBuffs.removeBuff(prayerBuff.getName());
        prayerBuff = selectedPrayer;
        playerOtherBuffs.addBuff(prayerBuff);
        
        if(selectedPrayer instanceof StackBuff){
            setPrayerSliderVisible(true);
        }else{
            setPrayerSliderVisible(false);
        }
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
        reCalculate();
    }
    
    public void selectedPlayerFamiliar(){
        if(disableComboBoxTrigger){
            return;
        }
        Buffs playerOtherBuffs = player.getPlayerOtherBuffs();
        Buff selectedFamiliar = BuffFlyweight.getBuff(familiarComboBox.getValue(),BuffFlyweight.Owner.LEFT);
        
        playerOtherBuffs.removeBuff(familiarBuff.getName());
        familiarBuff = selectedFamiliar;
        playerOtherBuffs.addBuff(familiarBuff);
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
        reCalculate();
    }
    
    
    
    public void clickedQuakeCheckBox(){
        if(quakeCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Quake,BuffFlyweight.Owner.RIGHT));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Quake);
        }
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }

    public void clickedGuthixStaffSpecCheckBox(){
        if(guthixStaffSpecCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Guthix_Staff_Spec,BuffFlyweight.Owner.RIGHT));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Guthix_Staff_Spec);
        }
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedSWHSpecCheckBox(){
        if(swhSpecCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Status_Warhammer_Spec,BuffFlyweight.Owner.RIGHT));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Status_Warhammer_Spec);
        }
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedVulnerabilityCheckBox(){
        if(vulnerabilityCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Vulnerability,BuffFlyweight.Owner.RIGHT));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Vulnerability);
        }
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedEnfeebleCheckBox(){
        if(enfeebleCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Enfeeble,BuffFlyweight.Owner.RIGHT));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Enfeeble);
        }
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedStaggerCheckBox(){
        if(staggerCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Stagger,BuffFlyweight.Owner.RIGHT));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Stagger);
        }
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }
    
    
    
    public void curseSliderChanged(){
        int stackValue = (int) prayerSlider.getValue();
        //all curse stacks are updated
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Malevolence,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Desolation,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Affliction,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Turmoil,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Anguish,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Torment,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        updateBuffsView(player.getBuffs(),playerBuffHBox);//for the tool tip stack info
        reCalculate();
    }
    
    public void reaperSliderChanged(){
        int stackValue = (int) reaperSlider.getValue();
        ((StackBuff) BuffFlyweight.getBuff(BuffName.Reaper_Necklace,BuffFlyweight.Owner.LEFT)).setStackValue(stackValue);
        reaperStackValueLabel.setText(Integer.toString(stackValue));
        updateBuffsView(player.getBuffs(),playerBuffHBox);//for the tool tip stack info
        reCalculate();
    }
    
    public void blackStoneArrowSliderChanged(){
        int stackValue = (int) blackstoneArrowSlider.getValue();
        StackBuff buff = (StackBuff) BuffFlyweight.getBuff(BuffName.Black_Stone_Arrows,BuffFlyweight.Owner.RIGHT);
        int oldValue = buff.getStackValue();
        
        if(oldValue==0&&stackValue>0){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Black_Stone_Arrows,BuffFlyweight.Owner.RIGHT));
        }
        if(oldValue>0&&stackValue==0){
            currentMonster.getEditableBuffs().removeBuff(BuffName.Black_Stone_Arrows);
        }
        
        buff.setStackValue(stackValue);
        blackstoneArrowStackValueLabel.setText(Integer.toString(stackValue));
        updateBuffsView(currentMonster.getBuffs(),monsterBuffHBox);
        reCalculate();
    }
    
    public void filterComboBoxSelected(){
        String selection = this.filterComboBox.getValue();
        
        this.equipmentFilters.clear();
        
        switch(selection){
        case "None":
            break;
        case "Melee":
            equipmentFilters.addFilter(meleeFilter);
            break;
        case "Ranged":
            equipmentFilters.addFilter(rangedFilter);
            break;
        case "Magic":
            equipmentFilters.addFilter(magicFilter);
            break;
            default:
                System.out.println("RSHitChance2Controller: unrecognized filter selection!");
        }
        
        this.applyEquipmentFilters();
    }
    
    public void abilityComboBoxSelected() {
    	if(disableComboBoxTrigger){
            return;
        }
    	String selection = this.AbilityComboBox.getValue();
    	player.setCurrentAbility(AbilityFlyweight.getAbility(selection));
    	updateBuffsView(player.getBuffs(),playerBuffHBox);
    	reCalculate();
    }
    
    public void damageModeChanged() {
        reCalculate();
    }
    
    public void preciseComboBoxChanged() {
        int stackValue = Integer.parseInt(preciseComboBox.getValue());
        StackBuff buff = (StackBuff) BuffFlyweight.getBuff(BuffName.Precise,BuffFlyweight.Owner.LEFT);
        
        int oldValue = buff.getStackValue();
        if(oldValue==0&&stackValue>0){
            player.getPlayerInventionPerkBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Precise,BuffFlyweight.Owner.LEFT));
        }
        if(oldValue>0&&stackValue==0){
            player.getPlayerInventionPerkBuffs().removeBuff(BuffName.Precise);
        }
        
        buff.setStackValue(stackValue);
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
        reCalculate();
    }
    
    public void equilibriumComboBoxChanged() {
        int stackValue = Integer.parseInt(equilibriumComboBox.getValue());
        StackBuff buff = (StackBuff) BuffFlyweight.getBuff(BuffName.Equilibrium,BuffFlyweight.Owner.LEFT);

        int oldValue = buff.getStackValue();
        if(oldValue==0&&stackValue>0){
            player.getPlayerInventionPerkBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Equilibrium,BuffFlyweight.Owner.LEFT));
        }
        if(oldValue>0&&stackValue==0){
            player.getPlayerInventionPerkBuffs().removeBuff(BuffName.Equilibrium);
        }
        
        buff.setStackValue(stackValue);
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
        reCalculate();
    }
    
    public void bitingComboBoxChanged() {
        int stackValue = Integer.parseInt(bitingComboBox.getValue());
        StackBuff buff = (StackBuff) BuffFlyweight.getBuff(BuffName.Biting,BuffFlyweight.Owner.LEFT);

        int oldValue = buff.getStackValue();
        if(oldValue==0&&stackValue>0){
            player.getPlayerInventionPerkBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Biting,BuffFlyweight.Owner.LEFT));
        }
        if(oldValue>0&&stackValue==0){
            player.getPlayerInventionPerkBuffs().removeBuff(BuffName.Biting);
        }
        
        buff.setStackValue(stackValue);
        
        updateBuffsView(player.getBuffs(),playerBuffHBox);
        reCalculate();
    }
    

}
