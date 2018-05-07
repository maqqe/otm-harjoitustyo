package barfightsimulator;

import barfightsimulator.dao.LocalizableObjectDao;
import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
import barfightsimulator.domain.LocalizableObject;
import barfightsimulator.domain.Player;
import barfightsimulator.ui.MissionLoader;
import barfightsimulator.ui.Ui;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Markus
 */
public class MainApp extends Application {
    
    private MissionLoader loader;
    
    @Override
    public void init() throws Exception {
        LocalizableObjectDao dao = new LocalizableObjectDao();
        loader = new MissionLoader(dao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox vbox = new VBox();
        vbox.getChildren().add(0, new Text(new String(loader.drawName())));
        vbox.getChildren().add(1, new Text(new String(loader.drawName())));
        GridPane pane = new GridPane();
        ColumnConstraints constraints = new ColumnConstraints(15);
        for (int i = 0; i < 20; i++) {
            pane.getColumnConstraints().add(constraints);
        }
        vbox.getChildren().add(2, pane);
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                pane.add(new Text("."), x, y);
            }
        }
        pane.add(new Text("@"), loader.getPlayer().getX(), loader.getPlayer().getY());
        
        Scene scene = new Scene(vbox,800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

//public class MainApp extends Application {

//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
//    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */

//public class MainApp {
    
    public static void main(String[] args) throws Exception {
        launch(args);
            
//        Scanner reader = new Scanner(System.in);
//        LocalizableObjectDao dao = new LocalizableObjectDao();
//        MissionLoader ml = new MissionLoader(dao);
//        Ui ui = new Ui(reader, ml);
//        ui.start();
    }
}
