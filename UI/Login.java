package UI;

import Scripts.SQL_Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

public class Login implements ActionListener {
    
    private ImageIcon icon = new ImageIcon("/../Res/black.jpg");
    private JLabel image = new JLabel(icon);
    private JFrame frame = new JFrame("JFrame Example");
    private JRootPane panel = new JRootPane();
    private JLabel lblPass = new JLabel();
    private JLabel lblUser = new JLabel();
    private JTextField txtUser = new JTextField();
    private JPasswordField txtPass = new JPasswordField();
    private JButton btnRegister = new JButton("Register");
    private JButton btnLogin = new JButton("Login");
    private JButton btnExit = new JButton("Exit");

    private SQL_Connection connection;
    private String server, database;

    public Login(SQL_Connection testCon, String server, String database) {

        this.connection = testCon;
        this.server = server;
        this.database = database;
        this.init_components();

    }

    private void init_components() {

        panel.setLayout(null);
        frame.add(panel);

        image.setBounds(30, 10, 100, 50);

        lblUser.setText("User");
        lblUser.setBounds(30, 130, 100, 30);
        txtUser.setText("");
        txtUser.setBounds(30, 160, 245, 40);

        lblPass.setText("ID");
        lblPass.setBounds(30, 220, 100, 30);
        txtPass.setText("");
        txtPass.setBounds(30, 250, 245, 40);

        btnRegister.setBounds(30, 320, 245, 60);
        btnRegister.addActionListener(this);

        btnLogin.setBounds(30, 390, 245, 60);
        btnLogin.addActionListener(this);

        btnExit.setBounds(30, 460, 245, 60);
        btnExit.addActionListener(this);

        panel.add(image);

        panel.add(lblUser);
        panel.add(txtUser);

        panel.add(lblPass);
        panel.add(txtPass);

        panel.add(btnRegister);
        panel.add(btnLogin);
        panel.add(btnExit);
        panel.setDefaultButton(btnLogin);
        

        frame.setSize(320, 570);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            System.exit(0);
        } else if (e.getSource() == btnLogin){
            String username =  txtUser.getText();
            String passwd = txtPass.getText();
            if (connection.connect(server, database, username, passwd)) {
                JOptionPane.showMessageDialog(null, "Connection granted.", "Connection status", 
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Connection denied.", "Connection status",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

}
