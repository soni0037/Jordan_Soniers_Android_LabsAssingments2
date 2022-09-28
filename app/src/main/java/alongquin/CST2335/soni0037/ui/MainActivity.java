package alongquin.CST2335.soni0037.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import alongquin.CST2335.soni0037.data.MainViewModel;
import alongquin.CST2335.soni0037.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.myButton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myEditText.getText().toString());
            variableBinding.textView.setText( "Your edit text has:"+ model.editString);
        });

        model.editString.observe(this, s -> {
            variableBinding.textView.setText("Your edit text has"+ s);
        });
    }
}