package com.activitymaster.website;

import com.jwebmp.core.base.angular.client.annotations.angular.NgApp;
import com.jwebmp.core.base.angular.services.NGApplication;
import com.jwebmp.plugins.fontawesome5pro.FontAwesome5ProPageConfigurator;
import com.jwebmp.webawesome.components.WebAwesomePageConfigurator;

@NgApp(value = "am-website", bootComponent = WebsiteBoot.class)
public class WebsiteApplication extends NGApplication<WebsiteApplication>
{
    public WebsiteApplication()
    {
        getOptions().setTitle("Activity Master - Reactive Canonical Data Manager");
        WebAwesomePageConfigurator.setWaKitCode("7fb1940849d240bb");
        FontAwesome5ProPageConfigurator.setKitCode("28394571bc");
    }

}
