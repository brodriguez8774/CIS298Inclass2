package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // Creates class level widget variables.
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    // Hardcoded array of questions to be used. Most apps want data to come from somewhere
    // else. IE: database, internet, etc.
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_cats, true),
            new Question(R.string.question_dogs, true),
            new Question(R.string.question_pets, true),
            new Question(R.string.question_homework, true),
            new Question(R.string.question_homework2, true),
            new Question(R.string.question_false, false)
    };

    // Makes a pointer to display current question.
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].getAnswerTrue();

        // Int which points to desired string.
        int messageResId = 0;

        // Compares answer to one passed in method. Assigns toast message accordingly.
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    // Default "setup" method for the app. Is called on launch.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        updateQuestion();

        // Fetch the widget control from view and then cast to previously declared variable.
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Sets up an onClickListener for widget. Basically checks for button click and then
        // performs action.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toasts a message to the screen.
                checkAnswer(true);

                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
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
                checkAnswer(false);

                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
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
