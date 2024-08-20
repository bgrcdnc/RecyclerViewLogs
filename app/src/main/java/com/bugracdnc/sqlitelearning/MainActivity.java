package com.bugracdnc.sqlitelearning;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bugracdnc.sqlitelearning.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Logs logs = Logs.getInstance();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(logs.getAdapter());

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY, name VARCHAR, age INTEGER)");


//            database.execSQL("INSERT INTO musicians(name, age) VALUES ('James', 50)");
//            database.execSQL("INSERT INTO musicians(name, age) VALUES ('Lars', 60)");
//            database.execSQL("INSERT INTO musicians(name, age) VALUES ('Kirk', 55)");

//            database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
//            database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

//            database.execSQL("DELETE FROM musicians WHERE id = 1");

            String rawQuery;
//            rawQuery = "SELECT * FROM musicians WHERE name = 'James'";
//            rawQuery = "SELECT * FROM musicians";
            rawQuery = "SELECT * FROM musicians WHERE name LIKE 'K%'";

            Cursor cursor = database.rawQuery(rawQuery, null);


            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            while (cursor.moveToNext()) {
                logs.printf("ID: %d, Name: %s, Age: %d", cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getInt(ageIndex));
            }

            cursor.close();
        } catch (Exception e) {
            logs.print(e.toString());
        }
    }
}