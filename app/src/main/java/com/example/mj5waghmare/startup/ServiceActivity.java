package com.example.mj5waghmare.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceActivity extends AppCompatActivity {
    TextView tvclient,service,tvloc;
    Button btnSubmit;
    EditText ClientName,location;
    Spinner tos;

    DatabaseReference databaseDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        databaseDetails = FirebaseDatabase.getInstance().getReference("detail");

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        tvclient = (TextView)findViewById(R.id.tvclient);
        service = (TextView)findViewById(R.id.service);
        tvloc = (TextView)findViewById(R.id.tvloc);
        ClientName = (EditText)findViewById(R.id.ClientName);
        location = (EditText)findViewById(R.id.location);
        tos = (Spinner)findViewById(R.id.tos);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetails();
            }
        });
    }


    public void addDetails(){
        String cname = ClientName.getText().toString().trim();
        String cserv = tos.getSelectedItem().toString();
        String cloc = location.getText().toString().trim();

        if (!TextUtils.isEmpty(cname) && !TextUtils.isEmpty(cserv) && !TextUtils.isEmpty(cloc)){

            String id = databaseDetails.push().getKey();

            Details details = new Details(id,cname,cserv,cloc);
            databaseDetails.child(id).setValue(details);

            Toast.makeText(this, "Details saved successfully...", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
        return true;
    }
}
