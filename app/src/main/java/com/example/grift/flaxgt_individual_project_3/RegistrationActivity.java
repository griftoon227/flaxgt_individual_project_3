package com.example.grift.flaxgt_individual_project_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grift.flaxgt_individual_project_3.db_model.MyDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.first_name_field_entry) EditText parent_first_name_etv;
    @BindView(R.id.last_name_field_entry) EditText parent_last_name_etv;
    @BindView(R.id.email_field_entry) EditText parent_email_etv;
    @BindView(R.id.username_field_entry) EditText parent_username_etv;
    @BindView(R.id.password_field_entry) EditText parent_password_etv;
    @BindView(R.id.confirm_password_field_entry) EditText parent_confirm_password_etv;
    @BindView(R.id.child_first_name_field_entry) EditText child_first_name_etv;
    @BindView(R.id.child_username_field_entry) EditText child_username_etv;
    @BindView(R.id.child_password_field_entry) EditText child_password_etv;
    @BindView(R.id.child_confirm_password_field_entry) EditText child_confirm_password_etv;

    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        db = new MyDatabase(getApplicationContext());
    }

    @OnClick(R.id.submit_btn)
    protected void confirm_information(View view) {
        if(parent_first_name_etv.getText().toString().isEmpty() ||
                parent_last_name_etv.getText().toString().isEmpty() ||
                parent_email_etv.getText().toString().isEmpty() ||
                parent_username_etv.getText().toString().isEmpty() ||
                parent_password_etv.getText().toString().isEmpty() ||
                parent_confirm_password_etv.getText().toString().isEmpty() ||
                child_first_name_etv.getText().toString().isEmpty() ||
                child_username_etv.getText().toString().isEmpty() ||
                child_password_etv.getText().toString().isEmpty() ||
                child_confirm_password_etv.getText().toString().isEmpty())
        {
            Toast.makeText(RegistrationActivity.this, "Please make sure all required fields are filled.",
                    Toast.LENGTH_SHORT).show();
        }
        else if((!(parent_first_name_etv.getText().toString().length() >= 3 && parent_first_name_etv.getText().toString().length() <= 30)) ||
                (!(parent_last_name_etv.getText().toString().length() >= 3 && parent_last_name_etv.getText().toString().length() <=30)) ||
                (!(parent_email_etv.getText().toString().endsWith("@example.com"))) ||
                (!(parent_password_etv.getText().toString().equals(parent_confirm_password_etv.getText().toString()))) ||
                (!(child_first_name_etv.getText().toString().length() >= 3 && child_first_name_etv.getText().toString().length() <=30)) ||
                (!(child_password_etv.getText().toString().equals(child_confirm_password_etv.getText().toString()))))
        {
            Toast.makeText(RegistrationActivity.this, "Please ensure all fields were entered properly.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            db.addRecord(new String[]{parent_first_name_etv.getText().toString(),
                    parent_last_name_etv.getText().toString(), parent_email_etv.getText().toString(),
                    parent_username_etv.getText().toString(), parent_password_etv.getText().toString(),
                    child_first_name_etv.getText().toString(), child_username_etv.getText().toString(),
                    child_password_etv.getText().toString()});

            SharedPreferences sharedPreferences = getSharedPreferences("log_file", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("Level_1_Easy", false);
            editor.putBoolean("Level_2_Easy", false);
            editor.putBoolean("Level_3_Easy", false);
            editor.putBoolean("Level_1_Hard", false);
            editor.putBoolean("Level_2_Hard", false);
            editor.putBoolean("Level_3_Hard", false);

            editor.commit();

            startActivity(new Intent(RegistrationActivity.this, IntroScreenActivity.class));
        }
    }
}
