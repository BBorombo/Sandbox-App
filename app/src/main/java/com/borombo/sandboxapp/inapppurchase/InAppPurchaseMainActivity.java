package com.borombo.sandboxapp.inapppurchase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.SandboxApplication;

import org.solovyev.android.checkout.ActivityCheckout;
import org.solovyev.android.checkout.Checkout;
import org.solovyev.android.checkout.EmptyRequestListener;
import org.solovyev.android.checkout.Inventory;
import org.solovyev.android.checkout.ProductTypes;
import org.solovyev.android.checkout.Purchase;

public class InAppPurchaseMainActivity extends AppCompatActivity {


    private final ActivityCheckout mCheckout = Checkout.forActivity(this, SandboxApplication.get().getBilling());
    private Inventory mInventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_purchase_main);

        mCheckout.start();

        mCheckout.createPurchaseFlow(new PurchaseListener());

        mInventory = mCheckout.makeInventory();
        mInventory.load(Inventory.Request.create()
                .loadAllPurchases()
                .loadSkus(ProductTypes.IN_APP, "sku_01"), new InventoryCallback());
    }

    @Override
    protected void onDestroy() {
        mCheckout.stop();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCheckout.onActivityResult(requestCode, resultCode, data);
    }


    private class PurchaseListener extends EmptyRequestListener<Purchase> {
        // your code here
    }

    private class InventoryCallback implements Inventory.Callback {
        @Override
        public void onLoaded(Inventory.Products products) {
            // your code here
        }
    }
}
