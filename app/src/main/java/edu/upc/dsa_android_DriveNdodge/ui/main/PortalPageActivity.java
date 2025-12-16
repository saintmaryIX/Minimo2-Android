package edu.upc.dsa_android_DriveNdodge.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa_android_DriveNdodge.R;
import edu.upc.dsa_android_DriveNdodge.api.EventService;
import edu.upc.dsa_android_DriveNdodge.api.RetrofitClient;
import edu.upc.dsa_android_DriveNdodge.ui.event.ViewEventUsersActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortalPageActivity extends AppCompatActivity {

    private Button shopBttn, perfilBttn, rankBttn, inventoryBttn;
    private Button registerEventBttn, viewEventBttn;

    //aqui he añadido los botones nuevos (asocio el view a un objeto)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portalpage);

        shopBttn = findViewById(R.id.shopBttn);
        perfilBttn = findViewById(R.id.perfilBttn);
        rankBttn = findViewById(R.id.rankBttn);
        inventoryBttn = findViewById(R.id.inventoryBttn);
        registerEventBttn = findViewById(R.id.registerEventBttn);
        viewEventBttn = findViewById(R.id.viewEventBttn);

        shopBttn.setOnClickListener(v ->
                startActivity(new Intent(this, edu.upc.dsa_android_DriveNdodge.ui.shop.ShopActivity.class)));

        perfilBttn.setOnClickListener(v ->
                startActivity(new Intent(this, edu.upc.dsa_android_DriveNdodge.ui.profile.ViewProfileActivity.class)));

        rankBttn.setOnClickListener(v ->
                startActivity(new Intent(this, edu.upc.dsa_android_DriveNdodge.ui.ranking.ViewRankingActivity.class)));

        inventoryBttn.setOnClickListener(v ->
                startActivity(new Intent(this, edu.upc.dsa_android_DriveNdodge.ui.inventario.InventarioActivity.class)));

        registerEventBttn.setOnClickListener(v -> registerEvent());
        viewEventBttn.setOnClickListener(v ->
                startActivity(new Intent(this, ViewEventUsersActivity.class)));

        findViewById(R.id.logoutButton).setOnClickListener(v -> logout());
    }

    private void registerEvent() {
        String username = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
                .getString("username", null);

        EventService service = RetrofitClient.getClient().create(EventService.class);

        service.registerToEvent(username).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(PortalPageActivity.this,
                        "Registrado en X-MAS Event", Toast.LENGTH_SHORT).show();
                Toast.makeText(PortalPageActivity.this,
                        "¡Prepárate para este futuro evento!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PortalPageActivity.this,
                        "Error al registrarse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        getSharedPreferences("MyAppPrefs", MODE_PRIVATE).edit().clear().apply();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
