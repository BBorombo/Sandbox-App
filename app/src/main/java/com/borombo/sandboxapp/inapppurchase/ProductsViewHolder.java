package com.borombo.sandboxapp.inapppurchase;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.sandboxapp.R;

import org.solovyev.android.checkout.Sku;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phantom on 17/05/2017.
 */

public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.productTItle)
    TextView productTitle;

    @BindView(R.id.productDescription)
    TextView productDescription;

    @BindView(R.id.productPrice)
    TextView productPrice;

    private final ProductAdapter adapter;

    @Nullable
    private Sku sku;


    public ProductsViewHolder(View itemView, ProductAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void updateUI(Sku sku){
        this.sku = sku;
        productTitle.setText(sku.title);
        productDescription.setText(sku.description);
        productPrice.setText(sku.price);
    }

    @Override
    public void onClick(View view) {
        if (sku != null){
            adapter.onClick(sku);
        }
    }
}
