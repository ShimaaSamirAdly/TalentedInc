package inc.talentedinc.presenter;

import android.util.Log;

import java.util.List;

import inc.talentedinc.interactor.categories.CategoriesInteractor;
import inc.talentedinc.interactor.categories.CategoriesInteractorImpl;
import inc.talentedinc.interactor.createcourse.CreateCourseImpl;
import inc.talentedinc.interactor.createcourse.CreateCourseInter;
import inc.talentedinc.listener.CategoriesListener;
import inc.talentedinc.listener.OnCreateCourse;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.Course;
import inc.talentedinc.model.response.CreateCourseResponse;

/**
 * Created by Alaa on 6/1/2018.
 */

public class CreateCoursePresenter  {

    CreateCourseView createCourseView ;
    private CreateCourseInter createCourseInter ;

    public CreateCoursePresenter(CreateCourseView createCourseView){
        this.createCourseView =createCourseView ;
        createCourseInter = new CreateCourseImpl();
    }

    public void courseCreated(Course course){
        createCourseInter.createCourse(course, new OnCreateCourse() {
            @Override
            public void onSuccess(CreateCourseResponse object) {
                if(object!=null) {
                    Log.i("courseCreated", object.getCourseId().toString());
                    createCourseView.successToCreateCourse(object.getCourseId());
                }
                else
                {
                    Log.i("objectISNull","gah b null");
                }
            }

            @Override
            public void onFail() {
                createCourseView.failToCreateCourse();
            }
        });

    }






    public interface CreateCourseView {
       void successToCreateCourse(int courseId);
       void failToCreateCourse();

    }
}
