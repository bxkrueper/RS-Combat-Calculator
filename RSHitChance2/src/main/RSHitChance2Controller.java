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

import buff.BuffFlyweight;
import buff.Buff;
import buff.BuffName;
import buff.Buffs;
import buff.DontShowOnBuffBar;
import buff.NullBuff;
import buff.StackBuff;
import buff.StackValueReference;
import buffSpecific.BlackStoneArrows;
import buffSpecific.Reaper;
import combatStyle.CantAttack;
import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.None;
import combatStyle.Ranged;
import combatent.Combatent;
import combatent.Monster;
import combatent.MonsterFlyweight;
import combatent.MonsterGroup;
import combatent.MonsterInterface;
import combatent.Player;
import combatent.PlayerStats;
import combatent.SpecialAffinity;
import combatent.MonsterStats;
import combatent.NullMonster;
import equipment.NotOtherPrimaryStyleFilter;
import equipment.Equipment;
import equipment.EquipmentFilter;
import equipment.EquipmentFilters;
import equipment.EquipmentFlyweight;
import equipment.Slot;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private Label playerAbilityDamageValue;
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
    
    
    private Player player;
    private MonsterInterface currentMonster;
    private List<MonsterInterface> monsterList;
    private Map<Slot,ComboBox<String>> equipmentComboBoxMap;
    private EquipmentFilters equipmentFilters;
    
    private boolean disableComboBoxTrigger;
    
    private Buff potionBuff;
    private Buff prayerBuff;
    private Buff familiarBuff;
    private StackValueReference curseStackValueReference;
    private StackValueReference reaperStackValueReference;
    private StackValueReference blackStoneStackValueReference;
    
    private EquipmentFilter meleeFilter;
    private EquipmentFilter rangedFilter;
    private EquipmentFilter magicFilter;
    
    
    
    //this optional method is called when the FXML file is loaded
    public void initialize(URL arg0, ResourceBundle arg1){
        System.out.println("initializing");
        this.disableComboBoxTrigger = false;
        this.monsterList = MonsterFlyweight.getListOfAllMonsters();
        
        this.player = makePlayer();
        setMonster("Null Monster");
        
        equipmentFilters = new EquipmentFilters();
        meleeFilter = new NotOtherPrimaryStyleFilter(Melee.getInstance());
        rangedFilter = new NotOtherPrimaryStyleFilter(Ranged.getInstance());
        magicFilter = new NotOtherPrimaryStyleFilter(Magic.getInstance());
        
        
        fillInterfaces();
        setSupportFields();
    }
    
    private void fillInterfaces() {
        fillInitialImageViews();
        makeEquipmentComboBoxMaps();
        fillEquipmentComboBoxes();
        fillBuffComboBoxes();
        fillFilterComboBox();
        setUpMonsterSelectionComboBox();
        fillTextFields();
        setSliderMethods();
        setSliderVisablility();
    }

    private void fillFilterComboBox() {
        ObservableList<String> options = this.filterComboBox.getItems();
        options.add("None");
        options.add("Melee");
        options.add("Ranged");
        options.add("Magic");
        filterComboBox.getSelectionModel().select("None");
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

    private void setSliderVisablility() {
        setPrayerSliderVisible(false);
        setReaperSliderVisible(false);
    }

    private void setReaperSliderVisible(boolean visable) {
        reaperSlider.setVisible(visable);
        reaperSliderLabel.setVisible(visable);
        reaperStackValueLabel.setVisible(visable);
    }

    private void setSupportFields() {
        potionBuff = NullBuff.getInstance();
        prayerBuff = NullBuff.getInstance();
        familiarBuff = NullBuff.getInstance();
        
        curseStackValueReference = new StackValueReference((int) prayerSlider.getValue());
        reaperStackValueReference = new StackValueReference((int) reaperSlider.getValue());
        blackStoneStackValueReference = new StackValueReference((int) blackstoneArrowSlider.getValue());
        
        StackBuff malevolence = (StackBuff) BuffFlyweight.getBuff(BuffName.Malevolence);
        StackBuff desolation = (StackBuff) BuffFlyweight.getBuff(BuffName.Desolation);
        StackBuff affliction = (StackBuff) BuffFlyweight.getBuff(BuffName.Affliction);
        StackBuff turmoil = (StackBuff) BuffFlyweight.getBuff(BuffName.Turmoil);
        StackBuff anguish = (StackBuff) BuffFlyweight.getBuff(BuffName.Anguish);
        StackBuff torment = (StackBuff) BuffFlyweight.getBuff(BuffName.Torment);
        StackBuff reaperBuff = (StackBuff) BuffFlyweight.getBuff(BuffName.Reaper_Necklace);
        StackBuff blackStoneArrowBuff = (StackBuff) BuffFlyweight.getBuff(BuffName.Black_Stone_Arrows);
        
        malevolence.setStackReference(curseStackValueReference);
        desolation.setStackReference(curseStackValueReference);
        affliction.setStackReference(curseStackValueReference);
        turmoil.setStackReference(curseStackValueReference);
        anguish.setStackReference(curseStackValueReference);
        torment.setStackReference(curseStackValueReference);
        reaperBuff.setStackReference(reaperStackValueReference);
        blackStoneArrowBuff.setStackReference(blackStoneStackValueReference);
    }

    private void setUpMonsterSelectionComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
        for(MonsterInterface monster:monsterList){
            options.add(monster.getName());
        }
        options.remove("Null Monster");
        monsterChoiceComboBox.setItems(options);
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
            list.add(new ImageView(BuffFlyweight.getBuff(name).getImage()));
        }
    }
    
    private void updateBuffsView(Combatent combatent, HBox hBox) {
        ObservableList<Node> list = hBox.getChildren();
        list.clear();
        for(BuffName name:combatent.getBuffs()){
            Buff buff = BuffFlyweight.getBuff(name);
            if(!(buff instanceof DontShowOnBuffBar)){
                list.add(new ImageView(buff.getImage()));
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
        
        monsterAttackMaxHitLabel.setText(Integer.toString(currentMonster.getAttacks().getAttack().getMaxHit()));
        if(currentMonster.getAttacks().numberOfAttacks()>1){
            monsterAttackLeftButton.setVisible(true);
            monsterAttackRightButton.setVisible(true);
        }else{
            monsterAttackLeftButton.setVisible(false);
            monsterAttackRightButton.setVisible(false);
        }
    }

    private void setPrayerSliderVisible(boolean visable) {
        prayerSlider.setVisible(visable);
        prayerSliderLabel.setVisible(visable);
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
    
    private int getInputIntegerFromText(String text) {
        int num;
        try{
            num = Integer.parseInt(text);
        }catch(NumberFormatException e){
            return -1;
        }
        
        return num;
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
    
    private void fillBuffComboBoxes() {
        List<Buff> potionBuffs = makeListOfPotions();
        List<Buff> prayerBuffs = makeListOfPrayers();
        List<Buff> familiarBuffs = makeListOffamiliars();
        
        BuffStringToImage buffStringToImage = new BuffStringToImage(Buff.IMAGE_WIDTH,Buff.IMAGE_HEIGHT);
        fillBuffComboBox(potionComboBox,potionBuffs,buffStringToImage);
        fillBuffComboBox(this.statPrayerComboBox,prayerBuffs,buffStringToImage);
        fillBuffComboBox(this.familiarComboBox,familiarBuffs,buffStringToImage);
    }
    
    //////put in csv file?
    private List<Buff> makeListOffamiliars() {
        List<Buff> list = new ArrayList<>();
        list.add(BuffFlyweight.getBuff(BuffName.No_Familiar));
        list.add(BuffFlyweight.getBuff(BuffName.Blood_Nihil));
        list.add(BuffFlyweight.getBuff(BuffName.Shadow_Nihil));
        list.add(BuffFlyweight.getBuff(BuffName.Smoke_Nihil));
        list.add(BuffFlyweight.getBuff(BuffName.Ice_Nihil));
        
        return list;
    }

    //////put in csv file?
    private List<Buff> makeListOfPrayers() {
        List<Buff> list = new ArrayList<>();
        list.add(BuffFlyweight.getBuff(BuffName.No_Prayer));
        list.add(BuffFlyweight.getBuff(BuffName.Malevolence));
        list.add(BuffFlyweight.getBuff(BuffName.Desolation));
        list.add(BuffFlyweight.getBuff(BuffName.Affliction));
        list.add(BuffFlyweight.getBuff(BuffName.Turmoil));
        list.add(BuffFlyweight.getBuff(BuffName.Anguish));
        list.add(BuffFlyweight.getBuff(BuffName.Torment));
        list.add(BuffFlyweight.getBuff(BuffName.Piety));
        list.add(BuffFlyweight.getBuff(BuffName.Rigour));
        list.add(BuffFlyweight.getBuff(BuffName.Augury));
        list.add(BuffFlyweight.getBuff(BuffName.Chivalry));
        
        return list;
    }

    //////put in csv file?
    private List<Buff> makeListOfPotions() {
        List<Buff> list = new ArrayList<>();
        list.add(BuffFlyweight.getBuff(BuffName.No_Potion));
        list.add(BuffFlyweight.getBuff(BuffName.Supreme_Overload));
        list.add(BuffFlyweight.getBuff(BuffName.Overload));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Attack_Potion));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Strength_Potion));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Defense_Potion));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Ranging_Potion));
        list.add(BuffFlyweight.getBuff(BuffName.Extreme_Magic_Potion));
        
        return list;
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

    private Player makePlayer() {
        int attackLevel = Integer.parseInt(playerAttackLevelTextField.getText());
        int rangedLevel = Integer.parseInt(playerRangedLevelTextField.getText());
        int magicLevel = Integer.parseInt(playerMagicLevelTextField.getText());
        int strengthLevel = Integer.parseInt(playerStrengthLevelTextField.getText());
        int defenseLevel = Integer.parseInt(playerDefenseLevelTextField.getText());
        Player player = new Player("TestPlayer", new PlayerStats(attackLevel,rangedLevel,magicLevel,strengthLevel,defenseLevel));
        
        return player;
    }

    private void reCalculate(){
        System.out.println("\n\nrecalculating player attack...");
        System.out.println("player buffs: " + player.getBuffs());
        System.out.println("monster buffs: " + currentMonster.getBuffs());
        System.out.println("monster vulnerabilities: " + currentMonster.getVulnerabilities());
        
        updatePlayerCombatStyleImageView();
        
        HitchanceCalculator.calculateHitchance(player,currentMonster);
        
        playerTotalAccuracyValueLabel.setText(Integer.toString(HitchanceCalculator.getAttackerTotalAccuracy()));
        monsterTotalDefenseValueLabel.setText(Integer.toString(HitchanceCalculator.getDefenderTotalDefense()));
        monsterMeleeAffinityValueLabel.setText(Integer.toString(currentMonster.getAffinityTo(Melee.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterRangedAffinityValueLabel.setText(Integer.toString(currentMonster.getAffinityTo(Ranged.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterMagicAffinityValueLabel.setText(Integer.toString(currentMonster.getAffinityTo(Magic.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        monsterSpecialAffinityValueLabel.setText(Integer.toString(currentMonster.getAffinityWeaknesses().getSpecialAffinityWeaknessValue()+HitchanceCalculator.getDefenderAffinityRaise()));
        
        playerHitChanceValueLabel.setText(String.format("%.2f%%",HitchanceCalculator.getHitChance()));
        
        playerAbilityDamageValue.setText(String.format("%d",DamageCalculator.calculateAbilityDamage(player, currentMonster)));
        playerMHDamageValueLabel.setText(String.format("%d",DamageCalculator.calculateAutoDamage(player, currentMonster,true)));
        if(player.getWornEquipment().getMainWeapon().getSlot()==Slot.TWO_HAND){
            playerOHDamageValueLabel.setText("-");
        }else{
            playerOHDamageValueLabel.setText(String.format("%d",DamageCalculator.calculateAutoDamage(player, currentMonster,false)));
        }
        
        
        System.out.println("\nrecalculating monster attack...");
        HitchanceCalculator.calculateHitchance(currentMonster,player);
        
        monsterTotalAccuracyValueLabel.setText(Integer.toString(HitchanceCalculator.getAttackerTotalAccuracy()));
        playerToatlDefenseValueLabel.setText(Integer.toString(HitchanceCalculator.getDefenderTotalDefense()));
        playerMeleeAffinityValueLabel.setText(Integer.toString(player.getAffinityTo(Melee.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        playerRangedAffinityValueLabel.setText(Integer.toString(player.getAffinityTo(Ranged.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        playerMagicAffinityValueLabel.setText(Integer.toString(player.getAffinityTo(Magic.getInstance())+HitchanceCalculator.getDefenderAffinityRaise()));
        
        
        monsterHitChanceValueLabel.setText(String.format("%.2f%%",HitchanceCalculator.getHitChance()));
        monsterDamageValue.setText(String.format("%d",DamageCalculator.calculateAbilityDamage(currentMonster,player)));
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
        updateBuffsView(player,playerBuffHBox);
        
    }
    
    private void playerEquip(Equipment toEquip) {
        player.getWornEquipment().equip(toEquip);
        updateBuffsView(player,playerBuffHBox);
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
    
    
    
    
    
    
    

    public void selectedMonster(){
        setMonster(monsterChoiceComboBox.getValue());
        updateBuffsView(currentMonster,monsterBuffHBox);
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
            offHandComboBox.getSelectionModel().select(player.getWornEquipment().getEquipment(Slot.OFF_HAND).getName());//this slot may have gotten de-equiped due to two handed weapons
            disableComboBoxTrigger = false;
        }
        
        
        
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
            mainHandComboBox.getSelectionModel().select(player.getWornEquipment().getMainWeapon().getName());//this slot may have gotten de-equiped due to two handed weapons
            disableComboBoxTrigger = false;
        }
        
        
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
        Buff selectedPotion = BuffFlyweight.getBuff(potionComboBox.getValue());
        
        playerOtherBuffs.removeBuff(potionBuff.getName());
        potionBuff = selectedPotion;
        playerOtherBuffs.addBuff(potionBuff);
        
        updateBuffsView(player,playerBuffHBox);
        reCalculate();
    }
    
    public void selectedPlayerPrayer(){
        if(disableComboBoxTrigger){
            return;
        }
        Buffs playerOtherBuffs = player.getPlayerOtherBuffs();
        Buff selectedPrayer = BuffFlyweight.getBuff(statPrayerComboBox.getValue());
        
        playerOtherBuffs.removeBuff(prayerBuff.getName());
        prayerBuff = selectedPrayer;
        playerOtherBuffs.addBuff(prayerBuff);
        
        if(selectedPrayer instanceof StackBuff){
            setPrayerSliderVisible(true);
        }else{
            setPrayerSliderVisible(false);
        }
        
        updateBuffsView(player,playerBuffHBox);
        reCalculate();
    }
    
    public void selectedPlayerFamiliar(){
        if(disableComboBoxTrigger){
            return;
        }
        Buffs playerOtherBuffs = player.getPlayerOtherBuffs();
        Buff selectedFamiliar = BuffFlyweight.getBuff(familiarComboBox.getValue());
        
        playerOtherBuffs.removeBuff(familiarBuff.getName());
        familiarBuff = selectedFamiliar;
        playerOtherBuffs.addBuff(familiarBuff);
        
        updateBuffsView(player,playerBuffHBox);
        reCalculate();
    }
    
    
    
    public void clickedQuakeCheckBox(){
        if(quakeCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Quake));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Quake);
        }
        updateBuffsView(currentMonster,monsterBuffHBox);
        reCalculate();
    }

    public void clickedGuthixStaffSpecCheckBox(){
        if(guthixStaffSpecCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Guthix_Staff_Spec));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Guthix_Staff_Spec);
        }
        updateBuffsView(currentMonster,monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedSWHSpecCheckBox(){
        if(swhSpecCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Status_Warhammer_Spec));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Status_Warhammer_Spec);
        }
        updateBuffsView(currentMonster,monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedVulnerabilityCheckBox(){
        if(vulnerabilityCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Vulnerability));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Vulnerability);
        }
        updateBuffsView(currentMonster,monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedEnfeebleCheckBox(){
        if(enfeebleCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Enfeeble));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Enfeeble);
        }
        updateBuffsView(currentMonster,monsterBuffHBox);
        reCalculate();
    }
    
    public void clickedStaggerCheckBox(){
        if(staggerCheckBox.isSelected()){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Stagger));
        }else{
            currentMonster.getEditableBuffs().removeBuff(BuffName.Stagger);
        }
        updateBuffsView(currentMonster,monsterBuffHBox);
        reCalculate();
    }
    
    
    //////////update buffs view with stack value?
    public void curseSliderChanged(){
        int stackValue = (int) prayerSlider.getValue();
        curseStackValueReference.setValue(stackValue);
        reCalculate();
    }
    
    public void reaperSliderChanged(){
        int stackValue = (int) reaperSlider.getValue();
        reaperStackValueReference.setValue(stackValue);
        reaperStackValueLabel.setText(Integer.toString(stackValue));
        reCalculate();
    }
    
    public void blackStoneArrowSliderChanged(){
        int stackValue = (int) blackstoneArrowSlider.getValue();
        int oldValue = blackStoneStackValueReference.getValue();
        
        if(oldValue==0&&stackValue>0){
            currentMonster.getEditableBuffs().addBuff(BuffFlyweight.getBuff(BuffName.Black_Stone_Arrows));
        }
        if(oldValue>0&&stackValue==0){
            currentMonster.getEditableBuffs().removeBuff(BuffName.Black_Stone_Arrows);
        }
        
        blackStoneStackValueReference.setValue(stackValue);
        blackstoneArrowStackValueLabel.setText(Integer.toString(stackValue));
        updateBuffsView(currentMonster,monsterBuffHBox);
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
    
    
    
    
    

}