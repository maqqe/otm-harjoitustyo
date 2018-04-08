package barfightsimulator;

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.LocalizableObject;
import barfightsimulator.domain.Player;
import java.util.ArrayList;
import java.util.List;
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
        
        Player p = new Player(0, 0);
        
        Enemy e = new Enemy(6, 3, p);
        
        List<LocalizableObject> objects = new ArrayList<>();
        
        objects.add(e);
        objects.add(p);
        
        List<LocalizableObject> pObjects = p.searchAdjacentSquares(objects);
        
        System.out.println(pObjects.size());
        
        List<LocalizableObject> eObjects = e.searchAdjacentSquares(objects);
        
        System.out.println(eObjects.size());
        
        e.attack();
        System.out.println("hp left   " + p.getHitpoints());
        
        e.chase(p.x, p.y);
        
        System.out.println(e);
        e.chase(p.x, p.y);
        e.attack();
        System.out.println("hp left   " + p.getHitpoints());
        System.out.println(e);
       
        
        e.chase(p.x, p.y);
        System.out.println(e);
        e.attack();
        System.out.println("hp left   " + p.getHitpoints());
        e.chase(p.x, p.y);
        System.out.println(e);
        e.chase(p.x, p.y);
        System.out.println(e);
        e.attack();
        System.out.println("hp left   " + p.getHitpoints());
        e.chase(p.x, p.y);
        System.out.println(e);
        e.chase(p.x, p.y);
        System.out.println(e);
        e.chase(p.x, p.y);
        e.attack();
        System.out.println("hp left   " + p.getHitpoints());
        e.chase(p.x, p.y);
        e.chase(p.x, p.y);
        e.chase(p.x, p.y);
        System.out.println(e);
        e.attack();
        System.out.println("hp left   " + p.getHitpoints());
        
                
        
    }

}
