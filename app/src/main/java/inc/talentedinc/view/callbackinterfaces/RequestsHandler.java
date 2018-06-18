package inc.talentedinc.view.callbackinterfaces;

import java.util.ArrayList;

import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;

public interface RequestsHandler {
    void viewRequests(ArrayList<OfferedCourseWorkspace> offeredCourseWorkspaces);

    void workspaceAcceptedSuccessfully();

    void gotoWorkspaceProfile(int workspaceId);
}
