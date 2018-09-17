package subhro.example.com.employeemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText user_name,user_pass;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name=(EditText)findViewById(R.id.username);
        user_pass=(EditText)findViewById(R.id.password);
        login_btn=(Button)findViewById(R.id.login);

        databaseHelper = new DatabaseHelper(LoginActivity.this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = databaseHelper.checkUserExist(user_name.getText().toString(), user_pass.getText().toString());

                if(isExist){
                    Intent intent = new Intent(LoginActivity.this, EmployeeDashboard.class);
                    intent.putExtra("username", user_name.getText().toString());
                    startActivity(intent);
                } else {
                    user_pass.setText(null);
                    Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
