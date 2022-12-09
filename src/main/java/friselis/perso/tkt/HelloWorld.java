package friselis.perso.tkt;

import io.github.palexdev.materialfx.builders.control.ButtonBuilder;
import io.github.palexdev.materialfx.enums.ButtonType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.minecraft.client.MinecraftClient;

public class HelloWorld extends Application {
    public static Stage pStage;
    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        ButtonBuilder b = new ButtonBuilder();
        b.setButtonType(ButtonType.RAISED);
        b.setText("Say 'Hello World'");
        b.setOnAction(event -> {
            if (MinecraftClient.getInstance().player != null) {
                MinecraftClient.getInstance().player.setPosition(0, 10000, 0);
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(b.getNode());

        Scene scene = new Scene(root, 1000, 500);

        Platform.setImplicitExit(false);

        primaryStage.setOnCloseRequest(Event::consume);

        primaryStage.setTitle("Tkt client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void run() {
        new Thread(Application::launch).start();
    }
}