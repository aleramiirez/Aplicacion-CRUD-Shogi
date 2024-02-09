package com.vedruna.aplication_crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vedruna.aplication_crud.interfaz.CRUD;
import com.vedruna.aplication_crud.model.Product;
import com.vedruna.aplication_crud.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteFragment extends Fragment {

    CRUD crudInterface;

    Spinner spinnerProductName;

    Button  btnDelete;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        // Inicializar el EditText para el ID
        spinnerProductName = view.findViewById(R.id.spinnerProductName);

        loadProductNames();

        // Configurar el botón de borrado con el clic
        setupDeleteButton(view);

        return view;
    }

    // En tu método loadProductNames() dentro de DeleteFragment.java
    private void loadProductNames() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de tu interfaz CRUD
        crudInterface = retrofit.create(CRUD.class);

        // Hacer la llamada para obtener los productos disponibles desde tu API
        Call<List<Product>> call = crudInterface.getAll();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    // Obtener la lista de productos desde la respuesta
                    List<Product> products = response.body();

                    // Crear una lista para almacenar los nombres de los productos
                    List<String> productNames = new ArrayList<>();

                    // Iterar sobre la lista de productos para extraer los nombres
                    for (Product product : products) {
                        productNames.add(product.getProductName());
                    }

                    // Configurar el adaptador del Spinner con los nombres de productos
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, productNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProductName.setAdapter(adapter);
                } else {
                    // Manejar el caso de respuesta no exitosa
                    Toast.makeText(getActivity(), "Error al cargar los productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Manejar el caso de falla en la llamada
                Log.e("Error", "Error al obtener productos: " + t.getMessage());
                Toast.makeText(getActivity(), "Error al obtener productos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDeleteButton(View view) {
        btnDelete = view.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productName = spinnerProductName.getSelectedItem().toString();

                if (!productName.isEmpty()) {
                    delete(productName);
                } else {
                    // Manejar el caso en el que el ID esté vacío
                    // Puedes mostrar un mensaje de error o realizar otras acciones
                    Log.e("Error", "El productName no puede estar vacío");
                }
            }
        });
    }

    private void delete(String productName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear la interfaz CRUDInterface
        crudInterface = retrofit.create(CRUD.class);

        // Llamar al método de borrado con el ID del producto
        Call<Void> call = crudInterface.delete(productName);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                mostrarToast("Product deleted: " + spinnerProductName.getSelectedItem());
                // Borrado exitoso, puedes realizar acciones adicionales si es necesario
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mostrarToast("Product could not be removed");
            }
        });
    }
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}