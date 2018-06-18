package inc.talentedinc.presenter;

import inc.talentedinc.interactor.workspaceprofile.WorkSpaceProfileImpl;
import inc.talentedinc.interactor.workspaceprofile.WorkSpaceProfileInter;
import inc.talentedinc.listener.OnReceivedWorkSpaceProfile;
import inc.talentedinc.model.WorkSpace;
import inc.talentedinc.view.activities.WorkSpaceProfile;

/**
 * Created by Alaa on 6/9/2018.
 */

public class WorkSpaceProfilePresenter {
    WorkSpaceProfileView workSpaceProfileView ;
    WorkSpaceProfileInter workSpaceProfileInter ;



    public WorkSpaceProfilePresenter ( WorkSpaceProfileView workSpaceProfileView){
        this.workSpaceProfileView = workSpaceProfileView;
        workSpaceProfileInter = new WorkSpaceProfileImpl();

    }

    public void getWorkSpaceData(Integer workSpaceId){
        workSpaceProfileInter.receivedProfile( workSpaceId, new OnReceivedWorkSpaceProfile() {
            @Override
            public void onSuccess(WorkSpace workSpace) {
                workSpaceProfileView.sendWorkSpaceData(workSpace);
            }

            @Override
            public void onFail() {
                workSpaceProfileView.failToSendWorkSapceData();
            }
        });



    }




    public interface WorkSpaceProfileView{
        public void sendWorkSpaceData(WorkSpace workSpace);
        public void failToSendWorkSapceData();
    }
}
