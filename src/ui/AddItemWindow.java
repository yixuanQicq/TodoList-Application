package ui;

import com.sun.tools.javac.comp.Todo;
import model.*;
import model.Exception.TodoListException;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddItemWindow extends JFrame implements ActionListener{

    JTextField itemText;
    JTextField itemDueDate;
    ViewListWindow vw;

    public AddItemWindow(ViewListWindow vw) {
        super("Add an Item");
        this.vw =vw;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);

        JLabel enterText = new JLabel("Enter the Item Text: ");
        enterText.setBounds(48, 40, 400, 20);
        add(enterText);

        itemText = new JTextField(30);
        itemText.setBounds(50, 70, 300, 20);
        add(itemText);

        JLabel enterDueDate = new JLabel("Enter the Item Due Date " +
                "(Please Follow the Format of [MMMM d, yyyy]): ");
        enterDueDate.setBounds(50, 100, 600, 20);
        add(enterDueDate);

        itemDueDate = new JTextField(30);
        itemDueDate.setBounds(50, 130,300,20);
        add(itemDueDate);

        JLabel typeSelection = new JLabel("Please select the type of Item you wish to create: ");
        typeSelection.setBounds(48, 160, 600, 20);
        add(typeSelection);

        JButton urgentItem = new JButton("Urgent");
        urgentItem.setBounds(70, 210, 100, 20);
        add(urgentItem);
        urgentItem.setActionCommand("Urgent");
        urgentItem.addActionListener(this);

        JButton regularItem = new JButton("Regular");
        regularItem.setBounds(190, 210,100,20);
        add(regularItem);
        regularItem.setActionCommand("Regular");
        regularItem.addActionListener(this);


        JButton businessItem = new JButton("Business");
        businessItem.setBounds(310,210,100,20);
        add(businessItem);
        businessItem.setActionCommand("Business");
        businessItem.addActionListener(this);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        TodoList todoList = new TodoList();
        try {
            todoList.load("src/savefile.txt");
        } catch (IOException ex) {
            System.out.println("File does not exist, please create the file before running the program!");
        }
        String t = itemText.getText();
        String due = itemDueDate.getText();
        if(e.getActionCommand().equals("Urgent")){
            Item item = new UrgentItem(t,due);
            try {
                todoList.addItem(item);
                JOptionPane.showMessageDialog(null,"Your Item has been successfully added.");
            } catch (TodoListException ex) {
                JOptionPane.showMessageDialog(null,"Item over limit, try again later");
            }
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        if(e.getActionCommand().equals("Regular")){
            Item item = new RegularItem(t,due);
            try {
                todoList.addItem(item);
                JOptionPane.showMessageDialog(null,"Your Item has been successfully added.");
            } catch (TodoListException ex) {
                JOptionPane.showMessageDialog(null,"Item over limit, try again later");
            }
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        if(e.getActionCommand().equals("Business")){
            Item item = new BusinessItem(t,due);
            try {
                todoList.addItem(item);
                JOptionPane.showMessageDialog(null,"Your Item has been successfully added.");
            } catch (TodoListException ex) {
                JOptionPane.showMessageDialog(null,"Item over limit, try again later");
            }
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException ex) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        vw.dispose();
        new ViewListWindow();
        dispose();
    }
}
