package javaapplication2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Information extends JFrame{
    JTextField name;
    JTextField salary;
    JTextField address;
    JTextField id;
    JButton addButton;
    JButton deleteButton;    
    JPanel topPanel;
    GridBagConstraints constraints;    
    JScrollPane pane;
    JTable table;
    DefaultTableModel model; 
    InformationController controller;
    Database database;
        
    public Information(){
        init();
        createComponent();
        createWindow();
        setVisible(true);
    }

     void init(){ 
        name = new JTextField();
        salary = new JTextField();
        address = new JTextField();
        id = new JTextField();
        database = new Database();
        controller = new InformationController(this,database);
        addButton = new JButton();
        deleteButton = new JButton();  
        topPanel = new JPanel();
        constraints = new GridBagConstraints();
        pane = new JScrollPane();
        table = new JTable();
        model = new DefaultTableModel();        
     }
     
     void createComponent(){ 
        name.setText("Name");
        name.setFont(new Font(Font.SERIF,Font.PLAIN,18));
        name.setColumns(8);
        name.addFocusListener(controller);
        
        salary.setText("Salary");
        salary.setFont(new Font(Font.SERIF,Font.PLAIN,18));
        salary.setColumns(8);
        salary.addFocusListener(controller);
        
        address.setText("Address");
        address.setFont(new Font(Font.SERIF,Font.PLAIN,18));
        address.setColumns(8);
        address.addFocusListener(controller);
        
        id.setText("ID");
        id.setFont(new Font(Font.SERIF,Font.PLAIN,18));
        id.setColumns(3);
        id.addFocusListener(controller);
        
        addButton.setText("ADD");
        addButton.addActionListener(controller);
        
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(controller);
        
        topPanel.setPreferredSize(new Dimension(50,100));
        topPanel.setLayout(new GridBagLayout());
        constraints.insets = new Insets(10,10,10,10);        
        topPanel.add(name,constraints);
        topPanel.add(salary,constraints);
        topPanel.add(address,constraints);
        topPanel.add(addButton, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        topPanel.add(id, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        topPanel.add(deleteButton, constraints);   

        
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("SALARY");
        model.addColumn("ADDRESS");             
        table.setModel(model);
        controller.init();
        pane.setViewportView(table);
     }
         
    void createWindow(){
        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(pane,BorderLayout.CENTER);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
