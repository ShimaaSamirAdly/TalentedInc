package inc.talentedinc.interactor.workspaceprofile;

import inc.talentedinc.listener.OnReceivedWorkSpaceProfile;

/**
 * Created by Alaa on 6/9/2018.
 */

public interface WorkSpaceProfileInter {
    public void receivedProfile( Integer workSpaceId ,OnReceivedWorkSpaceProfile receivedWorkSpaceProfile);
}
