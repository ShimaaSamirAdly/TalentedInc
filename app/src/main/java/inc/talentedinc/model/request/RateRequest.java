package inc.talentedinc.model.request;

import java.io.Serializable;

/**
 * Created by asmaa on 06/25/2018.
 */

public class RateRequest extends MainRequest implements Serializable {
    private Float courseRate;
    private Float instructorRate;
    private Float workSpaceRate;


    public Float getCourseRate() {
        return courseRate;
    }

    public void setCourseRate(Float courseRate) {
        this.courseRate = courseRate;
    }

    public Float getInstructorRate() {
        return instructorRate;
    }

    public void setInstructorRate(Float instructorRate) {
        this.instructorRate = instructorRate;
    }

    public Float getWorkSpaceRate() {
        return workSpaceRate;
    }

    public void setWorkSpaceRate(Float workSpaceRate) {
        this.workSpaceRate = workSpaceRate;
    }
}
