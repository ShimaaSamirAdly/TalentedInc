package inc.talentedinc.model;

import java.util.Collection;

/**
 * Created by MMM on 6/11/2018.
 */

public class OtherUsers {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String userDob;

    private String email;

    private Character gender;

    private String imgUrl;

    private String phone;

    private String city;
    private boolean following;

    private Integer userType;

    private Instructor instructor;

    private int followingNumber;

    private int followersNumber;

    private Collection<Categories> categoryCollection;

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserDob() {
        return userDob;
    }

    public String getEmail() {
        return email;
    }

    public Character getGender() {
        return gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCity() {
        return city;
    }

    public boolean isFollowing() {
        return following;
    }

    public Integer getUserType() {
        return userType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public int getFollowingNumber() {
        return followingNumber;
    }

    public int getFollowersNumber() {
        return followersNumber;
    }

    public Collection<Categories> getCategoryCollection() {
        return categoryCollection;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setFollowingNumber(int followingNumber) {
        this.followingNumber = followingNumber;
    }

    public void setFollowersNumber(int followersNumber) {
        this.followersNumber = followersNumber;
    }

    public void setCategoryCollection(Collection<Categories> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
