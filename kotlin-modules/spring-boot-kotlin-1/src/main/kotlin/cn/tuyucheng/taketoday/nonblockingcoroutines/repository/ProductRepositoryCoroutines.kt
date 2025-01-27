package cn.tuyucheng.taketoday.nonblockingcoroutines.repository


import cn.tuyucheng.taketoday.nonblockingcoroutines.model.Product
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryCoroutines(private val client: DatabaseClient) {

   suspend fun getProductById(id: Int): Product? =
         client.execute("SELECT * FROM products WHERE id = $1")
               .bind(0, id)
               .`as`(Product::class.java)
               .fetch()
               .one()
               .awaitFirstOrNull()

   suspend fun addNewProduct(name: String, price: Float) =
         client.execute("INSERT INTO products (name, price) VALUES($1, $2)")
               .bind(0, name)
               .bind(1, price)
               .then()
               .awaitFirstOrNull()

   @FlowPreview
   fun getAllProducts(): Flow<Product> =
         client.select()
               .from("products")
               .`as`(Product::class.java)
               .fetch()
               .all()
               .log()
               .asFlow()
}