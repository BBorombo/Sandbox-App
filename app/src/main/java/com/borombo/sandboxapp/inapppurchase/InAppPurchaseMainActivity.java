package com.borombo.sandboxapp.inapppurchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.SandboxApplication;
import com.borombo.sandboxapp.common.activities.CommonActivity;

import org.solovyev.android.checkout.ActivityCheckout;
import org.solovyev.android.checkout.Billing;
import org.solovyev.android.checkout.BillingRequests;
import org.solovyev.android.checkout.Checkout;
import org.solovyev.android.checkout.Inventory;
import org.solovyev.android.checkout.ProductTypes;
import org.solovyev.android.checkout.Purchase;
import org.solovyev.android.checkout.RequestListener;
import org.solovyev.android.checkout.Sku;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InAppPurchaseMainActivity extends CommonActivity {

    private ActivityCheckout checkout;
    private InventoryCallback inventoryCallback;

    @BindView(R.id.productsRecyclerView)
    RecyclerView productRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_purchase_main);
        ButterKnife.bind(this);

        setUpActionBar(getString(R.string.checkout), ContextCompat.getColor(this,R.color.checkout));

        final ProductAdapter adapter = new ProductAdapter();
        inventoryCallback = new InventoryCallback(adapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setAdapter(adapter);

        final Billing billing = SandboxApplication.get(this).getBilling();
        checkout = checkout.forActivity(this, billing);
        checkout.start();
        reloadInventory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        checkout.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Purchase a non-consumable item
     * @param sku
     */
    public void purchase(Sku sku){
        final RequestListener<Purchase> listener = makeRequestistener();
        checkout.startPurchaseFlow(sku, null, listener);
    }

    public void consume(final Purchase purchase){
        checkout.whenReady(new Checkout.EmptyListener() {
            @Override
            public void onReady(@Nonnull BillingRequests requests) {
                requests.consume(purchase.token, makeRequestistener());
            }
        });
    }

    private <T> RequestListener<T> makeRequestistener(){
        return new RequestListener<T>() {
            @Override
            public void onSuccess(@Nonnull T result) {
                reloadInventory();
            }

            @Override
            public void onError(int response, @Nonnull Exception e) {
                reloadInventory();
            }
        };
    }

    private void reloadInventory(){
        final Inventory.Request request = Inventory.Request.create();

        request.loadAllPurchases();
        request.loadSkus(ProductTypes.IN_APP, getInAppSkus());
        checkout.loadInventory(request, inventoryCallback);
    }

    private static List<String> getInAppSkus(){
        final List<String> skus = new ArrayList<>();
        skus.add("inapp_coffee");
        return skus;
    }

    private static class InventoryCallback implements Inventory.Callback {
        private final ProductAdapter adapter;

        public  InventoryCallback(ProductAdapter adapter){
            this.adapter = adapter;
        }

        @Override
        public void onLoaded(@Nonnull Inventory.Products products) {
            Log.d("IN_APP","onLoaded");
            final Inventory.Product product = products.get(ProductTypes.IN_APP);
            Log.d("IN_APP","Size : " + product.getSkus().size());
            if (!product.supported) {
                return;
            }
            adapter.update(product);
        }
    }

}
