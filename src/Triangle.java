package src;
import javax.swing.*;
//import java.awt.event.*;
import java.awt.*;
import src.*;;
//import javax.imageio.ImageIO;
//import java.io.IOException;

public class Triangle extends JFrame {
    // Khai báo biến.
    JFrame frame;
    private float[][] a = new float[8][5]; // Mảng chứa 8 thuộc tính và 5 công thức tính.
    private static final double PI = 3.1415926535897931;
    private static final float ANGLE = 180f;
    private static final float PIF = 3.141592f;
    private JTextField txtAlpha, txtBeta, txtDeta, txtA, txtB, txtH, txtC, txtS;
    private JButton btnStartCalculate, btnRestart, btnHelp;
    private JComboBox<String> cbbValue;

    
    public Triangle() {
        setTitle("Semantic Triangle");
        setSize(1100, 800);
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null); 
        initializeImage();
        
        initComponents();
        //init();
    }
    private void initializeImage() {
        BackgroundPanel backgroundPanel = new BackgroundPanel("/img/Backround.png");
        setContentPane(backgroundPanel);
    }

    private void initComponents() {
        // Tạo và định vị các thành phần giao diện tương tự như trong code C#
        // Ví dụ: txtAlpha = new JTextField();
        // btnStartCalculate = new JButton("Bắt đầu tính");
        // Thêm các thành phần vào JFrame
        // Thêm bộ lắng nghe sự kiện cho các nút và hộp chọn

        // Giả sử bạn đã thêm các thành phần và bộ lắng nghe sự kiện tương tự như trong C#
        setLayout(null);

        // Initialize components
        txtAlpha = new JTextField();
        txtBeta = new JTextField();
        txtDeta = new JTextField();
        txtA = new JTextField();
        txtB = new JTextField();
        txtH = new JTextField();
        txtC = new JTextField();
        txtS = new JTextField();
        btnStartCalculate = new JButton("Bắt đầu tính");
        btnRestart = new JButton("Làm bài khác");
        btnHelp = new JButton("Help!");
        cbbValue = new JComboBox<>(new String[]{"Giá trị cần tính.", "Góc Alpha", "Góc Beta", "Góc Deta", "Cạnh a", "Cạnh b", "Cạnh c", "Diện tích S", "Chiều cao h"});

        // Set component properties and positions
        txtAlpha.setBounds(390, 230, 70, 29);
        txtBeta.setBounds(620, 230, 74, 29);
        txtDeta.setBounds(836, 230, 67, 29);
        txtA.setBounds(325, 460, 64, 29);
        txtB.setBounds(485, 460, 64, 29);
        txtH.setBounds(650, 460, 84, 29);
        txtC.setBounds(820, 460, 64, 29);
        txtS.setBounds(620, 700, 146, 29);
        btnStartCalculate.setBounds(910, 120, 117, 41);
        btnRestart.setBounds(910, 184, 120, 41);
        btnHelp.setBounds(910, 690, 105, 29);
        cbbValue.setBounds(880, 82, 150, 25);

        // Add components to frame
        add(txtAlpha);
        add(txtBeta);
        add(txtDeta);
        add(txtA);
        add(txtB);
        add(txtH);
        add(txtC);
        add(txtS);
        add(btnStartCalculate);
        add(btnRestart);
        add(btnHelp);
        add(cbbValue);

        // actions
        btnStartCalculate.addActionListener(e -> btnStartCalculateActionPerformed());
        btnRestart.addActionListener(e -> btnRestartActionPerformed());
        btnHelp.addActionListener(e -> helpAction());

        // Group box for information
        JPanel groupInfo = new JPanel();
        groupInfo.setBounds(2, 1, 252, 170);
        groupInfo.setBorder(BorderFactory.createTitledBorder("Thông Tin"));
        groupInfo.setLayout(null);
        add(groupInfo);

        JLabel label1 = new JLabel("<html>Bài tập giải tam giác sử dụng mạng ngữ nghĩa cho các công thức tam giác</html>");
        label1.setBounds(6, 21, 225, 45);
        label1.setForeground(Color.RED);
        groupInfo.add(label1);

        JLabel label2 = new JLabel("<html>Nhóm Học viên:<br>- Hoàng Trường Thịnh<br>-  Nguyễn Thị Nguyệt Dưỡng<br>- Nguyễn Thành Được</html>");
        label2.setBounds(6, 69, 241, 100);
        label2.setForeground(Color.BLUE);
        groupInfo.add(label2);

        // Group box for annotations
        JPanel groupAnnotations = new JPanel();
        groupAnnotations.setBounds(2, 650, 348, 120);
        groupAnnotations.setBorder(BorderFactory.createTitledBorder("Chú thích"));
        groupAnnotations.setLayout(null);
        add(groupAnnotations);

        JLabel label3 = new JLabel("<html>- alpha, beta, deta là các góc của tam giác.<br>- a, b, c là các cạnh của tam giác.<br>- h là chiều cao.<br>- S là diện tích tam giác.</html>");
        label3.setBounds(43, 16, 267, 80);
        groupAnnotations.add(label3);
    }

    private void init() {
        float temp = -1; // Biến tạm
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                a[i][j] = 0;
            }
        }

        a[0][0] = a[1][0] = a[3][0] = a[4][0] = temp;
        a[1][1] = a[2][1] = a[4][1] = a[5][1] = temp;
        a[3][2] = a[4][2] = a[5][2] = a[6][2] = temp;
        a[0][3] = a[1][3] = a[2][3] = temp;
        a[5][4] = a[6][4] = a[7][4] = temp;
    }

    // Phương thức main để khởi chạy ứng dụng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Triangle frame = new Triangle();
            frame.setVisible(true);
        });
    }

    // Thêm các phương thức khác tương tự như trong C#, ví dụ:
    // private void btnStartCalculateActionPerformed(ActionEvent e) { ... }
    public void btnStartCalculateActionPerformed() {
        try {
            if (cbbValue.getSelectedIndex() == 0) { // Check if the first item is selected
                JOptionPane.showMessageDialog(Triangle.this, 
                    "Bạn Chưa chọn Giá trị cần tính. \n Vui lòng chọn một giá trị cần tính rồi mới nhấn Bắt Đầu tính!", 
                    "Cảnh báo từ chương trình!", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                solution(); // This method needs to be implemented according to your specific logic
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Triangle.this, 
                "Bạn đã thực hiện 1 thao tác lỗi, Lỗi trả về là: \n" + ex.getMessage() + "\n  Nhấn OK để quay lại chương trình.", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    private void solution() {
        try {
            activationElementKnow();
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int i = 0; i < 5; i++) {
                    int elementNotKnow = getElementNotKnow(i);
                    if (elementNotKnow != -1) {
                        spreadingActivation(i, elementNotKnow);
                        flag = true;
                        if (findSuccess()) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (!findSuccess()) {
                JOptionPane.showMessageDialog(this, 
                    "Không thể tìm ra  " + cbbValue.getSelectedItem().toString() + " trên mạng ngữ nghĩa đã xây dựng", 
                    "Thông Báo!", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi trả về là: \n" + ex.getMessage(), 
                "Lỗi...", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Placeholder methods that you need to implement
    private void activationElementKnow() {
        try {
            int temp;
            float pi = (float) Math.PI;
            float angle = 180f;
    
            if (!txtAlpha.getText().isEmpty()) {
                float num = (pi * Float.parseFloat(txtAlpha.getText())) / angle;
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[0][temp] == -1f) {
                        this.a[0][temp] = num;
                    }
                }
            }
            if (!txtBeta.getText().isEmpty()) {
                float num = (pi * Float.parseFloat(txtBeta.getText())) / angle;
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[1][temp] == -1f) {
                        this.a[1][temp] = num;
                    }
                }
            }
            if (!txtDeta.getText().isEmpty()) {
                float num = (pi * Float.parseFloat(txtDeta.getText())) / angle;
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[2][temp] == -1f) {
                        this.a[2][temp] = num;
                    }
                }
            }
            if (!txtA.getText().isEmpty()) {
                float num = Float.parseFloat(txtA.getText());
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[3][temp] == -1f) {
                        this.a[3][temp] = num;
                    }
                }
            }
            if (!txtB.getText().isEmpty()) {
                float num = Float.parseFloat(txtB.getText());
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[4][temp] == -1f) {
                        this.a[4][temp] = num;
                    }
                }
            }
            if (!txtC.getText().isEmpty()) {
                float num = Float.parseFloat(txtC.getText());
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[5][temp] == -1f) {
                        this.a[5][temp] = num;
                    }
                }
            }
            if (!txtS.getText().isEmpty()) {
                float num = Float.parseFloat(txtS.getText());
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[6][temp] == -1f) {
                        this.a[6][temp] = num;
                    }
                }
            }
            if (!txtH.getText().isEmpty()) {
                float num = Float.parseFloat(txtH.getText());
                for (temp = 0; temp < 5; temp++) {
                    if (this.a[7][temp] == -1f) {
                        this.a[7][temp] = num;
                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Lỗi trả về là:\n " + ex.getMessage(), "Lỗi thực thi.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private int getElementNotKnow(int k) {
        try {
            int counter = 0;
            int number = -1;
            for (int i = 0; i < 8; i++) {
                if (a[i][k] == -1) { // Note the difference in array access syntax between C# and Java
                    counter++;
                    number = i;
                }
            }
            if (counter == 1) {
                return number;
            }
            return -1;
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, 
                    "Lỗi trả về là:\n " + ex.getMessage(), "Lỗi thực thi.", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    
    
    private void spreadingActivation(int j, int elementNotKnow) {
        try {
            float value = -1;
            float pi = (float) Math.PI;
            float angle = 180f;
    
            switch (j) {
                case 0:
                    switch (elementNotKnow) {
                        case 0:
                            value = (float) (((Math.asin((a[3][0] * Math.sin(a[1][0])) / a[4][0])) * angle) / pi);
                            txtAlpha.setText(String.valueOf(value));
                            break;
                        case 1:
                            value = (float) ((Math.asin((a[4][0] * Math.sin(a[0][0])) / a[3][0])) * angle / pi);
                            txtBeta.setText(String.valueOf(value));
                            break;
                        case 3:
                            value = (float) ((a[4][0] * Math.sin(a[0][0])) / Math.sin(a[1][0]));
                            txtA.setText(String.valueOf(value));
                            break;
                        case 4:
                            value = (float) ((a[3][0] * Math.sin(a[1][0])) / Math.sin(a[0][0]));
                            txtB.setText(String.valueOf(value));
                            break;
                    }
                    break;
    
                case 1:
                    switch (elementNotKnow) {
                        case 1:
                            value = (float) ((Math.asin((a[4][0] * Math.sin(a[2][1])) / a[5][1])) * 180.0 / pi);
                            txtBeta.setText(String.valueOf(value));
                            break;
                        case 2:
                            value = (float) ((Math.asin((a[5][1] * Math.sin(a[1][0])) / a[4][0])) * 180.0 / pi);
                            txtDeta.setText(String.valueOf(value));
                            break;
                        case 4:
                            value = (float) ((a[5][1] * Math.sin(a[1][0])) / Math.sin(a[2][1]));
                            txtB.setText(String.valueOf(value));
                            break;
                        case 5:
                            value = (float) ((a[4][0] * Math.sin(a[2][1])) / Math.sin(a[1][0]));
                            txtC.setText(String.valueOf(value));
                            break;
                    }
                    break;
    
                case 2:
                    float temp1, temp2, temp3;
                    switch (elementNotKnow) {
                        case 3:
                            temp1 = (float) Math.pow(a[4][0], 2.0);
                            temp2 = (float) Math.pow(a[5][1], 2.0);
                            temp3 = (float) Math.pow(a[6][2], 2.0);
                            value = (float) Math.sqrt(temp1 + temp2 + Math.sqrt(temp1 * temp2 - (4 * temp3)));
                            txtA.setText(String.valueOf(value));
                            break;
                        case 4:
                            temp1 = (float) Math.pow(a[3][0], 2.0);
                            temp2 = (float) Math.pow(a[5][1], 2.0);
                            temp3 = (float) Math.pow(a[6][2], 2.0);
                            value = (float) Math.sqrt(temp1 + temp2 + Math.sqrt(temp1 * temp2 - (4 * temp3)));
                            txtB.setText(String.valueOf(value));
                            break;
                        case 5:
                            temp1 = (float) Math.pow(a[3][0], 2.0);
                            temp2 = (float) Math.pow(a[4][0], 2.0);
                            temp3 = (float) Math.pow(a[6][2], 2.0);
                            value = (float) Math.sqrt(temp1 + temp2 + Math.sqrt(temp1 * temp2 - (4 * temp3)));
                            txtC.setText(String.valueOf(value));
                            break;
                        case 6:
                            float num6 = (a[3][0] + a[4][0] + a[5][1]) / 2;
                            value = (float) Math.sqrt(num6 * (num6 - a[3][0]) * (num6 - a[4][0]) * (num6 - a[5][1]));
                            txtS.setText(String.valueOf(value));
                            break;
                    }
                    break;
    
                case 3:
                    switch (elementNotKnow) {
                        case 0:
                            value = (float) ((((pi - a[1][0]) - a[2][1]) * angle) / pi);
                            txtAlpha.setText(String.valueOf(value));
                            break;
                        case 1:
                            value = (float) ((((pi - a[0][0]) - a[2][1]) * angle) / pi);
                            txtBeta.setText(String.valueOf(value));
                            break;
                        case 2:
                            value = (float) ((((pi - a[0][0]) - a[1][0]) * angle) / pi);
                            txtDeta.setText(String.valueOf(value));
                            break;
                    }
                    break;
    
                case 4:
                    switch (elementNotKnow) {
                        case 5:
                            value = (2f * a[6][2]) / a[7][4];
                            txtC.setText(String.valueOf(value));
                            break;
                        case 6:
                            value = (a[7][4] * a[5][1]) / 2f;
                            txtS.setText(String.valueOf(value));
                            break;
                        case 7:
                            value = (2f * a[6][2]) / a[5][1];
                            txtH.setText(String.valueOf(value));
                            break;
                    }
                    break;
            }
    
            for (int i = 0; i < 5; i++) {
                if (this.a[elementNotKnow][i] == -1f) {
                    this.a[elementNotKnow][i] = value;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi trả về là:\n " + ex.getMessage(), "Lỗi thực thi.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private boolean findSuccess() {
        try {
            switch (cbbValue.getSelectedIndex()) {
                case 1:
                    if (a[0][0] == -1) {
                        break;
                    }
                    return true;
                case 2:
                    if (a[1][0] == -1) {
                        break;
                    }
                    return true;
                case 3:
                    if (a[2][1] == -1) {
                        break;
                    }
                    return true;
                case 4:
                    if (a[3][0] == -1) {
                        break;
                    }
                    return true;
                case 5:
                    if (a[4][0] == -1) {
                        break;
                    }
                    return true;
                case 6:
                    if (a[5][1] == -1) {
                        break;
                    }
                    return true;
                case 7:
                    if (a[6][2] == -1) {
                        break;
                    }
                    return true;
                case 8:
                    if (a[7][4] == -1) {
                        break;
                    }
                    return true;
            }
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi trả về là: \n" + ex.getMessage(), "Lỗi....", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    // private void btnRestartActionPerformed(ActionEvent e) { ... }
    
    public void btnRestartActionPerformed() {
        txtAlpha.setText("");
        txtBeta.setText("");
        txtDeta.setText("");
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
        txtH.setText("");
        txtS.setText("");
        init(); // Assuming init() is a method that initializes or resets your form to its default state
    }
    
    // private void btnHelpActionPerformed(ActionEvent e) { ... }
    public void helpAction() {
        String notice = "Nhập các giá trị thuộc tính của tam giác ở các ô màu vàng tương ứng với giá trị." +
                        "\nChọn Giá trị cần tính ở combobox." +
                        "\nNhấn Bắt đầu tính và xem kết quả ở ô màu vàng mà bạn muốn xem." +
                        "\nNhấn Nút Làm bài khác để nhập một bài toán mới để xử lý." +
                        "\n\nNếu có vấn đề gì về chương trình hãy liên hệ tác giả\n\tEmail: thinhht.18@grad.uit.edu.vn";
        
        JOptionPane.showMessageDialog(this, notice, "Trợ giúp sử dụng chương trình!", JOptionPane.INFORMATION_MESSAGE);
    }

}
