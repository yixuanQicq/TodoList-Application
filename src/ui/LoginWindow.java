package ui;

import model.UserSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginWindow extends JFrame implements ActionListener {

    JTextField username;
    JTextField userpassword;

    public LoginWindow(){
        super("TodoList Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);

        JLabel welcome = new JLabel("Welcome to the CPSC 210 Project for 2018W1!", JLabel.CENTER);
        welcome.setBounds(50,40, 300,20);
        add(welcome);

        JLabel enterYourUsername = new JLabel("Please enter your username", JLabel.CENTER);
        enterYourUsername.setBounds(40,70, 200,20);
        add(enterYourUsername);

        username = new JTextField(10);
        username.setBounds(50,100, 100,20);
        add(username);

        JLabel enterYourPassword = new JLabel("Please enter your password", JLabel.CENTER);
        enterYourPassword.setBounds(40,150, 200,20);
        add(enterYourPassword);

        userpassword = new JTextField();
        userpassword.setBounds(50,180, 100,20);
        add(userpassword);

        JButton btn = new JButton("Enter");
        btn.setBounds(220,240, 60,20);
        add(btn);

        btn.setActionCommand("Enter");
        btn.addActionListener(this);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserSystem userSystem = new UserSystem();
        try {
            userSystem.load("src/userfile");
        } catch (IOException excp) {
            System.out.println("File does not exist, please create the file before running the program!");
        }
        if(e.getActionCommand().equals("Enter"))
        {
            String nm = username.getText();
            int pw =  Integer.parseInt(userpassword.getText());
            Boolean run = userSystem.accessVerification(pw, nm);
            if (run){
                this.dispose();
                new OptionWindow();
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid username or password");
            }
        }
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}
