package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionWindow extends JFrame implements ActionListener {

    public OptionWindow(){
        super("TodoList Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout(1));

        JLabel optionMessage = new JLabel("Please select an option: ", JLabel.CENTER);
        add(optionMessage);

        JButton addItem = new JButton("Add a Todo-list Item");
        add(addItem);
        addItem.setActionCommand("AddItem");
        addItem.addActionListener(this);

        JButton removeItem = new JButton("Cross-Off an Item");
        add(removeItem);
        removeItem.setActionCommand("removeItem");
        removeItem.addActionListener(this);

        JButton viewList = new JButton("View Current Todo-list");
        add(viewList);
        viewList.setActionCommand("view");
        viewList.addActionListener(this);

        JButton viewOverDue = new JButton("View Overdues ");
        add(viewOverDue);
        viewOverDue.setActionCommand("viewOver");
        viewOverDue.addActionListener(this);

        JButton resetDue = new JButton("Reset Item Due Dates ");
        add(viewOverDue);
        resetDue.setActionCommand("resetDue");
        resetDue.addActionListener(this);

        JButton resetPassword = new JButton("Reset Passwords");
        add(viewOverDue);
        resetPassword.setActionCommand("resetPassword");
        resetPassword.addActionListener(this);

        JButton empty = new JButton("Empty Todolist");
        add(empty);
        empty.setActionCommand("empty");
        empty.addActionListener(this);

        JButton newUser = new JButton("Add A New User to the System");
        add(newUser);
        newUser.setActionCommand("newUser");
        newUser.addActionListener(this);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
