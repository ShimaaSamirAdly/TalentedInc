package inc.talentedinc.view.activities;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Set;

import inc.talentedinc.R;
import inc.talentedinc.model.User;
import inc.talentedinc.view.callbackinterfaces.SetDateTextView;
import inc.talentedinc.view.fragmnts.DatePickerFragment;

public class SignUpActivity extends AppCompatActivity implements SetDateTextView,AdapterView.OnItemSelectedListener {

    /******************************mina*************************/
    private TextView dobTxtVw;
    private Spinner citiesSpinner;
    private User signedUpUser = new User();
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText phone;
    private RadioButton male;
    private RadioButton female;
    /***********************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /******************************mina*************************/


        final DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setDateSetter(this);

        Button dobBtn = (Button) findViewById(R.id.dob_btn);
        dobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        dobTxtVw = (TextView) findViewById(R.id.dob_txtvw);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        Button signupBtn = (Button) findViewById(R.id.sinup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignup();
            }
        });

        //setting cities spinner
        citiesSpinner = (Spinner) findViewById(R.id.cities_spinner);
        citiesSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        citiesSpinner.setAdapter(adapter);

        /***********************************************************/
    }


    /******************************mina*************************/

    @Override
    public void setDateTextView(String date) {
        dobTxtVw.setText(date);
        signedUpUser.setUserDob(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0 :
                signedUpUser.setCity("Cairo");
                break;
            case 1 :
                signedUpUser.setCity("Alexandria");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        signedUpUser.setCity("");
    }


    private void performSignup(){
        signedUpUser.setFirstName(firstName.getText().toString());
        signedUpUser.setLastName(lastName.getText().toString());
        signedUpUser.setEmail(email.getText().toString());
        signedUpUser.setPassword(password.getText().toString());
        signedUpUser.setPhone(phone.getText().toString());
        if(male.isSelected()){
            signedUpUser.setGender('m');
        }

        if (female.isSelected()){
            signedUpUser.setGender('f');
        }

        //intent to go to interests

    }

    /***********************************************************/
}
