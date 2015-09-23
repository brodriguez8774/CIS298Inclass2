package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // Creates class level widget variables.
    private Button mTrueButton;
    private Button mFalseButton;

    // Default "setup" method for the app. Is called on launch.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Fetch the widget control from view and then cast to previously declared variable.
        mTrueButton = (Button) findViewById(R.id.true_button);

        // Sets up an onClickListener for widget. Basically checks for button click and then
        // performs action.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toasts a message to the screen.
                Toast.makeText(QuizActivity.this,
                                R.string.correct_toast,
                                Toast.LENGTH_SHORT).show();
            }
        });

        // Fetch the widget control from view and then cast to previously declared variable.
        mFalseButton = (Button) findViewById(R.id.false_button);

        // Sets up an onClickListener for widget. Basically checks for button click and then
        // performs action.
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toasts a message to the screen.
                Toast.makeText(QuizActivity.this,
                        R.string.incorrect_toast,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
