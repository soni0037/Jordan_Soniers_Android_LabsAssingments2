package alongquin.CST2335.soni0037;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/** This page is used to verify the complexity of the users password and checks if it meets
 * the standards required to use it
 * @author Jordan Sonier
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * This holds the text at the centre of the screen
     */
    private TextView tv = null;
    /**
     * This holds the text 32dp align with the bottom of the TextView box
     */
    private EditText et = null;
    /**
     * This holds the button 50dp from the button of the screen
     */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();
            if (!checkPasswordComplexity(password)) {
            tv.setText("You shall not pass!");
            } else {
                tv.setText("Your password is complex enough");
            }
        });
    }

    /** This function is used to check if these requirements were met when creating your
     * complex password. If one of these requirements were used then the function will return true
     * and go through with the password. It must meet these conditions in order to have the password
     * accepted.
     * @param pw The String object that we are checking
     * @return true if the requirements are met, false if the requirements are not of these types
     */
    private boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char[] passWord = pw.toCharArray();
            String password = String.valueOf(passWord[i]);
            if (Pattern.matches("[a-z]+", password)) {
                foundLowerCase = true;
            } else if (Pattern.matches("[A-Z]+", password)) {
                foundUpperCase = true;
            } else if (Pattern.matches("\\d+", password)) {
                foundNumber = true;
            } else if (Pattern.matches("[(#$%^&*!@?)]+", password)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            Toast.makeText(getApplicationContext(), "Password must have an uppercase letter", Toast.LENGTH_LONG).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(getApplicationContext(), "Password must have a lowercase letter", Toast.LENGTH_LONG).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(getApplicationContext(), "Password must have a number", Toast.LENGTH_LONG).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(getApplicationContext(), "Password must have a special character", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true; //only get here if they're all true
        }
    }
}
