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

import com.vedruna.aplication_crud.dto.ProductDto;
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

public class UpdateFragment extends Fragment {

    Spinner spinnerProductName;

    EditText editTextQuantity;

    EditText editTextPrice;

    Button btnUpdate;

    private Retrofit retrofit;

    CRUD crudInterface;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        // Inicializar los EditText
        spinnerProductName = rootView.findViewById(R.id.spinnerProductName);
        editTextQuantity = rootView.findViewById(R.id.editTextQuantity);
        editTextPrice = rootView.findViewById(R.id.editTextPrice);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        // Inicializar el botón
        btnUpdate = rootView.findViewById(R.id.btnUpdate);

        loadProductNames();

        // Puedes agregar un listener al botón si deseas manejar clics
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        return rootView;
    }

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
                    Toast.makeText(getActivity(), "Error loading products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Manejar el caso de falla en la llamada
                Toast.makeText(getActivity(), "Error in obtaining the products", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizar() {
        String productName = spinnerProductName.getSelectedItem().toString();
        String quantity = editTextQuantity.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();

        if (quantity.isEmpty() || price.isEmpty()) {
            mostrarToast("Please complete all fields.");
            return;
        }

        // Crear un objeto ProductDTO en lugar de Product
        ProductDto productDto = new ProductDto(productName, Integer.parseInt(quantity), Float.parseFloat(price));

        crudInterface = retrofit.create(CRUD.class);

        // Llamar al método actualizar con el DTO y el poductName
        Call<Product> call = crudInterface.update(productDto, productName);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                Product product = response.body();
                // Hacer algo con el producto actualizado si es necesario
                mostrarToast("Updated product: " + product.getProductName());

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
                mostrarToast("Error updating the product");
            }
        });
    }

    // Método para mostrar un Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}