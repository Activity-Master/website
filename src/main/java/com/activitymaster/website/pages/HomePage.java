package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.webawesome.components.PageSize;
import com.jwebmp.webawesome.components.Variant;
import com.jwebmp.webawesome.components.WaCluster;
import com.jwebmp.webawesome.components.WaGrid;
import com.jwebmp.webawesome.components.WaStack;
import com.jwebmp.webawesome.components.button.Appearance;
import com.jwebmp.webawesome.components.card.WaCard;
import com.jwebmp.webawesome.components.icon.WaIcon;

@NgComponent("am-home")
@NgRoutable(path = "home", isDefault = true)
public class HomePage extends WebsitePage<HomePage> implements INgComponent<HomePage>
{
    public HomePage()
    {
        removeClass("website-content");
        buildLandingPage();
    }

    private void buildLandingPage()
    {
        var layout = new WaStack<>();
        layout.setGap(PageSize.ExtraLarge);
        getMain().add(layout);

        layout.add(buildHero());
        layout.add(buildDomainOverview());
        layout.add(buildMindMap());
        layout.add(buildRelationshipSection());
        layout.add(buildCallToAction());
    }

    // ── Hero ──────────────────────────────────────────

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.setID("hero");
        hero.addClass("hero-banner");

        // Eyebrow
        var eyebrow = captionText("FUNCTIONAL SERVICE DATA MODEL");
        eyebrow.addClass("hero-eyebrow");
        hero.add(eyebrow);

        // Main heading
        var heading = headingText("h1", "xl", "Activity Master — Enterprise Resource Management, Modelled Right.");
        heading.addClass("hero-heading");
        hero.add(heading);

        // Subtitle
        var subtitle = bodyTextHtml("An open-source implementation of the " + brandCode("Functional Service Data Model") +
                " (FSDM) for canonical enterprise resource management. Model " + brandCode("people") + ", " +
                brandCode("products") + ", " + brandCode("events") + ", " + brandCode("agreements") + ", " +
                brandCode("resources") + ", " + brandCode("rules") + ", and " + brandCode("classifications") +
                " — with reactive persistence, row-level security, and temporal tracking built in.", "l");
        subtitle.setWaColorText("quiet");
        subtitle.addClass("hero-subtitle");
        hero.add(subtitle);

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.addClass("hero-tags");
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("7 Core Domains", Variant.Success));
        tags.add(buildTag("Reactive", Variant.Neutral));
        tags.add(buildTag("Row-Level Security", Variant.Warning));
        tags.add(buildTag("Temporal SCD", Variant.Neutral));
        tags.add(buildTag("GuicedEE", Variant.Brand));
        hero.add(tags);

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.addClass("hero-ctas");
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Event", "/event", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Resource Item", "/resource-item", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Classifications", "/classifications", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Neutral, Appearance.Outlined));
        hero.add(ctas);

        return hero;
    }

    // ── Domain overview ────────────────────────────────

    private WaStack<?> buildDomainOverview()
    {
        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(domainCard("Involved Party",
                "People, organisations, and groups that participate in business activities. " +
                        "Customers, employees, vendors, regulatory bodies — any entity that can act or be acted upon.",
                "user-group", "/involved-party"));

        grid.add(domainCard("Arrangement",
                "Contracts, agreements, and formal relationships between parties. " +
                        "Service agreements, subscriptions, policies, memberships — anything with terms and conditions.",
                "file-contract", "/arrangement"));

        grid.add(domainCard("Event",
                "Activities and occurrences that happen at a point in time or over a duration. " +
                        "Transactions, communications, appointments, incidents — the temporal heartbeat of business.",
                "calendar-star", "/event"));

        grid.add(domainCard("Product",
                "Goods and services offered, consumed, or managed. " +
                        "Financial products, insurance plans, subscription tiers, physical inventory — what the business delivers.",
                "box-open", "/product"));

        grid.add(domainCard("Resource Item",
                "Assets, documents, data objects, and configurable resources. " +
                        "Attachments, templates, configuration blobs, digital certificates — the building blocks that support operations.",
                "toolbox", "/resource-item"));

        grid.add(domainCard("Classifications",
                "A universal categorisation system that can tag any entity. " +
                        "Types, statuses, categories, hierarchies — the taxonomy layer that gives meaning to raw data.",
                "tags", "/classifications"));

        grid.add(domainCard("Rules",
                "Business rules and decision logic that govern behaviour across domains. " +
                        "Validation rules, eligibility criteria, pricing logic, workflow conditions — the brain of the system.",
                "scale-balanced", "/rules"));

        return buildSection("The 7 Domains", "Everything in business maps to seven canonical concepts",
                "The FSDM provides a universal language for enterprise data — no matter the industry, geography, or system.",
                true, grid);
    }

    // ── Mind Map ──────────────────────────────────────

    private WaStack<?> buildMindMap()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        var intro = bodyTextHtml("Every domain connects to every other domain through " + brandCode("cross-reference tables") +
                ". This creates a rich, queryable graph of business relationships — without the complexity of a graph database.", "m");
        intro.setWaColorText("quiet");
        content.add(intro);

        // Mermaid mind map showing the 7 domains and their connections
        content.add(mermaidDiagram("""
                mindmap
                  root((Activity Master))
                    Involved Party
                      Customers
                      Employees
                      Organisations
                      Groups
                    Arrangement
                      Contracts
                      Agreements
                      Policies
                      Subscriptions
                    Event
                      Transactions
                      Communications
                      Appointments
                      Incidents
                    Product
                      Services
                      Goods
                      Plans
                      Tiers
                    Resource Item
                      Documents
                      Assets
                      Templates
                      Configurations
                    Classifications
                      Types
                      Statuses
                      Categories
                      Hierarchies
                    Rules
                      Validation
                      Eligibility
                      Pricing
                      Workflows
                """));

        return buildSection("Domain Mind Map", "Seven domains, infinite combinations",
                "Each domain is self-contained yet richly connected. Classifications can tag anything. " +
                        "Rules can govern anything. Events link parties to products through arrangements.",
                false, content);
    }

    // ── Relationship section ──────────────────────────

    private WaStack<?> buildRelationshipSection()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        var intro = bodyTextHtml("The power of FSDM lies in its " + brandCode("cross-reference architecture") +
                ". Every entity can relate to every other entity through typed relationship tables — each carrying " +
                "a value, temporal tracking, and row-level security.", "m");
        intro.setWaColorText("quiet");
        content.add(intro);

        // Entity relationship diagram
        content.add(mermaidDiagramWithTitle("Core Domain Relationships", """
                graph LR
                    IP[Involved Party] --- AR[Arrangement]
                    IP --- EV[Event]
                    IP --- PD[Product]
                    IP --- RI[Resource Item]
                    AR --- EV
                    AR --- PD
                    AR --- RI
                    EV --- PD
                    EV --- RI
                    PD --- RI
                    CL[Classifications] --- IP
                    CL --- AR
                    CL --- EV
                    CL --- PD
                    CL --- RI
                    CL --- RU[Rules]
                    RU --- IP
                    RU --- AR
                    RU --- PD
                    RU --- RI
                """));

        // Programming complexities
        var complexityIntro = bodyTextHtml("A fully cross-referenced, temporal, multi-tenant model introduces significant " +
                brandCode("programming complexity") + " that traditional CRUD frameworks cannot handle out of the box:", "m");
        complexityIntro.setWaColorText("quiet");
        content.add(complexityIntro);

        var complexityGrid = new WaGrid<>();
        complexityGrid.setMinColumnSize("16rem");
        complexityGrid.setGap(PageSize.Medium);

        complexityGrid.add(featureCardHtml("N&#178; Relationship Explosion",
                "With 7 domains, every cross-reference table (" + brandCode("InvolvedPartyXArrangement") + ", " +
                        brandCode("EventXProduct") + ", etc.) needs its own entity, query builder, security token table, " +
                        "and API surface. A naive implementation produces hundreds of near-identical repository classes.",
                "Solved by CRTP generics and EntityAssist."));

        complexityGrid.add(featureCardHtml("Temporal Query Complexity",
                "Every query must filter by " + brandCode("effectiveFromDate &#8804; now &#8804; effectiveToDate") +
                        " and handle SCD-2 versioning. Point-in-time joins across multiple temporal tables " +
                        "compound into combinatorial WHERE clauses that are error-prone to write by hand.",
                "Solved by query-builder DSL with built-in temporal filters."));

        complexityGrid.add(featureCardHtml("Security Token Joins",
                "Row-level security means every SELECT must join to a " + brandCode("SecurityToken") +
                        " table and verify the caller&#8217;s token grants. Forgetting a single join leaks data. " +
                        "Applying it across hundreds of entities requires systematic enforcement, not developer discipline.",
                "Solved by persistence-layer interceptors."));

        complexityGrid.add(featureCardHtml("Classification Indirection",
                "Instead of dedicated columns, most attributes live in " + brandCode("XClassification") +
                        " bridge tables. Querying &#8220;find all employed people&#8221; requires joining through " +
                        "the classification bridge, resolving the concept, and filtering the value &#8212; not a simple WHERE clause.",
                "Solved by dot-notation path filters."));

        complexityGrid.add(featureCardHtml("Multi-Tenant Context Propagation",
                "Every insert, update, and query must carry the " + brandCode("EnterpriseID") +
                        " context. In reactive, non-blocking pipelines the tenant context must survive thread hops, " +
                        "async boundaries, and event-bus messages without global mutable state.",
                "Solved by CallScope and reactive context propagation."));

        complexityGrid.add(featureCardHtml("Reactive Persistence Pipelines",
                "Blocking JDBC cannot serve thousands of concurrent requests efficiently. Reactive drivers " +
                        "(Hibernate Reactive + Vert.x SQL clients) require " + brandCode("Uni/Multi") +
                        " chains where every operation is non-blocking &#8212; making transaction boundaries, " +
                        "error handling, and sequencing significantly harder than synchronous code.",
                "Solved by Mutiny-based EntityAssist operations."));

        content.add(complexityGrid);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCardHtml("Temporal SCD Tracking",
                "Every record carries " + brandCode("effectiveFromDate") + " and " + brandCode("effectiveToDate") + " — " +
                        "enabling point-in-time queries, audit trails, and slowly changing dimension analysis.",
                "Full history, no data loss."));

        grid.add(featureCardHtml("Row-Level Security",
                "Every entity and relationship has a corresponding " + brandCode("SecurityToken") + " table " +
                        "controlling CRUD permissions per token. Fine-grained access without application-level filtering.",
                "Security at the data layer."));

        grid.add(featureCardHtml("Self-Referencing Hierarchies",
                "Involved Parties, Products, Classifications, Rules, and Geography all support " +
                        brandCode("self-referencing") + " relationships — enabling unlimited depth hierarchies.",
                "Trees and graphs built in."));

        grid.add(featureCardHtml("Multi-Tenant Isolation",
                "Every record belongs to an " + brandCode("Enterprise") + " via " + brandCode("enterpriseID") + ". " +
                        "Tenant isolation is enforced at the persistence layer — no cross-contamination possible.",
                "One schema, many tenants."));

        content.add(grid);

        return buildSection("Architecture", "Cross-references, security, and time — built into every table",
                "The warehouse schema provides temporal tracking, row-level security, and multi-tenant isolation by default.",
                true, content);
    }

    // ── Call to action ────────────────────────────────

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText(
                "Explore the domain concepts, understand the relationships, and see how Activity Master " +
                        "provides a canonical data model for any enterprise.",
                "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.addClass("hero-ctas");
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Brand, Appearance.Outlined));
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Event", "/event", Variant.Neutral, Appearance.Outlined));

        var githubCta = new com.jwebmp.webawesome.components.button.WaButton<>(escapeAngular("View on GitHub"), Variant.Neutral);
        githubCta.setAppearance(Appearance.Outlined);
        githubCta.setAsLink("https://github.com/Activity-Master/", "_blank", null);
        ctas.add(githubCta);
        content.add(ctas);

        var section = buildSection(null,
                "Start Exploring",
                "Seven domains. One canonical model. Infinite possibilities.",
                false, content);
        section.addClass("cta-section");
        return section;
    }

    // ── Card helpers ──────────────────────────────────

    private WaCard<?> domainCard(String title, String description, String icon, String route)
    {
        var card = new WaCard<>();
        card.setAppearance(Appearance.Outlined);
        card.addClass("feature-card");
        card.addClass("domain-card");

        var stack = new WaStack<>();
        stack.setGap(PageSize.Small);

        // Icon + title cluster
        var header = new WaCluster<>();
        header.setGap(PageSize.Small);

        var waIcon = new WaIcon<>(icon);
        waIcon.setFamily("sharp-duotone");
        waIcon.addStyle("font-size", "var(--wa-font-size-xl)");
        waIcon.addStyle("color", "var(--wa-color-brand-normal)");
        header.add(waIcon);

        var titleText = headingText("h3", "m", title);
        titleText.addClass("feature-card-title");
        header.add(titleText);

        stack.add(header);

        var bodyCopy = bodyText(description, "m");
        bodyCopy.addClass("feature-card-body");
        bodyCopy.setWaColorText("quiet");
        stack.add(bodyCopy);

        card.add(stack);

        // Footer with navigation button
        if (route != null)
        {
            var footer = new DivSimple<>();
            var btn = buildCta("Explore " + title + " →", route, Variant.Brand, Appearance.Plain);
            btn.setSize(com.jwebmp.webawesome.components.Size.Small);
            footer.add(btn);
            card.withFooter(footer);
        }

        return card;
    }
}