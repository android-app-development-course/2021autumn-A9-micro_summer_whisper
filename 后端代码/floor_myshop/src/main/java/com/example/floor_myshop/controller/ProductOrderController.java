package com.example.floor_myshop.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.floor_myshop.conditon.OrderCondition;
import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.entity.ProductOrder;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IProductOrderService;
import com.example.floor_myshop.service.IProductService;
import com.example.floor_myshop.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.floor_myshop.util.ControllerUtils.checkALessThanB;
import static com.example.floor_myshop.util.ControllerUtils.checkALessThanBOnLocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh & tong
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    private IProductOrderService productOrderService;

    @Autowired
    private IProductService productService;

    @PostMapping("/getOrderList")
    public ApiResponse getOrderList(
            @RequestBody OrderCondition orderCondition
            ){
        Map<SFunction<ProductOrder, ?>, Object> map = new HashMap<>();
        map.put(ProductOrder::getOrderId,orderCondition.getOrderId());
        map.put(ProductOrder::getShopId,orderCondition.getShopId());
        map.put(ProductOrder::getLogisticsStatus,orderCondition.getLogisticsStatus());
        map.put(ProductOrder::getLogisticsId,orderCondition.getLogisticsId());
        map.put(ProductOrder::getUserId,orderCondition.getUserId());
        map.put(ProductOrder::getIsDeleted,orderCondition.getIsDeleted());
        map.put(ProductOrder::getOrderState,orderCondition.getOrderState());
        map.put(ProductOrder::getLogisticsOrderNumber,orderCondition.getLogisticsOrderNumber());
        final List<ProductOrder> list = productOrderService.list(Wrappers.<ProductOrder>lambdaQuery()
                .like(StringUtils.isNotBlank(orderCondition.getAddress()), ProductOrder::getAddress, orderCondition.getAddress())
                .like(StringUtils.isNotBlank(orderCondition.getReceiverName()), ProductOrder::getReceiverName, orderCondition.getReceiverName())
                .like(StringUtils.isNotBlank(orderCondition.getReceiverPhone()), ProductOrder::getReceiverPhone, orderCondition.getReceiverPhone())
                .between(checkALessThanBOnLocalDateTime(orderCondition.getMinCreateTime(),orderCondition.getMaxCreateTime()),
                        ProductOrder::getCreateTime,orderCondition.getMinCreateTime(),orderCondition.getMaxCreateTime()
                )
                .between(checkALessThanB(orderCondition.getMinAmount(),orderCondition.getMaxAmount(),0),
                        ProductOrder::getAmount,orderCondition.getMinAmount(),orderCondition.getMaxAmount()
                )
                .eq(ProductOrder::getShopId,orderCondition.getShopId())
                .allEq(map, false)
        );
        final List<OrderVo> collect = list.stream().map(new Function<ProductOrder, OrderVo>() {
            @Override
            public OrderVo apply(ProductOrder productOrder) {
                final Product prot = productService.getOne(Wrappers.<Product>lambdaQuery().eq(Product::getProductId, productOrder.getProductId()));
                return productOrder.toOrderVo(prot.getProductName(), prot.getImgAddr(),
                        prot.getNormalPrice(), null, null);
            }
        }).collect(Collectors.toList());
        return ApiResponse.success("获取订单列表成功",collect);
    }

    @PostMapping("/updateOrder")
    public ApiResponse<OrderVo> updateOrder(
            @RequestParam("orderId") Integer orderId,
            @RequestParam(value = "orderState",required = false) Integer orderState,
            @RequestParam(value = "logisticsOrderNumber", required = false) String logisticsOrderNumber
                                            ){
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(orderId);
        orderVo.setOrderState(orderState);
        orderVo.setLogisticsOrderNumber(logisticsOrderNumber);
        if (productOrderService.updateById(orderVo.toProductOrder())) {
            final ProductOrder productOrderServiceById = productOrderService.getById(orderVo.getOrderId());
            return ApiResponse.success("更新订单成功",
                    productOrderServiceById.toOrderVo(orderVo.getProductName(),
                            orderVo.getProductImg(),orderVo.getOnePrice(),orderVo.getTotalPrice(),
                            null
                    ));
        } else {
            return ApiResponse.failed("更新订单失败");
        }

    }

    @PostMapping("/addOrder")
    public ApiResponse addOrder(@RequestBody OrderVo orderVo){
        final ProductOrder productOrder = orderVo.toProductOrder();
        if (productOrderService.save(productOrder)) {
            final Product prot = productService.getById(productOrder.getProductId());
            return ApiResponse.success("添加订单成功",productOrder.toOrderVo(prot.getProductName(),
                    prot.getImgAddr(), prot.getNormalPrice(), null, null
                    ));
        } else {
            return ApiResponse.failed("添加订单失败");
        }
    }

}

