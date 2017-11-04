package in.coderszone.shadow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.coderszone.shadow.service.ContactService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent myIntent = new Intent(getApplicationContext(), ContactService.class);
        getApplicationContext().startService(myIntent);
    }
}
