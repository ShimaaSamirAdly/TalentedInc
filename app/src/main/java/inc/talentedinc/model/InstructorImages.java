package inc.talentedinc.model;

import java.io.Serializable;

/**
 * Created by MMM on 6/1/2018.
 */

public class InstructorImages implements Serializable{

    private int instructorId;

    private String imageUrl;

    public InstructorImages(int instructorId, String imageUrl) {
        this.instructorId = instructorId;
        this.imageUrl = imageUrl;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
