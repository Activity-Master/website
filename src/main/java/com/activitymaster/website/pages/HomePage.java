package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.html.DivSimple;

@NgComponent("am-home")
@NgRoutable(path = "home", isDefault = true)
public class HomePage extends DivSimple<HomePage> implements INgComponent<HomePage>
{

}