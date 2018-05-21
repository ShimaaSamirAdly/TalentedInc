package inc.talentedinc.utilitis;

/**
 * Created by asmaa on 05/21/2018.
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

public abstract class EndlessAdapter<T> extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_OTHER = 1;

    private ArrayList<T> data = new ArrayList<>();
    private boolean isLoading = false;

    private int loadingLayout;
    private View loadingView;

    public EndlessAdapter(LinearLayoutManager linearLayoutManager) {
    }

    public EndlessAdapter(@Nullable GridLayoutManager layoutManager) {

        if(layoutManager != null) {
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isLoading && position == data.size()) {
                        return 1;
                    } else {
                        return 1;
                    }
                }
            });

        }
    }

    public void setData(ArrayList<T> data) {
        this.data.clear();
        this.data.addAll(data);
        isLoading = false;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<T> data) {
        this.data.addAll(data);
        isLoading = false;
        notifyDataSetChanged();
    }

    public void setLoading(boolean value) {
        isLoading = value;
        notifyDataSetChanged();
    }

    public void setLoadingView(@LayoutRes int layout) {
        loadingLayout = layout;
    }

    public void setLoadingView(View view) {
        loadingView = view;
    }

    public T getDataItem(int position) {
        return data.get(position);
    }

    protected abstract RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent);

    protected abstract void bindMyViewController(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        if (isLoading && position == data.size()) {
            return VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_OTHER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingViewHolder(createLoadingView(parent));
        } else {
            return createMyViewHolder(parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() != VIEW_TYPE_LOADING) {
            bindMyViewController(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return isLoading ? data.size() + 1 : data.size();
    }

    @NonNull
    private View createLoadingView(ViewGroup parent) {

        if (loadingLayout == 0) {
            LinearLayout layout = new LinearLayout(parent.getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(params);

            ProgressBar progressBar = new ProgressBar(parent.getContext());
            progressBar.setIndeterminate(true);
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            layout.addView(progressBar);

            return layout;
        } else if (loadingView != null) {

            return loadingView;
        } else {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return inflater.inflate(loadingLayout, parent, false);

        }

    }

    public void updateItem(int position, T dataItem) {
        data.set(position,dataItem);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {

        private LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }


}
