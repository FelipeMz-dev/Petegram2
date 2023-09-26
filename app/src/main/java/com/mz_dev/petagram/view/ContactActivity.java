package com.mz_dev.petagram.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.mz_dev.petagram.OptionsMenuHandler;
import com.mz_dev.petagram.R;
import com.mz_dev.petagram.mailHelper.SendMailTask;

import java.util.Objects;

public class ContactActivity extends AppCompatActivity {

    EditText etContactName, etContactEmail, etContactMessage;
    Button btnContactSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar myToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.menuContact).setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (OptionsMenuHandler.handleOptionsItemSelected(item, this)) return true;
        return super.onOptionsItemSelected(item);
    }

    private void initUI(){
        etContactName = findViewById(R.id.etContactName);
        etContactEmail = findViewById(R.id.etContactEmail);
        etContactMessage = findViewById(R.id.etContactMessage);
        btnContactSend = findViewById(R.id.btnContactSend);
    }

    private void initListener(){
        btnContactSend.setOnClickListener(v -> sendEmail());
    }

    private void sendEmail(){
        String name = etContactName.getText().toString().trim();
        String email = etContactEmail.getText().toString().trim();
        String comment = etContactMessage.getText().toString().trim();

        //send email in background
        SendMailTask sendMailTask = new SendMailTask (this, email, name, comment);
        sendMailTask.execute ();
    }

}