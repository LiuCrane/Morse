package com.crane.morse;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private TextView output;
    private TextView source;
    private TextView target;
    String input_str;
    String output_str;
    int tag;

    char[] alpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '0', ' '};
    String[] dottie = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
            "-....", "--...", "---..", "----.", "-----", "\n" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tag = 1;
        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
        source = (TextView)findViewById(R.id.source_language);
        target = (TextView)findViewById(R.id.target_language);
    }

    public void clickConvertHandler(View view){
        switch (tag){
            case 1:
                input_str = input.getText().toString();
                char[] translates = input_str.toLowerCase().toCharArray();
                System.out.println(translates);
                output_str = toMorse(translates);
                output.setText(output_str);
                break;
            case 2:
                output.setText("Sorry, this part is still under development..");
                break;
        }

    }

    public void clickCopyHandler(View view){
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("morse",output_str);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this,"Copied",Toast.LENGTH_SHORT).show();
    }

    public void clickSwitchHandler(View view){
        if(tag == 1) {
            tag = 2;
            source.setText(R.string.morse);
            target.setText(R.string.alpha);
        } else if(tag == 2) {
            tag = 1;
            source.setText(R.string.alpha);
            target.setText(R.string.morse);
        }
    }

    public void clickClearHandler(View view){
        input.setText("");
        output.setText("");
    }

    public String toMorse(char[] translates){
        String morse = "";
        for (int j = 0; j < translates.length; j++)
        {
            char c = translates[j];
            for (int i = 0; i<alpha.length; i++){
               if (c == alpha[i]){
                   morse += dottie[i];
               }
            }
        }
        return morse;
    }

    public String toAlpha(String[] tanslates){
        return null;
    }
}
