package inc.talentedinc.view.callbackinterfaces;

public interface SignupActivityHandler {

    void setUserData(String email,String password,String phone,String city);
    void moveToNextPage();
}
