package inc.talentedinc.view.fragmnts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import inc.talentedinc.R;
import inc.talentedinc.model.User;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;


public class SecondSignUpFragment extends Fragment implements SetDateTextView {

//    ..................................... mina ....................................
    private EditText firstNameTxt;
    private EditText lastNameTxt;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private Button dobBtn;
    private TextView dobTxtView;
    private FragmentManager supportFragmentManager;
    private User user = new User();
//    ...................................................................................

    public SecondSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        ..................................... mina ................................

        // Inflate the layout for this fragment
        View secondSignupView = inflater.inflate(R.layout.fragment_second_sign_up, container, false);

        firstNameTxt = (EditText)secondSignupView.findViewById(R.id.first_name_edt_txt);
        lastNameTxt = (EditText)secondSignupView.findViewById(R.id.last_name_edt_txt);
        maleRadio = (RadioButton)secondSignupView.findViewById(R.id.male_radio);
        femaleRadio = (RadioButton)secondSignupView.findViewById(R.id.female_radio);
        dobTxtView = (TextView)secondSignupView.findViewById(R.id.dob_txt);
        //DOB button
        dobBtn = (Button)secondSignupView.findViewById(R.id.dob_btn);
        final DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setDateSetter(this);
        dobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment.show(supportFragmentManager, "datePicker");
            }
        });
//        ....................................................................................

        return secondSignupView;
    }

//    ............................................. mina .......................................
    @Override
    public void setDateTextView(String date) {
        dobTxtView.setText(date);
    }

    public void setSupportFragmentManager(FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
    }

    public User getUser() {
        fillUser();
        return user;
    }

    private void fillUser() {
        user.setFirstName(firstNameTxt.getText().toString());
        user.setLastName(lastNameTxt.getText().toString());
        user.setUserDob(dobTxtView.getText().toString());

        if (maleRadio.isChecked()) {
            user.setGender('m');
        }

        if (femaleRadio.isChecked()) {
            user.setGender('f');
        }

//        Log.i("userGen", user.getGender().toString());
    }
//    ........................................................................................
}
