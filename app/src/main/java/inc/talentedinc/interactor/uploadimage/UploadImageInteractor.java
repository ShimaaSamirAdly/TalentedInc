package inc.talentedinc.interactor.uploadimage;

import android.net.Uri;

import inc.talentedinc.listener.UploadImageListener;

/**
 * Created by MMM on 6/1/2018.
 */

public interface UploadImageInteractor {

    public void uploadImage(Uri filePath, UploadImageListener listener);
}
