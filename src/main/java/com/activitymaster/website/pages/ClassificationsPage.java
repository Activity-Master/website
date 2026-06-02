package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;

@NgComponent("am-classifications")
@NgRoutable(path = "classifications")
public class ClassificationsPage extends ConceptPage<ClassificationsPage> {
    public ClassificationsPage() {
        super("assets/concepts/classifications.md");
    }
}
