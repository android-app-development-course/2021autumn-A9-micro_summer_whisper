package com.example.floor_myshop.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.floor_myshop.conditon.ProductCondition;
import com.example.floor_myshop.entity.Category;
import com.example.floor_myshop.entity.Product;
import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.model.ResponseCode;
import com.example.floor_myshop.service.ICategoryService;
import com.example.floor_myshop.service.IProductService;
import com.example.floor_myshop.vo.ProductVo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.floor_myshop.util.ControllerUtils.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coderpwh
 * @since 2021-12-19
 */
@Api(tags = "商品api")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Transactional
    @PostMapping("/saveOrUpdateProduct")
    public ApiResponse addProduct(@RequestBody final ProductVo product){
        trySetImg(product, product.getImgAddr(), (pv , p) -> pv.setImgAddr(p));
        trySetImg(product, product.getPictureA(), (pv , p) -> pv.setPictureA(p));
        trySetImg(product, product.getPictureB(), (pv , p) -> pv.setPictureB(p));
        trySetImg(product, product.getPictureC(), (pv , p) -> pv.setPictureC(p));
        trySetImg(product, product.getPictureD(), (pv , p) -> pv.setPictureD(p));
        trySetImg(product, product.getPictureE(), (pv , p) -> pv.setPictureE(p));
        trySetImg(product, product.getPictureF(), (pv , p) -> pv.setPictureF(p));
        if ("".equals(product.getCategoryName())){
            product.setCategoryName(null);
        }
        Map<SFunction<Category,?>, Object> mapCate = new HashMap<>();
        mapCate.put(Category::getCategoryName,product.getCategoryName());
        mapCate.put(Category::getShopId,product.getShopId());
        Category dbCate = categoryService.getOne(Wrappers.<Category>lambdaQuery()
                .allEq(mapCate)
        );
        if (dbCate==null){
            final Category dbCate1 = new Category(null, product.getCategoryName(), "", 0, LocalDateTime.now(),
                    LocalDateTime.now(), product.getShopId(), 0
            );
            if (!categoryService.save(dbCate1)) {
                return ApiResponse.failed("创建分类失败");
            } else {
                dbCate = dbCate1;
            }
        }
        product.setCategoryId(dbCate.getCategoryId());
        final Product prot1 = product.toProduct();
        if (productService.saveOrUpdate(prot1)) {
            return ApiResponse.success("添加或更新商品成功",prot1.toProductVo(product.getCategoryName()));
        } else {
            return ApiResponse.failed(ResponseCode.PRODUCT_IS_EXISTS.getMessage(),ResponseCode.PRODUCT_IS_EXISTS.getCode());
        }
    }



    @PostMapping("/removeProduct/{id}")
    public ApiResponse removeProduct(@PathVariable("id") Integer id){
        if (productService.removeById(id)) {
            return ApiResponse.success("商品删除成功",null);
        } else {
            return ApiResponse.failed(ResponseCode.PRODUCT_NOT_EXISTS.getMessage(),ResponseCode.PRODUCT_NOT_EXISTS.getCode());
        }
    }


    @PostMapping("/getProductList")
    public ApiResponse getProductList(
            @RequestBody ProductCondition productCondition
            ) {
        Category cateOne = null;
        Map<SFunction<Category,?>, Object> mapCate = new HashMap<>();
        mapCate.put(Category::getCategoryName,productCondition.getCategoryName());
        mapCate.put(Category::getShopId,productCondition.getShopId());
        cateOne = categoryService.getOne(Wrappers.<Category>lambdaQuery()
                .allEq(mapCate)
        );
        Map<SFunction<Product,?>, Object> map = new HashMap<>();
        map.put(Product::getProductId,productCondition.getProductId());
        map.put(Product::getIsDeleted,productCondition.getIsDeleted());
        map.put(Product::getEnableStatus,productCondition.getEnableStatus());
        if (productCondition.getCategoryId()!=null){
            map.put(Product::getCategoryId,productCondition.getCategoryId());
        } else if (cateOne!=null){
            map.put(Product::getCategoryId,cateOne.getCategoryId());
        }
        final List<Product> list = productService.list(Wrappers.<Product>lambdaQuery()
                .like(StringUtils.isNotBlank(productCondition.getProductName()), Product::getProductName, productCondition.getProductName())
                .like(StringUtils.isNotBlank(productCondition.getProductDesc()), Product::getProductDesc, productCondition.getProductDesc())
                .between(checkALessThanB(productCondition.getMinNormalPrice(),productCondition.getMaxNormalPrice(),0),
                                Product::getNormalPrice,productCondition.getMinNormalPrice(),productCondition.getMaxNormalPrice()
                                )
                .between(checkALessThanB(productCondition.getMinPromotionPrice(),productCondition.getMaxPromotionPrice(),0),
                        Product::getPromotionPrice,productCondition.getMinPromotionPrice(),productCondition.getMaxPromotionPrice()
                )
                .between(checkALessThanB(productCondition.getMinPriority(),productCondition.getMaxPriority(),0),
                        Product::getPriority,productCondition.getMinPriority(),productCondition.getMaxPriority()
                )
                .between(checkALessThanBOnLocalDateTime(productCondition.getMinCreateTime(),productCondition.getMaxCreateTime()),
                        Product::getCreateTime,productCondition.getMinCreateTime(),productCondition.getMaxCreateTime()
                )
                .between(checkALessThanBOnLocalDateTime(productCondition.getMinLastEditTime(),productCondition.getMaxLastEditTime()),
                        Product::getLastEditTime,productCondition.getMinLastEditTime(),productCondition.getMaxLastEditTime()
                )
                .between(checkALessThanB(productCondition.getMinStock(),productCondition.getMaxStock(),0),
                        Product::getStock,productCondition.getMinStock(),productCondition.getMaxStock()
                )
                        .eq(Product::getShopId,productCondition.getShopId())
                .allEq(map, false));
        final List<ProductVo> collect = list.stream().map(new Function<Product, ProductVo>() {
            @Override
            public ProductVo apply(Product product) {
                return product.toProductVo(productCondition.getCategoryName());
            }
        }).collect(Collectors.toList());
        return ApiResponse.success("获取商品列表成功",collect);
    }



}

