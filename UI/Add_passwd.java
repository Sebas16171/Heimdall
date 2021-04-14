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
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import Scripts.SQL_Connection;
import Scripts.Session;
import Scripts.Encrypter;

public class Add_passwd implements ActionListener {

    private JRootPane panel = new JRootPane();
    private JFrame frame = new JFrame("JFrame Example");

    private JLabel lblDomain = new JLabel();
    private JLabel lblUsername = new JLabel();
    private JLabel lblPassword = new JLabel();

    private JTextField txtDomain = new JTextField();
    private JTextField txtUsername = new JTextField();
    private JTextField txtPassword = new JTextField();

    private JButton btnAccept = new JButton("Accept");
    private JButton btnCancel = new JButton("Cancel");

    private SQL_Connection connection;
    private Session session;
    private Encrypter crpt;

    public Add_passwd(SQL_Connection connection, Session session){
        /* DATOS A RECOGER

            id: Viene en Session
            owner: Viene en Session
            domain: Lo escribe el usuario
            username: Lo escribe el usuario
            password: Lo escribe el usuario
                Debe encriptarse: El metodo viene en Session
            updated: se recoge del sistema

        
        */

        this.connection = connection;
        this.session = session;
        this.crpt = new Encrypter();

        init_components();
    }

    private void init_components() {

        panel.setLayout(null);
        frame.add(panel);

        lblDomain.setText("Domain");
        lblDomain.setBounds(30, 30, 100, 30);
        txtDomain.setText("");
        txtDomain.setBounds(30, 60, 245, 40);

        lblUsername.setText("Username");
        lblUsername.setBounds(300, 30, 100, 30);
        txtUsername.setText("");
        txtUsername.setBounds(300, 60, 245, 40);

        lblPassword.setText("Password");
        lblPassword.setBounds(30, 100, 100, 30);
        txtPassword.setText("");
        txtPassword.setBounds(30, 130, 245, 40);

        btnAccept.setBounds(395, 200, 150, 40);
        btnAccept.addActionListener(this);
        btnCancel.setBounds(225, 200, 150, 40);
        btnCancel.addActionListener(this);

        panel.add(lblDomain);
        panel.add(txtDomain);

        panel.add(lblUsername);
        panel.add(txtUsername);

        panel.add(lblPassword);
        panel.add(txtPassword);

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
            //  Formato de fecha: YYYY-MM-DD

            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();

                String passwd, date = dtf.format(now);

                passwd = this.crpt.encrypt(this.session.getCryptMethod(), this.txtPassword.getText(),
                        this.session.getName() + this.session.getLastName());

                String sql = String.format(
                        "INSERT INTO `data` (`owner`, `domain`, `username`, `password`, `updated`) VALUES ('%d', '%s', '%s', '%s', '%s')",
                        this.session.getID(), this.txtDomain.getText(), this.txtUsername.getText(), passwd, date);
                connection.Register(sql);

                sql = String.format(
                        "INSERT INTO `log` (`fecha`, `usuario`) VALUES ('%s', '%s')", date, this.session.getUsername());
                connection.Register(sql);


                JOptionPane.showMessageDialog(null, "Se agrego exitosamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }

            
            

        } else if (e.getSource() == btnCancel) {
            frame.dispose();
        }

    }
    
}
