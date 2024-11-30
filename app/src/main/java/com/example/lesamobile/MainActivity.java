package com.example.lesamobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lesamobile.Models.Login;
import com.example.lesamobile.Models.LoginDTO;
import com.example.lesamobile.Retrofit.RetrofitAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText et_correo;
    EditText et_contraseña;
    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_correo = findViewById(R.id.et_correo);
        et_contraseña = findViewById(R.id.et_contraseña);
        btn_ingresar = findViewById(R.id.btn_ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(et_correo.getText().toString(), et_contraseña.getText().toString());
            }
        });


    }

    public void login(String correo, String contraseña) {
        Login objLogin = new Login();
        objLogin.setEmail(correo);
        objLogin.setPassword(contraseña);

        Call<LoginDTO> login = RetrofitAdapter.getServices().login(objLogin);
        login.enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Obtener el token de la respuesta
                    String token = response.body().getToken();

                    // Crear el Intent para cambiar a la siguiente actividad (MenuActivity)
                    Intent cambiarAct = new Intent(MainActivity.this, HomeActivity.class);

                    // Pasar el token a la siguiente actividad
                    cambiarAct.putExtra("Token", token);

                    // Iniciar la nueva actividad
                    startActivity(cambiarAct);

                    // Mostrar un mensaje indicando el inicio de sesión
                    Toast.makeText(MainActivity.this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();

                    // Opcional: Finalizar la actividad actual para que no se pueda regresar con el botón "Atrás"
                    finish();
                } else  {
                    Log.i("Respuesta", "Error al iniciar sesión." + response.raw() + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                Log.e("Error en la conexión", "onFailure: " + t.getMessage() + t.getCause());
            }
        });
    }
}