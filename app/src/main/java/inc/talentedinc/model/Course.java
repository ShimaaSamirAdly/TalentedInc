package inc.talentedinc.model;

//<<<<<<< HEAD

/**
 * Created by Alaa on 5/24/2018.
 */

public class Course {
    private String courseName ;
    private String startDate ;
    private String endDate ;
    private Integer cost ;
    private Integer numOfApplicants ;
    private String description ;
    private String category ;
    private Integer duration;

//  private Integer instructorId ;

    //ana hna na2s el imstructor


    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setNumOfApplicants(Integer numOfApplicants) {
        this.numOfApplicants = numOfApplicants;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getNumOfApplicants() {
        return numOfApplicants;
    }

    public String getCategory() {
        return category;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;

    }
//  ----------------------- 7aget mena -----------------------------------------
//public class Course {
//
//    private PublishedCoursePK publishedCoursePK;
//    private int publishApproval;
//    private int status;
//    private int applicantNoCompleted;
//
//    public Course() {
//    }
//
//    public PublishedCoursePK getPublishedCoursePK() {
//        return publishedCoursePK;
//    }
//
//    public void setPublishedCoursePK(PublishedCoursePK publishedCoursePK) {
//        this.publishedCoursePK = publishedCoursePK;
//    }
//
//    public int getPublishApproval() {
//        return publishApproval;
//    }
//
//    public void setPublishApproval(int publishApproval) {
//        this.publishApproval = publishApproval;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getApplicantNoCompleted() {
//        return applicantNoCompleted;
//    }
//
//    public void setApplicantNoCompleted(int applicantNoCompleted) {
//        this.applicantNoCompleted = applicantNoCompleted;
////>>>>>>> d4b815582bb4fa6f706e8785c555d77af4e0387d
//    }
}
