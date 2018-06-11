package inc.talentedinc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import inc.talentedinc.R;
import inc.talentedinc.listener.AdapterListener;
import inc.talentedinc.model.Instructor;
import inc.talentedinc.model.recycleview.RecycleModel;
import inc.talentedinc.presenter.BecomeInstructorPresenter;
import inc.talentedinc.presenter.BecomeInstructorPresenterImpl;
import inc.talentedinc.viewholder.PortofolioViewHolder;
import inc.talentedinc.viewholder.SkillsViewHolder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by MMM on 6/8/2018.
 */

public class MultiViewAdapter extends RecyclerView.Adapter {

    private ArrayList<RecycleModel> data;
    Context mContext;
    SkillsGridAdapter adapter;
    private ImageView firstImg;
    private ImageView secondImg;
    private EditText url;
    private AdapterListener listener;
    private ArrayList<Uri> imgPaths;
    private BecomeInstructorPresenter presenter;
    private ArrayList<String> skills;

    public MultiViewAdapter(ArrayList<RecycleModel>data, Context context, AdapterListener listener, BecomeInstructorPresenter presenter) {
        this.data = data;
        this.mContext = context;
        this.listener = listener;
        imgPaths = new ArrayList<Uri>();
        imgPaths.add(null);
        imgPaths.add(null);
        this.presenter = presenter;
        skills = new ArrayList<>();


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
            case RecycleModel.SKILLS_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_layout, parent, false);
                return new SkillsViewHolder(view);

            case RecycleModel.PORTOFOLIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portofoliolayout, parent, false);
                return new PortofolioViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final RecycleModel model = data.get(position);
        if(model != null){
            switch (model.type){
                case RecycleModel.SKILLS_TYPE:
                    adapter = new SkillsGridAdapter(mContext, model.skills);

                    skills = model.skills;

                    ((SkillsViewHolder)holder).getSkillsData().setAdapter(adapter);

                    ((SkillsViewHolder)holder).getAddSkill().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String skill = ((SkillsViewHolder)holder).getSkillText().getText().toString();
                            if(!skill.isEmpty() && !skill.startsWith(" ") && !skill.endsWith(" ")){
                                if(skill.length() > 12){
                                    Toast.makeText(mContext, "Max Text Length is 12 Character", Toast.LENGTH_LONG).show();
                                }else {
                                    skills.add(skill);
                                    adapter.setSkills(skills);
                                    Log.i("modelskill", "" + skills.size());
                                    adapter.notifyDataSetChanged();
                                    ((SkillsViewHolder) holder).getSkillText().setText("");
                                }
                            }else{
                                Toast.makeText(mContext, "Invalid Skill", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    break;


                case RecycleModel.PORTOFOLIO_TYPE:

                    firstImg = ((PortofolioViewHolder)holder).getFirstImg();
                    secondImg = ((PortofolioViewHolder)holder).getSecondImg();
                    url = ((PortofolioViewHolder)holder).getVideoUrl();

                    firstImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            listener.onClickedImage(0, firstImg);
                        }
                    });

                    secondImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onClickedImage(1, secondImg);
                        }
                    });

                    ((PortofolioViewHolder)holder).getSubmitInstructor().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.createInstructor(skills, imgPaths, url.getText().toString());
                        }
                    });
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position){

        switch (data.get(position).type) {
            case 0:
                return RecycleModel.SKILLS_TYPE;
            case 1:
                return RecycleModel.PORTOFOLIO_TYPE;
            case 2:
                return RecycleModel.VIDEOS_TYPE;
            default:
                return -1;
        }
    }

//    public Instructor getData(){
//
//    }

    public void addImg(Uri imgPath, int index){

        this.imgPaths.set(index, imgPath);
    }


}
