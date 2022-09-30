package alongquin.CST2335.soni0037.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import alongquin.CST2335.soni0037.data.MainViewModel;
import alongquin.CST2335.soni0037.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    private Observer observe = new Observer() {
        @Override
        public void update(Observable observable, Object o) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

//        variableBinding.textView.setText(model.editString.toString());
        variableBinding.myButton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myEditText.getText().toString());
        });

        model.editString.observe(this, s -> {
            variableBinding.textView.setText("Your edit text has"+ s);
        });

        model.isSelected.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            Toast.makeText(this, "Now the value is: "+ selected, Toast.LENGTH_SHORT).show();
        });

        variableBinding.checkBox.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });
        variableBinding.radioButton.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });
        variableBinding.switch1.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });
        variableBinding.myImageButton.setOnClickListener(click -> Toast.makeText(this, "The width = " + click.getWidth() + " and height = " + click.getHeight(), Toast.LENGTH_SHORT).show());
    }
}