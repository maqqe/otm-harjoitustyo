package barfightsimulator;

import barfightsimulator.dao.LocalizableObjectDao;
import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
import barfightsimulator.ui.MissionLoader;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Markus
 */
public class MainApp extends Application {
    
    private Scene gameCompletedScene;
    private Scene gameOverScene;
    private MissionLoader loader;
    
    
    @Override
    public void init() throws Exception {
        LocalizableObjectDao dao = new LocalizableObjectDao("config.properties");
        loader = new MissionLoader(dao);
        loader.loadMission(1);
        
        
        Label gameOverLabel = new Label("Game over, chump");
        Pane gameOverPane = new Pane(gameOverLabel);        
        this.gameOverScene = new Scene(gameOverPane, 800, 600);
        
        Label gameCompletedLabel = new Label("All missions completed, congratulations!");
        Pane gameCompletedPane = new Pane(gameCompletedLabel);
        this.gameCompletedScene = new Scene(gameCompletedPane, 800, 600);
    }
    
    public void drawLocalizableObjects(GridPane pane) {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                pane.add(new Text("."), x, y);
            }
        }
        pane.add(new Text("@"), loader.getPlayer().getX(), loader.getPlayer().getY());
        for (Enemy e : loader.getEnemies()) {
            if (e.isAlive()) {
                if (e.getHitpoints() == 2) {
                    pane.add(new Text("e"), e.getX(), e.getY());
                } else {
                    Text t = new Text("e");
                    t.setFill(Color.RED);
                    pane.add(t, e.getX(), e.getY());
                }
                
            }
                    
        }
        
        if (loader.getItems()!= null) {
            for (Item i : loader.getItems()) {
                if (!i.isEquipped()) {
                        
                    if (i.getItemtype() == Itemtype.BEER) {
                        pane.add(new Text("b"), i.getX(), i.getY()); 
                    } else if (i.getItemtype() == Itemtype.KNIFE) {
                        pane.add(new Text("k"), i.getX(), i.getY());
                    }
                }                
            }
        }
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane gamePane = new GridPane();
        GridPane pane = new GridPane();
        ColumnConstraints constraints = new ColumnConstraints(15);
        HBox commandBox = new HBox();
        Label commandText = new Label("Command: ");
        TextField commandField = new TextField();
        commandBox.getChildren().addAll(commandText, commandField);
        Label playerStatus = new Label();
        Label playerItem = new Label();
        TextArea commandTable = new TextArea("Commands:\n"
                + "z - move down and left\n"
                + "x - move down\n"
                + "c - move down and right\n"
                + "a - mova left\n"
                + "s - hold at current position\n"
                + "d - move right\n"
                + "q - move up and left\n"
                + "w - move up\n"
                + "e - move up and right\n"
                + "u - use item\n\n\n"
                + "@ - you\n"
                + "e - enemy\n"
                + "k - knife\n"
                + "b - beer");
        commandTable.setEditable(false);
        TextArea instructions = new TextArea("Your objective is to kill all the bad guys.\n"
                + "You attack an enemy by trying to move into the same tile.\n"
                + "You move by typing an appropriate number into command textfield\n"
                + "and pressing the enter key.\n"
                + "Enemies have two hitpoints.\n"
                + "Wounded enemies are shown in red.\n"
                + "You can pick up one item at maximum.\n"
                + "Using beer increases your hitpoints by 1.\n"
                + "Knife instakills enemies.");
        instructions.setEditable(false);
                
        
        
        for (int i = 0; i < 20; i++) {
            pane.getColumnConstraints().add(constraints);
        }
        
        drawLocalizableObjects(pane);
        playerStatus.setText("Your hit points: " + loader.getPlayer().getHitpoints());
        playerItem.setText("You hold no item");
        
        gamePane.setPadding(new Insets(10, 10, 10, 10));
        gamePane.setVgap(10);
        gamePane.setHgap(10);
        
        gamePane.add(pane, 0, 0);
        gamePane.add(commandBox, 0, 1);
        gamePane.add(playerStatus, 0, 2);
        gamePane.add(playerItem, 0, 3);
        gamePane.add(commandTable, 1, 0);
        gamePane.add(instructions, 1, 1);
        
        
        commandField.setOnAction(e -> {
            
            pane.getChildren().clear();
            loader.playTurn(commandField.getText());
            
            if (!loader.isOngoing()) {
                primaryStage.setScene(gameOverScene);
                primaryStage.show();
                return;
            }
            
            if (loader.isAllMissionsCompleted()) {
                primaryStage.setScene(gameCompletedScene);
                primaryStage.show();
            }
            
            drawLocalizableObjects(pane);
            commandField.clear();
            playerStatus.setText("Your hit points: " + loader.getPlayer().getHitpoints());
            if (loader.getPlayer().getItem() != null) {
                playerItem.setText("You hold a " + loader.getPlayer().getItem().getItemtype().toString());
            } else {
                playerItem.setText("You hold no item");
            }
        });
        
        Scene scene = new Scene(gamePane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
