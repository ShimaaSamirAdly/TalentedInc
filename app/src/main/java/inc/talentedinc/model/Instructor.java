package inc.talentedinc.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by MMM on 6/1/2018.
 */

public class Instructor implements Serializable{

        private Integer userId;

        private Double totalRate;

//        private Collection<WorkSpace> workSpaceCollection;
        private Collection<Categories> categoryCollection;

//        private Collection<OfferedCourse> offeredCourseCollection;
//        @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
//        private Collection<InstructorRequestOfferedCourse> instructorRequestOfferedCourseCollection;
//        @OneToOne(cascade = CascadeType.ALL, mappedBy = "instructor")
//        private InstructorVideos instructorVideos;
//
//        private Collection<Skills> skillsCollection;

//        private Admin adminApprovedrId;

        private User user;

        private Collection<InstructorImages> instructorImagesCollection;

        private Collection<InstructorVideos> instructorUrlsCollection;

        public Instructor() {
        }

        public Instructor(Integer userId) {
            this.userId = userId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Double getTotalRate() {
            return totalRate;
        }

        public void setTotalRate(Double totalRate) {
            this.totalRate = totalRate;
        }

//        public Collection<WorkSpace> getWorkSpaceCollection() {
//            return workSpaceCollection;
//        }
//
//        public void setWorkSpaceCollection(Collection<WorkSpace> workSpaceCollection) {
//            this.workSpaceCollection = workSpaceCollection;
//        }

        public Collection<Categories> getCategoryCollection() {
            return categoryCollection;
        }

        public void setCategoryCollection(Collection<Categories> categoryCollection) {
            this.categoryCollection = categoryCollection;
        }

//        public Collection<OfferedCourse> getOfferedCourseCollection() {
//            return offeredCourseCollection;
//        }
//
//        public void setOfferedCourseCollection(Collection<OfferedCourse> offeredCourseCollection) {
//            this.offeredCourseCollection = offeredCourseCollection;
//        }

//        public Collection<InstructorRequestOfferedCourse> getInstructorRequestOfferedCourseCollection() {
//            return instructorRequestOfferedCourseCollection;
//        }
//
//        public void setInstructorRequestOfferedCourseCollection(Collection<InstructorRequestOfferedCourse> instructorRequestOfferedCourseCollection) {
//            this.instructorRequestOfferedCourseCollection = instructorRequestOfferedCourseCollection;
//        }

//        public InstructorVideos getInstructorVideos() {
//            return instructorVideos;
//        }
//
//        public void setInstructorVideos(InstructorVideos instructorVideos) {
//            this.instructorVideos = instructorVideos;
//        }

//        public Collection<Skills> getSkillsCollection() {
//            return skillsCollection;
//        }
//
//        public void setSkillsCollection(Collection<Skills> skillsCollection) {
//            this.skillsCollection = skillsCollection;
//        }
//
//        public Admin getAdminApprovedrId() {
//            return adminApprovedrId;
//        }
//
//        public void setAdminApprovedrId(Admin adminApprovedrId) {
//            this.adminApprovedrId = adminApprovedrId;
//        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Collection<InstructorImages> getInstructorImagesCollection() {
            return instructorImagesCollection;
        }

        public void setInstructorImagesCollection(Collection<InstructorImages> instructorImagesCollection) {
            this.instructorImagesCollection = instructorImagesCollection;
        }

        public Collection<InstructorVideos> getInstructorUrlsCollection() {
            return instructorUrlsCollection;
        }

        public void setInstructorUrlsCollection(Collection<InstructorVideos> instructorUrlsCollection) {
            this.instructorUrlsCollection = instructorUrlsCollection;
        }



}
