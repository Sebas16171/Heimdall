package UI;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login {
    
    ImageIcon icon = new ImageIcon("/../Res/black.jpg");
    JLabel image = new JLabel(icon);
    JFrame frame = new JFrame("JFrame Example");
    JPanel panel = new JPanel();
    JLabel lblPass = new JLabel();
    JLabel lblUser = new JLabel();
    JTextField txtUser = new JTextField();
    JPasswordField txtPass = new JPasswordField();
    JButton btnLogin = new JButton("Login");

    public Login() {
        this.init_components();

    }
    
    private void init_components(){
        
        panel.setLayout(null);
        frame.add(panel);

        image.setBounds(30, 10, 100, 50);

        lblUser.setText("User");
        lblUser.setBounds(30, 130, 100, 30);
        txtUser.setText("");
        txtUser.setBounds(30, 160, 245, 40);
        
        lblPass.setText("Passwd");
        lblPass.setBounds(30, 220, 100, 30);
        txtPass.setText("");
        txtPass.setBounds(30, 250, 245, 40);

        btnLogin.setBounds(30, 320, 245, 60);

        panel.add(image);

        panel.add(lblUser);  
        panel.add(txtUser);

        panel.add(lblPass);  
        panel.add(txtPass);
        
        panel.add(btnLogin);

        frame.setSize(300, 450);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);

    }

}
