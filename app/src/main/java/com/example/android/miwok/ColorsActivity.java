package com.example.android.miwok;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new fragment_color())
                .commit();
    }
}