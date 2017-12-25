package services;

import entities.User;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class UserRegistrator {
    static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", Pattern.CASE_INSENSITIVE);
    static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("/^[a-z0-9_-]{8,18}$/", Pattern.CASE_INSENSITIVE);

    public UserRegistrator() {
    }

    public static boolean registrate(User user) {
        if (UserRegistrator.validateEmail(user,user.getEmail())&&UserRegistrator.validatePhone(user,user.getPhone())&&
                UserRegistrator.validatePassword(user,user.getPassword())){
        return true;
        }
        else {
            return false;
        }
    }
    public static boolean validateEmail(User user,String emailStr){
        emailStr = user.getEmail();
        Matcher matcher1 = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher1.find();
    }
    public static boolean validatePhone(User user, String phone){
        phone = user.getPhone();
        Matcher matcher2 = VALID_PHONE_NUMBER_REGEX.matcher(phone);
        return matcher2.find();
    }
    public static boolean validatePassword(User user, String password){
        password = user.getPassword();
        Matcher matcher3 = VALID_PASSWORD_REGEX.matcher(password);
        return matcher3.find();
    }

}
