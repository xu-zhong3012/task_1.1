import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 主布局
        BorderPane root = new BorderPane();

        // 顶部菜单
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().addAll(fileMenu);
        root.setTop(menuBar);

        // 左侧导航栏
        VBox leftPanel = new VBox(10); // 间距为10
        leftPanel.getChildren().addAll(new Button("快递查询"), new Button("添加快递"), new Button("编辑快递"));
        root.setLeft(leftPanel);

        // 中心内容区
        StackPane centerContent = new StackPane();
        centerContent.getChildren().add(new Label("欢迎使用快递管理系统"));
        root.setCenter(centerContent);

        // 底部状态栏
        Label statusLabel = new Label("状态: 准备就绪");
        root.setBottom(statusLabel);

        // 设置场景和舞台
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("快递管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
