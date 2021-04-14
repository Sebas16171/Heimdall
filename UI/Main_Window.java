package UI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import Scripts.SQL_Connection;
import Scripts.Session;
import Scripts.Encrypter;

public class Main_Window implements ActionListener {

    private JFrame frame = new JFrame("JFrame Example");
    private JRootPane panel = new JRootPane();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model_user = new DefaultTableModel();
    private JTable table = new JTable();
    private JButton btnAdd = new JButton("Add new");
    private JButton btnRemove = new JButton("Remove");
    private JButton btnEdit = new JButton("Edit");

    private SQL_Connection connection;
    private Session session;
    private Statement st;
    private Encrypter crpt;

    private String[] selected_row = {"", "", "", "", ""};

    public Main_Window(SQL_Connection connection, Session session) {

        this.connection = connection;
        this.session = session;
        this.crpt = new Encrypter();

        init_components();

    }

    public void init_components() {

        panel.setLayout(null);
        frame.add(panel);

        btnAdd.setBounds(550, 30, 150, 40);
        btnAdd.addActionListener(this);

        btnRemove.setBounds(550, 100, 150, 40);
        btnRemove.addActionListener(this);

        btnEdit.setBounds(550, 170, 150, 40);
        btnEdit.addActionListener(this);

        panel.add(btnAdd);
        panel.add(btnRemove);
        panel.add(btnEdit);
        panel.add(table);
        table.setBounds(20, 20, 500, 420);
        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

                JTable temp_table = (JTable) arg0.getSource();
                Point point = arg0.getPoint();
                int row = table.rowAtPoint(point);

                if (arg0.getClickCount() == 2 && temp_table.getSelectedRow() != -1) {
                    // your valueChanged overridden method
                    String str = crpt.decrypt(session.getCryptMethod(), String.valueOf(
                        temp_table.getValueAt(row, 3)), session.getName() + session.getLastName());
                    JOptionPane.showMessageDialog(null, str);

                } else {
                    for (int i = 0; i < 5; i++) {
                        selected_row[i] = String.valueOf(temp_table.getValueAt(row, i));
                    }

                }
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
        });
        update_table();

        frame.setSize(750, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);
    }

    public void update_table() {
        ResultSet data = connection.select("SELECT * FROM `data` WHERE `owner` = " + this.session.getID());
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

        try {
            while (data.next()) {
                String owner = data.getString("owner");
                String domain = data.getString("domain");
                String username = data.getString("username");
                String password = data.getString("password");
                String updated = data.getString("updated");
                model.addRow(new Object[] { owner, domain, username,password,updated });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        

        table.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            Add_passwd add_pass = new Add_passwd(this.connection, this.session);
            
        }
        if (e.getSource() == btnRemove) {
            if (this.selected_row[0] == ""){
                JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
            } else {
                String sql = "DELETE FROM `data` WHERE `id` = " + selected_row[0];

                //System.out.println(sql);

                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Would You Like to Save your Previous Note First?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    connection.Register(sql);
                }
            }
            
        }
        if (e.getSource() == btnEdit) {
            Add_passwd edit_pass = new Add_passwd(this.connection, this.session, selected_row[0]);

        }

    }
}
