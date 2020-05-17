package com.tesusil.jezyki.wysokiego.poziomu.order

import com.tesusil.jezyki.wysokiego.poziomu.order.request.NewOrderRequest
import com.tesusil.jezyki.wysokiego.poziomu.order.response.OrderFullDetailsResponse
import com.tesusil.jezyki.wysokiego.poziomu.order.response.OrderShortDetailsResponse
import com.tesusil.jezyki.wysokiego.poziomu.orderItem.OrderItem
import com.tesusil.jezyki.wysokiego.poziomu.orderItem.OrderItemRepository
import com.tesusil.jezyki.wysokiego.poziomu.product.ProductRepository
import com.tesusil.jezyki.wysokiego.poziomu.user.UserException
import com.tesusil.jezyki.wysokiego.poziomu.user.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/order/")
class OrderController(private val orderRepo: OrderRepository,
                      private val orderItemsRepo: OrderItemRepository,
                      private val productRepository: ProductRepository,
                      private val userRepository: UserRepository) {

    @GetMapping("main/")
    fun mainDetails(): List<OrderShortDetailsResponse> {
        return orderRepo.findAll().asSequence().map {
            castOrderToOrderShortDetails(it)
        }.toList()
    }

    @GetMapping("details/{orderId}")
    fun orderDetail(@PathVariable orderId: Long): OrderFullDetailsResponse {
        val optional = orderRepo.findById(orderId)
        if (optional.isPresent) {
            val details = castOrderToOrderShortDetails(optional.get())
            val productList = orderItemsRepo.findAll().asSequence().filter { it.order.id == orderId }.toList()
            return OrderFullDetailsResponse(details, productList)
        }
        throw OrderException.NotFound(orderId)
    }

    @PostMapping("new/")
    fun newOrder(@RequestBody body: NewOrderRequest) {
        if (!userRepository.findById(body.userId).isPresent) {
            throw UserException.UserNotFoundException()
        }
        var cost = 0.0
        body.productList.forEach { cost += it.product.price * it.amount }
        var order = Order(user = userRepository.findById(body.userId).get(),
                date = Calendar.getInstance().time,
                cost = cost)
        order = orderRepo.save(order)
        body.productList.forEach {
            val orderItem = OrderItem(it, order)
            orderItemsRepo.save(orderItem)
        }
    }

    private fun getCostOfOrder(orderId: Long): Double {
        val productList = orderItemsRepo.findAll().asSequence()
                .filter { it.order.id == orderId }.toList()

        var result = 0.0
        productList.forEach {
            result += (it.product.price * it.amount)
        }
        return result
    }

    private fun castOrderToOrderShortDetails(order: Order): OrderShortDetailsResponse {
        val userName = order.user.userName
        val amount = getCostOfOrder(order.id)
        return OrderShortDetailsResponse(userName, order.date, amount, order.id)
    }
}