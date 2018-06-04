
package inc.talentedinc.model;

/**
 * Created by asmaa on 05/21/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable
{

    private Integer offeredCourseId;
    private String name;
    private Integer durationHours;
    private String startDate;
    private String endDate;
    private String description;
    private Integer noOfApplicant;
    private Integer cost;
    private Object imageUrl;
    private CategoryId categoryId;
    private InstructorId instructorId;
    private HostingWorkSpaceId hostingWorkSpaceId;
    private Integer numberOfLikes;
    private Integer numberOfComments;
    private Boolean liked;
    private ArrayList<CourseComment> courseComments ;
    private final static long serialVersionUID = -8458613531321444255L;

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

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
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

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryId categoryId) {
        this.categoryId = categoryId;
    }

    public InstructorId getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(InstructorId instructorId) {
        this.instructorId = instructorId;
    }

    public HostingWorkSpaceId getHostingWorkSpaceId() {
        return hostingWorkSpaceId;
    }

    public void setHostingWorkSpaceId(HostingWorkSpaceId hostingWorkSpaceId) {
        this.hostingWorkSpaceId = hostingWorkSpaceId;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public ArrayList<CourseComment> getCourseComments() {
        return courseComments;
    }

    public void setCourseComments(ArrayList<CourseComment> courseComments) {
        this.courseComments = courseComments;
    }

}
