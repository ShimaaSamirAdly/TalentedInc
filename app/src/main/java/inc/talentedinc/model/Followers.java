package inc.talentedinc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MMM on 6/11/2018.
 */

public class Followers {


    @SerializedName("userId")
    @Expose
    private Integer userId;
    //required
    @SerializedName("firstName")
    @Expose
    private String firstName;
    //required
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImgUrl() {
        return imgUrl;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
