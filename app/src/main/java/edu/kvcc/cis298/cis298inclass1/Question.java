package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by brodriguez8774 on 9/23/2015.
 */
public class Question {

    // Class level variables.
    private int mTextResId;         // Int which holds ref value for string.xml string.
    private boolean mAnswerTrue;    // Bool value for true/false.


    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    // Properties

    public void setAnswerTrue(boolean answerTrue){
        mAnswerTrue = answerTrue;
    }

    public boolean getAnswerTrue(){
        return mAnswerTrue;
    }

    public void setTextResId(int textResId){
        mTextResId = textResId;
    }

    public int getTextResId(){
        return mTextResId;
    }



}
