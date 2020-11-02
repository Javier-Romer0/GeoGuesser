package cat.itb.androidquiz;

public class QuestionModel {
    int questionText;
    boolean answer;

    public QuestionModel(int questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public int getQuestionText() {
        return questionText;
    }

    public void setQuestionText(int questionText) {
        this.questionText = questionText;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}