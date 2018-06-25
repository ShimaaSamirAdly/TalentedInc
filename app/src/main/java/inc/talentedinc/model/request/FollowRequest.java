package inc.talentedinc.model.request;

import java.io.Serializable;

/**
 * Created by MMM on 6/25/2018.
 */

public class FollowRequest implements Serializable {

    private int id;
    private int userToFollowId;

    public int getId() {
        return id;
    }

    public int getUserToFollowId() {
        return userToFollowId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserToFollowId(int userToFollowId) {
        this.userToFollowId = userToFollowId;
    }
}
