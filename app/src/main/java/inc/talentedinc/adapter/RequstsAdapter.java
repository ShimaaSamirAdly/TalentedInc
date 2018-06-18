package inc.talentedinc.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.model.offeredcourse.OfferedCourseWorkspace;
import inc.talentedinc.presenter.RequestsPresenter;
import inc.talentedinc.viewholder.OfferedCoursesViewHolder;
import inc.talentedinc.viewholder.RequestsViewHolder;

public class RequstsAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    private ArrayList<OfferedCourseWorkspace> requestingWorkspaces;
    private RequestsPresenter requestsPresenter;
    private View requestView;
    private RequestsViewHolder requestsViewHolder;
    private int courseId;

    public RequstsAdapter(ArrayList<OfferedCourseWorkspace> requestingWorkspaces) {
        this.requestingWorkspaces = requestingWorkspaces;
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        requestView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.request_row, parent, false);
        requestsViewHolder = new RequestsViewHolder(requestView);
        return requestsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsViewHolder holder, final int position) {

        holder.getWorkspaceName().setText(requestingWorkspaces.get(position).getName());
        holder.getWorkspaceName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestsPresenter.gotoWorkspace(requestingWorkspaces.get(position).getWorkSpaceId());
            }
        });
        holder.getAcceptButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestsPresenter.acceptWorkspace(courseId,requestingWorkspaces.get(position).getWorkSpaceId());
            }
        });
    }

    @Override
    public int getItemCount() {

        return requestingWorkspaces.size();
    }

    public void setRequestsPresenter(RequestsPresenter requestsPresenter) {
        this.requestsPresenter = requestsPresenter;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
