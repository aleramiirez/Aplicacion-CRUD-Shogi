package com.vedruna.aplication_crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.aplication_crud.R;
import com.vedruna.aplication_crud.model.Product;

import java.util.List;

/**
 * Adaptador personalizado para mostrar una lista de productos en una vista de lista.
 */
public class ProductAdapter extends BaseAdapter {

    // Lista de productos a mostrar en la vista de lista
    private List<Product> products;

    // Contexto de la aplicación
    private Context context;

    /**
     * Constructor del adaptador de productos.
     *
     * @param context  Contexto de la aplicación.
     * @param products Lista de productos a mostrar.
     */
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getProductID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Verificar si la vista actual es nula
        if (convertView == null) {

            // Inflar el diseño de la fila de producto si aún no se ha inflado
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);

            // Inicializar el ViewHolder y asignar las vistas de diseño a los campos correspondientes
            viewHolder = new ViewHolder();
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.quantityText = convertView.findViewById(R.id.quantityText);
            viewHolder.stockText = convertView.findViewById(R.id.stockText);
            viewHolder.priceText = convertView.findViewById(R.id.priceText);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);

            // Establecer el ViewHolder como una etiqueta de la vista convertida
            convertView.setTag(viewHolder);
        } else {
            // Si la vista convertida no es nula, obtener el ViewHolder de la etiqueta de la vista convertida
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtener el producto actual en la posición dada
        Product product = (Product) getItem(position);

        // Asignar los valores del producto a las vistas correspondientes en el diseño de fila
        viewHolder.nameText.setText(product.getProductName().toUpperCase());
        viewHolder.quantityText.setText(String.valueOf(product.getQuantity()));
        viewHolder.stockText.setText(String.valueOf(product.getStock()));
        viewHolder.priceText.setText(String.valueOf(product.getPrice() + " €"));

        // Cargar la imagen del producto utilizando Picasso y asignarla al ImageView correspondiente
        Picasso.get().load(product.getImageURL()).into(viewHolder.imageView);

        return convertView;
    }

    /**
     * Clase estática para contener las vistas de diseño de fila y evitar la recreación innecesaria de vistas.
     */
    static class ViewHolder {
        TextView nameText;
        TextView quantityText;
        TextView stockText;
        TextView priceText;
        ImageView imageView;
    }

}
