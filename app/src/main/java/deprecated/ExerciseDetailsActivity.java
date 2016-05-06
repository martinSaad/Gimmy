/*
package deprecated;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.martinsaad.hackidc.R;

public class ExerciseDetailsActivity extends AppCompatActivity {
    ImageButton startChrono;
    Chronometer chrono;
    long time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        ImageButton btn = (ImageButton) findViewById(R.id.button_swap_excerise);
        ImageButton nextBtn = (ImageButton) findViewById(R.id.next);
        startChrono = (ImageButton)findViewById(R.id.timer);
        chrono = (Chronometer)findViewById(R.id.chronometer);


        final EditText set1,set2,set3,set4,set5;
        set1 = (EditText)findViewById(R.id.newSet1);
        set2 = (EditText)findViewById(R.id.newSet2);
        set3 = (EditText)findViewById(R.id.newSet3);
        set4 = (EditText)findViewById(R.id.newSet4);
        set5 = (EditText)findViewById(R.id.newSet5);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("IDC",set1.getText().toString()+","+set2.getText().toString()+","+set3.getText().toString()+","+set4.getText().toString()+","+set5.getText().toString());
            }
        });

        startChrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.timer:
                        chrono.setBase(SystemClock.elapsedRealtime()+time);
                        chrono.start();
                        break;
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("IDC", "adi ya zain");
            }
        });
    }
}
*/
