package com.zahid.sharedtransition.data.datasource

import com.zahid.sharedtransition.data.model.ProductDataModel
import com.zahid.sharedtransition.data.model.ProductItemDataModel
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DataSource @Inject constructor() {

    private val PRODUCT_JSON_DATA : String = "{\"products\":[{\"id\":1,\"name\":\"Wireless Bluetooth Headphones\",\"description\":\"Over-ear noise-cancelling headphones with long battery life.\",\"price\":89.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.5,\"category\":\"Electronics\",\"imageUrl\":\"https://images.unsplash.com/photo-1679533662345-b321cf2d8792?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":2,\"name\":\"Smart Fitness Watch\",\"description\":\"Track workouts, heart rate, and sleep patterns.\",\"price\":129.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.6,\"category\":\"Wearables\",\"imageUrl\":\"https://plus.unsplash.com/premium_photo-1712761998611-c59db7dff27e?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":3,\"name\":\"Mechanical Keyboard\",\"description\":\"RGB mechanical keyboard for fast and accurate typing.\",\"price\":109.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.7,\"category\":\"Computers\",\"imageUrl\":\"https://plus.unsplash.com/premium_photo-1664699099309-afd82b75bd1a?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":4,\"name\":\"Wireless Mouse\",\"description\":\"Ergonomic mouse with adjustable DPI.\",\"price\":29.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.3,\"category\":\"Computers\",\"imageUrl\":\"https://plus.unsplash.com/premium_photo-1671611822374-4719df5c89bb?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":5,\"name\":\"Laptop Backpack\",\"description\":\"Water-resistant backpack for laptops up to 15 inches.\",\"price\":49.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.4,\"category\":\"Accessories\",\"imageUrl\":\"https://images.unsplash.com/photo-1673505379580-fe6de14f3080?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":6,\"name\":\"Smartphone\",\"description\":\"High-resolution OLED smartphone with fast processor.\",\"price\":699.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.8,\"category\":\"Electronics\",\"imageUrl\":\"https://plus.unsplash.com/premium_photo-1661884002007-fea55e211ec7?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":7,\"name\":\"Wireless Earbuds\",\"description\":\"Compact earbuds with noise isolation.\",\"price\":79.99,\"currency\":\"USD\",\"inStock\":false,\"rating\":4.4,\"category\":\"Electronics\",\"imageUrl\":\"https://images.unsplash.com/photo-1632835746204-22f652dac3af?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":8,\"name\":\"Tablet Device\",\"description\":\"Lightweight tablet for media and browsing.\",\"price\":249.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.5,\"category\":\"Electronics\",\"imageUrl\":\"https://plus.unsplash.com/premium_photo-1706548331079-f46bc392cdea?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":9,\"name\":\"Gaming Chair\",\"description\":\"Comfortable chair with lumbar support.\",\"price\":199.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.6,\"category\":\"Furniture\",\"imageUrl\":\"https://images.unsplash.com/photo-1670946839270-cc4febd43b09?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"},{\"id\":10,\"name\":\"Desk Lamp\",\"description\":\"LED desk lamp with adjustable brightness.\",\"price\":24.99,\"currency\":\"USD\",\"inStock\":true,\"rating\":4.2,\"category\":\"Home\",\"imageUrl\":\"https://plus.unsplash.com/premium_photo-1681223965653-5d20efd0b86c?w=256\",\"createdAt\":\"2026-01-09T00:00:00Z\"}]}"

    fun fetchProductList(): List<ProductItemDataModel> {
        val json = Json { ignoreUnknownKeys = true }
        val product = json.decodeFromString<ProductDataModel>(PRODUCT_JSON_DATA)
        return product.products
    }

    fun fetchProduct(productId: Int): ProductItemDataModel? {
        val json = Json { ignoreUnknownKeys = true }
        val productData = json.decodeFromString<ProductDataModel>(PRODUCT_JSON_DATA)
        val product: ProductItemDataModel? = productData.products.find {
            (it.id == productId)
        }
        return product
    }



}