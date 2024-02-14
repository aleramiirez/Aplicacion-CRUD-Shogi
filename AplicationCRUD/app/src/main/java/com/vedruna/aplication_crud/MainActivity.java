package com.vedruna.aplication_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * MainActivity es la actividad principal que muestra la interfaz de usuario principal de la aplicación.
 * Desde aquí, los usuarios pueden navegar a diferentes fragmentos y opciones de la aplicación.
 */
public class MainActivity extends AppCompatActivity {

    // Autenticación de Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Encontrar la vista BottomNavigationView en el diseño
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Establecer la opción seleccionada por defecto en la vista BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Encontrar el fragmento de NavController del diseño
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // Configurar un Listener para la selección de elementos en BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {

            // Navegar a diferentes destinos según el elemento seleccionado
            if (item.getItemId() == R.id.navigation_home) {
                navController.navigate(R.id.homeFragment);
            } else if (item.getItemId() == R.id.navigation_create) {
                navController.navigate(R.id.createFragment);
            } else if (item.getItemId() == R.id.navigation_update) {
                navController.navigate(R.id.updateFragment);
            } else if (item.getItemId() == R.id.navigation_delete) {
                navController.navigate(R.id.deleteFragment);
            } else if (item.getItemId() == R.id.navigation_exit) {
                // Mostrar un diálogo de confirmación al intentar cerrar sesión
                showLogoutConfirmationDialog();
            }
            return true;
        });
    }

    /**
     * Método para cerrar la sesión del usuario actual y redirigirlo a la actividad de inicio de sesión.
     */
    private void logOut() {
        // Cerrar sesión en Firebase Auth
        mAuth.signOut();

        // Redirigir a la actividad de inicio de sesión
        goLogin();
    }

    /**
     * Método para iniciar la actividad de inicio de sesión.
     */
    private void goLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }


    /**
     * Método para mostrar un diálogo de confirmación al intentar cerrar sesión.
     */
    private void showLogoutConfirmationDialog() {
        // Construir el diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Surely you want to go out?");

        // Establecer el texto y el color del botón positivo ("Yes")
        builder.setPositiveButton("Yes", (dialog, which) -> logOut());

        // Establecer el texto y el color del botón negativo ("No")
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        // Obtener el color principal
        int colorMain = getResources().getColor(R.color.main); // Cambia R.color.main por el identificador de tu color principal

        // Crear el diálogo y mostrarlo
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Obtener los botones del diálogo
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                // Establecer el color del texto de los botones
                positiveButton.setTextColor(colorMain);
                negativeButton.setTextColor(colorMain);
            }
        });
        dialog.show();
    }
}