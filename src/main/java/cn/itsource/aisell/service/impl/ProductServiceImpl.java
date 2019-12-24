package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.service.IProductService;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements IProductService {
}
