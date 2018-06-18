package inc.talentedinc.listener;

/**
 * Created by MMM on 6/2/2018.
 */

public interface SignUpListener {

    public void onSuccess(int userId);

    public void onFailure(String error);

    public void onFailedConnection();
}
