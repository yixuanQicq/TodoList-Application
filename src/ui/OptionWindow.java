package ui;

import model.Item;
import model.TodoList;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OptionWindow extends JFrame implements ActionListener {
    private int xPosition = 100;
    private int buttonWidth = 300;
    private int buttonHeight = 20;

    private User currentUser;


    public OptionWindow(User currentUser){
        super("TodoList Application");
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);

        JLabel optionMessage = new JLabel("Please select an option: ", JLabel.CENTER);
        optionMessage.setBounds(26, 10, 300, 20);
        add(optionMessage);

        JButton viewList = new JButton("View Current Todolist");
        viewList.setBounds(xPosition, 50, buttonWidth, buttonHeight );
        add(viewList);
        viewList.setActionCommand("view");
        viewList.addActionListener(this);

        JButton empty = new JButton("Empty Todolist");
        empty.setBounds(xPosition, 90, buttonWidth, buttonHeight );
        add(empty);
        empty.setActionCommand("empty");
        empty.addActionListener(this);

        this.currentUser = currentUser;
        JButton resetPassword = new JButton("Reset Passwords");
        resetPassword.setBounds(xPosition, 130, buttonWidth, buttonHeight );
        add(resetPassword);
        resetPassword.setActionCommand("resetPassword");
        resetPassword.addActionListener(this);


        JButton newUser = new JButton("Add A New User to the System");
        newUser.setBounds(xPosition, 170, buttonWidth, buttonHeight );
        add(newUser);
        newUser.setActionCommand("newUser");
        newUser.addActionListener(this);

        JButton close = new JButton("Close TodoList Application");
        close.setBounds(xPosition, 210, buttonWidth, buttonHeight );
        add(close);
        close.setActionCommand("close");
        close.addActionListener(this);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("view")){
            new ViewListWindow();
        }
        if(e.getActionCommand().equals("resetPassword")){}
        if(e.getActionCommand().equals("empty")){
            TodoList todoList = new TodoList();
                try {
                    todoList.emptyFile("src/savefile.txt");
                } catch (IOException ex) {
                    System.out.println("This should never happen, I know this file exists");
                }
            JOptionPane.showMessageDialog(null,"Your TodoList has been emptied!");
        }
        if(e.getActionCommand().equals("newUser")){}
        if(e.getActionCommand().equals("close")){
           dispose();
        }
    }
}
