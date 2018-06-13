package inc.talentedinc.model.offeredcourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import inc.talentedinc.model.User;

public class OfferedCourseDetailed implements Serializable {

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
//    @SerializedName("instructorId")
//    @Expose
//    private OfferedCourseInstructor instructorId;
    @SerializedName("hostingWorkSpaceId")
    @Expose
    private OfferedCourseWorkspace hostingWorkSpaceId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("noOfApplicant")
    @Expose
    private Integer noOfApplicant;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("durationHours")
    @Expose
    private Integer durationHours;
    @SerializedName("requested")
    @Expose
    private boolean requested;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfApplicant() {
        return noOfApplicant;
    }

    public void setNoOfApplicant(Integer noOfApplicant) {
        this.noOfApplicant = noOfApplicant;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

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

//    public OfferedCourseInstructor getInstructorId() {
//        return instructorId;
//    }
//
//    public void setInstructorId(OfferedCourseInstructor instructorId) {
//        this.instructorId = instructorId;
//    }

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

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }
}
