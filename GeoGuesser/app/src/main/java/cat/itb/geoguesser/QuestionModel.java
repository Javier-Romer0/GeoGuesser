package cat.itb.geoguesser;

public class QuestionModel {
    int questionText;
    final String[] answer;

    public QuestionModel(int questionText, String[] answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public int getQuestionText() {
        return questionText;
    }

    public void setQuestionText(int questionText) {
        this.questionText = questionText;
    }

    public String[] getAnswer() {
        return answer;
    }
}
