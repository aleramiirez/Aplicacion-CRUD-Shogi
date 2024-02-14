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
import com.vedruna.aplication_crud.model.StockType;
import com.vedruna.aplication_crud.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para agregar un nuevo producto a la base de datos.
 */
public class AddFragment extends Fragment {

    // Campo de texto para el nombre del producto
    EditText editTextName;

    // Campo de texto para la cantidad del producto
    EditText editTextQuantity;

    // Campo de texto para el precio del producto
    EditText editTextPrice;

    // Spinner para seleccionar el tipo de stock del producto
    Spinner spinnerStock;

    // Campo de texto para la URL de la imagen del producto
    EditText editTextImageURL;

    // Botón para crear un nuevo producto
    Button btnAdd;

    // Interfaz CRUD para comunicarse con la API
    CRUD crudInterface;

    public AddFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        // Inicializar los EditText
        editTextName = rootView.findViewById(R.id.editTextName);
        editTextQuantity = rootView.findViewById(R.id.editTextQuantity);
        editTextPrice = rootView.findViewById(R.id.editTextPrice);
        editTextImageURL = rootView.findViewById(R.id.editTextImageURL);

        // Inicializar el Spinner
        spinnerStock = rootView.findViewById(R.id.spinnerStock);

        // Obtener las opciones de stock del archivo de recursos de strings
        String[] stockOptions = getResources().getStringArray(R.array.stock_options);

        // Crear un adaptador para el spinner con las opciones de stock
        ArrayAdapter<String> stockAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, stockOptions);
        stockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el spinner
        spinnerStock.setAdapter(stockAdapter);

        // Inicializar el botón
        btnAdd = rootView.findViewById(R.id.btnAdd);

        // Puedes agregar un listener al botón si deseas manejar clics
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().toUpperCase();
                String quantityString = editTextQuantity.getText().toString();
                String stock = spinnerStock.getSelectedItem().toString();
                String priceString = editTextPrice.getText().toString();
                String imageURL = editTextImageURL.getText().toString();

                if (name.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
                    mostrarToast("Please complete all fields.");
                    return;
                }

                float quantity = Float.parseFloat(quantityString);
                float price = Float.parseFloat(priceString);
                StockType stockType = StockType.valueOf(stock.toUpperCase());
                ProductDto dto = new ProductDto(name, quantity, price, stockType, imageURL);
                create(dto);
            }
        });

        return rootView;
    }

    /**
     * Crear un nuevo producto en la API.
     *
     * @param productDto El objeto ProductDto que contiene la nueva información del producto.
     */
    private void create(ProductDto productDto){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        crudInterface= retrofit.create(CRUD.class);
        Call<Product> call = crudInterface.create(productDto);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if(!response.isSuccessful()){
                    Log.e("Response err ",response.message());
                    return;
                }
                Product product = response.body();
                mostrarToast("The product has been added : " + product.getProductName());


            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                mostrarToast("the product could not be added");
            }
        });

    }

    /**
     * Muestra un toast en la pantalla.
     *
     * @param mensaje El mensaje que quieres que se muestre en el toast.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}