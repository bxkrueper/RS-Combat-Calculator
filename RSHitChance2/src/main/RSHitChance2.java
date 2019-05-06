package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import combatent.Combatent;
import combatent.Monster;
import combatent.MonsterFileReader;
import combatent.MonsterStats;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//drag fxml file into main package to get it to recognize the classes and show up in the ide
public class RSHitChance2 extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        //Load the FXML file.
        Parent parent = FXMLLoader.load(getClass().getResource("RSHitchance2Layout.fxml"));

        //build the scene graph.
        Scene scene = new Scene(parent);

        //Display the window, using the scene graph.
        stage.setTitle("Runescape Combat Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        //Launch the application.
        launch(args);
    }
}