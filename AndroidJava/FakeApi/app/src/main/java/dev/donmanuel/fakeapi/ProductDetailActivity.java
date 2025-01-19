package dev.donmanuel.fakeapi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import dev.donmanuel.fakeapi.models.Product;
import dev.donmanuel.fakeapi.network.ApiCallback;
import dev.donmanuel.fakeapi.network.ApiClient;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView image;

    private TextView title, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);

        // Obtener el ID del producto desde el Intent
        int productId = getIntent().getIntExtra("productId", 0);

        // Validar el ID del producto
        if (productId == 0) {
            Toast.makeText(this, "Producto no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Llamar al cliente API para obtener los detalles del producto
        fetchProductDetails(productId);
    }

    private void fetchProductDetails(int productId) {
        ApiClient apiClient = ApiClient.getInstance();

        apiClient.fetchProductById(productId, new ApiCallback<Product>() {
            @Override
            public void onSuccess(Product product) {
                runOnUiThread(() -> {
                    title.setText(product.getTitle());
                    price.setText("$" + product.getPrice());
                    description.setText(product.getDescription());
                    Picasso.get().load(product.getImages().get(0)).into(image);
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> Toast.makeText(ProductDetailActivity.this, "Error al cargar detalles: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}