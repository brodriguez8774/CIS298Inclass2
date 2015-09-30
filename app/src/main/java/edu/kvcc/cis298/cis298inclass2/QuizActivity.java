package edu.kvcc.cis298.cis298inclass2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //region Variables
    // Creates class level widget variables.

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mSubmitButton;

    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private RadioGroup mQuestionGroup;

    private RadioButton mChoice1;
    private RadioButton mChoice2;
    private RadioButton mChoice3;
    private RadioButton mChoice4;

    private int[] mChoiceResId = new int[] {R.string.choice_1, R.string.choice_2, R.string.choice_3, R.string.choice_4};

    // Hardcoded array of questions to be used. Most apps want data to come from somewhere
    // else. IE: database, internet, etc.
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_cats, R.id.choice_1_radio, mChoiceResId),
            new Question(R.string.question_dogs, R.id.choice_1_radio, mChoiceResId),
            new Question(R.string.question_pets, R.id.choice_1_radio, mChoiceResId),
            new Question(R.string.question_homework, R.id.choice_1_radio, mChoiceResId),
            new Question(R.string.question_homework2, R.id.choice_1_radio, mChoiceResId),
            new Question(R.string.question_false, R.id.choice_1_radio, mChoiceResId)
    };
    /* Used for non-radio questions.
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_cats, true),
            new Question(R.string.question_dogs, true),
            new Question(R.string.question_pets, true),
            new Question(R.string.question_homework, true),
            new Question(R.string.question_homework2, true),
            new Question(R.string.question_false, false)
    };*/

    //endregion

    //region Standard Methods

    // Makes a pointer to display current question.
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        // Fetch the question choice strings.
        int[] choices = mQuestionBank[mCurrentIndex].getChoiceResIds();

        // Assigns each choice text to the text property of the corresponding radio button.
        mChoice1.setText(choices[0]);
        mChoice2.setText(choices[1]);
        mChoice3.setText(choices[2]);
        mChoice4.setText(choices[3]);
    }

    private void checkTrueFalseAnswer(boolean userPressedTrue){

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

    private void checkMultipleChoiceAnswer(int selectedRadioButtonId){

        int correctAnswer = mQuestionBank[mCurrentIndex].getCorrectAnswerResId();

        // Int which points to desired string.
        int messageResId = 0;

        // Compares answer to one passed in method. Assigns toast message accordingly.
        if (selectedRadioButtonId == correctAnswer) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region Override Methods

    // Default "setup" method for the app. Is called on launch.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestionGroup = (RadioGroup) findViewById(R.id.multiple_choice_group);
        mChoice1 = (RadioButton) findViewById(R.id.choice_1_radio);
        mChoice2 = (RadioButton) findViewById(R.id.choice_2_radio);
        mChoice3 = (RadioButton) findViewById(R.id.choice_3_radio);
        mChoice4 = (RadioButton) findViewById(R.id.choice_4_radio);



        // Checks to see if there is a bundle that is not null.
        // If so, fetches KEY_INDEX which will be index for current question.
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();

        /*
        // Fetch the widget control from view and then cast to previously declared variable.
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Sets up an onClickListener for widget. Basically checks for button click and then
        // performs action.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toasts a message to the screen.
                checkTrueFalseAnswer(true);

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
                checkTrueFalseAnswer(false);

                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });
*/
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Query the radio button group to determine what is selected.
                int selectAnswerId = mQuestionGroup.getCheckedRadioButtonId();
                // Pass the id of the selected radio button.
                checkMultipleChoiceAnswer(selectAnswerId);
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

    //endregion
}
