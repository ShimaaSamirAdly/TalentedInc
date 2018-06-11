package inc.talentedinc.listener;

import inc.talentedinc.model.WorkSpace;

/**
 * Created by Alaa on 6/9/2018.
 */

public interface OnReceivedWorkSpaceProfile {

    public void onSuccess(WorkSpace workSpace);
    public void onFail();
}
