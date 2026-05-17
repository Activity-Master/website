package com.activitymaster.website;

import com.jwebmp.core.Page;
import com.jwebmp.core.base.angular.client.annotations.angularconfig.NgStyleSheet;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootImportProvider;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootImportReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.services.TypescriptIndexPageConfigurator;
import com.jwebmp.core.base.references.CSSReference;
import com.jwebmp.core.services.IPage;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.fontawesome5pro.FontAwesome5ProPageConfigurator;
import com.jwebmp.webawesome.components.WebAwesomePageConfigurator;

@NgStyleSheet(value = "public/base.css", name = "ActivityMasterBase", sortOrder = 200)
@NgStyleSheet(value = "public/layout.css", name = "ActivityMasterLayout", sortOrder = 201)
@NgStyleSheet(value = "public/components.css", name = "ActivityMasterComponents", sortOrder = 202)
@NgStyleSheet(value = "public/features.css", name = "ActivityMasterFeatures", sortOrder = 203)
@NgStyleSheet(value = "public/code.css", name = "ActivityMasterCode", sortOrder = 204)
@NgComponentReference(MarkdownClipboardButton.class)
@NgBootImportProvider(value = "provideMarkdown({ mermaidOptions: { provide: MERMAID_OPTIONS, useValue: { startOnLoad: false } }, clipboardOptions: { provide: CLIPBOARD_OPTIONS, useValue: { buttonComponent: MarkdownClipboardButton } } })", overrides = true)
@NgBootImportReference(value = "provideMarkdown", reference = "ngx-markdown")
@NgBootImportReference(value = "MERMAID_OPTIONS", reference = "ngx-markdown")
@NgBootImportReference(value = "CLIPBOARD_OPTIONS", reference = "ngx-markdown")
@NgBootImportReference(value = "MarkdownClipboardButton", reference = "./com/activitymaster/website/MarkdownClipboardButton/MarkdownClipboardButton")
public class WebsitePageConfigurator implements IPageConfigurator<WebsitePageConfigurator>, TypescriptIndexPageConfigurator<WebsitePageConfigurator>
{
    @Override
    public IPage<?> configure(IPage<?> page)
    {
        page.addCssReference(new CSSReference("ActivityMasterBase", 1.0, "/base.css"));
        page.addCssReference(new CSSReference("ActivityMasterLayout", 1.0, "/layout.css"));
        page.addCssReference(new CSSReference("ActivityMasterComponents", 1.0, "/components.css"));
        page.addCssReference(new CSSReference("ActivityMasterFeatures", 1.0, "/features.css"));
        page.addCssReference(new CSSReference("ActivityMasterCode", 1.0, "/code.css"));
        FontAwesome5ProPageConfigurator.setKitCode("28394571bc");
        Page<?> p = (Page<?>) page;
        return page;
    }

    @Override
    public IPage<?> configureAngular(IPage<?> page) {
        return configure(page);
    }

    @Override
    public boolean enabled()
    {
        return true;
    }

    @Override
    public Integer sortOrder()
    {
        return -8000;
    }
}

