package inc.talentedinc.view.callbackinterfaces;

public interface RequestListener {

    void courseRequestCanceled(int position);
    void errorCancelingCopurse(int position);
    void makeToastRequestResult(int result,int position);
}
