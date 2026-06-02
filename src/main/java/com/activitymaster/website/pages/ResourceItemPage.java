package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;

@NgComponent("am-resource-item")
@NgRoutable(path = "resource-item")
public class ResourceItemPage extends ConceptPage<ResourceItemPage> {
    public ResourceItemPage() {
        super("assets/concepts/resource-item.md");
    }
}
