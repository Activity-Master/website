package com.activitymaster.website;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootImportProvider;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootImportReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportProvider;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.services.RouterOutlet;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.webawesome.components.PageSize;
import com.jwebmp.webawesome.components.Variant;
import com.jwebmp.webawesome.components.button.Appearance;
import com.jwebmp.webawesome.components.button.WaButton;
import com.jwebmp.webawesome.components.icon.WaIcon;
import com.jwebmp.webawesome.components.page.WaPage;
import com.jwebmp.webawesome.components.toast.WaToastDataService;
import com.jwebmp.webawesome.components.tooltip.WaTooltip;
import com.jwebmp.webawesome.components.tree.WaTree;
import com.jwebmp.webawesome.components.tree.WaTreeItem;
import com.jwebmp.webawesome.components.WaDiv;
import com.jwebmp.webawesome.tokens.WaSpaceToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Top-level boot component for the Activity Master website.
 * <p>
 * The WaPage is the outermost shell.  The banner holds a product navigation
 * bar inspired by the Web Awesome "Awesomeverse" pattern — a wa-cluster of
 * links with icons and tooltips for each DevSuite project.  Header, menu,
 * and navigation live in their normal page slots.  The main content area
 * holds the RouterOutlet for page routing.
 */
@NgComponent("activitymaster-app")
@NgRoutable(path = "")
@NgImportProvider("{provide: LOCALE_ID, useValue: 'en-ZA'}")
@NgBootImportProvider(value = "{ provide: LOCALE_ID, useValue: 'en-ZA' }")
@NgBootImportReference(value = "provideHttpClient", reference = "@angular/common/http")
@NgBootImportProvider("provideHttpClient()")
@NgBootImportReference(value = "LOCALE_ID", reference = "@angular/core")
@NgBootImportReference(value = "registerLocaleData", reference = "@angular/common")
@NgBootImportReference(value = "localeEnZa", reference = "@angular/common/locales/en-ZA", direct = true)
@NgImportReference(value = "localeEnZa", reference = "@angular/common/locales/en-ZA", direct = true, wrapValueInBraces = false)
@NgImportReference(value = "LOCALE_ID", reference = "@angular/core")
@NgImportReference(value = "registerLocaleData", reference = "@angular/common")
@NgImportReference(value = "signal", reference = "@angular/core")
@NgImportReference(value = "DOCUMENT", reference = "@angular/common")
@NgImportReference(value = "Router, NavigationStart, NavigationEnd", reference = "@angular/router")
@NgImportReference(value = "inject", reference = "@angular/core")
@NgImportReference(value = "filter", reference = "rxjs/operators")
@NgComponentReference(WaToastDataService.class)
@NgComponentReference(value = App.class)
public class WebsiteBoot extends DivSimple<WebsiteBoot> implements INgComponent<WebsiteBoot> {
    public WebsiteBoot() {
        setTag("ng-container");
        addStyle("width:100%");
        addStyle("height:100%");

        // ── WaPage is the top-level shell ──
        WaPage<?> page = new WaPage<>();
        page.addStyle("width:100%");
        page.addStyle("height:100%");

        // ── WaPage settings ──
        page.getMain().setPageSize(PageSize.ExtraSmall);

        // ── Banner: product navigation bar ──
        var banner = page.getHeader();

        DivSimple<?> navWrapper = new DivSimple<>();
        navWrapper.addClass("wrapper-nav-products");

        DivSimple<?> nav = new DivSimple<>();
        nav.setTag("nav");
        nav.addClass("nav-products");
        nav.addClass("nav-products-full");
        nav.addAttribute("aria-label", "DevSuite Products");

        // Primary product links
        DivSimple<?> primary = new DivSimple<>();
        primary.addClass("nav-products-primary");
        primary.addClass("wa-split");
        primary.addClass("wa-align-items-stretch");

        DivSimple<?> cluster = new DivSimple<>();
        cluster.addClass("wa-cluster");
        cluster.addClass("wa-align-items-stretch");
        cluster.addClass("wa-gap-0");

        // Activity Master — active product
        WaButton<?> activityBtn = new WaButton<>();
        activityBtn.setAppearance(Appearance.Plain);
        activityBtn.setVariant(Variant.Brand);
        activityBtn.addAttribute("routerLink", "/home");
        activityBtn.addClass("product");
        activityBtn.addClass("product-activity-master");
        activityBtn.addClass("product-active");
        activityBtn.setID("product-activity-master");
        activityBtn.setSize(com.jwebmp.webawesome.components.Size.Small);
        var activityLogo = new WaIcon<>();
        activityLogo.addClass("fak");
        activityLogo.addClass("fa-activitymaster-logo");
        activityLogo.addClass("logo-icon");
        activityLogo.addClass("logo-activity-master");
        activityLogo.addAttribute("label", "Activity Master");
        activityBtn.add(activityLogo);
        activityBtn.setText("Activity Master");
        activityBtn.setRenderTextBeforeChildren(false);
        cluster.add(activityBtn);

        // GuicedEE
        WaButton<?> guicedeeBtn = new WaButton<>();
        guicedeeBtn.setAppearance(Appearance.Plain);
        guicedeeBtn.setVariant(Variant.Brand);
        guicedeeBtn.setAsLink("https://guicedee.com", "guicedee", null);
        guicedeeBtn.addClass("product");
        guicedeeBtn.addClass("product-guicedee");
        guicedeeBtn.setID("product-guicedee");
        guicedeeBtn.setSize(com.jwebmp.webawesome.components.Size.Small);
        var guicedeeLogo = new WaIcon<>();
        guicedeeLogo.addClass("fak");
        guicedeeLogo.addClass("fa-guicedee-logo");
        guicedeeLogo.addClass("logo-icon");
        guicedeeLogo.addClass("logo-guicedee");
        guicedeeLogo.addAttribute("label", "GuicedEE");
        guicedeeBtn.add(guicedeeLogo);
        cluster.add(guicedeeBtn);
        WaTooltip<?> guicedeeTip = new WaTooltip<>();
        guicedeeTip.setForId("product-guicedee");
        guicedeeTip.setText("GuicedEE");
        cluster.add(guicedeeTip);

        // JWebMP
        WaButton<?> jwebmpBtn = new WaButton<>();
        jwebmpBtn.setAppearance(Appearance.Plain);
        jwebmpBtn.setVariant(Variant.Brand);
        jwebmpBtn.setAsLink("https://jwebmp.com", "jwebmp", null);
        jwebmpBtn.addClass("product");
        jwebmpBtn.addClass("product-jwebmp");
        jwebmpBtn.setID("product-jwebmp");
        jwebmpBtn.setSize(com.jwebmp.webawesome.components.Size.Small);
        var jwebmpLogo = new WaIcon<>();
        jwebmpLogo.addClass("fak");
        jwebmpLogo.addClass("fa-jwebmp-logo");
        jwebmpLogo.addClass("logo-icon");
        jwebmpLogo.addClass("logo-jwebmp");
        jwebmpLogo.addAttribute("label", "JWebMP");
        jwebmpBtn.add(jwebmpLogo);
        cluster.add(jwebmpBtn);
        WaTooltip<?> jwebmpTip = new WaTooltip<>();
        jwebmpTip.setForId("product-jwebmp");
        jwebmpTip.setText("JWebMP");
        cluster.add(jwebmpTip);

        // Entity Assist
        WaButton<?> entityBtn = new WaButton<>();
        entityBtn.setAppearance(Appearance.Plain);
        entityBtn.setVariant(Variant.Brand);
        entityBtn.setAsLink("https://entityassist.com", "entityassist", null);
        entityBtn.addClass("product");
        entityBtn.addClass("product-entity-assist");
        entityBtn.setID("product-entity-assist");
        entityBtn.setSize(com.jwebmp.webawesome.components.Size.Small);
        var entityLogo = new WaIcon<>();
        entityLogo.addClass("fak");
        entityLogo.addClass("fa-entityassist-logo");
        entityLogo.addClass("logo-icon");
        entityLogo.addClass("logo-entity-assist");
        entityLogo.addAttribute("label", "Entity Assist");
        entityBtn.add(entityLogo);
        cluster.add(entityBtn);
        WaTooltip<?> entityTip = new WaTooltip<>();
        entityTip.setForId("product-entity-assist");
        entityTip.setText("Entity Assist");
        cluster.add(entityTip);

        // ── Version badge ──
        com.jwebmp.webawesome.components.badge.WaBadge<?> versionBadge = new com.jwebmp.webawesome.components.badge.WaBadge<>();
        versionBadge.addClass("version-badge");
        versionBadge.setVariant(Variant.Brand);
        versionBadge.setPill(true);
        versionBadge.setFontSize(com.jwebmp.webawesome.tokens.WaTypographyToken.FontSize2XS);
        versionBadge.setOnColour(Variant.Brand);
        versionBadge.setFillColour(Variant.Brand);
        versionBadge.addStyle("border", "2px solid var(--wa-color-brand-light)");
        versionBadge.addStyle("box-shadow", "0 0 6px color-mix(in srgb, var(--wa-color-brand-normal) 40%, transparent)");
        versionBadge.addStyle("cursor", "pointer");
        versionBadge.setText("3.0.0");
        versionBadge.setID("version-badge");
        cluster.add(versionBadge);

        primary.add(cluster);

        // Secondary links (GitHub, Theme toggle)
        DivSimple<?> secondary = new DivSimple<>();
        secondary.addClass("nav-products-secondary");
        secondary.addClass("wa-cluster");
        secondary.addClass("wa-gap-2xs");

        WaButton<?> githubBtn = new WaButton<>();
        githubBtn.setAppearance(Appearance.Plain);
        githubBtn.setVariant(Variant.Brand);
        githubBtn.setAsLink("https://github.com/Activity-Master/", "activitymaster-github", null);
        githubBtn.addClass("pseudo-product");
        githubBtn.addClass("product-github");
        githubBtn.setID("product-github");
        githubBtn.add(new WaIcon<>("github").addAttribute("family", "brands")
                                            .addAttribute("label", "GitHub"));
        secondary.add(githubBtn);
        WaTooltip<?> githubTip = new WaTooltip<>();
        githubTip.setForId("product-github");
        githubTip.setText("GitHub");
        secondary.add(githubTip);

        WaButton<?> starBtn = new WaButton<>();
        starBtn.setAppearance(Appearance.Plain);
        starBtn.setVariant(Variant.Brand);
        starBtn.setAsLink("https://github.com/Activity-Master/Website", "activitymaster-github", null);
        starBtn.addClass("pseudo-product");
        starBtn.addClass("product-star");
        starBtn.setID("product-star");
        starBtn.add(new WaIcon<>("star").addAttribute("family", "sharp-duotone").addAttribute("label", "Star this Repository"));
        secondary.add(starBtn);
        WaTooltip<?> starTip = new WaTooltip<>();
        starTip.setForId("product-star");
        starTip.setText("Star this Repository");
        secondary.add(starTip);

        WaButton<?> patreonBtn = new WaButton<>();
        patreonBtn.setAppearance(Appearance.Plain);
        patreonBtn.setVariant(Variant.Brand);
        patreonBtn.setAsLink("https://www.patreon.com/GedMarc", "activitymaster-patreon", null);
        patreonBtn.addClass("pseudo-product");
        patreonBtn.addClass("product-patreon");
        patreonBtn.setID("product-patreon");
        patreonBtn.add(new WaIcon<>("patreon").addAttribute("family", "brands")
                                              .addAttribute("label", "Patreon"));
        secondary.add(patreonBtn);
        WaTooltip<?> patreonTip = new WaTooltip<>();
        patreonTip.setForId("product-patreon");
        patreonTip.setText("Support me on Patreon");
        secondary.add(patreonTip);

        // Theme toggle (dark ↔ light)
        WaButton<?> themeBtn = new WaButton<>();
        themeBtn.setAppearance(Appearance.Plain);
        themeBtn.setVariant(Variant.Brand);
        themeBtn.addAttribute("(click)", "toggleDarkMode()");
        themeBtn.addClass("pseudo-product");
        themeBtn.addClass("product-theme");
        themeBtn.setID("product-theme");
        var themeIcon = new WaIcon<>();
        themeIcon.addAttribute("[name]", "darkMode() ? 'sun-bright' : 'moon'");
        themeIcon.addAttribute("family", "sharp-duotone");
        themeIcon.addAttribute("label", "Toggle Theme");
        themeBtn.add(themeIcon);
        secondary.add(themeBtn);
        WaTooltip<?> themeTip = new WaTooltip<>();
        themeTip.setForId("product-theme");
        themeTip.setText("Toggle Theme");
        secondary.add(themeTip);

        primary.add(secondary);
        nav.add(primary);
        navWrapper.add(nav);
        banner.add(navWrapper);

        // ── Menu: WaTree navigation with sub-items ──
        var menu = page.getMenu();
        WaTree<?> menuTree = new WaTree<>();
        menuTree.setIndentSize("2px");
        menuTree.setIndentGuideColor("var(--wa-color-neutral-300)");

        menuTree.add(createRouterTreeItem("/home", "Home", "house"));

        menu.add(menuTree);

        // ── Built-on attribution links below menu tree ──
        var builtOn = new WaDiv<>();
        builtOn.setPadding(WaSpaceToken.SpaceM);
        builtOn.addStyle("border-top", "1px solid var(--wa-color-neutral-200)");
        builtOn.addStyle("margin-top", "auto");
        var builtOnLabel = new DivSimple<>();
        builtOnLabel.setTag("span");
        builtOnLabel.setText("Built on");
        builtOnLabel.addClass("wa-body-2xs");
        builtOnLabel.addStyle("color", "var(--wa-color-text-quiet)");
        builtOnLabel.addStyle("display", "block");
        builtOnLabel.addStyle("margin-bottom", WaSpaceToken.SpaceXS.var());
        builtOn.add(builtOnLabel);
        var builtOnLinks = new DivSimple<>();
        builtOnLinks.addClass("wa-stack");
        builtOnLinks.addClass("wa-gap-2xs");
        builtOnLinks.addClass("built-on-links");

        Link<?> angularAwesomeLink = new Link<>();
        angularAwesomeLink.setTag("a");
        angularAwesomeLink.addAttribute("href", "https://www.npmjs.com/package/angular-awesome");
        angularAwesomeLink.addAttribute("target", "angular-awesome");
        angularAwesomeLink.add(new WaIcon<>("npm").addAttribute("family", "brands"));
        angularAwesomeLink.setText("Angular Awesome");
        angularAwesomeLink.setRenderTextBeforeChildren(false);
        angularAwesomeLink.addClass("wa-body-xs");
        angularAwesomeLink.addStyle("color", "var(--wa-color-brand-normal)");
        builtOnLinks.add(angularAwesomeLink);

        Link<?> webAwesomeLink = new Link<>();
        webAwesomeLink.setTag("a");
        webAwesomeLink.addAttribute("href", "https://www.webawesome.com");
        webAwesomeLink.addAttribute("target", "web-awesome");
        webAwesomeLink.add(new WaIcon<>("web-awesome").addAttribute("family", "sharp-duotone"));
        webAwesomeLink.setText("Web Awesome");
        webAwesomeLink.setRenderTextBeforeChildren(false);
        webAwesomeLink.addClass("wa-body-xs");
        webAwesomeLink.addStyle("color", "var(--wa-color-brand-normal)");
        builtOnLinks.add(webAwesomeLink);

        Link<?> jwebmpMenuLink = new Link<>();
        jwebmpMenuLink.setTag("a");
        jwebmpMenuLink.addAttribute("href", "https://jwebmp.com");
        jwebmpMenuLink.addAttribute("target", "jwebmp");
        var jwebmpBuiltIcon = new DivSimple<>();
        jwebmpBuiltIcon.setTag("i");
        jwebmpBuiltIcon.addClass("fak");
        jwebmpBuiltIcon.addClass("fa-jwebmp-logo-green");
        jwebmpBuiltIcon.addClass("built-on-logo");
        jwebmpMenuLink.add(jwebmpBuiltIcon);
        jwebmpMenuLink.setText("JWebMP");
        jwebmpMenuLink.setRenderTextBeforeChildren(false);
        jwebmpMenuLink.addClass("wa-body-xs");
        jwebmpMenuLink.addStyle("color", "var(--wa-color-brand-normal)");
        builtOnLinks.add(jwebmpMenuLink);

        builtOn.add(builtOnLinks);
        menu.add(builtOn);

        // ── Navigation Toggle (burger button, slot="navigation-toggle") ──
        var navToggle = page.getNavigationToggle();
        WaButton<?> burgerBtn = new WaButton<>();
        burgerBtn.setAppearance(Appearance.Plain);
        burgerBtn.setVariant(Variant.Neutral);
        burgerBtn.addAttribute("aria-label", "Toggle navigation menu");
        burgerBtn.add(new WaIcon<>("bars").addAttribute("family", "sharp-duotone"));
        navToggle.add(burgerBtn);

        // ── Navigation Toggle Icon (slot="navigation-toggle-icon") ──
        var navToggleIcon = page.getNavigationToggleIcon();
        navToggleIcon.add(new WaIcon<>("bars").addAttribute("family", "sharp-duotone"));

        // ── Navigation Header (branding inside the drawer, slot="navigation-header") ──
        var navHeader = page.getNavigationHeader();
        Link<?> drawerLogo = new Link<>();
        drawerLogo.setTag("a");
        drawerLogo.addAttribute("routerLink", "/home");
        drawerLogo.addAttribute("aria-label", "Activity Master Home");
        drawerLogo.addClass("appearance-plain");
        var drawerLogoSpan = new DivSimple<>();
        drawerLogoSpan.setTag("i");
        drawerLogoSpan.addClass("fak");
        drawerLogoSpan.addClass("fa-activitymaster-logo");
        drawerLogoSpan.addClass("logo-icon");
        drawerLogoSpan.addClass("logo-activity-master");
        drawerLogo.add(drawerLogoSpan);
        drawerLogo.setText("Activity Master");
        drawerLogo.setRenderTextBeforeChildren(false);
        navHeader.add(drawerLogo);

        // ── Burger Menu Navigation (drawer contents, slot="navigation") ──
        var burgerMenuNavigation = page.getNavigation();
        WaTree<?> navTree = new WaTree<>();
        navTree.setIndentSize("2px");
        navTree.setIndentGuideColor("var(--wa-color-neutral-300)");

        navTree.add(createRouterTreeItem("/home", "Home", "house"));
        burgerMenuNavigation.add(navTree);

        // ── Built-on attribution links below drawer tree ──
        var drawerBuiltOn = new WaDiv<>();
        drawerBuiltOn.setPadding(WaSpaceToken.SpaceM);
        drawerBuiltOn.addStyle("border-top", "1px solid var(--wa-color-neutral-200)");
        drawerBuiltOn.addStyle("margin-top", "auto");
        var drawerBuiltOnLabel = new DivSimple<>();
        drawerBuiltOnLabel.setTag("span");
        drawerBuiltOnLabel.setText("Built on");
        drawerBuiltOnLabel.addClass("wa-body-2xs");
        drawerBuiltOnLabel.addStyle("color", "var(--wa-color-text-quiet)");
        drawerBuiltOnLabel.addStyle("display", "block");
        drawerBuiltOnLabel.addStyle("margin-bottom", WaSpaceToken.SpaceXS.var());
        drawerBuiltOn.add(drawerBuiltOnLabel);
        var drawerBuiltOnLinks = new DivSimple<>();
        drawerBuiltOnLinks.addClass("wa-stack");
        drawerBuiltOnLinks.addClass("wa-gap-2xs");
        drawerBuiltOnLinks.addClass("built-on-links");

        Link<?> drawerAngularLink = new Link<>();
        drawerAngularLink.setTag("a");
        drawerAngularLink.addAttribute("href", "https://www.npmjs.com/package/angular-awesome");
        drawerAngularLink.addAttribute("target", "angular-awesome");
        drawerAngularLink.add(new WaIcon<>("npm").addAttribute("family", "brands"));
        drawerAngularLink.setText("Angular Awesome");
        drawerAngularLink.setRenderTextBeforeChildren(false);
        drawerAngularLink.addClass("wa-body-xs");
        drawerAngularLink.addStyle("color", "var(--wa-color-brand-normal)");
        drawerBuiltOnLinks.add(drawerAngularLink);

        Link<?> drawerWebAwesomeLink = new Link<>();
        drawerWebAwesomeLink.setTag("a");
        drawerWebAwesomeLink.addAttribute("href", "https://www.webawesome.com");
        drawerWebAwesomeLink.addAttribute("target", "web-awesome");
        drawerWebAwesomeLink.add(new WaIcon<>("web-awesome").addAttribute("family", "sharp-duotone"));
        drawerWebAwesomeLink.setText("Web Awesome");
        drawerWebAwesomeLink.setRenderTextBeforeChildren(false);
        drawerWebAwesomeLink.addClass("wa-body-xs");
        drawerWebAwesomeLink.addStyle("color", "var(--wa-color-brand-normal)");
        drawerBuiltOnLinks.add(drawerWebAwesomeLink);

        Link<?> drawerJwebmpLink = new Link<>();
        drawerJwebmpLink.setTag("a");
        drawerJwebmpLink.addAttribute("href", "https://jwebmp.com");
        drawerJwebmpLink.addAttribute("target", "jwebmp");
        var drawerJwebmpBuiltIcon = new DivSimple<>();
        drawerJwebmpBuiltIcon.setTag("i");
        drawerJwebmpBuiltIcon.addClass("fak");
        drawerJwebmpBuiltIcon.addClass("fa-jwebmp-logo-green");
        drawerJwebmpBuiltIcon.addClass("built-on-logo");
        drawerJwebmpLink.add(drawerJwebmpBuiltIcon);
        drawerJwebmpLink.setText("JWebMP");
        drawerJwebmpLink.setRenderTextBeforeChildren(false);
        drawerJwebmpLink.addClass("wa-body-xs");
        drawerJwebmpLink.addStyle("color", "var(--wa-color-brand-normal)");
        drawerBuiltOnLinks.add(drawerJwebmpLink);

        drawerBuiltOn.add(drawerBuiltOnLinks);
        burgerMenuNavigation.add(drawerBuiltOn);

        page.getMain().add(new RouterOutlet<>());

        add(page);
    }

    private static WaTreeItem<?> createRouterTreeItem(String path, String text, String icon)
    {
        if (!path.startsWith("/"))
        {
            path = "/" + path;
        }

        WaTreeItem<?> item = new WaTreeItem<>();
        Link<?> link = new Link<>("#");
        item.add(link);
        link.addAttribute("routerLink", path);
        link.setRenderTextBeforeChildren(false);
        if (icon != null)
        {
            WaIcon<?> waIcon = new WaIcon<>(icon).addClass("wa-gap-1").addStyle("color", "var(--wa-color-brand-on-normal)");
            waIcon.setFamily("sharp-duotone");
            link.add(waIcon);
        }
        link.setText("&nbsp;"+ text);
        return item;
    }

    private static WaTreeItem<?> createExternalTreeItem(String url, String text, String icon)
    {
        WaTreeItem<?> item = new WaTreeItem<>();
        Link<?> link = new Link<>("#");
        item.add(link);
        link.addAttribute("href", url);
        link.addAttribute("target", "activitymaster-external");
        link.setRenderTextBeforeChildren(false);
        if (icon != null)
        {
            WaIcon<?> waIcon = new WaIcon<>(icon).addClass("wa-gap-1").addStyle("color", "var(--wa-color-brand-on-normal)");
            waIcon.setFamily("sharp-duotone");
            link.add(waIcon);
        }
        link.setText("&nbsp;" + text);
        return item;
    }

    @Override
    public List<String> host() {
        return List.of("""
                {
                    '[style.width]': '"100%"',
                    '[style.height]': '"100%"',
                 }
                """);
    }

    @Override
    public List<String> fields() {
        var f = new ArrayList<>(INgComponent.super.fields());
        f.add("private router: Router = inject(Router);");
        f.add("private document = inject(DOCUMENT);");
        f.add("darkMode = signal(true);");
        return f;
    }

    @Override
    public List<String> methods() {
        var m = new ArrayList<>(INgComponent.super.methods());
        m.add("""
                toggleDarkMode() {
                    const isDark = !this.darkMode();
                    this.darkMode.set(isDark);
                    this.document.body.classList.toggle('wa-dark', isDark);
                    localStorage.setItem('activitymaster-theme', isDark ? 'dark' : 'light');
                }""");
        return m;
    }

    @Override
    public List<String> onInit() {
        var init = new ArrayList<>(INgComponent.super.onInit());
        init.add("registerLocaleData(localeEnZa, 'en-ZA')");
        init.add("""
                const savedTheme = localStorage.getItem('activitymaster-theme');
                const prefersDark = savedTheme ? savedTheme === 'dark' : true;
                this.darkMode.set(prefersDark);
                this.document.body.classList.toggle('wa-dark', prefersDark);""");
        return init;
    }

}

