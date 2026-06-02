package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;

@NgComponent("am-event")
@NgRoutable(path = "event")
public class EventPage extends ConceptPage<EventPage> {
    public EventPage() {
        super("assets/concepts/event.md");
    }
}
