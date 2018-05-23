package inc.talentedinc.view.fragmnts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import inc.talentedinc.view.callbackinterfaces.SetDateTextView;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private SetDateTextView dateSetter;

    public DatePickerFragment(){}

    public void setDateSetter(SetDateTextView dateSetter) {
        this.dateSetter = dateSetter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = ""+day+"/"+month+"/"+year;
        dateSetter.setDateTextView(date);
    }
}
