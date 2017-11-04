package com.example.pk1.driverstable.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pk1.driverstable.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetNetAddressActivity extends AppCompatActivity {
    @BindView(R.id.editTextIP) EditText editTextIP;
    @BindView(R.id.editTextPort) EditText editTextPort;
    @BindView(R.id.buttonNext) Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_net_address);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonNext)
    public void nextToMainActivity(View view){
        Intent intent=new Intent(SetNetAddressActivity.this,MainActivity.class);
        intent.putExtra("address", editTextIP.getText().toString() + ":" + editTextPort.getText().toString());
        startActivity(intent);
    }
}
