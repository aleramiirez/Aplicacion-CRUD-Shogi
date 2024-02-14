package com.vedruna.aplication_crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vedruna.aplication_crud.dto.ProductDto;
import com.vedruna.aplication_crud.interfaz.CRUD;
import com.vedruna.aplication_crud.model.Product;
import com.vedruna.aplication_crud.model.StockType;
import com.vedruna.aplication_crud.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para actualizar un producto a la base de datos.
 */
public class UpdateFragment extends Fragment {

    // Elementos de la interfaz gráfica
    Spinner spinnerProductName;
    EditText editTextQuantity;
    Spinner spinnerStock;
    EditText editTextPrice;
    EditText editTextImageURL;
    Button btnUpdate;

    // Elementos de la interfaz gráfica
    private Retrofit retrofit;

    // Interfaz para acceder a los métodos CRUD de la API
    CRUD crudInterface;

    // Lista de productos obtenida de la API
    List<Product> productList;

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
        // Infla la vista del fragmento
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        // Inicializa los elementos de la interfaz gráfica
        spinnerProductName = rootView.findViewById(R.id.spinnerProductName);
        editTextQuantity = rootView.findViewById(R.id.editTextQuantity);
        editTextPrice = rootView.findViewById(R.id.editTextPrice);
        editTextImageURL = rootView.findViewById(R.id.editTextImageURL);
        spinnerStock = rootView.findViewById(R.id.spinnerStock);

        // Obtener las opciones de stock del archivo de recursos de strings
        String[] stockOptions = getResources().getStringArray(R.array.stock_options);

        // Crear un adaptador para el spinner con las opciones de stock
        ArrayAdapter<String> stockAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, stockOptions);
        stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el spinner
        spinnerStock.setAdapter(stockAdapter);

        btnUpdate = rootView.findViewById(R.id.btnUpdate);

        // Crea una instancia de Retrofit con la URL base y el convertidor Gson
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        // Carga los nombres de los productos en el spinner
        loadProductNames();

        // Establece un listener para el spinner de nombres de productos
        spinnerProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Actualiza los EditTexts con la cantidad, el precio y la url de la imagen del producto seleccionado
                if (productList != null && productList.size() > position) {
                    Product selectedProduct = productList.get(position);
                    editTextQuantity.setText(String.valueOf(selectedProduct.getQuantity()));
                    editTextPrice.setText(String.valueOf(selectedProduct.getPrice()));

                    editTextImageURL.setText(selectedProduct.getImageURL());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Establece un listener para el botón de actualizar
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        return rootView;
    }

    /**
     * Carga los nombres de los productos de la API y los muestra en el spinner.
     */
    private void loadProductNames() {
        crudInterface = retrofit.create(CRUD.class);
        Call<List<Product>> call = crudInterface.getAll();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    List<String> productNames = new ArrayList<>();
                    for (Product product : productList) {
                        productNames.add(product.getProductName().toUpperCase());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, productNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProductName.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Error loading products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error in obtaining the products", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Actualiza un producto en la API.
     */
    private void actualizar() {
        String productName = spinnerProductName.getSelectedItem().toString();
        String quantity = editTextQuantity.getText().toString().trim();
        String stock = spinnerStock.getSelectedItem().toString();
        String price = editTextPrice.getText().toString().trim();
        String imageURL = editTextImageURL.getText().toString().trim();

        if (quantity.isEmpty() || price.isEmpty() || imageURL.isEmpty()) {
            mostrarToast("Please complete all fields.");
            return;
        }

        StockType stockType = StockType.valueOf(stock.toUpperCase());
        ProductDto productDto = new ProductDto(productName, Float.parseFloat(quantity), Float.parseFloat(price), stockType, imageURL);
        crudInterface = retrofit.create(CRUD.class);
        Call<Product> call = crudInterface.update(productDto, productName);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                Product product = response.body();
                mostrarToast("Updated product: " + product.getProductName());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
                mostrarToast("Error updating the product");
            }
        });
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
