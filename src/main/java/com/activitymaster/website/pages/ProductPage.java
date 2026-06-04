package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.annotations.routing.NgRoutable;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.webawesome.components.PageSize;
import com.jwebmp.webawesome.components.Variant;
import com.jwebmp.webawesome.components.WaGrid;
import com.jwebmp.webawesome.components.WaStack;
import com.jwebmp.webawesome.components.WaCluster;
import com.jwebmp.webawesome.components.button.Appearance;

@NgComponent("am-product")
@NgRoutable(path = "product")
public class ProductPage extends WebsitePage<ProductPage> implements INgComponent<ProductPage>
{
    public ProductPage()
    {
        buildPage();
    }

    private void buildPage()
    {
        var layout = new WaStack<>();
        layout.setGap(PageSize.ExtraLarge);
        getMain().add(layout);

        layout.add(buildHero());
        layout.add(buildOverview());
        layout.add(buildMentalModel());
        layout.add(buildProductTypes());
        layout.add(buildLifecycle());
        layout.add(buildEntityCatalogue());
        layout.add(buildStructureTypes());
        layout.add(buildPartyRelationships());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Product"));
        hero.add(bodyTextHtml("A " + brandCode("good") + ", " + brandCode("service") + ", " +
                brandCode("financial offering") + ", " + brandCode("access channel") + ", or other " +
                brandCode("marketable capability") + " that can be offered, sold, purchased, maintained, or tracked. " +
                "Product describes the shelf item; " + brandCode("Arrangement") + " records the agreed deal.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Catalogue", Variant.Success));
        tags.add(buildTag("Lifecycle", Variant.Neutral));
        tags.add(buildTag("Taxonomy", Variant.Warning));
        tags.add(buildTag("Market Features", Variant.Neutral));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Products describe " + brandCode("what is available to the market") +
                " or " + brandCode("what the institution manages") + " about goods and services. " +
                "They are not the customer's executed contract &#8212; that belongs to " +
                brandCode("Arrangement") + ".", "m"));

        content.add(bodyTextHtml("ActivityMaster products include: " +
                brandCode("deposit products") + ", " + brandCode("access services") + " (cards, electronic), " +
                brandCode("finance services") + " (loans, credit), " + brandCode("trading services") + ", " +
                brandCode("information services") + " (reporting, reconciliation), and " +
                brandCode("support services") + " (advisory, maintenance, processing).", "m"));

        return buildSection("OVERVIEW", "What Are Products?",
                "The catalogue of goods, services, and offerings &#8212; what the business delivers.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((Product))
                    Identity
                      ProductName
                      ProductDesc
                      ProductCode
                    Type
                      ProductType
                      Deposit Product
                      Finance Service
                      Access Service
                      Trading Service
                      Information Service
                      Support Service
                    Classifications
                      Lifecycle Status
                      Structure Type
                      Return Capability
                      Market Feature
                    Relationships
                      InvolvedPartyXProduct
                      ProductXResourceItem
                      ProductXProduct
                      RulesXProduct
                      EventXProduct
                """));

        return buildSection("MENTAL MODEL", "Product Domain Map",
                "Products connect to parties, rules, events, and resources through typed relationships.",
                true, content);
    }

    private WaStack<?> buildProductTypes()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagramWithTitle("Product Type Taxonomy", """
                graph TD
                  PT[ProductType]
                  PT --> Dep[Deposit Products]
                  Dep --> FTD[Fixed Term Deposit]
                  Dep --> DD[Demand Deposit]
                  PT --> Fin[Finance Services]
                  Fin --> LOC[Line of Credit]
                  Fin --> TL[Term Loan]
                  Fin --> Lease[Leasing]
                  PT --> Acc[Access Services]
                  Acc --> DC[Debit Card]
                  Acc --> CC[Credit Card]
                  Acc --> Elec[Electronic Access]
                  PT --> Trade[Trading Services]
                  PT --> Info[Information Services]
                  Info --> Report[Reporting]
                  Info --> Recon[Reconciliation]
                  PT --> Supp[Support Services]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Deposit Products", "Fixed term, demand, and special deposits.", "Checking, savings, time deposits"));
        grid.add(featureCard("Finance Services", "Letters of credit, lines of credit, term loans, leasing.", "Lending and credit products"));
        grid.add(featureCard("Access Services", "Debit cards, credit cards, electronic, passbook, draft.", "How customers access products"));
        grid.add(featureCard("Trading Services", "Sale, repurchase, exchange, rights, securities lending.", "Market-facing trading"));
        grid.add(featureCard("Information Services", "Account maintenance, reporting, reconciliation, agent services.", "Data and reporting products"));
        grid.add(featureCard("Support Services", "Advisory, maintenance, processing, outsourcing.", "Operational support"));

        content.add(grid);

        return buildSection("TAXONOMY", "Product Type Hierarchy",
                "Products are classified by their characteristics and market need &#8212; not by creating a table per type.",
                false, content);
    }

    private WaStack<?> buildLifecycle()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                stateDiagram-v2
                  [*] --> Proposed
                  Proposed --> InitialFeasibility
                  InitialFeasibility --> UnderDevelopment
                  UnderDevelopment --> Rejected
                  UnderDevelopment --> Approved
                  Approved --> SubmittedForSignoff
                  SubmittedForSignoff --> ReleasePending
                  ReleasePending --> Announced
                  Announced --> Rollout
                  Rollout --> Available
                  Available --> TemporarilyUnavailable
                  TemporarilyUnavailable --> Available
                  Available --> NoLongerAvailable
                  NoLongerAvailable --> Obsolete
                  Rejected --> [*]
                  Obsolete --> [*]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("14rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Proposed", "Presented for consideration.", null));
        grid.add(featureCard("Under Development", "Being assembled and specified.", null));
        grid.add(featureCard("Approved", "Accepted for market.", null));
        grid.add(featureCard("Available", "May be sold to customers.", null));
        grid.add(featureCard("Temporarily Unavailable", "Withdrawn but not obsolete.", null));
        grid.add(featureCard("Obsolete", "Fully retired, no active arrangements.", null));

        content.add(grid);

        return buildSection("LIFECYCLE", "Product Lifecycle",
                "From proposal to market availability to retirement &#8212; the full product journey.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Product", "Canonical product/service record with name, description, code.", "ProductID (UUID)"));
        grid.add(featureCard("ProductType", "Major product taxonomy classification.", "Hierarchical type system"));
        grid.add(featureCard("ProductXClassification", "Lifecycle, structure, return capability, market features.", "Flexible descriptors"));
        grid.add(featureCard("ProductXProduct", "Package products, facilities, component links.", "Composition hierarchy"));
        grid.add(featureCard("InvolvedPartyXProduct", "Parties that manage, market, sell, or supply products.", "Role-based relationships"));
        grid.add(featureCard("RulesXProduct", "Eligibility, pricing, limits, and restrictions.", "Terms and conditions"));
        grid.add(featureCard("ProductXResourceItem", "Prospectuses, media releases, documentation.", "Supporting resources"));
        grid.add(featureCard("EventXProduct", "Events that maintain, advertise, or affect products.", "Activity linkage"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Product domain.",
                false, content);
    }

    private WaStack<?> buildStructureTypes()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCardHtml("Single Product",
                "Can be sold independently. May be related to product facilities.",
                "Travellers cheque, personal loan, interest-bearing deposit"));

        grid.add(featureCardHtml("Package Product",
                "Made up of at least two single products and any number of facilities.",
                "Multi-option facility: credit line + letter of credit + overdraft"));

        grid.add(featureCardHtml("Product Facility",
                "Has a price and conditions, but must be related to a single or package product to be sold.",
                "Statement facility sold only with an account-based product"));

        content.add(grid);

        content.add(bodyTextHtml("Product composition uses " + brandCode("ProductXProduct") +
                " to link parent/package products to their components and facilities.", "m"));

        return buildSection("STRUCTURE", "Product Structure Types",
                "Products can stand alone, form packages, or act as facilities attached to other products.",
                true, content);
    }

    private WaStack<?> buildPartyRelationships()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Product-party relationships identify who participates in the product lifecycle. " +
                "Use " + brandCode("InvolvedPartyXProduct") + " for this relationship direction. " +
                "Customer purchases belong to " + brandCode("Arrangement") + ", not product-party ownership.", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Is Marketed By", "A party promotes the product.", null));
        grid.add(featureCard("Is Owned By", "A party legally owns or is responsible.", null));
        grid.add(featureCard("Is Managed By", "A party operates or manages the product.", null));
        grid.add(featureCard("Is Sold By", "A party performs the actual sale.", null));
        grid.add(featureCard("Is Supplied By", "A party acts as vendor or supplier.", null));
        grid.add(featureCard("Is Designed By", "A party develops specifications.", null));

        content.add(grid);

        return buildSection("PARTY ROLES", "Product and Involved Party",
                "Who markets, owns, manages, sells, supplies, and designs products.",
                false, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Creating a deposit product", """
                // Create the product
                Product product = new Product_();
                product.setProductName("Personal Checking Account");
                product.setProductDesc("Demand deposit account for day-to-day transactions.");
                product.setProductCode("CHK001");
                
                // Assign type
                ProductXProductType typeLink = new ProductXProductType_();
                typeLink.setProduct(product);
                typeLink.setProductType(findProductType("Demand Deposit"));
                
                // Set lifecycle to Available
                ProductXClassification lifecycle = new ProductXClassification_();
                lifecycle.setProduct(product);
                lifecycle.setClassification(findClassification("ProductLifeCycleStatuses"));
                lifecycle.setValue("Available Product");
                
                // Add an eligibility rule
                RulesXProduct rule = new RulesXProduct_();
                rule.setRules(findRule("Age >= 18"));
                rule.setProduct(product);
                rule.setValue("Eligibility Requirement");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating and configuring products.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Products are sold through Arrangements, governed by Rules, and linked to Parties. " +
                "Explore the related concepts.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Brand, Appearance.Outlined));
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Resource Item", "/resource-item", Variant.Neutral, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}
