package com.vedruna.aplication_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();


        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.navigation_home) {
                navController.navigate(R.id.homeFragment);
            } else if (item.getItemId() == R.id.navigation_create) {
                navController.navigate(R.id.createFragment);
            } else if (item.getItemId() == R.id.navigation_update) {
                navController.navigate(R.id.updateFragment);
            } else if (item.getItemId() == R.id.navigation_delete) {
                navController.navigate(R.id.deleteFragment);
            } else if (item.getItemId() == R.id.navigation_exit) {
                showLogoutConfirmationDialog();
            }
            return true;
        });
    }

    private void logOut() {
        mAuth.signOut();
        goLogin();
    }

    private void goLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void showLogoutConfirmationDialog() {
        // Construir el diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Surely you want to go out?");

        // Establecer el texto y el color del botón positivo ("Sí")
        builder.setPositiveButton("Yes", (dialog, which) -> logOut());

        // Establecer el texto y el color del botón negativo ("Cancelar")
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