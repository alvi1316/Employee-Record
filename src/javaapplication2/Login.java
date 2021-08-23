package javaapplication2;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{

    JPanel panel;
    JButton button;
    JTextField usernameField;
    JTextField passwordField;
    GridBagConstraints constraints;
    LoginController controller;
    Database database;
    
    public Login(){
        init();
        createEliments();
        createPanel();
        createWindow();
        setVisible(true);
    }
    
    void disposeAll(){
        dispose();
        database.closeConnection();
    }
    
    void init(){
        database = new Database();
        controller = new LoginController(this,database);
        panel = new JPanel();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        constraints = new GridBagConstraints();
        button = new JButton();
    }
    
    void createEliments(){
        usernameField.setColumns(8);
        usernameField.setFont(new Font(Font.SERIF,Font.PLAIN,22));
        usernameField.setText("Username");
        usernameField.addFocusListener(controller);
        
        passwordField.setColumns(8);        
        passwordField.setFont(new Font(Font.SERIF,Font.PLAIN,22));
        passwordField.setText("Password");
        passwordField.addFocusListener(controller);
        
        button.setText("Login");        
        button.addActionListener(controller);
    }
    
    void createPanel(){
        panel.setLayout(new GridBagLayout());
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(usernameField,constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(button, constraints);
    }
    
    
    void createWindow(){
        add(panel);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
