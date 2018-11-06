package com.example.grift.flaxgt_individual_project_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grift.flaxgt_individual_project_3.db_model.MyDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username_field_entry) EditText username_etv;
    @BindView(R.id.password_field_entry) EditText password_etv;
    @BindView(R.id.toggle_account_checkbox) CheckBox toggle_acct_cb;

    private MyDatabase db;

    private enum Account_Types {
        PARENT,
        CHILD
    }

    private Account_Types current_account_type = Account_Types.PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        db = new MyDatabase(getApplicationContext());

        toggle_acct_cb.setText(getString(R.string.toggle_account_cb_text) + " " + current_account_type);
    }

    @OnClick(R.id.login_btn)
    protected void confirm_login(View view){
        if(current_account_type.equals(Account_Types.PARENT))
        {
            //check db for parent acct
            if(db.validateParentAccount(username_etv.getText().toString(), password_etv.getText().toString()))
                //start new statistics for child activity
                startActivity(new Intent(LoginActivity.this, ChildStatsActivity.class));
            else
                //Toast
                Toast.makeText(getApplicationContext(),"Username or password does not exist or is incorrect.",
                        Toast.LENGTH_SHORT).show();
        }
        else
        {
            //check db for existence of child acct
            if(db.validateChildAccount(username_etv.getText().toString(), password_etv.getText().toString()))
                //start new game activity
                startActivity(new Intent(LoginActivity.this, LevelsScreenActivity.class));
            else
                //Toast
                Toast.makeText(getApplicationContext(),"Username or password does not exist or is incorrect.",
                        Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.toggle_account_checkbox)
    protected void toggle_account_type(View view){
        if(toggle_acct_cb.isChecked())
            current_account_type = Account_Types.CHILD;
        else
            current_account_type = Account_Types.PARENT;

        toggle_acct_cb.setText(getString(R.string.toggle_account_cb_text) + " " + current_account_type);
    }
}
