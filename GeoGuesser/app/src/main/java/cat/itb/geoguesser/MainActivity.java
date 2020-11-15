package cat.itb.geoguesser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    QuestionModel[] questionBank;
    private int currentIndex, numhints;
    private double points;
    private List<QuestionModel> questionList;
    private List<String> answersList;
    private String correctAnswer;
    private boolean hintUsed;

    TextView textView_currentQuestion, textView_progress, textView_remainingQuestions;
    ProgressBar progressBar;
    Button button_opt1, button_opt2, button_opt3, button_opt4, button_hint;

    private QuizViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewModel();

        textView_currentQuestion = findViewById(R.id.textView_question);
        textView_progress = findViewById(R.id.textView_progress);
        textView_remainingQuestions = findViewById(R.id.textView_remainingQuestions);
        progressBar = findViewById(R.id.progressBar);

        button_opt1 = findViewById(R.id.button_option1);
        button_opt2 = findViewById(R.id.button_option2);
        button_opt3 = findViewById(R.id.button_option3);
        button_opt4 = findViewById(R.id.button_option4);
        button_hint = findViewById(R.id.button_hint);


        button_opt1.setOnClickListener(this);
        button_opt2.setOnClickListener(this);
        button_opt3.setOnClickListener(this);
        button_opt4.setOnClickListener(this);

        button_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countHints();
                setTextHints();
            }
        });

        randomQuestion();
        mixAnswers();
        updateProgress();
        updateScreen();
        setTextHints();
        resetButtonColor();
    }

    public void randomQuestion(){
        questionList = new ArrayList<>(Arrays.asList(questionBank));
        Collections.shuffle(questionList);
        viewModel.setQuestionList(questionList);
    }

    public void mixAnswers(){
        answersList = new ArrayList<String>(Arrays.asList(questionList.get(currentIndex).getAnswer()));
        viewModel.setAnswerList(answersList);
        correctAnswer = answersList.get(0);
        viewModel.setCorrectAnswer(correctAnswer);
        Collections.shuffle(answersList);
    }

    public void nextQuestion(){
        if (currentIndex == questionBank.length-1){
            endMessage();
        }
        currentIndex = (currentIndex+1)%questionBank.length;
        viewModel.setIndex(currentIndex);
    }

    String progress = "";
    String remainingQuestions = "";
    public void updateProgress(){
        progress = "Question " + (currentIndex + 1) + " of " + questionBank.length;
        textView_progress.setText(progress);
        remainingQuestions = (questionBank.length - (currentIndex+1)) + " questions remaining";
        textView_remainingQuestions.setText(remainingQuestions);
        progressBar.setProgress((currentIndex+1)*5);
    }

    public void updateScreen(){
        textView_currentQuestion.setText(questionList.get(currentIndex).getQuestionText());
        button_opt1.setText(answersList.get(0));
        button_opt2.setText(answersList.get(1));
        button_opt3.setText(answersList.get(2));
        button_opt4.setText(answersList.get(3));
    }

    public void checkAnswer(String answer){
        if ((answer.equals(correctAnswer))&&hintUsed){
            points += 0;
            Toast.makeText(this, "Correct answer (with help)", Toast.LENGTH_SHORT).show();
        }else{
            if (answer.equals(correctAnswer)){
                points += 1;
                Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
                points -= 0.5;
            }
        }
        viewModel.setPoints(points);
    }

    public void endMessage(){
        if (points<0) points = 0;
        viewModel.setPoints(points);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Punctuatuion");
        builder.setMessage(points*100/questionBank.length + " out of 100");
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                numhints = 3;
                viewModel.setHints(numhints);
                setTextHints();
                button_hint.setEnabled(true);
                currentIndex = 0;
                viewModel.setIndex(currentIndex);
                points = 0;
                viewModel.setPoints(points);
            }
        });
        builder.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        checkAnswer(((Button)v).getText().toString());
        nextQuestion();
        updateProgress();
        mixAnswers();
        updateScreen();
        hintUsed = false;
        viewModel.setHintUsed(hintUsed);
        resetButtonColor();
    }

    public void countHints(){
        if (button_hint.isPressed()){
            numhints--;
            hintUsed = true;
            showCorrectAnswerHint();
            viewModel.setHints(numhints);
            viewModel.setHintUsed(hintUsed);
        }
        if (numhints==0) {
            button_hint.setEnabled(false);
        }else button_hint.setEnabled(true);
    }

    String remainingHints = "";
    public void setTextHints(){
        if (numhints == 1) remainingHints = numhints + " hint remaining";
        else remainingHints = numhints + " hints remaining";
        button_hint.setText(remainingHints);
    }

    public void showCorrectAnswerHint(){
        if (correctAnswer == button_opt1.getText()){
            button_opt1.setBackgroundColor(getResources().getColor(R.color.colorCorrect));
        }else if (correctAnswer == button_opt2.getText()){
            button_opt2.setBackgroundColor(getResources().getColor(R.color.colorCorrect));
        }else if (correctAnswer == button_opt3.getText()){
            button_opt3.setBackgroundColor(getResources().getColor(R.color.colorCorrect));
        }else if (correctAnswer == button_opt4.getText()){
            button_opt4.setBackgroundColor(getResources().getColor(R.color.colorCorrect));
        }
    }

    public void resetButtonColor(){
        button_opt1.setBackgroundColor(getResources().getColor(R.color.colorButton));
        button_opt2.setBackgroundColor(getResources().getColor(R.color.colorButton));
        button_opt3.setBackgroundColor(getResources().getColor(R.color.colorButton));
        button_opt4.setBackgroundColor(getResources().getColor(R.color.colorButton));
    }

    public void setViewModel(){
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        currentIndex = viewModel.getIndex();
        numhints = viewModel.getHints();
        questionBank = viewModel.getQuestionModelArray();
        hintUsed = viewModel.isHintUsed();
        points = viewModel.getPoints();
        correctAnswer = viewModel.getCorrectAnswer();
        answersList = viewModel.getAnswerList();
        questionList = viewModel.getQuestionList();
    }
}