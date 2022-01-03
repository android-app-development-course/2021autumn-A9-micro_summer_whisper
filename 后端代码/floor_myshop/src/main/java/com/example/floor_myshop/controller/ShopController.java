package com.example.floor_myshop.controller;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.floor_myshop.entity.Person;
import com.example.floor_myshop.entity.ProductOrder;
import com.example.floor_myshop.entity.Shop;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.service.IPersonService;
import com.example.floor_myshop.service.IProductOrderService;
import com.example.floor_myshop.service.IShopService;
import com.example.floor_myshop.util.ControllerUtils;
import com.example.floor_myshop.util.DateTimeUtils;
import com.example.floor_myshop.vo.StoreDashVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private IProductOrderService productOrderService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private IPersonService personService;

    @GetMapping("/getStoreInfo/{id}")
    public ApiResponse getStoreInfo(@PathVariable("id") Integer id){
        final Shop one = shopService.getById(id);
        return ApiResponse.success("查询店铺信息成功",one);
    }

    @GetMapping("/hasStore/{userId}")
    public ApiResponse hasStore(@PathVariable("userId") Integer userId){
        try {
            final Shop one = shopService.getOne(Wrappers.<Shop>lambdaQuery()
                    .eq(Shop::getOwnerId, userId)
            );
            return ApiResponse.success("存在店铺",one);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.failed("不存在店铺");
        }
    }

    @PostMapping("/updateStoreInfo")
    public ApiResponse updateStoreInfo(@RequestBody Shop reqShop){
        ControllerUtils.trySetImg(reqShop,reqShop.getShopImg(),(pv, p) -> pv.setShopImg(p));
        final Shop[] one = {null};
        final Boolean success = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    if (shopService.updateById(reqShop)){
                        one[0] = shopService.getById(reqShop.getShopId());
                        if (reqShop.getShopImg()==null){
                            return true;
                        }
                        final Person person = new Person();
                        person.setUserId(one[0].getOwnerId());
                        person.setProfileImg(one[0].getShopImg());
                        if (personService.updateById(person)) {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status.setRollbackOnly();
                }
                return false;
            }
        });
        if (success){
            return ApiResponse.success("更新 店铺信息成功", one[0]);
        } else {
            return ApiResponse.failed("更新店铺信息失败");
        }
    }

    @PostMapping("/createStore")
    public ApiResponse createStore(@RequestBody Shop reqShop){
        if (shopService.save(reqShop)) {
            return ApiResponse.success("创建店铺成功",reqShop);
        } else {
            return ApiResponse.failed("创建店铺失败");
        }
    }

    @PostMapping("/deleteStore")
    public ApiResponse deleteStore(@RequestBody Shop reqShop){
        if (shopService.removeById(reqShop)) {
            return ApiResponse.success("删除店铺成功",reqShop);
        } else {
            return ApiResponse.failed("删除店铺失败");
        }
    }


    @GetMapping("/getStoreDash/{id}")
    public ApiResponse getStoreDash(@PathVariable("id") Integer id){
        final Date yesterdayYesterday = DateTime.now().offset(DateField.DAY_OF_MONTH, -2).toJdkDate();
        final Date yesterday = DateTime.now().offset(DateField.DAY_OF_MONTH, -1).toJdkDate();
        final Date now = DateTime.now().toJdkDate();
        final long yesterdayVisitCount = 1001;
        final long todayVisitCount = 1002;
        final long yesterdayOrderCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                        .eq(ProductOrder::getShopId,id)
                .between(ProductOrder::getCreateTime,
                        DateTimeUtils.toLocalDateTimeFromDate(yesterdayYesterday),
                        DateTimeUtils.toLocalDateTimeFromDate(yesterday)
                )
        );
        final long todayOrderCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .between(ProductOrder::getCreateTime,
                        DateTimeUtils.toLocalDateTimeFromDate(yesterday),
                        DateTimeUtils.toLocalDateTimeFromDate(now)
                )
        );
        final long waitPayCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .eq(ProductOrder::getOrderState,ProductOrder.WAIT_PAY)
        );
        final long waitSendGoodCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .eq(ProductOrder::getOrderState,ProductOrder.WAIT_SEND_GOOD)
        );
        final long waitConfirmReceiveCount = productOrderService.count(Wrappers.<ProductOrder>lambdaQuery()
                .eq(ProductOrder::getShopId,id)
                .eq(ProductOrder::getOrderState,ProductOrder.WAIT_CONFIRM_RECEIVE)
        );

        return ApiResponse.success("查询店铺信息成功",new StoreDashVo((int)todayVisitCount,(int)yesterdayVisitCount,
                (int)todayOrderCount,(int)yesterdayOrderCount,
                (int)waitConfirmReceiveCount,(int)waitSendGoodCount,(int)waitPayCount));
    }
}

