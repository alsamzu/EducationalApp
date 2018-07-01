package com.example.zulfa.education;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Check52 = findViewById(R.id.question_2_check52);
        CheckBox checkBoxQuestion2Check48 = findViewById(R.id.question_2_check48);
        CheckBox checkBoxQuestion2Check70 = findViewById(R.id.question_2_check70);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Check52.isChecked() == true && checkBoxQuestion2Check48.isChecked() == false && checkBoxQuestion2Check70.isChecked() == true) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion41 = findViewById(R.id.question_4_1);
        CheckBox checkBoxQuestion42 = findViewById(R.id.question_4_3);
        CheckBox checkBoxQuestion43 = findViewById(R.id.question_4_2);

        Boolean answerQuestion4 = false;

        Boolean fourOne = checkBoxQuestion41.isChecked();
        Boolean fourTwo = checkBoxQuestion42.isChecked();
        Boolean fourThree = checkBoxQuestion43.isChecked();


        if (fourOne == false && fourTwo == false && fourThree == true) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"two", "true", "ngeois", "true", "hgomu"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_check52);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_check48);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_check70);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_1);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_3);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_2);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}
