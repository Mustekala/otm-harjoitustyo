/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitysimulator.ui;

import gravitysimulator.domain.CelestialObject;
import gravitysimulator.domain.Game;
import gravitysimulator.domain.Load;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Class provides the backbone of the UI, and functions as main menu
 * @author eero
 * 
 */

public class GravitysimulatorUI extends Application {
    
    Game game;
    GameUI gameUI;
    
    /**
    *
    * Creates the program layout
     * @param window the windows to add the scene to
     * @throws java.lang.Exception
    */  
    @Override
    public void start(Stage window) throws Exception {

        //class for loading save TODO if time saves?
        Load load = new Load();
                   
        BorderPane layout = new BorderPane();

	VBox menu = new VBox();
	menu.setSpacing(50);
        
        Button newGameButton = new Button("New game");
	menu.getChildren().add(newGameButton);
	
        Button loadSaveButton = new Button("Load save");
	menu.getChildren().add(loadSaveButton);
       
        menu.setAlignment(Pos.CENTER);
                 
	HBox top = new HBox();
	top.setSpacing(10);
        Button returnButton = new Button("Return to menu");
        
        top.getChildren().add(returnButton);
                 
        layout.setTop(top);
	layout.setCenter(menu);
                  
        BackgroundImage space = new BackgroundImage(new Image("/images/space.jpg",1000,1000,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT);
        layout.setBackground(new Background(space));
        
        //Buttons
        newGameButton.setOnAction((event) -> {
            try {
                CelestialObjectUI objectUI = new CelestialObjectUI();
                gameUI = new GameUI(objectUI);
                game = new Game(gameUI);  
                //Load game for ui use
                gameUI.loadGame(game);
                GameMenu gameMenu = new GameMenu(game, objectUI);
                layout.setLeft(gameMenu.getScene());                       
                layout.setCenter(gameUI.getScene());          
                game.startUpdate();
            } catch (Exception ex) {
                Logger.getLogger(GravitysimulatorUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        loadSaveButton.setOnAction((event) -> {
            try {
                CelestialObjectUI objectUI = new CelestialObjectUI();
                gameUI = new GameUI(objectUI);
                game = new Game(gameUI);
                //Load game for ui use
                gameUI.loadGame(game);
                GameMenu gameMenu = new GameMenu(game, objectUI);                                
                ArrayList<CelestialObject> objects = (ArrayList<CelestialObject>) load.loadGame();
                game.setObjects(objects);
                layout.setLeft(gameMenu.getScene()); 
                layout.setCenter(gameUI.getScene());
                game.startUpdate();
            } catch (Exception ex) {
                Logger.getLogger(GravitysimulatorUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        returnButton.setOnAction((event) -> {
            if (game != null ){
                game.stop();
            }    
            layout.setLeft(null);
            layout.setCenter(menu);
        });
        
        Scene view = new Scene(layout, 1200, 800);
              
	window.setScene(view);
	window.show();
    }
    
}