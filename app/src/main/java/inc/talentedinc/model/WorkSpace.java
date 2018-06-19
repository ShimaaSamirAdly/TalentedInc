package inc.talentedinc.model;

/**
 * Created by Alaa on 6/8/2018.
 */


import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkSpace implements Serializable {

    @SerializedName("workSpaceId")
    @Expose
    private Integer workSpaceId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("closingTime")
    @Expose
    private String closingTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("facebookPageUrl")
    @Expose
    private String facebookPageUrl;
    @SerializedName("holiday")
    @Expose
    private String holiday;
    @SerializedName("imageUrl")
    @Expose
    private Object imageUrl;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longtitude")
    @Expose
    private Object longtitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("noOfRooms")
    @Expose
    private Integer noOfRooms;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("totalRate")
    @Expose
    private Object totalRate;
    @SerializedName("instructorCollection")
    @Expose
    private List<Object> instructorCollection = null;
    @SerializedName("userTableCollection")
    @Expose
    private List<Object> userTableCollection = null;
    @SerializedName("approvingAdminId")
    @Expose
    private Object approvingAdminId;
    @SerializedName("workSpacePhonesCollection")
    @Expose
    private List<Object> workSpacePhonesCollection = null;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

    public Integer getWorkSpaceId() {
        return workSpaceId;
    }

    public void setWorkSpaceId(Integer workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookPageUrl() {
        return facebookPageUrl;
    }

    public void setFacebookPageUrl(String facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Object longtitude) {
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Object getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Object totalRate) {
        this.totalRate = totalRate;
    }

    public List<Object> getInstructorCollection() {
        return instructorCollection;
    }

    public void setInstructorCollection(List<Object> instructorCollection) {
        this.instructorCollection = instructorCollection;
    }

    public List<Object> getUserTableCollection() {
        return userTableCollection;
    }

    public void setUserTableCollection(List<Object> userTableCollection) {
        this.userTableCollection = userTableCollection;
    }

    public Object getApprovingAdminId() {
        return approvingAdminId;
    }

    public void setApprovingAdminId(Object approvingAdminId) {
        this.approvingAdminId = approvingAdminId;
    }

    public List<Object> getWorkSpacePhonesCollection() {
        return workSpacePhonesCollection;
    }

    public void setWorkSpacePhonesCollection(List<Object> workSpacePhonesCollection) {
        this.workSpacePhonesCollection = workSpacePhonesCollection;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }


}