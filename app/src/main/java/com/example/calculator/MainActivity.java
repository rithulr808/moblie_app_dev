package com.example.calculator;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, View.OnLongClickListener {

    TextView resulttext, soulutiontext;
    MaterialButton button_openbracket, button_closedbracket, button_mod, button_divide, button_multiply, button_addition, button_subtract, button_equals,
            button_C, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resulttext = findViewById(R.id.resultstext);
        soulutiontext = findViewById(R.id.soultiontext);

        assignId(button_openbracket, R.id.button_openbracket);
        assignId(button_closedbracket, R.id.button_closedbracket);
        assignId(button_mod, R.id.button_mod);
        assignId(button_divide, R.id.button_divide);
        assignId(button_multiply, R.id.button_multiply);
        assignId(button_addition, R.id.button_addition);
        assignId(button_subtract, R.id.button_subtract);
        assignId(button_equals, R.id.button_equals);

        assignId(button_C, R.id.button_c);
        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_dot, R.id.button_dot);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btn_text = btn.getText().toString();
        String data = soulutiontext.getText().toString();

        if (btn.getId() == R.id.button_c) {
//            clear_one();
            soulutiontext.setText("");
            resulttext.setText("0");
            return;

        }

        if(btn.getId() == R.id.button_equals)
        {
            soulutiontext.setText(resulttext.getText());
            return;
        }



        data = data + btn_text;
        soulutiontext.setText(data);

        String finalres = getResult(data);
        if(!finalres.equals("error"))
        {
            resulttext.setText(finalres);
        }


        // if long click
        if (btn.getId() == R.id.button_c) {
            soulutiontext.setText("");
            resulttext.setText("");
            return;
        }
    }
    @Override
    public boolean onLongClick(View view) {
       MaterialButton btn = (MaterialButton) view;
       if (btn.getId() == R.id.button_c) {
           soulutiontext.setText("");
           resulttext.setText("");
       }
       return true;
   }

   String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalresult.endsWith(".0"))
            {
                finalresult = finalresult.replace(".0", "");
            }
            return finalresult;
        }
        catch (Exception e){
            return "error";
        }
   }

    void clear_one() {
        String data = soulutiontext.getText().toString();
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
            soulutiontext.setText(data);
        }
    }

}