
package inc.talentedinc.model;

/**
 * Created by asmaa on 05/21/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {
    private Integer offeredCourseId;
    private String name;
    private Integer durationHours;
    private String startDate;
    private String endDate;
    private String description;
    private Integer noOfApplicant;
    private Integer cost;
    private String imageUrl;
    private String categoryName;
    private String workSpaceName;
    private Integer idOfInstructor;
    private Integer idOfWorkSpace;
    private Integer numberOfLikes;
    private Integer numberOfComments;
    private Boolean liked;
    private Boolean registered;
    private Boolean rated;
    private Integer courseStatus;
    private String nameOfInstructor;
    private String workSpaceAddress;
    private ArrayList<CourseComment> courseComments;
    private String publishedDate;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getWorkSpaceName() {
        return workSpaceName;
    }

    public void setWorkSpaceName(String workSpaceName) {
        this.workSpaceName = workSpaceName;
    }

    public Integer getIdOfInstructor() {
        return idOfInstructor;
    }

    public void setIdOfInstructor(Integer idOfInstructor) {
        this.idOfInstructor = idOfInstructor;
    }

    public Integer getIdOfWorkSpace() {
        return idOfWorkSpace;
    }

    public void setIdOfWorkSpace(Integer idOfWorkSpace) {
        this.idOfWorkSpace = idOfWorkSpace;
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

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public Boolean getRated() {
        return rated;
    }

    public void setRated(Boolean rated) {
        this.rated = rated;
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getNameOfInstructor() {
        return nameOfInstructor;
    }

    public void setNameOfInstructor(String nameOfInstructor) {
        this.nameOfInstructor = nameOfInstructor;
    }

    public ArrayList<CourseComment> getCourseComments() {
        return courseComments;
    }

    public void setCourseComments(ArrayList<CourseComment> courseComments) {
        this.courseComments = courseComments;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getWorkSpaceAddress() {
        return workSpaceAddress;
    }

    public void setWorkSpaceAddress(String workSpaceAddress) {
        this.workSpaceAddress = workSpaceAddress;
    }
}