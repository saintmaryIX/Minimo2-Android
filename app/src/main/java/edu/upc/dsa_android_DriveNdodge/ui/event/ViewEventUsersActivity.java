package edu.upc.dsa_android_DriveNdodge.ui.event;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa_android_DriveNdodge.R;
import edu.upc.dsa_android_DriveNdodge.api.EventService;
import edu.upc.dsa_android_DriveNdodge.api.RetrofitClient;
import edu.upc.dsa_android_DriveNdodge.models.EventUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewEventUsersActivity extends AppCompatActivity {

    private RecyclerView rvEventUsers;
    private EventUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_users);

        rvEventUsers = findViewById(R.id.rvEventUsers);
        rvEventUsers.setLayoutManager(new LinearLayoutManager(this));

        loadEventUsers(); //funcion get de usersevento
    }

    private void loadEventUsers() {
        EventService service = RetrofitClient.getClient().create(EventService.class);

        service.getEventUsers().enqueue(new Callback<List<EventUser>>() {
            @Override
            public void onResponse(Call<List<EventUser>> call, Response<List<EventUser>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(ViewEventUsersActivity.this,
                            "Error servidor: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter = new EventUsersAdapter(response.body());
                rvEventUsers.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<EventUser>> call, Throwable t) {
                Toast.makeText(ViewEventUsersActivity.this,
                        "Error de conexión",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
