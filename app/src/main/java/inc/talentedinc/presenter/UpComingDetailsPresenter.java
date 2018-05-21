package inc.talentedinc.presenter;

import inc.talentedinc.model.Result;

/**
 * Created by asmaa on 05/21/2018.
 */

public class UpComingDetailsPresenter {

    private Result data = new Result();

    private ViewUpComingDetails view;

    public UpComingDetailsPresenter(){
    }

    public void setView(Result data, ViewUpComingDetails view) {
        this.view=view;
        setDetailsData(data);
    }

    public void setDetailsData(Result data) {
        this.data = data;
        if (data!=null){
            view.setId(data.getId());
            view.setPoster(data.getBackdrop_path());
            view.setVote(data.getVote_average());
            view.setTitle(data.getTitle());
            view.setOverview(data.getOverview());
        }
    }

    public interface ViewUpComingDetails {
        void showProgress();
        void hideProgress();
        void setTitle(String title);
        void setOverview(String overview);
        void setPoster(String img);
        void setVote(float vote);
        void setId(int id);
        void isFav(boolean isFav);
        void showDone(String msg);
    }



}
