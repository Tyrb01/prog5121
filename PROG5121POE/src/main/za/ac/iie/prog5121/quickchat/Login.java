package za.ac.iie.prog5121.quickchat;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    private boolean isLoggedIn;
    
    public Login() {
        this.isLoggedIn = false;
    }
    
    public boolean checkUserName(String username) {
        if (username == null || username.length() > 5) {
            return false;
        }
        
        if (!username.contains("_")) {
            return false;
        }
        
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }
        
        return hasUpper && hasDigit && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null) return false;
        return cellPhone.matches("^\\+27\\d{9}$");
    }
    
    public String registerUser(String username, String password, String cellPhone, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cellphone number is not correctly formatted, please ensure that the cellphone number starts with +27 and is followed by 9 digits.";
        }
        
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "Username successfully captured.Password successfully captured.Cell number successfully captured.";
    }
    
    public boolean loginUser(String username, String password) {
        if (this.username != null && this.username.equals(username) && 
            this.password != null && this.password.equals(password)) {
            this.isLoggedIn = true;
            return true;
        }
        this.isLoggedIn = false;
        return false;
    }
    
    public String returnLoginStatus() {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}