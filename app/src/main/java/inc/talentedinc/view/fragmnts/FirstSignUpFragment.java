package inc.talentedinc.view.fragmnts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import inc.talentedinc.R;
import inc.talentedinc.model.Categories;
import inc.talentedinc.model.User;
import inc.talentedinc.presenter.CategoriesPresenter;
import inc.talentedinc.view.callbackinterfaces.SignupActivityHandler;


public class FirstSignUpFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //    ............................. mina .............................
    private EditText emailTxt;
    private EditText passwordTxt;
    private EditText phoneTxt;
    private Spinner citiesSpinner;
    private User user = new User();
//    ...................................................................

    public FirstSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        ......................................... mina ........................................

        // Inflate the layout for this fragment
        View firstSignup = inflater.inflate(R.layout.fragment_first_sign_up, container, false);

        emailTxt = firstSignup.findViewById(R.id.email_edt_txt);
        passwordTxt = firstSignup.findViewById(R.id.password_edt_txt);
        phoneTxt = firstSignup.findViewById(R.id.phone_edt_txt);

        //setting cities spinner
        citiesSpinner = (Spinner) firstSignup.findViewById(R.id.city_spinner);
        citiesSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        citiesSpinner.setAdapter(adapter);

//        .........................................................................................

        return firstSignup;
    }

    //    ............................................. mina .....................................
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                user.setCity("Cairo");
                break;
            case 1:
                user.setCity("Alexandria");
                break;
            default:
                user.setCity("Alexandria");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        user.setCity("");
    }

    public User getUser() {
        fillUser();
        return user;
    }

    private void fillUser() {
        user.setEmail(emailTxt.getText().toString());
        user.setPassword(passwordTxt.getText().toString());
        user.setPhone(phoneTxt.getText().toString());
    }


//    ...........................................................................................

}
