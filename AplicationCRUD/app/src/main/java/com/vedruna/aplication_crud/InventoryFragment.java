package com.vedruna.aplication_crud;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vedruna.aplication_crud.adapter.ProductAdapter;
import com.vedruna.aplication_crud.interfaz.CRUD;
import com.vedruna.aplication_crud.model.Product;
import com.vedruna.aplication_crud.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryFragment extends Fragment {

    // Lista de productos
    List<Product> productList;

    // Interfaz CRUD para realizar operaciones con los productos
    CRUD crudInterface;

    // ListView para mostrar los productos
    ListView listView;

    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        // Se obtiene la referencia al ListView desde la vista
        listView = view.findViewById(R.id.listView);

        // Se llama a la función para obtener todos los productos al crear el fragmento
        gelAll();
        return view;
    }

    /**
     * Obtiene todos los productos de la API y los muestra en el ListView
     */
    private void gelAll(){
        // Se construye un cliente Retrofit para realizar la petición a la API
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        // Se crea una instancia de la interfaz CRUD para acceder a los métodos de la API
        crudInterface = retrofit.create(CRUD.class);

        // Se realiza la petición para obtener todos los productos
        Call<List<Product>> call=crudInterface.getAll();

        // Se maneja la respuesta de la API
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    Log.e("Response err ",response.message());
                    return;
                }

                // Se obtiene la lista de productos de la respuesta
                productList=response.body();

                // Se crea un adaptador para mostrar los productos en el ListView
                ProductAdapter productAdapter = new ProductAdapter(requireContext(), productList);
                listView.setAdapter(productAdapter);

                // Si la versión de Android es N o superior, se imprime la lista de productos en el log
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    productList.forEach(p-> Log.i("Productos: ",p.toString()));
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Throw err:",t.getMessage());
            }
        });
    }
}