package com.borombo.sandboxapp.inapppurchase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.sandboxapp.R;

import org.solovyev.android.checkout.Inventory;
import org.solovyev.android.checkout.ProductTypes;
import org.solovyev.android.checkout.Purchase;
import org.solovyev.android.checkout.Sku;

/**
 * Created by Phantom on 17/05/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductsViewHolder>{

    Inventory.Product products = Inventory.Products.empty().get(ProductTypes.IN_APP);;

    Context context;

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.inapp_products_row, parent, false);
        this.context = view.getContext();
        return new ProductsViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Sku sku = products.getSkus().get(position);
        holder.updateUI(sku);
    }

    @Override
    public int getItemCount() {return products.getSkus().size();}

    public void onClick(Sku sku){
        final Purchase purchase = products.getPurchaseInState(sku, Purchase.State.PURCHASED);
        if (purchase != null){
            ((InAppPurchaseMainActivity) context).consume(purchase);
        }else{
            ((InAppPurchaseMainActivity) context).purchase(sku);
        }
    }

    public void update(Inventory.Product products){
        this.products = products;
        notifyDataSetChanged();
    }

}
