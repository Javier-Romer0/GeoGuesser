package cat.itb.androidquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    QuestionModel[] questionBank = {
            new QuestionModel(R.string.question_1, false),
            new QuestionModel(R.string.question_2, true),
            new QuestionModel(R.string.question_3, false),
            new QuestionModel(R.string.question_4, true),
            new QuestionModel(R.string.question_5, true),
            new QuestionModel(R.string.question_6, false),
            new QuestionModel(R.string.question_7, true),
            new QuestionModel(R.string.question_8, false),
            new QuestionModel(R.string.question_9, false),
            new QuestionModel(R.string.question_10, false)
    };
    private TextView textView_Question, textView_Progress;
    private ProgressBar progressBar;
    Button button_true, button_false;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_Question = findViewById(R.id.textView_Question);
        textView_Progress = findViewById(R.id.textView_Progress);
        progressBar = findViewById(R.id.progressBar);
        button_true = findViewById(R.id.button_true);
        button_false = findViewById(R.id.button_false);

        textView_Question.setText(questionBank[index].getQuestionText());

        button_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                nextQuestion();
                refreshScreen();
            }
        });

        button_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                nextQuestion();
                refreshScreen();
            }
        });

    }

    public void checkAnswer(Boolean answer){
        if (answer == questionBank[index].isAnswer()){
            Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
    }

    public void nextQuestion(){
        index = (index + 1) % questionBank.length;
        if (index == 0){
            endMessage();
        }
    }

    String progress;
    public void refreshScreen(){
        progress = "Question " + (index+1) + " of " + questionBank.length;
        textView_Question.setText(questionBank[index].getQuestionText());
        textView_Progress.setText(progress);
        progressBar.setProgress((index+1)*10);
    }

    public void endMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertDialog_title);
        builder.setMessage(R.string.alertDialog_text);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                index = index++;
            }
        });
        builder.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}