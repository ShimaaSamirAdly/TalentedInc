package inc.talentedinc.model.recycleview;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by MMM on 6/8/2018.
 */

public class RecycleModel {

    public static final int SKILLS_TYPE = 0;
    public static final int PORTOFOLIO_TYPE = 1;
    public static final int VIDEOS_TYPE = 2;

    public int type;
    public ArrayList<String> skills;
    public ArrayList<String> videos;
    public ArrayList<Uri> filePath;

    public RecycleModel(int type, ArrayList<String> skills, ArrayList<String> videos, ArrayList<Uri> filePath){

        this.type = type;
        this.skills = skills;
        this.videos = videos;
        this.filePath = filePath;
    }
}
