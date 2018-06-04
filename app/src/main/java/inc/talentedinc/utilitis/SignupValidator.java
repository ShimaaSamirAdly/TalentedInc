package inc.talentedinc.utilitis;

import java.util.Calendar;

public class SignupValidator {

//    ..................................mina..............................................

    private static volatile SignupValidator signupValidator;

    public static SignupValidator getValidationInstance(){
        if (signupValidator == null){
            synchronized (SignupValidator.class){
                if (signupValidator == null){
                    signupValidator = new SignupValidator();
                }
            }
        }
        return signupValidator;
    }

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String phonePattern = "(201)[0-5]{1}[0-9]{8}";

    public boolean validatemail(String userMail) {
        return userMail != null && userMail.matches(emailPattern) && userMail.length() > 0;
    }

    public boolean validatePhone(String userPhone){
        return userPhone != null && userPhone.matches(phonePattern) && userPhone.length() > 0;
    }

    public boolean validatePassword(String userPassword){
        return userPassword != null && userPassword.length() > 6;
    }

    public boolean validateDob(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - year > 10;
    }

    public boolean validateNotEmptyString(String data) {
        return data != null && data.length() > 0;
    }
//.................................................................................................

}
