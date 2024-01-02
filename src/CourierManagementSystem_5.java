import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourierManagementSystem_5 extends Application{

    private ObservableList<OwnerInfo> curList = FXCollections.observableArrayList();
    private List<OwnerInfo> list = new ArrayList<>();
    private TableView<OwnerInfo> tableView = new TableView<>();

    private TextField addName = new TextField();
    private TextField addPhone = new TextField();
    private TextField addBuilding = new TextField();
    private TextField addUnit = new TextField();
    private TextField addRoom = new TextField();
    private Label addOE = new Label();
    private Label addOW = new Label();
    private TextField addIOW = new TextField();
    private TextField addIOE = new TextField();
    private Label addID = new Label();
    private TextField addNotes = new TextField();
    private Label addCreate = new Label();
    private Label addUEN = new Label();
    private Label addUWN = new Label();
    private TextField addISWF = new TextField();
    private TextField addISMF = new TextField();
    private TextField addIDCard = new TextField();


    private void initCellVal() {

        TableColumn<OwnerInfo, Integer> id = new TableColumn<>("id");
        TableColumn<OwnerInfo, String> owner_name = new TableColumn<>("name");
        TableColumn<OwnerInfo, String> contact_number = new TableColumn<>("phone");
        TableColumn<OwnerInfo, String> building_number= new TableColumn<>("building");
        TableColumn<OwnerInfo, String> unit_number = new TableColumn<>("unit");
        TableColumn<OwnerInfo, String> room_number = new TableColumn<>("room");
        TableColumn<OwnerInfo, String> notes = new TableColumn<>("notes");
        TableColumn<OwnerInfo, Integer> is_submit_management_fee = new TableColumn<>("is_submit_management_fee");
        TableColumn<OwnerInfo, Integer> is_submit_warn_fee = new TableColumn<>("is_submit_warn_fee");
        TableColumn<OwnerInfo, Float> use_water_num = new TableColumn<>("water");
        TableColumn<OwnerInfo, Float> use_elec_num = new TableColumn<>("electricity");
        TableColumn<OwnerInfo, Timestamp> created_at = new TableColumn<>("time");
        TableColumn<OwnerInfo, Integer> is_own_water = new TableColumn<>("is_own_water");
        TableColumn<OwnerInfo, Integer> is_own_elec = new TableColumn<>("is_own_elec");
        TableColumn<OwnerInfo, Integer> water_fee = new TableColumn<>("water_fee");
        TableColumn<OwnerInfo, Integer> electricity_fee = new TableColumn<>("electricity_fee");
        TableColumn<OwnerInfo, String> IDCard = new TableColumn<>("IDCard");

        id.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("id"));
        id.setText("用户ID");
        owner_name.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("name"));
        owner_name.setText("业主名称");
        contact_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("phone"));
        contact_number.setText("联系电话");
        building_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("building"));
        building_number.setText("楼号");
        unit_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("unit"));
        unit_number.setText("单元号");
        room_number.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("room"));
        room_number.setText("房号");
        notes.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("notes"));
        notes.setText("标注");
        is_submit_management_fee.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_submit_management_fee"));
        is_submit_management_fee.setText("是否交物业费");
        is_submit_warn_fee.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_submit_warn_fee"));
        is_submit_warn_fee.setText("是否交取暖费");
        use_water_num.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Float>("water"));
        use_water_num.setText("用水量");
        use_elec_num.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Float>("electricity"));
        use_elec_num.setText("用电量");
        created_at.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Timestamp>("time"));
        created_at.setText("创建时间");
        is_own_water.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_own_water"));
        is_own_water.setText("是否提交水费");
        is_own_elec.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("is_own_elec"));
        is_own_elec.setText("是否提交电费");
        water_fee.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("water_fee"));
        water_fee.setText("当月水费");
        electricity_fee.setCellValueFactory(new PropertyValueFactory<OwnerInfo, Integer>("electricity_fee"));
        electricity_fee.setText("当月电费");
        IDCard.setCellValueFactory(new PropertyValueFactory<OwnerInfo, String>("IDCard"));
        IDCard.setText("身份证号码");

        tableView.getColumns().addAll(id, IDCard, owner_name, contact_number, building_number, unit_number, room_number, notes, is_submit_management_fee, is_submit_warn_fee, use_water_num, use_elec_num, created_at, is_own_water, is_own_elec,water_fee, electricity_fee);
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
                String IDCard = rs.getString("IDCard");
                OwnerInfo info = new OwnerInfo(owner_id, owner_name, contact_number, building_number, unit_number, room_number, notes, is_submit_management_fee, is_submit_warn_fee, use_water_num, getWaterFee(use_water_num), use_elec_num, getElecFee(use_elec_num), is_own_water, is_own_elec, time, false, false, false, IDCard);
                list.add(info);
                curList.add(info);
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
        tableView.setItems(curList);
//        for (OwnerInfo ownerInfo: list) {
//            System.out.println(ownerInfo);
//        }
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
        addName.setPromptText("业主名");
        editAreaBottom.add(addName, 1, 1);

        editAreaBottom.add(new Label("联系电话："), 2, 1);
        addPhone.setPromptText("联系电话");
        editAreaBottom.add(addPhone, 3, 1);

        editAreaBottom.add(new Label("楼号："), 4, 1);
        addBuilding.setPromptText("楼号");
        editAreaBottom.add(addBuilding, 5, 1);

        editAreaBottom.add(new Label("单元号："), 0, 2);
        addUnit.setPromptText("单元号");
        editAreaBottom.add(addUnit, 1, 2);

        editAreaBottom.add(new Label("房号："), 2, 2);
        addRoom.setPromptText("房号");
        editAreaBottom.add(addRoom, 3, 2);

        editAreaBottom.add(new Label("物业费已交："), 4, 2);
        addISMF.setPromptText("物业费已交");
        editAreaBottom.add(addISMF, 5, 2);

        editAreaBottom.add(new Label("取暖费已交："), 0, 3);
        addISWF.setPromptText("取暖费已交");
        editAreaBottom.add(addISWF, 1, 3);

        editAreaBottom.add(new Label("用水量："), 2, 3);
        addUWN.setText("0t");
        editAreaBottom.add(addUWN, 3, 3);

        editAreaBottom.add(new Label("用电量："), 4, 3);
        addUEN.setText("0°");
        editAreaBottom.add(addUEN, 5, 3);

        editAreaBottom.add(new Label("创建时间："), 0, 4);
        addCreate.setText("2023-1-2 13:10:06");
        editAreaBottom.add(addCreate, 1, 4);

        editAreaBottom.add(new Label("说明："), 2, 4);
        addNotes.setPromptText("说明");
        editAreaBottom.add(addNotes, 3, 4);

        editAreaBottom.add(new Label("ID："), 4, 4);
        addID.setText("4384375834");
        editAreaBottom.add(addID, 5, 4);

        editAreaBottom.add(new Label("是否已缴电费："), 0, 5);
        addIOE.setPromptText("0");
        editAreaBottom.add(addIOE, 1, 5);

        editAreaBottom.add(new Label("是否已缴水费："), 2, 5);
        addIOW.setPromptText("0");
        editAreaBottom.add(addIOW, 3, 5);

        editAreaBottom.add(new Label("已欠水费："), 4, 5);
        addOW.setText("0");
        addOW.setStyle("-fx-text-fill: red;");
        editAreaBottom.add(addOW, 5, 5);

        editAreaBottom.add(new Label("已欠电费："), 6, 5);
        addOE.setText("0");
        addOE.setStyle("-fx-text-fill: red;");
        editAreaBottom.add(addOE, 7, 5);

        editAreaBottom.add(new Label("身份证号码："), 0, 6);
        addIDCard.setText("0");
        editAreaBottom.add(addIDCard, 1, 6);

        Button query = new Button("查询");
        Button queryUnPaids = new Button("查询未缴费");
        Button add = new Button("添加");
        Button modify = new Button("修改");
        Button delete = new Button("删除");
        Button save = new Button("保存");
        editAreaBottom.add(add, 6, 1);
        editAreaBottom.add(modify, 7, 1);
        editAreaBottom.add(delete,6, 2);
        editAreaBottom.add(save,7, 2);
        editAreaBottom.add(query,6, 3);
        editAreaBottom.add(queryUnPaids,7, 3);

        // 快递信息显示区域



        // 将所有组件添加到布局容器
        root.getChildren().addAll(menuBar, editAreaBottom, tableView);


        //功能区域
        query.setOnAction(e->searchOwner());
        queryUnPaids.setOnAction(e->searchUnPaidOwners());
        add.setOnAction(e->addOwner());
        modify.setOnAction(e->modifyOwner());
        delete.setOnAction(e->deleteOwner());
        save.setOnAction(e->saveOwners());
        tableView.setRowFactory(e->OnClickMouseListener());



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

    private TableRow<OwnerInfo> OnClickMouseListener() {
        TableRow<OwnerInfo> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                OwnerInfo info = row.getItem();
                // 处理鼠标点击后的OwnerInfo对象
                addID.setText("" + info.getId());
                addName.setText(info.getName());
                addPhone.setText(info.getPhone());
                addBuilding.setText(info.getBuilding());
                addUnit.setText(info.getUnit());
                addRoom.setText(info.getRoom());
                addISMF.setText("" + info.getIs_submit_management_fee());
                addISWF.setText("" + info.getIs_submit_warn_fee());
                addUWN.setText("" + info.getWater());
                addUEN.setText("" + info.getElectricity());
                addCreate.setText(info.getTime());
                addNotes.setText(info.getNotes());
                addIOE.setText("" + info.getIs_own_elec());
                addIOW.setText("" + info.getIs_own_water());
                addOW.setText("" + info.getWater_fee());
                addOE.setText("" + info.getElectricity_fee());
                addIDCard.setText(info.getIDCard());
            }
        });
        return row;
    }

    private void searchOwner() {
        // 查询业主信息
        String build = addBuilding.getText();
        String unit = addUnit.getText();
        String phone = addPhone.getText();
        String name = addName.getText();
        curList.clear();
        for (OwnerInfo o : list) {
            if (o.getBuilding().equals(build) || o.getUnit().equals(unit) || o.getPhone().equals(phone) || o.getName().equals(name)) {
                curList.add(o);
            }
        }
    }

    private void searchUnPaidOwners() {
        curList.clear();
        for (OwnerInfo o : list) {
            if (o.getIsDelete()) continue;
            if (o.getIs_submit_management_fee() == 0 || o.getIs_submit_warn_fee() == 0) {
                curList.add(o);
            }
        }
    }

    private void addOwner() {
        // 添加业主信息
        String name = addName.getText();
        if (name == null || name.equals("")) {
            alert("名字不能为空！", Alert.AlertType.ERROR);
            return;
        }
        String phone = addPhone.getText();
        if (phone == null || phone.equals("")) {
            alert("联系方式不能为空！", Alert.AlertType.ERROR);
            return;
        }
        String building = addBuilding.getText();
        if (building == null || building.equals("")) {
            alert("楼号不能为空！", Alert.AlertType.ERROR);
            return;
        }
        String unit = addUnit.getText();
        if (unit == null || unit.equals("")) {
            alert("单元号不能为空！", Alert.AlertType.ERROR);
            return;
        }
        String room = addRoom.getText();
        if (room == null || room.equals("")) {
            alert("房号不能为空！", Alert.AlertType.ERROR);
            return;
        }
        String notes = addNotes.getText();
        String IDCard = addIDCard.getText();
        OwnerInfo info = new OwnerInfo();
        info.setName(name);
        info.setPhone(phone);
        info.setBuilding(building);
        info.setUnit(unit);
        info.setRoom(room);
        info.setNotes(notes);
        if (!IDCard.equals("")) {
            info.setIDCard(IDCard);
        }
        info.setIsAdd(true);
        list.add(info);
        onRefresh();
        refreshText();
        alert("添加成功！", Alert.AlertType.INFORMATION);
    }

    public void refreshText() {
        addName.setText("");
        addPhone.setText("");
        addBuilding.setText("");
        addUnit.setText("");
        addRoom.setText("");
        addOE.setText("");
        addOW.setText("");
        addIOW.setText("");
        addIOE.setText("");
        addID.setText("");
        addNotes.setText("");
        addCreate.setText("");
        addUEN.setText("");
        addUWN.setText("");
        addISWF.setText("");
        addISMF.setText("");
        addIDCard.setText("");

    }

    public void alert(String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void modifyOwner() {
        // 修改业主信息
        OwnerInfo info = tableView.getSelectionModel().getSelectedItem();
        if (info == null) {
            alert("未选择", Alert.AlertType.WARNING);
            return;
        }
        info.setName(addName.getText());
        info.setPhone(addPhone.getText());
        info.setBuilding(addBuilding.getText());
        info.setUnit(addUnit.getText());
        info.setRoom(addRoom.getText());
        info.setNotes(addNotes.getText());
        info.setIs_submit_management_fee(Integer.valueOf(addISMF.getText()));
        info.setIs_submit_warn_fee(Integer.valueOf(addISWF.getText()));
        int isW = Integer.valueOf(addIOW.getText());
        int isE = Integer.valueOf(addIOE.getText());
        info.setIs_own_water(isW);
        info.setIs_own_elec(isE);
        if (isW != 0) {
            info.setWater(0);
        }
        if (isW != 0) {
            info.setWater(0);
        }
        info.setIDCard(addIDCard.getText());
        info.setIsModify(true);
        int oid = info.getId();
        for (int i = 0; i < list.size(); ++i) {
            OwnerInfo o = list.get(i);
            if (o.getId() == oid) {
                list.set(i, info);
                break;
            }
        }
        alert("修改成功", Alert.AlertType.INFORMATION);
        onRefresh();
        refreshText();
    }

    private void deleteOwner() {
        // 删除业主信息
        OwnerInfo info = tableView.getSelectionModel().getSelectedItem();
        if (info == null) {
            alert("未选择", Alert.AlertType.WARNING);
            return;
        }
        info.setIsDelete(true);
        for (int i = 0; i < list.size(); ++i) {
            OwnerInfo o = list.get(i);
            if (o.getId() == info.getId()) {
                list.set(i, info);
                break;
            }
        }
        onRefresh();
        alert("删除成功！", Alert.AlertType.INFORMATION);
    }

    private void onRefresh() {
        curList.clear();
        for (OwnerInfo ownerInfo: list) {
            if (!ownerInfo.getIsDelete()) {
                curList.add(ownerInfo);
            }
        }
    }

    private void saveOwners() {
        // 保存业主信息
        String url = "jdbc:mysql://localhost:3306/task";//task：数据库名
        String user = "root"; // 用自己的数据库用户名
        String password = "123456";//用自己数据库的密码

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立数据库连接
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            // 执行语句



            try {
                for (int i = 0; i < list.size(); ++i) {
                    OwnerInfo info =  list.get(i);
                    if (info.getIsDelete()) {
                        String sql = "DELETE FROM community_owners WHERE owner_id = ?";
                        try {
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setInt(1, info.getId());
                            pstmt.executeUpdate();
                            pstmt.close();
                        } catch (Exception e) {

                        }
                    } else if (info.getIsAdd()) {
                        String sql = "INSERT INTO community_owners(owner_name, contact_number, building_number, unit_number, room_number, notes, IDCard) values (?, ?, ?, ?, ?, ?, ?)";
                        try {
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, info.getName());
                            pstmt.setString(2, info.getPhone());
                            pstmt.setString(3, info.getBuilding());
                            pstmt.setString(4, info.getUnit());
                            pstmt.setString(5, info.getRoom());
                            pstmt.setString(6, info.getNotes());
                            pstmt.setString(7, info.getIDCard());
                            pstmt.executeUpdate();
                            pstmt.close();
                        } catch (Exception e) {

                        }
                    } else if (info.getIsModify()) {
                        String sql = "UPDATE community_owners SET "
                                + "owner_name=?,"
                                + "contact_number=?,"
                                + "building_number=?,"
                                + "unit_number=?,"
                                + "room_number=?,"
                                + "notes=?,"
                                + "is_submit_management_fee=?,"
                                + "is_submit_warn_fee=?,"
                                + "use_water_num=?,"
                                + "use_elec_num=?,"
                                + "is_own_water=?,"
                                + "is_own_elec=?,"
                                + "IDCard=?"
                                + "WHERE owner_id=?";

                        try {
                            // 设置
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, info.getName());
                            pstmt.setString(2, info.getPhone());
                            pstmt.setString(3, info.getBuilding());
                            pstmt.setString(4, info.getUnit());
                            pstmt.setString(5, info.getRoom());
                            pstmt.setString(6, info.getNotes());
                            pstmt.setInt(7, info.getIs_submit_management_fee());
                            pstmt.setInt(8, info.getIs_submit_warn_fee());
                            pstmt.setFloat(9, info.getWater());
                            pstmt.setFloat(10, info.getElectricity());
                            pstmt.setInt(11, info.getIs_own_water());
                            pstmt.setInt(12, info.getIs_own_elec());
                            pstmt.setString(13, info.getIDCard());
                            pstmt.setInt(14, info.getId());
                            // 执行更新操作
                            int affectedRows = pstmt.executeUpdate();
                            pstmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {

            }
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            alert("保存成功！", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // 快递信息类（示例）
    public class OwnerInfo {
        private SimpleIntegerProperty id;
        private SimpleStringProperty name;
        private SimpleStringProperty phone;
        private SimpleStringProperty building;
        private SimpleStringProperty unit;
        private SimpleStringProperty room;
        private SimpleStringProperty notes;
        private SimpleIntegerProperty is_submit_management_fee;
        private SimpleIntegerProperty is_submit_warn_fee;
        private SimpleFloatProperty water;
        private SimpleFloatProperty water_fee;
        private SimpleFloatProperty electricity;
        private SimpleFloatProperty electricity_fee;
        private SimpleIntegerProperty is_own_water;
        private SimpleIntegerProperty is_own_elec;
        private SimpleStringProperty time;
        private SimpleStringProperty IDCard;
        private boolean isDelete;
        private boolean isModify;
        private boolean isAdd;

        public OwnerInfo(int id, String name, String phone, String building, String unit, String room, String notes, int is_submit_management_fee, int is_submit_warn_fee, float water, float water_fee, float electricity, float electricity_fee, int is_own_water, int is_own_elec, String time, boolean isDelete, boolean isModify, boolean isAdd, String IDCard) {
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
            this.isDelete = isDelete;
            this.isModify = isModify;
            this.isAdd = isAdd;
            this.IDCard = new SimpleStringProperty(IDCard);
        }

        public OwnerInfo() {
            this.id = new SimpleIntegerProperty();
            this.name = new SimpleStringProperty();
            this.phone = new SimpleStringProperty();
            this.building = new SimpleStringProperty();
            this.unit = new SimpleStringProperty();
            this.room = new SimpleStringProperty();
            this.notes = new SimpleStringProperty();
            this.is_submit_management_fee = new SimpleIntegerProperty();
            this.is_submit_warn_fee = new SimpleIntegerProperty();
            this.water = new SimpleFloatProperty();
            this.water_fee = new SimpleFloatProperty();
            this.electricity = new SimpleFloatProperty();
            this.electricity_fee = new SimpleFloatProperty();
            this.is_own_water = new SimpleIntegerProperty();
            this.is_own_elec = new SimpleIntegerProperty();
            this.time = new SimpleStringProperty();
            this.isDelete = false;
            this.isModify = false;
            this.isAdd = false;
            this.IDCard = new SimpleStringProperty();
        }

        public int getId() {
            return id.get();
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

        public void setIs_own_water(int is_own_water) {
            this.is_own_water.set(is_own_water);
        }

        public int getIs_own_elec() {
            return is_own_elec.get();
        }

        public void setIs_own_elec(int is_own_elec) {
            this.is_own_elec.set(is_own_elec);
        }

        public String getTime() {
            return time.get();
        }

        public void setTime(String  time) {
            this.time.set(time);
        }


        public void setIsDelete(boolean b) {
            this.isDelete = b;
        }
        public boolean getIsDelete() {
            return isDelete;
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

        public boolean getIsModify() {
            return isModify;
        }

        public void setIsModify(boolean modify) {
            this.isModify = modify;
        }

        public boolean getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(boolean add) {
            this.isAdd = add;
        }

        public String getIDCard() {
            return IDCard.get();
        }

        public void setIDCard(String IDCard) {
            this.IDCard.set(IDCard);
        }
    }
}
