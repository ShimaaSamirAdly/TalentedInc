package inc.talentedinc.utilitis;

/**
 * Created by asmaa on 05/21/2018.
 */

public class ValidationUtility {
    public static boolean validateEmptyString(String string){
        if(string.equals("") || string.trim().length()==0 || string.equals(null) || string == null){
            return false;
        }

        return true;
    }
}
