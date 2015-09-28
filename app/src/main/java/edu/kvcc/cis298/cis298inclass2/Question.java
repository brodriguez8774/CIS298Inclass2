package edu.kvcc.cis298.cis298inclass2;

/**
 * Created by brodriguez8774 on 9/23/2015.
 */
public class Question {

    //region Variables
    private int mTextResId;         // Int which holds ref value for string.xml string.
    private boolean mAnswerTrue;    // Bool value for true/false.

    //endregion

    //region Constructor
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
    //endregion

    //region Properties

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

    //endregion

}
