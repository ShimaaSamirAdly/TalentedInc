package inc.talentedinc.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import inc.talentedinc.R;

public class RequestsViewHolder extends RecyclerView.ViewHolder {

    private View requestView;
    private TextView workspaceName;
    private Button acceptButton;

    public RequestsViewHolder(View itemView) {
        super(itemView);
        requestView = itemView;
    }

    public TextView getWorkspaceName() {
        if(workspaceName == null){
            workspaceName = requestView.findViewById(R.id.requester_txt);
        }
        return workspaceName;
    }

    public Button getAcceptButton() {
        if(acceptButton == null){
            acceptButton = requestView.findViewById(R.id.acccept_request_btn);
        }
        return acceptButton;
    }
}
