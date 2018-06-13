package inc.talentedinc.model;

import java.io.Serializable;

/**
 * Created by MMM on 5/27/2018.
 */

public class Categories implements Serializable{

    private int categoryId;
    private String name;
    private String imgUrl;

    public Categories(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

//    ********************************* Asmaa **********************
    public String toString()
    {
        return name  ;
    }
//    **********************************************************
}

