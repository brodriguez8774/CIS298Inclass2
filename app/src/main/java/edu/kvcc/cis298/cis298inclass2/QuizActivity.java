package edu.kvcc.cis298.cis298inclass2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private Button mCheatButton;

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

        // Checks to see if there is a bundle that is not null.
        // If so, fetches KEY_INDEX which will be index for current question.
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
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

                // Changes index and also creates a loop, going back to 0 when last is reached.
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

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].getAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivity(i);
            }
        });

    }

    // Java's version of constants. String to use for override methods.
    private static final String TAG = "QuizActivity";

    // String to be used as the key in key/value bundle for onSaveInstanceState.
    private static final String KEY_INDEX = "index";

    //Override Method to store any necessary information about our activity when saving state.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    // Below are the main activity methods which can be overriden to 'do work.'
    // Our app will call all of these in sequence as it loads and is closed.
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
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
