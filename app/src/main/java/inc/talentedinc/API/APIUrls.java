package inc.talentedinc.API;

/**
 * Created by asmaa on 05/18/2018.
 */

/**
 * constant urls
 */
public class APIUrls {
//****************************** Asmaa *************************************************
   public static final int SUCCESS =200;
   public static final String finishedCourses ="finishedCourses";
   public static final String BASE_URL="https://tal-company.herokuapp.com";


  // public static final String BASE_URL="http://172.16.5.177:3000";



   public static final String UPCOMING="/publishedCourses/upComing";
   public static final String SEARCH_BY_NAME="/publishedCourses/searchUpComing";
   public static final String SEARCH_BY_FILTER="/publishedCourses/searchUpComing/filter";
   public static final String HISTORY="/users";
   public static final String REGISTER_COURSE="/publishedCourses/register";
   public static final String UNREGISTER_COURSE="/publishedCourses/unRegister";
   public static final String LIKE="/publishedCourses/LikeCourse";
   public static final String DISLIKE="publishedCourses/disLikeCourse";
   public static final String COMMENT="/publishedCourses/commentOnCourse";
   public static final String RATE="/publishedCourses/rate";
//*****************************************************************************************

   public static final String WORK_SPACE_PROFILE = "workspace/getWorkSpaceProgile" ;

    //alaa
    public static final String USER_LOGIN = "/users/login";
    public static final String CREATE_COURSE = "/offeredcourse/createofferedcoursebyinstructor";

    //    ........................................mina................................................
   // public static final String BASE_URL = "https://f940191e-5b7a-4c0d-8e45-05b482b2e6e8.mock.pstmn.io/";
//    ...............................................................................................


}
