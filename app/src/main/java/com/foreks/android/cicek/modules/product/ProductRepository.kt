package com.foreks.android.cicek.modules.product

import com.foreks.android.cicek.data.ProductListService
import com.foreks.android.cicek.util.Result
import org.json.JSONObject
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productListService: ProductListService
) {

    suspend fun getProducts(): Result<List<Product>?> {
        val jsonObject = JSONObject(productListService.getProduct().string())

        val productList = mutableListOf<Product>()

        val result = jsonObject.optJSONObject("result")
        val data = result?.optJSONObject("data")
        val productsArray = data?.optJSONArray("products")
        val length = productsArray?.length() ?: 0

        for (x in 0 until length) {
            val jsonObjectProduct = productsArray?.getJSONObject(x)
            val priceObject = jsonObjectProduct?.optJSONObject("price")
            productList.add(
                Product(
                    image = jsonObjectProduct?.optString("image") ?: "-",
                    name = jsonObjectProduct?.optString("name") ?: "-",
                    price = Price(priceObject?.optDouble("current") ?: 0.0)
                )
            )
        }
        return if (result != null && productList.isNotEmpty()) {
            Result.success(productList)
        } else {
            Result.error("Bir hata oluştu.", data = null)
        }
    }
}

data class Product(
    val image: String,
    val name: String,
    val price: Price
)

data class Price(
    val currentPrice: Double
)
