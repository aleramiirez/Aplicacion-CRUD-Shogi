package com.vedruna.aplication_crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vedruna.aplication_crud.R;
import com.vedruna.aplication_crud.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private List<Product> products;
    private Context context;

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

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nameLabel = convertView.findViewById(R.id.nameLabel);
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.quantityLabel = convertView.findViewById(R.id.quantityLabel);
            viewHolder.quantityText = convertView.findViewById(R.id.quantityText);
            viewHolder.priceLabel = convertView.findViewById(R.id.priceLabel);
            viewHolder.priceText = convertView.findViewById(R.id.priceText);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtén el producto actual
        Product product = (Product) getItem(position);

        // Asignar los valores del producto a las vistas
        viewHolder.nameLabel.setText("Name: ");
        viewHolder.nameText.setText(product.getProductName());
        viewHolder.quantityLabel.setText("Quantity: ");
        viewHolder.quantityText.setText(String.valueOf(product.getQuantity()));
        viewHolder.priceLabel.setText("Price: ");
        viewHolder.priceText.setText(String.valueOf(product.getPrice()));

        return convertView;
    }

    static class ViewHolder {
        TextView nameLabel;
        TextView nameText;
        TextView quantityLabel;
        TextView quantityText;
        TextView priceLabel;
        TextView priceText;
    }

}
