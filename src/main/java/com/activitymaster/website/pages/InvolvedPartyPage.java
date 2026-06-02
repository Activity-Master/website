package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;

@NgComponent("am-involved-party")
@NgRoutable(path = "involved-party")
public class InvolvedPartyPage extends ConceptPage<InvolvedPartyPage> {
    public InvolvedPartyPage() {
        super("assets/concepts/involved-party.md");
    }
}
