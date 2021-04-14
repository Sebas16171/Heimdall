package UI;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import Scripts.SQL_Connection;
import Scripts.Session;

public class Main_Window implements ActionListener {

    private JFrame frame = new JFrame("JFrame Example");
    private JRootPane panel = new JRootPane();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model_user = new DefaultTableModel();
    private JTable table = new JTable();
    private JButton btnAdd = new JButton("Add new");

    private SQL_Connection connection;
    private Session session;
    private Statement st;

    public Main_Window(SQL_Connection connection, Session session) {

        this.connection = connection;
        this.session = session;

        init_components();

    }

    public void init_components() {

        System.out.println("Session ID: " + this.session.getID());
        System.out.println("Session Name: " + this.session.getName());
        System.out.println("Session Last name: " + this.session.getLastName());
        System.out.println("Session Username: " + this.session.getUsername());
        System.out.println("Session CryptMethod: " + this.session.getCryptMethod());

        panel.setLayout(null);
        frame.add(panel);

        btnAdd.setBounds(550, 30, 150, 40);
        btnAdd.addActionListener(this);

        panel.add(btnAdd);
        panel.add(table);
        table.setBounds(20, 20, 500, 420);
        update_table();

        frame.setSize(750, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);
    }

    public void update_table() {
        ResultSet data = connection.select("SELECT * FROM data");
        ResultSet data_user = connection.select("SELECT * FROM user");

        model = new DefaultTableModel();
        model.addColumn("Owner");
        model.addColumn("Domain");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Updated");

        model_user = new DefaultTableModel();
        model_user.addColumn("id");
        model_user.addColumn("user");
        model_user.addColumn("name");
        model_user.addColumn("last name");
        model_user.addColumn("method");

        try {
            while (data_user.next()) {
                String id = data_user.getString("id");
                String user = data_user.getString("user");
                String name = data_user.getString("name");
                String lst_name = data_user.getString("lst_name");
                String method = data_user.getString("crpt_method");
                model_user.addRow(new String[] { id, user, name,lst_name, method });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try {
            while (data.next()) {
                String owner = data.getString("owner");
                String domain = data.getString("domain");
                String username = data.getString("username");
                String password = data.getString("password");
                model.addRow(new Object[] { owner, domain, username,password });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        

        table.setModel(model_user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            Add_passwd add_pass = new Add_passwd(this.connection, this.session);
            
        }

    }
}
