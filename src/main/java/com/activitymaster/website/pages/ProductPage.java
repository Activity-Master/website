package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;

@NgComponent("am-product")
@NgRoutable(path = "product")
public class ProductPage extends ConceptPage<ProductPage> {
    public ProductPage() {
        super("assets/concepts/product.md");
    }
}
