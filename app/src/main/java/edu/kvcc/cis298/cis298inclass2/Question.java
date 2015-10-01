package edu.kvcc.cis298.cis298inclass2;

/**
 * Created by brodriguez8774 on 9/23/2015.
 */
public class Question {

    //region Variables
    private int mTextResId;             // Int which holds ref value for string.xml string.
    private boolean mAnswerTrue;        // Bool value for true/false.
    private int mCorrectAnswerResId;    // Holds id of correct resource.
    private int[] mChoiceResIds;        // Int array to hold id's of choices.

    //endregion



    //region Constructor

    /**
     * Constructor for True/False Questions.
     *
     * @param textResId Pointer to current question in string.xml file.
     * @param answerTrue Correct answer to current question.
     */
    public Question(int textResId, boolean answerTrue) {
        setTextResId(textResId);
        setAnswerTrue(answerTrue);
    }

    /**
     * Constructor for Multiple Choice Questions.
     *
     * @param textResId Pointer to current question in string.xml file.
     * @param correctAnswerId Correct answer to current question.
     * @param choiceResIds Array holding list of possible answers to current question.
     */
    public Question(int textResId, int correctAnswerId, int[] choiceResIds){
        setTextResId(textResId);
        setCorrectAnswerResId(correctAnswerId);
        setChoiceResIds(choiceResIds);
    }
    //endregion



    //region Properties

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean getAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getCorrectAnswerResId() {
        return mCorrectAnswerResId;
    }

    public void setCorrectAnswerResId(int correctAnswerResId) {
        mCorrectAnswerResId = correctAnswerResId;
    }

    public int[] getChoiceResIds() {
        return mChoiceResIds;
    }

    public void setChoiceResIds(int[] choiceResIds) {
        mChoiceResIds = choiceResIds;
    }

    //endregion

}
