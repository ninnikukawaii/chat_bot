package logic;

import java.io.Serializable;

public class Question implements Serializable {
    private String mQuestion;
    private String mAnswer;

    public Question(String question, String answer){
        mQuestion = question;
        mAnswer = answer;
    }

    public String getQuestion(){
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Question){
            Question question = (Question) object;
            return question.mQuestion.equals(mQuestion) && question.mAnswer.equals(mAnswer);
        }
        return false;
    }

    @Override
    public String toString() {
        return getQuestion() + '~' + getAnswer();
    }
}
