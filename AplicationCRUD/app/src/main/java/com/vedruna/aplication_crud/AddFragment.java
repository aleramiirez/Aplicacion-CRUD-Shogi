package com.vedruna.aplication_crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.aplication_crud.dto.ProductDto;
import com.vedruna.aplication_crud.interfaz.CRUD;
import com.vedruna.aplication_crud.model.Product;
import com.vedruna.aplication_crud.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddFragment extends Fragment {

    EditText editTextName;

    EditText editTextQuantity;

    EditText editTextPrice;


    Button btnAdd;

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

        // Inicializar el botón
        btnAdd = rootView.findViewById(R.id.btnAdd);

        // Puedes agregar un listener al botón si deseas manejar clics
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String quantityString = editTextQuantity.getText().toString();
                String priceString = editTextPrice.getText().toString();

                if (name.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
                    mostrarToast("Please complete all fields.");
                    return;
                }

                int quantity = Integer.parseInt(quantityString);
                float price = Float.parseFloat(priceString);
                ProductDto dto = new ProductDto(name, quantity, price);
                create(dto);
            }
        });

        return rootView;
    }

    private void create(ProductDto dto){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        crudInterface= retrofit.create(CRUD.class);
        Call<Product> call = crudInterface.create(dto);

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
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}