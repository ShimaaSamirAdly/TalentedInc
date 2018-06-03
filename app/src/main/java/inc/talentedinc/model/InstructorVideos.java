package inc.talentedinc.model;

import java.io.Serializable;

/**
 * Created by MMM on 6/1/2018.
 */

public class InstructorVideos implements Serializable{

    private int instructorId;
    private String urlValue;

    public InstructorVideos() {
    }

    public InstructorVideos(int instructorId, String urlValue) {
        this.instructorId = instructorId;
        this.urlValue = urlValue;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

}
