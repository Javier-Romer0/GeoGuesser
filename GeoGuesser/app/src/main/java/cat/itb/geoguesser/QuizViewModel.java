package cat.itb.geoguesser;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizViewModel extends ViewModel {
    QuestionModel[] questionBank = {
            new QuestionModel(R.string.question_1, new String[]{"París", "Barcelona", "London", "Berlín"}),
            new QuestionModel(R.string.question_2, new String[]{"New York", "Washington D. C.", "San Francisco", "Orlando"}),
            new QuestionModel(R.string.question_3, new String[]{"London", "París", "Edinburgh", "Glasgow"}),
            new QuestionModel(R.string.question_4, new String[]{"Rome", "Milan", "Florence", "Venice"}),
            new QuestionModel(R.string.question_5, new String[]{"India", "Thailand", "Malaysia", "Bangladesh"}),
            new QuestionModel(R.string.question_6, new String[]{"Australia", "Japan", "Sri Lanka", "United States"}),
            new QuestionModel(R.string.question_7, new String[]{"Chile", "United States", "Hawaii", "Argentina"}),
            new QuestionModel(R.string.question_8, new String[]{"Moscow", "Saint Petersburg", "San Francisco", "London"}),
            new QuestionModel(R.string.question_9, new String[]{"Granada", "Seville", "Madrid", "Almería"}),
            new QuestionModel(R.string.question_10, new String[]{"Mexico", "Honduras", "Costa Rica", "Guatemala"}),
            new QuestionModel(R.string.question_11, new String[]{"Egypt", "Jordan", "Libya", "Irak"}),
            new QuestionModel(R.string.question_12, new String[]{"Barcelona", "Madrid", "Tarragona", "Bilbao"}),
            new QuestionModel(R.string.question_13, new String[]{"San Francisco", "Los Angeles", "Las Vegas", "New York"}),
            new QuestionModel(R.string.question_14, new String[]{"Athens", "Rome", "Ankara", "Corinth"}),
            new QuestionModel(R.string.question_15, new String[]{"Berlin", "Frankfurt", "Munich", "Milan"}),
            new QuestionModel(R.string.question_16, new String[]{"Cambodia", "Thailand", "India", "Bangladesh"}),
            new QuestionModel(R.string.question_17, new String[]{"Washington D. C.", "New York", "Los Angeles", "Chicago"}),
            new QuestionModel(R.string.question_18, new String[]{"Rio de Janeiro", "Brasilia", "São Paulo", "Buenos Aires"}),
            new QuestionModel(R.string.question_19, new String[]{"Jordan", "Siria", "Egypt", "Iran"}),
            new QuestionModel(R.string.question_20, new String[]{"Peru", "Ecuador", "Bolivia", "Colombia"})
    };
    private int index = 0, hints = 3;
    private double points = 0;
    private boolean hintUsed = false;
    private String correctAnswer;
    private List<String> answerList;
    private List<QuestionModel> questionList;

    public QuestionModel[] getQuestionModelArray(){ return questionBank; }

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    public int getHints(){ return hints; }
    public void setHints(int hints) { this.hints = hints; }

    public boolean isHintUsed() { return hintUsed; }
    public void setHintUsed(boolean hintUsed) { this.hintUsed = hintUsed; }

    public double getPoints() { return points; }
    public void setPoints(double points) { this.points = points; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public List<String> getAnswerList() { return answerList; }
    public void setAnswerList(List<String> answerList) { this.answerList = answerList; }

    public List<QuestionModel> getQuestionList() { return questionList; }
    public void setQuestionList(List<QuestionModel> questionList) { this.questionList = questionList; }
}
