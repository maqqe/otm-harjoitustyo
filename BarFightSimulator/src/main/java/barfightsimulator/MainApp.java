package barfightsimulator;

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.LocalizableObject;
import barfightsimulator.domain.Player;
import barfightsimulator.ui.Ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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

public class MainApp {
    
    public static void main(String[] args) {
//        launch(args);

        Player player = new Player(5, 5);
        Enemy e1 = new Enemy(0, 0, player);
        Enemy e2 = new Enemy(15, 15, player);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);
        List<LocalizableObject> objects = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        Ui ui = new Ui(player, objects, enemies, reader);
        ui.start();
        
    }

}
