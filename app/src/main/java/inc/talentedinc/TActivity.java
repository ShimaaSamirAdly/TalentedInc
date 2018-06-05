package inc.talentedinc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class TActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_details);

        LinearLayout myRoot = (LinearLayout) findViewById(R.id.ll);
        LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.VERTICAL);
        View child = getLayoutInflater().inflate(R.layout.comments_row_design, null);
        View child2 = getLayoutInflater().inflate(R.layout.comments_row_design, null);
        a.addView(child);
        a.addView(child2);
        myRoot.addView(a);
    }
}
