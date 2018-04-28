package barfightsimulator;

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
import barfightsimulator.domain.LocalizableObject;
import barfightsimulator.domain.Player;
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
    
    public static void main(String[] args) throws Exception {
//        launch(args);
        
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String names = properties.getProperty("enemyNameFile");
        
        List<String> nameList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(names));
            while (reader.hasNext()) {
                String name = reader.next();
                if (name.length() > 20) {
                    nameList.add(name.substring(0, 20));
                } else {
                    nameList.add(name);
                }                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e + "\nAll enemies will be named Ilja");
            nameList.add("Ilja");
        }
        
        Random rng = new Random();
        
        String enemyLocations = properties.getProperty("enemyLocationsFile");        
        List<String> enemyLocationList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(enemyLocations));
            while (reader.hasNext()) {
                enemyLocationList.add(reader.next());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        String itemLocations = properties.getProperty("itemLocationsFile");
        List<String> itemLocationList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(itemLocations));
            while (reader.hasNext()) {
                itemLocationList.add(reader.next());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        Scanner reader = new Scanner(System.in);
        List<LocalizableObject> o = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        
        enemyLocationList.stream().forEach(System.out::println);
        
        for (int i = 0; i < enemyLocationList.size(); i++) {
            System.out.println("ROUND " + (i + 1) + "\n");
            Player player = new Player(5, 5);
            String[] allEnemyCoordinates = enemyLocationList.get(i).split(";");
            String[] allItemCoordinates = itemLocationList.get(i).split(";");
            
            for (int j = 0; j < allEnemyCoordinates.length; j++) {
                String[] enemyCoordinates = allEnemyCoordinates[j].split(",");
                Enemy enemy = new Enemy(Integer.parseInt(enemyCoordinates[0]), Integer.parseInt(enemyCoordinates[1]), player, drawName(nameList, rng));
                enemies.add(enemy);                              
            }
            
            for (int k = 0; k < allItemCoordinates.length; k++) {
                if (!allItemCoordinates[k].equals("noItems")) {
                    String[] itemCoordinates = allItemCoordinates[k].split(",");
                    Item item = new Item(Integer.parseInt(itemCoordinates[0]), Integer.parseInt(itemCoordinates[1]), Itemtype.valueOf(itemCoordinates[2]));
                    items.add(item);
                }  
            }           
            
            Ui ui = new Ui(player, o, enemies, reader, items);
            ui.start();
            enemies.clear();
            items.clear();
        }
        
//        Player player = new Player(5, 5);
//        Enemy e1 = new Enemy(0, 0, player, drawName(nameList, rng));
//        Enemy e2 = new Enemy(15, 15, player, drawName(nameList, rng));
//        List<Enemy> enemies = new ArrayList<>();
//        enemies.add(e1);
//        enemies.add(e2);
//        List<Item> items = new ArrayList<>();
//        Item i1 = new Item(4, 4, Itemtype.KNIFE);
//        Item i2 = new Item(5, 6, Itemtype.BEER);
//        items.add(i1);
//        items.add(i2);
//        List<LocalizableObject> objects = new ArrayList<>();
//        Scanner reader = new Scanner(System.in);
//        Ui ui = new Ui(player, objects, enemies, reader, items);
//        ui.start();
        
    }
    
    public static String drawName(List<String> names, Random rng) {
        return names.get(rng.nextInt(names.size()));
    }

}
