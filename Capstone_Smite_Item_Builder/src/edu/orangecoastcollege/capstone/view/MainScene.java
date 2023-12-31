package edu.orangecoastcollege.capstone.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.capstone.controller.Controller;
import edu.orangecoastcollege.capstone.model.Build;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * Main Scene of the Smite Item Builder application.
 * @author kevin_000
 *
 */
public class MainScene implements Initializable 
{
	private static Controller controller = Controller.getInstance();
	protected static int itemNumber;
    @FXML
    private ComboBox<String> allGodsCB;

    @FXML
    private Button viewAllGodsButton;

    @FXML
    private Button loreButton;

    @FXML
    private ComboBox<String> item1CB;

    @FXML
    private ComboBox<String> item2CB;

    @FXML
    private ComboBox<String> item3CB;

    @FXML
    private ComboBox<String> item4CB;

    @FXML
    private ComboBox<String> item5CB;

    @FXML
    private ComboBox<String> item6CB;

    @FXML
    private ComboBox<String> relic1CB;

    @FXML
    private ComboBox<String> relic2CB;

    @FXML
    private TextArea statsTextArea;

    @FXML
    private Button godStatsButton;

    @FXML
    private Button abilityStatsButton;
    
    @FXML
    private Button saveBuildButton;
   
    private Build currentBuild;

    /**
     * Sets the current build if one exists. Enables the buttons if one exists and sets all comboboxes
     * to the items in the build. Otherwise disable the buttons.
     */
    @Override
	public void initialize(URL location, ResourceBundle resources) 
    {
    	if(controller.getCurrentBuild() != null)
    	{
    		currentBuild = controller.getCurrentBuild();
    		allGodsCB.getSelectionModel().select(currentBuild.getGod().getName());
    		if(currentBuild.getItem1() != null)
    			item1CB.getSelectionModel().select(currentBuild.getItem1().getName());
    		if(currentBuild.getItem2() != null)
    			item2CB.getSelectionModel().select(currentBuild.getItem2().getName());
    		if(currentBuild.getItem3() != null)
    			item3CB.getSelectionModel().select(currentBuild.getItem3().getName());
    		if(currentBuild.getItem4() != null)
    			item4CB.getSelectionModel().select(currentBuild.getItem4().getName());
    		if(currentBuild.getItem5() != null)
    			item5CB.getSelectionModel().select(currentBuild.getItem5().getName());
    		if(currentBuild.getItem6() != null)
    			item6CB.getSelectionModel().select(currentBuild.getItem6().getName());
    		if(currentBuild.getRelic1() != null)
    			relic1CB.getSelectionModel().select(currentBuild.getRelic1().getName());
    		if(currentBuild.getRelic2() != null)
    			relic2CB.getSelectionModel().select(currentBuild.getRelic2().getName());
    	}
    	else
    	{
    		loreButton.setDisable(true);
        	godStatsButton.setDisable(true);
        	abilityStatsButton.setDisable(true);
        	saveBuildButton.setDisable(true);
    	}
		allGodsCB.setItems(controller.getGodNames());
		item1CB.setItems(controller.getItemNames());
		item2CB.setItems(controller.getItemNames());
		item3CB.setItems(controller.getItemNames());
		item4CB.setItems(controller.getItemNames());
		item5CB.setItems(controller.getItemNames());
		item6CB.setItems(controller.getItemNames());
		relic1CB.setItems(controller.getRelicNames());
		relic2CB.setItems(controller.getRelicNames());
	}
    
    /**
     * Loads the ViewGodStats Scene
     * @return
     */
    @FXML
    public Object viewGodStats()
    {
    	ViewNavigator.loadScene("God Stats", ViewNavigator.GOD_STATS_SCENE);
    	return this;
    }
    
    /**
     * Loads the viewAbilityStatsScene
     * @return
     */
    @FXML
    public Object viewAbilityStats()
    {
    	ViewNavigator.loadScene("Ability Stats", ViewNavigator.ABILITIES_STATS_SCENE);
    	return this;
    }
    
    /**
     * Loads the lore scene for the selected god
     * @return
     */
    @FXML
    public Object viewLore()
    {
    	ViewNavigator.loadScene(controller.getCurrentBuild().getGod().getName() + 
    							"'s Lore", ViewNavigator.GOD_LORE_SCENE);
    	return this;
    }
    
    /**
     * Loads the allItemsScene
     * @param event
     */
    @FXML
    void viewAllItems(MouseEvent event)
    {
    	String id = event.getPickResult().getIntersectedNode().getId();
    	itemNumber = Integer.parseInt(id); 	
    	ViewNavigator.loadScene("All Items List", ViewNavigator.ALL_ITEMS_SCENE);
    }
    
    /**
     * Loads the allGodsScene
     * @return
     */
    @FXML
    public Object viewAllGods()
    {
    	ViewNavigator.loadScene("All Gods List", ViewNavigator.ALL_GODS_SCENE);
    	return this;
    }
    
    /**
     * Loads the AllRelicsScene
     * @param event
     */
    @FXML
    void viewAllRelics(MouseEvent event)
    {
    	String id = event.getPickResult().getIntersectedNode().getId();
    	itemNumber = Integer.parseInt(id); 	
    	ViewNavigator.loadScene("All Relics List", ViewNavigator.ALL_RELICS_SCENE);
    }
    
    /**
     * Loads the viewAllBuildsScene
     * @return
     */
    @FXML
    public Object viewAllBuilds()
    {
    	ViewNavigator.loadScene("All Builds List", ViewNavigator.ALL_BUILDS_SCENE);
    	return this;
    }
    
    /**
     * Adds the god to the current build
     * @return
     */
    @FXML
    public Object addGodToBuild()
    {
    	
    	String godName = allGodsCB.getSelectionModel().getSelectedItem();
    	loreButton.setDisable(false);
    	godStatsButton.setDisable(false);
    	abilityStatsButton.setDisable(false);
    	saveBuildButton.setDisable(false);
    	controller.addGodToCurrentBuild(godName);
    	return this;
    }
    
    /**
     * Adds the item to the first item slot
     * @return
     */
    @FXML
    public Object addItem1ToBuild()
    {
    	String itemName = item1CB.getSelectionModel().getSelectedItem();
    	controller.addItem1ToCurrentBuild(itemName);
    	return this;
    }
    
    /**
     * Adds the item to the second item slot
     * @return
     */
    @FXML
    public Object addItem2ToBuild()
    {
    	String itemName = item2CB.getSelectionModel().getSelectedItem();
    	controller.addItem2ToCurrentBuild(itemName);
    	return this;
    }
    
    /**
     * Adds the item to the third item slot
     * @return
     */
    @FXML
    public Object addItem3ToBuild()
    {
    	String itemName = item3CB.getSelectionModel().getSelectedItem();
    	controller.addItem3ToCurrentBuild(itemName);
    	return this;
    }
    
    /**
     * Adds the item to the fourth item slot
     * @return
     */
    @FXML
    public Object addItem4ToBuild()
    {
    	String itemName = item4CB.getSelectionModel().getSelectedItem();
    	controller.addItem4ToCurrentBuild(itemName);
    	return this;
    }
    
    /**
     * Adds the item to the fifth item slot
     * @return
     */
    @FXML
    public Object addItem5ToBuild()
    {
    	String itemName = item5CB.getSelectionModel().getSelectedItem();
    	controller.addItem5ToCurrentBuild(itemName);
    	return this;
    }
    
    /** Adds the item to the sixth item slot
     * 
     * @return
     */
    @FXML
    public Object addItem6ToBuild()
    {
    	String itemName = item6CB.getSelectionModel().getSelectedItem();
    	controller.addItem6ToCurrentBuild(itemName);
    	return this;
    }
    
    /**
     * Adds the relic to the first relic slot
     * @return
     */
    @FXML
    public Object addRelic1ToBuild()
    {
    	String relicName = relic1CB.getSelectionModel().getSelectedItem();
    	controller.addRelic1ToCurrentBuild(relicName);
    	return this;
    }
    
    /**
     * Adds the relic to the second relic slot
     * @return
     */
    @FXML
    public Object addRelic2ToBuild()
    {
    	String relicName = relic2CB.getSelectionModel().getSelectedItem();
    	controller.addRelic2ToCurrentBuild(relicName);
    	return this;
    }
    
    /**
     * Adds the current build to the database
     * @return
     */
    @FXML
    public Object saveBuild()
    {
    	controller.saveCurrentBuild();
    	return this;
    }
    
    
}
