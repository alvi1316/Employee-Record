package javaapplication2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;


public class LoginController implements ActionListener, FocusListener{
    
    Login login;
    Database database;
    
    LoginController(Login login, Database database){
        this.login = login;
        this.database = database;
    }
    
    void loginAction(){
        if(database.checkLogin(login.usernameField.getText(), login.passwordField.getText())){
            login.disposeAll();
            new Information();
        }else{
            JOptionPane.showMessageDialog(login,"Wrong username or password");
        }
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login.button){
            loginAction();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == login.usernameField){
             if(login.usernameField.getText().equals("Username")){
                 login.usernameField.setText("");
             }            
        }else if(e.getSource() == login.passwordField){
             if(login.passwordField.getText().equals("Password")){
                 login.passwordField.setText("");
             }            
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == login.usernameField){
             if(login.usernameField.getText().equals("")){
                 login.usernameField.setText("Username");
             }            
        }else if(e.getSource() == login.passwordField){
             if(login.passwordField.getText().equals("")){
                 login.passwordField.setText("Password");
             }            
        }
    }
    
}
