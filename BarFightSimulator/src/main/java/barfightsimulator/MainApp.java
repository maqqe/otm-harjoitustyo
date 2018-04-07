package barfightsimulator;

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Player;
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
        System.out.println("hi");
        Player p = new Player(0, 0);
        System.out.println(p);
        p.move(1, 0);
        System.out.println(p);
        
        Enemy e = new Enemy(3, 8, p);
        System.out.println(e);
        e.move(p.x, p.y);
        System.out.println(e);
        e.move(p.x, p.y);
        System.out.println(e);
        e.move(p.x, p.y);
        System.out.println(e);
        e.move(p.x, p.y);
        System.out.println(e);
    }

}
