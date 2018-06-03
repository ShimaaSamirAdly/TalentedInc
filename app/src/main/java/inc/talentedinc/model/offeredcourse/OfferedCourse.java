package inc.talentedinc.model.offeredcourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import inc.talentedinc.model.User;

public class OfferedCourse {

    @SerializedName("offeredCourseId")
    @Expose
    private Integer offeredCourseId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("instructorId")
    @Expose
    private OfferedCourseInstructor instructorId;
    @SerializedName("hostingWorkSpaceId")
    @Expose
    private OfferedCourseWorkspace hostingWorkSpaceId;

    private User courseCreator;

    public Integer getOfferedCourseId() {
        return offeredCourseId;
    }

    public void setOfferedCourseId(Integer offeredCourseId) {
        this.offeredCourseId = offeredCourseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OfferedCourseInstructor getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(OfferedCourseInstructor instructorId) {
        this.instructorId = instructorId;
    }

    public OfferedCourseWorkspace getHostingWorkSpaceId() {
        return hostingWorkSpaceId;
    }

    public void setHostingWorkSpaceId(OfferedCourseWorkspace hostingWorkSpaceId) {
        this.hostingWorkSpaceId = hostingWorkSpaceId;
    }

    public User getCourseCreator() {
        return courseCreator;
    }

    public void setCourseCreator(User courseCreator) {
        this.courseCreator = courseCreator;
    }
}
