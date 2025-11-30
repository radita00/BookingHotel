package frontend;

import backend.Pegawai;
import javax.swing.*;

public class FrmLogin extends JFrame {
    JTextField txtUsername = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JButton btnLogin = new JButton("Login");
    JButton btnCancel = new JButton("Batal");

    public FrmLogin(){
        setTitle("Form Login Pegawai");
        setSize(420,250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("LOGIN PEGAWAI");
        lblTitle.setBounds(150,10,200,20);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(40,50,100,30);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(40,90,100,30);

        txtUsername.setBounds(140,55,200,25);
        txtPassword.setBounds(140,95,200,25);

        btnLogin.setBounds(140,140,90,30);
        btnCancel.setBounds(250,140,90,30);

        add(lblTitle);
        add(lblUsername);
        add(lblPassword);
        add(txtUsername);
        add(txtPassword);
        add(btnLogin);
        add(btnCancel);

        // aksi login
        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());

            if(Pegawai.login(user, pass)){
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                dispose();
                new App().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Username/Password salah");
            }
        });

        btnCancel.addActionListener(e -> {
            System.exit(0);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
