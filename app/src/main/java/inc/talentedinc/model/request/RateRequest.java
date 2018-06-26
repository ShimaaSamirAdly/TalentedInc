package inc.talentedinc.model.request;

import java.io.Serializable;

/**
 * Created by asmaa on 06/25/2018.
 */

public class RateRequest extends MainRequest implements Serializable {
    private int courseRate;
    private int instructorRate;
    private int workSpaceRate;

    public int getCourseRate() {
        return courseRate;
    }

    public void setCourseRate(int courseRate) {
        this.courseRate = courseRate;
    }

    public int getInstructorRate() {
        return instructorRate;
    }

    public void setInstructorRate(int instructorRate) {
        this.instructorRate = instructorRate;
    }

    public int getWorkSpaceRate() {
        return workSpaceRate;
    }

    public void setWorkSpaceRate(int workSpaceRate) {
        this.workSpaceRate = workSpaceRate;
    }
}
