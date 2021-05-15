package exercise.find.roots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessFindingRoots extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_finding_roots);
        Intent intentCreatedMe = getIntent();
        if(intentCreatedMe.hasExtra("number")){
            long number = intentCreatedMe.getLongExtra("number", 0);
            long root1 = intentCreatedMe.getLongExtra("root1", 0);
            long root2 = intentCreatedMe.getLongExtra("root2", 0);
            long time = intentCreatedMe.getLongExtra("time", 0);
            TextView textView = findViewById(R.id.success_message);
            TextView timeText = findViewById(R.id.time_message);
            String answer = "The Answer: " + number + " = " + root1 +  " * " + root2;
            String timeTook = "Took " + time  + " seconds";
            textView.setText(answer);
            timeText.setText(timeTook);
        }
    }
}