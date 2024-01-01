import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.beans.property.SimpleStringProperty;

public class CourierManagementSystem extends Application{

    private ObservableList<OwnerInfo> list = FXCollections.observableArrayList();
    private TableView<OwnerInfo> tableView = new TableView<>();
    @FXML
    private TableColumn<OwnerInfo, Integer> id;
    @FXML
    private TableColumn<OwnerInfo, String> owner_name;
    @FXML
    private TableColumn<OwnerInfo, String> contact_number;
    @FXML
    private TableColumn<OwnerInfo, String> building_number;
    @FXML
    private TableColumn<OwnerInfo, String> unit_number;
    @FXML
    private TableColumn<OwnerInfo, String> room_number;
    @FXML
    private TableColumn<OwnerInfo, String> notes;
    @FXML
    private TableColumn<OwnerInfo, Integer> is_submit_management_fee;
    @FXML
    private TableColumn<OwnerInfo, Integer> is_submit_warn_fee;
    @FXML
    private TableColumn<OwnerInfo, Float> use_water_num;
    @FXML
    private TableColumn<OwnerInfo, Float> use_elec_num;
    @FXML
    private TableColumn<OwnerInfo, Timestamp> created_at;
    @FXML
    private TableColumn<OwnerInfo, Integer> is_own_water;
    @FXML
    private TableColumn<OwnerInfo, Integer> is_own_elec;

    private void initCellVal() {
        id.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("owner_id"));
        owner_name.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("owner_name"));
        contact_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("contact_number"));
        building_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("building_number"));
        unit_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("unit_number"));
        room_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("room_number"));
        notes.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("notes"));
        is_submit_management_fee.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_submit_management_fee"));
        is_submit_warn_fee.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_submit_warn_fee"));
        use_water_num.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Float>("use_water_num"));
        use_elec_num.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Float>("use_elec_num"));
        created_at.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Timestamp>("created_at"));
        is_own_water.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_own_water"));
        is_own_elec.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_own_elec"));
    }
    private void initData() {
        String url = "jdbc:mysql://localhost:3306/task";//task：数据库名
        String user = "root"; // 用自己的数据库用户名
        String password = "123456";//用自己数据库的密码

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立数据库连接
            Connection conn = DriverManager.getConnection(url, user, password);
            // 创建Statement对象
            Statement stmt = conn.createStatement();
            // 创建数据库表
            String queryTable = "SELECT * FROM community_owners";
            ResultSet rs = stmt.executeQuery(queryTable);
            //初始化数据
            while (rs.next()) {
                // OwnerInfo info =
                int owner_id = rs.getInt("owner_id");
                String owner_name = rs.getString("owner_name");
                String contact_number = rs.getString("contact_number");
                String building_number = rs.getString("building_number");
                String unit_number = rs.getString("unit_number");
                String room_number = rs.getString("room_number");
                String notes = rs.getString("notes");
                int is_submit_management_fee = rs.getInt("is_submit_management_fee");
                int is_submit_warn_fee = rs.getInt("is_submit_warn_fee");
                float use_water_num = rs.getFloat("use_water_num");
                float use_elec_num = rs.getFloat("use_elec_num");
                Timestamp created_at = rs.getTimestamp("created_at");
                String time = created_at.toString();
                int is_own_water = rs.getInt("is_own_water");
                int is_own_elec = rs.getInt("is_own_elec");
                OwnerInfo info = new OwnerInfo(owner_id, owner_name, contact_number, building_number, unit_number, room_number, notes, is_submit_management_fee, is_submit_warn_fee, use_water_num, getWaterFee(use_water_num), use_elec_num, getElecFee(use_elec_num), is_own_water, is_own_elec, time, false);
                list.add(info);
                // System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Position: " + rs.getString("position"));
            }
            // 关闭所有资源
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initCellVal();
        initData();
        tableView.setItems(list);
        for (OwnerInfo ownerInfo: list) {
            System.out.println(ownerInfo);
        }
        VBox root = new VBox(20); // 垂直布局
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("功能升级中...");
        menuBar.getMenus().addAll(fileMenu);
        root.setPadding(new Insets(10, 10, 10, 10));

        // ----------------------------------------
        GridPane editAreaBottom = new GridPane();
        // editAreaBottom.setGridLinesVisible(true);
        editAreaBottom.setVgap(20);
        editAreaBottom.setHgap(20);

        editAreaBottom.add(new Label("业主名："), 0, 1);
        TextField addName = new TextField();
        addName.setPromptText("业主名");
        editAreaBottom.add(addName, 1, 1);

        editAreaBottom.add(new Label("联系电话："), 2, 1);
        TextField addPhone = new TextField();
        addPhone.setPromptText("联系电话");
        editAreaBottom.add(addPhone, 3, 1);

        editAreaBottom.add(new Label("楼号："), 4, 1);
        TextField addBuilding = new TextField();
        addBuilding.setPromptText("楼号");
        editAreaBottom.add(addBuilding, 5, 1);

        editAreaBottom.add(new Label("单元号："), 0, 2);
        TextField addUnit = new TextField();
        addUnit.setPromptText("单元号");
        editAreaBottom.add(addUnit, 1, 2);

        editAreaBottom.add(new Label("房号："), 2, 2);
        TextField addRoom = new TextField();
        addRoom.setPromptText("房号");
        editAreaBottom.add(addRoom, 3, 2);

        editAreaBottom.add(new Label("物业费已交："), 4, 2);
        TextField addISMF = new TextField();
        addISMF.setPromptText("物业费已交");
        editAreaBottom.add(addISMF, 5, 2);

        editAreaBottom.add(new Label("取暖费已交："), 0, 3);
        TextField addISWF = new TextField();
        addISWF.setPromptText("取暖费已交");
        editAreaBottom.add(addISWF, 1, 3);

        editAreaBottom.add(new Label("用水量："), 2, 3);
        TextField addUWN = new TextField();
        addUWN.setPromptText("用水量");
        editAreaBottom.add(addUWN, 3, 3);

        editAreaBottom.add(new Label("用电量："), 4, 3);
        TextField addUEN = new TextField();
        addUEN.setPromptText("用电量");
        editAreaBottom.add(addUEN, 5, 3);

        editAreaBottom.add(new Label("创建时间："), 0, 4);
        TextField addCreate = new TextField();
        addCreate.setPromptText("创建时间");
        editAreaBottom.add(addCreate, 1, 4);

        editAreaBottom.add(new Label("说明："), 2, 4);
        TextField addNotes = new TextField();
        addNotes.setPromptText("说明");
        editAreaBottom.add(addNotes, 3, 4);

        editAreaBottom.add(new Label("ID："), 4, 4);
        Label addID = new Label("4384375834");
        editAreaBottom.add(addID, 5, 4);

        editAreaBottom.add(new Label("已缴电费："), 0, 5);
        TextField addIOE = new TextField();
        addIOE.setPromptText("0");
        editAreaBottom.add(addIOE, 1, 5);

        editAreaBottom.add(new Label("已缴水费："), 2, 5);
        TextField addIOW = new TextField();
        addIOW.setPromptText("0");
        editAreaBottom.add(addIOW, 3, 5);

        editAreaBottom.add(new Label("已欠水费："), 4, 5);
        Label addOW = new Label();
        addOW.setText("0");
        addOW.setStyle("-fx-text-fill: red;");
        editAreaBottom.add(addOW, 5, 5);

        editAreaBottom.add(new Label("已欠电费："), 6, 5);
        Label addOE = new Label();
        addOE.setText("0");
        addOE.setStyle("-fx-text-fill: red;");
        editAreaBottom.add(addOE, 7, 5);

        Button query = new Button("查询");
        Button add = new Button("添加");
        Button modify = new Button("修改");
        Button delete = new Button("删除");
        Button save = new Button("保存");
        editAreaBottom.add(add, 6, 1);
        editAreaBottom.add(modify, 7, 1);
        editAreaBottom.add(delete,6, 2);
        editAreaBottom.add(save,7, 2);
        editAreaBottom.add(query,6, 3);

        // 快递信息显示区域



        // 将所有组件添加到布局容器
        root.getChildren().addAll(menuBar, editAreaBottom, tableView);


        //功能区域
        query.setOnAction(e->searchOwner(addBuilding.getText(), addUnit.getText(), addPhone.getText(), addName.getText()));
        add.setOnAction(e->addOwner(addName.getText(), addPhone.getText(), addBuilding.getText(), addUnit.getText(), addRoom.getText(), addNotes.getText()));
        modify.setOnAction(e->modifyOwner(addID.getText(), addName.getText(), addPhone.getText(), addBuilding.getText(), addUnit.getText(), addRoom.getText(), addNotes.getText(), addISMF.getText(), addISWF.getText(), addIOW.getText(), addIOE.getText()));
        delete.setOnAction(e->deleteOwner(addID.getText()));
        save.setOnAction(e->saveOwner());

        // 设置场景和显示舞台
        Scene scene = new Scene(root, 1100, 800);
        primaryStage.setTitle("小区物业管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private float getElecFee(float fee) {
        if (fee < 240) return (float)(fee * 0.56);
        if (fee < 400) return (float)(fee * 0.61);
        return (float) (fee * 0.62);
    }

    private float getWaterFee(float fee) {
        return (float) (fee * 2.8);
    }

    private void searchOwner(String building, String unit, String phone, String name) {
        // 查询业主信息

    }

    private void addOwner(String name, String phone, String building, String unit, String room, String notes) {
        // 添加业主信息
    }

    private void modifyOwner(String id, String name, String phone, String building, String unit, String room, String notes, String is_submit_management_fee, String is_submit_warn_fee, String is_own_water, String is_own_elec) {
        // 修改业主信息
    }

    private void deleteOwner(String id) {
        // 删除业主信息
        System.out.println(id);
    }

    private void saveOwner() {
        // 保存业主信息
    }

    public static void main(String[] args) {
        launch(args);
    }

    // 快递信息类（示例）
    public class OwnerInfo {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty building;
        private final SimpleStringProperty unit;
        private final SimpleStringProperty room;
        private final SimpleStringProperty notes;

        public OwnerInfo(int id, String name, String phone, String building, String unit, String room, String notes, int is_submit_management_fee, int is_submit_warn_fee, float water, float water_fee, float electricity, float electricity_fee, int is_own_water, int is_own_elec, String time, boolean isDelete) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.building = new SimpleStringProperty(building);
            this.unit = new SimpleStringProperty(unit);
            this.room = new SimpleStringProperty(room);
            this.notes = new SimpleStringProperty(notes);
            this.is_submit_management_fee = new SimpleIntegerProperty(is_submit_management_fee);
            this.is_submit_warn_fee = new SimpleIntegerProperty(is_submit_warn_fee);
            this.water = new SimpleFloatProperty(water);
            this.water_fee = new SimpleFloatProperty(water_fee);
            this.electricity = new SimpleFloatProperty(electricity);
            this.electricity_fee = new SimpleFloatProperty(electricity_fee);
            this.is_own_water = new SimpleIntegerProperty(is_own_water);
            this.is_own_elec = new SimpleIntegerProperty(is_own_elec);
            this.time = new SimpleStringProperty(time);
            this.isDelete = new SimpleBooleanProperty(isDelete);
        }

        public int getId() {
            return Integer.valueOf(id.get());
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getPhone() {
            return phone.get();
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public String getBuilding() {
            return building.get();
        }

        public void setBuilding(String building) {
            this.building.set(building);
        }

        public String getUnit() {
            return unit.get();
        }

        public void setUnit(String unit) {
            this.unit.set(unit);
        }

        public String getRoom() {
            return room.get();
        }

        public void setRoom(String room) {
            this.room.set(room);
        }

        public String getNotes() {
            return notes.get();
        }

        public void setNotes(String notes) {
            this.notes.set(notes);
        }

        public int getIs_submit_management_fee() {
            return is_submit_management_fee.get();
        }

        public void setIs_submit_management_fee(int is_submit_management_fee) {
            this.is_submit_management_fee.set(is_submit_management_fee);
        }

        public int getIs_submit_warn_fee() {
            return is_submit_warn_fee.get();
        }

        public void setIs_submit_warn_fee(int is_submit_warn_fee) {
            this.is_submit_warn_fee.set(is_submit_warn_fee);
        }

        public float getWater() {
            return water.get();
        }

        public void setWater(float water) {
            this.water.set(water);
        }

        public float getWater_fee() {
            return water_fee.get();
        }

        public void setWater_fee(float water_fee) {
            this.water_fee.set(water_fee);
        }

        public float getElectricity() {
            return electricity.get();
        }

        public void setElectricity(float electricity) {
            this.electricity.set(electricity);
        }

        public float getElectricity_fee() {
            return electricity_fee.get();
        }

        public void setElectricity_fee(float electricity_fee) {
            this.electricity_fee.set(electricity_fee);
        }

        public int getIs_own_water() {
            return is_own_water.get();
        }

        public void setIs_own_water(short is_own_water) {
            this.is_own_water.set(is_own_water);
        }

        public int getIs_own_elec() {
            return is_own_elec.get();
        }

        public void setIs_own_elec(short is_own_elec) {
            this.is_own_elec.set(is_own_elec);
        }

        public String getTime() {
            return time.get();
        }

        public void setTime(String  time) {
            this.time.set(time);
        }

        private final SimpleIntegerProperty is_submit_management_fee;
        private final SimpleIntegerProperty is_submit_warn_fee;
        private final SimpleFloatProperty water;
        private final SimpleFloatProperty water_fee;
        private final SimpleFloatProperty electricity;
        private final SimpleFloatProperty electricity_fee;
        private final SimpleIntegerProperty is_own_water;
        private final SimpleIntegerProperty is_own_elec;
        private final SimpleStringProperty time;
        private final SimpleBooleanProperty isDelete;
        public void setIsDelete(boolean b) {
            this.isDelete.set(b);
        }
        public boolean getIsDelete() {
            return isDelete.get();
        }

        @Override
        public String toString() {
            return "OwnerInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", building='" + building + '\'' +
                    ", unit='" + unit + '\'' +
                    ", room='" + room + '\'' +
                    ", notes='" + notes + '\'' +
                    ", is_submit_management_fee=" + is_submit_management_fee +
                    ", is_submit_warn_fee=" + is_submit_warn_fee +
                    ", water=" + water +
                    ", water_fee=" + water_fee +
                    ", electricity=" + electricity +
                    ", electricity_fee=" + electricity_fee +
                    ", is_own_water=" + is_own_water +
                    ", is_own_elec=" + is_own_elec +
                    ", time=" + time +
                    ", isDelete=" + isDelete +
                    '}';
        }
    }
}
