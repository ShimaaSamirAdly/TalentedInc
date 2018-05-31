package inc.talentedinc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class User {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userDob")
    @Expose
    private String userDob;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private Character gender;
    @SerializedName("fbToken")
    @Expose
    private String fbToken;
    @SerializedName("fbId")
    @Expose
    private String fbId;
    @SerializedName("twitterToken")
    @Expose
    private String twitterToken;
    @SerializedName("twitterId")
    @Expose
    private String twitterId;
    @SerializedName("googleToken")
    @Expose
    private String googleToken;
    @SerializedName("googleId")
    @Expose
    private String googleId;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("userType")
    @Expose
    private Integer userType;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("workSpaceCollection")
    @Expose
    private Collection<Object> workSpaceCollection = null;
    @SerializedName("userCollection")
    @Expose
    private Collection<Object> userCollection = null;
    @SerializedName("userCollection1")
    @Expose
    private Collection<Object> userCollection1 = null;
    @SerializedName("publishedCourseCollection")
    @Expose
    private Collection<Object> publishedCourseCollection = null;
    @SerializedName("categoryCollection")
    @Expose
    private Collection<Object> categoryCollection = null;
    @SerializedName("admin")
    @Expose
    private Object admin;
    @SerializedName("instructor")
    @Expose
    private Object instructor;
    @SerializedName("publishedCourseHasUserCollection")
    @Expose
    private Collection<Object> publishedCourseHasUserCollection = null;
    @SerializedName("userCommentOnPublishedCourseCollection")
    @Expose
    private Collection<Object> userCommentOnPublishedCourseCollection = null;


    public User() {
    }

    public User(String firstName, String lastName, String email, String fbToken, String fbId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fbToken = fbToken;
        this.fbId = fbId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getTwitterToken() {
        return twitterToken;
    }

    public void setTwitterToken(String twitterToken) {
        this.twitterToken = twitterToken;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Object> getWorkSpaceCollection() {
        return workSpaceCollection;
    }

    public void setWorkSpaceCollection(Collection<Object> workSpaceCollection) {
        this.workSpaceCollection = workSpaceCollection;
    }

    public Collection<Object> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<Object> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<Object> getUserCollection1() {
        return userCollection1;
    }

    public void setUserCollection1(Collection<Object> userCollection1) {
        this.userCollection1 = userCollection1;
    }

    public Collection<Object> getPublishedCourseCollection() {
        return publishedCourseCollection;
    }

    public void setPublishedCourseCollection(Collection<Object> publishedCourseCollection) {
        this.publishedCourseCollection = publishedCourseCollection;
    }

    public Collection<Object> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Object> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    public Object getAdmin() {
        return admin;
    }

    public void setAdmin(Object admin) {
        this.admin = admin;
    }

    public Object getInstructor() {
        return instructor;
    }

    public void setInstructor(Object instructor) {
        this.instructor = instructor;
    }

    public Collection<Object> getPublishedCourseHasUserCollection() {
        return publishedCourseHasUserCollection;
    }

    public void setPublishedCourseHasUserCollection(Collection<Object> publishedCourseHasUserCollection) {
        this.publishedCourseHasUserCollection = publishedCourseHasUserCollection;
    }

    public Collection<Object> getUserCommentOnPublishedCourseCollection() {
        return userCommentOnPublishedCourseCollection;
    }

    public void setUserCommentOnPublishedCourseCollection(Collection<Object> userCommentOnPublishedCourseCollection) {
        this.userCommentOnPublishedCourseCollection = userCommentOnPublishedCourseCollection;
    }
}
