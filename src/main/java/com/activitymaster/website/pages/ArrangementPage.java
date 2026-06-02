package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;

@NgComponent("am-arrangement")
@NgRoutable(path = "arrangement")
public class ArrangementPage extends ConceptPage<ArrangementPage> {
    public ArrangementPage() {
        super("assets/concepts/arrangement.md");
    }
}
