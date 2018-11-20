package ui;

import model.Exception.DateIncorrectFormatException;
import model.Item;
import model.TodoList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class ViewListWindow extends JFrame implements ActionListener{
    DefaultTableModel model;
    JTable table;
    TodoList todoList;

    public ViewListWindow(){
        String[] columns = new String[] {
                "Item Index", "Type", "Name","Due Date", "Status"
        };
        final Class[] columnClass = new Class[] {
                Integer.class,String.class, String.class, String.class, String.class
        };
        model = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 4;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };
        table = new JTable(model);

        todoList = new TodoList();
        try {
            todoList.load("src/savefile.txt");
        } catch (IOException e) {
            System.out.println("File does not exist, please create the file before running the program!");
        }

        try {
            todoList.checkOverDue();
        } catch (DateIncorrectFormatException e) {
            System.out.println("Should not happen");
        }

        for (Item i: todoList.getTodoList()){
            Vector row = new Vector();
            row.add(todoList.getTodoList().indexOf(i));
            row.add(i.getItemType());
            row.add(i.getName());
            row.add(i.getDueDate());
            row.add(i.getStatus());
            model.addRow(row);
        }

        add(new JScrollPane(table));

        JButton removeSelectedItem = new JButton("Cross-Off Selected Item");
        add(removeSelectedItem);
        removeSelectedItem.setActionCommand("remove");
        removeSelectedItem.addActionListener(this);

        JButton addItem = new JButton("Add a New Item");
        add(addItem);
        addItem.setActionCommand("add");
        addItem.addActionListener(this);

        JButton resetDue = new JButton("Reset Item Due date");
        add(resetDue);
        resetDue.setActionCommand("resetDue");
        resetDue.addActionListener(this);


        setTitle("TodoList");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("add")){
            new AddItemWindow(this);
        }
        if(e.getActionCommand().equals("remove")){
            int row = table.getSelectedRow();
            todoList.crossedOffItem(row);
            JOptionPane.showMessageDialog(null,"Item status has been changed successfully.");
            String done = "Done";
            table.setValueAt((Object)done,row,4);
            try {
                todoList.save("src/savefile.txt");
            } catch (IOException excp) {
                System.out.println("This should never happen, I know this file exists");
            }
        }
        if(e.getActionCommand().equals("resetDue")){
            int row = table.getSelectedRow();
            Item item = todoList.getItem(row -1);
            new ResetDueWindow(item, this);


        }
    }


}
