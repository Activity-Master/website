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

@NgComponent("am-resource-item")
@NgRoutable(path = "resource-item")
public class ResourceItemPage extends WebsitePage<ResourceItemPage> implements INgComponent<ResourceItemPage>
{
    public ResourceItemPage()
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
        layout.add(buildEntityCatalogue());
        layout.add(buildNatures());
        layout.add(buildCrossDomain());
        layout.add(buildEvidencePatterns());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Resource Item"));
        hero.add(bodyTextHtml(brandCode("Assets") + ", " + brandCode("documents") + ", " +
                brandCode("data objects") + ", and " + brandCode("configurable resources") +
                " &#8212; the building blocks that support operations. Attachments, templates, " +
                "certificates, collateral, equipment, and financial instruments.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Assets", Variant.Success));
        tags.add(buildTag("Documents", Variant.Neutral));
        tags.add(buildTag("Evidence", Variant.Warning));
        tags.add(buildTag("Cross-Domain", Variant.Neutral));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Resource Items are the " + brandCode("tangible and intangible objects") +
                " that the business owns, manages, holds, pledges, sells, leases, lends, or tracks. " +
                "They also serve as the " + brandCode("evidence layer") + " &#8212; documents, receipts, " +
                "certificates, and logs that support business activities.", "m"));

        content.add(bodyTextHtml("Every other domain can link to Resource Items: " +
                brandCode("InvolvedPartyXResourceItem") + " for identity documents and certificates, " +
                brandCode("ArrangementXResourceItem") + " for collateral and agreement documents, " +
                brandCode("EventXResourceItem") + " for receipts and evidence, " +
                brandCode("ProductXResourceItem") + " for prospectuses and descriptions, and " +
                brandCode("RulesXResourceItem") + " for policy documentation.", "m"));

        return buildSection("OVERVIEW", "What Are Resource Items?",
                "The assets, documents, and evidence that support every domain in ActivityMaster.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((ResourceItem))
                    Tangible
                      Equipment
                      Real Property
                      Personal Property
                      Vehicles
                      Inventory
                    Intangible
                      Documents
                      Certificates
                      Financial Instruments
                      Data Objects
                      Templates
                      Configurations
                    Evidence
                      Receipts
                      Logs
                      Identity Documents
                      Professional Certificates
                      Legal Documents
                    Relationships
                      InvolvedPartyXResourceItem
                      ArrangementXResourceItem
                      EventXResourceItem
                      ProductXResourceItem
                      RulesXResourceItem
                      ClassificationXResourceItem
                """));

        return buildSection("MENTAL MODEL", "Resource Item Domain Map",
                "Resource Items connect to every other domain as assets, evidence, or documentation.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("ResourceItem", "Canonical resource/asset/document record.", "ResourceItemID (UUID)"));
        grid.add(featureCard("ResourceItemType", "Classifies the inherent kind of resource.", "Type taxonomy"));
        grid.add(featureCard("ResourceItemXClassification", "Descriptors: nature, status, ownership, data classification.", "Flexible vocabulary"));
        grid.add(featureCard("ResourceItemXResourceItem", "Resource-to-resource hierarchy and composition.", "Contains, Describes, Supports"));
        grid.add(featureCard("InvolvedPartyXResourceItem", "Links parties to owned/managed/held resources.", "Ownership, custody, evidence"));
        grid.add(featureCard("ArrangementXResourceItem", "Links arrangements to collateral, documents, assets.", "Pledged, documented, governed"));
        grid.add(featureCard("EventXResourceItem", "Links events to receipts, evidence, affected assets.", "Is Documented By"));
        grid.add(featureCard("ProductXResourceItem", "Links products to prospectuses and descriptions.", "Comprises, Is Described By"));
        grid.add(featureCard("RulesXResourceItem", "Links rules to supporting documentation.", "Policy documents, specifications"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Resource Item domain.",
                false, content);
    }

    private WaStack<?> buildNatures()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Collateral", "Resources pledged as security for obligations.", "Property, securities, equipment"));
        grid.add(featureCard("Documentation Item", "Business documents, records, and files.", "Contracts, reports, correspondence"));
        grid.add(featureCard("Equipment", "Operational machinery and tools.", "ATMs, computers, vehicles"));
        grid.add(featureCard("Intangible Asset", "Non-physical assets with business value.", "Patents, licences, certificates"));
        grid.add(featureCard("Financial Instrument", "Tradeable securities and monetary instruments.", "Stocks, bonds, commercial paper"));
        grid.add(featureCard("Real Property", "Land, buildings, and permanent fixtures.", "Office buildings, branches, land"));

        content.add(grid);

        return buildSection("NATURES", "Resource Item Natures",
                "Resource Items span tangible assets, intangible assets, documents, and financial instruments.",
                true, content);
    }

    private WaStack<?> buildCrossDomain()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Resource Items are the universal " + brandCode("evidence and asset layer") +
                ". Every domain links to them when it needs to reference a document, attach proof, " +
                "manage collateral, or track physical/digital assets.", "m"));

        content.add(mermaidDiagramWithTitle("Cross-Domain Resource Connections", """
                graph LR
                    RI[ResourceItem] --> IPRI[InvolvedPartyXResourceItem]
                    RI --> ARI[ArrangementXResourceItem]
                    RI --> ERI[EventXResourceItem]
                    RI --> PRI[ProductXResourceItem]
                    RI --> RRI[RulesXResourceItem]
                    RI --> CRI[ClassificationXResourceItem]
                    IPRI --> IP[InvolvedParty]
                    ARI --> A[Arrangement]
                    ERI --> E[Event]
                    PRI --> P[Product]
                    RRI --> R[Rules]
                """));

        return buildSection("CROSS-DOMAIN", "Universal Asset Layer",
                "Every domain connects to Resource Items for evidence, documentation, and asset management.",
                false, content);
    }

    private WaStack<?> buildEvidencePatterns()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("When a party descriptor depends on evidence, prefer linking the evidence through " +
                brandCode("XResourceItem") + " rather than expanding the party or event model:", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Skill Certificate", "Professional certification document linked to a party.", "InvolvedPartyXResourceItem"));
        grid.add(featureCard("Death Certificate", "Lifecycle evidence linked with status change.", "InvolvedPartyXResourceItem"));
        grid.add(featureCard("Transaction Receipt", "Deposit or payment evidence.", "EventXResourceItem"));
        grid.add(featureCard("Product Prospectus", "Product description document.", "ProductXResourceItem"));
        grid.add(featureCard("Arrangement Contract", "Signed agreement document.", "ArrangementXResourceItem"));
        grid.add(featureCard("Rule Specification", "Policy or rule documentation.", "RulesXResourceItem"));

        content.add(grid);

        return buildSection("EVIDENCE", "Evidence and Documentation Patterns",
                "Attach evidence to any domain through Resource Item relationships &#8212; not by adding columns.",
                true, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Attaching a certificate to a party", """
                // Create the resource item
                ResourceItem certificate = new ResourceItem_();
                certificate.setResourceItemName("CPA Certificate - John Smith");
                
                // Classify it
                ResourceItemXClassification nature = new ResourceItemXClassification_();
                nature.setResourceItem(certificate);
                nature.setClassification(findClassification("ResourceItemNatures"));
                nature.setValue("Documentation Item");
                
                // Link it to the party
                InvolvedPartyXResourceItem link = new InvolvedPartyXResourceItem_();
                link.setInvolvedParty(johnSmith);
                link.setResourceItem(certificate);
                link.setClassification(findClassification("PartyResourceRelationships"));
                link.setValue("Is Certified By");"""));

        content.add(codeBlockWithTitle("Linking collateral to an arrangement", """
                // Link a property as collateral
                ArrangementXResourceItem collateral = new ArrangementXResourceItem_();
                collateral.setArrangement(mortgageArrangement);
                collateral.setResourceItem(propertyAsset);
                collateral.setClassification(findClassification("ArrangementResourceRelationships"));
                collateral.setValue("Is Secured By");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating and linking resource items.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Resource Items are the evidence and asset backbone of ActivityMaster. " +
                "Explore how they connect to the other domains.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Event", "/event", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Classifications", "/classifications", Variant.Brand, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}
