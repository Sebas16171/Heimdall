package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import Scripts.SQL_Connection;

public class Register implements ActionListener {
    
    private JRootPane panel = new JRootPane();
    private JFrame frame = new JFrame("JFrame Example");
    
    private JLabel lblUser = new JLabel();
    private JLabel lblName = new JLabel();
    private JLabel lblLstName = new JLabel();
    private JLabel lblCrptMethod = new JLabel();

    private JTextField txtUser = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtLstName = new JTextField();
    private JComboBox<String> cmbCrptMethod = new JComboBox<String>();

    private JButton btnAccept = new JButton("Accept");
    private JButton btnCancel = new JButton("Cancel");

    private SQL_Connection connection;

    public Register(SQL_Connection connection){

        this.connection = connection;

        init_components();
    }

    private void init_components(){
        
        panel.setLayout(null);
        frame.add(panel);

        lblUser.setText("User");
        lblUser.setBounds(30, 30, 100, 30);
        txtUser.setText("");
        txtUser.setBounds(30, 60, 245, 40);

        lblName.setText("Name");
        lblName.setBounds(300, 30, 100, 30);
        txtName.setText("");
        txtName.setBounds(300, 60, 245, 40);
        
        lblLstName.setText("Last Name");
        lblLstName.setBounds(30, 100, 100, 30);
        txtLstName.setText("");
        txtLstName.setBounds(30, 130, 245, 40);

        lblCrptMethod.setText("Encryption Method");
        lblCrptMethod.setBounds(300, 100, 200, 30);

        cmbCrptMethod.addItem("0 - Base64");
        cmbCrptMethod.addItem("1 - Blowfish");

        cmbCrptMethod.setBounds(300, 130, 245, 40);

        btnAccept.setBounds(395, 200, 150, 40);
        btnAccept.addActionListener(this);
        btnCancel.setBounds(225, 200, 150, 40);
        btnCancel.addActionListener(this);

        panel.add(lblUser);
        panel.add(txtUser);

        panel.add(lblName);
        panel.add(txtName);

        panel.add(lblLstName);
        panel.add(txtLstName);

        panel.add(lblCrptMethod);
        panel.add(cmbCrptMethod);
        
        panel.add(btnAccept);
        panel.add(btnCancel);

        frame.setSize(570, 320);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAccept) {

            String user = txtUser.getText();
            String name = txtName.getText();
            String last_name = txtLstName.getText();
            int crypt_method = cmbCrptMethod.getSelectedIndex();

            connection.RegisterUser(user, name, last_name, crypt_method);

        } else if (e.getSource() == btnCancel){
            frame.dispose();
        }

    }
}
