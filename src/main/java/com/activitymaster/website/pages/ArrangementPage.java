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

@NgComponent("am-arrangement")
@NgRoutable(path = "arrangement")
public class ArrangementPage extends WebsitePage<ArrangementPage> implements INgComponent<ArrangementPage>
{
    public ArrangementPage()
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
        layout.add(buildLifecycle());
        layout.add(buildArrangementTypes());
        layout.add(buildEntityCatalogue());
        layout.add(buildClassificationPattern());
        layout.add(buildHierarchy());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Arrangement"));
        hero.add(bodyTextHtml("The formal memory of an " + brandCode("agreement") +
                " &#8212; proposed, offered, accepted, active, suspended, completed, cancelled, or terminated &#8212; " +
                "between " + brandCode("Involved Parties") + ", with the rules, obligations, products, classifications, " +
                "and statuses that make the agreement meaningful.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Agreements", Variant.Success));
        tags.add(buildTag("Lifecycle", Variant.Neutral));
        tags.add(buildTag("Hierarchy", Variant.Warning));
        tags.add(buildTag("Security", Variant.Neutral));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("An Arrangement sits at the intersection between " + brandCode("Involved Party") +
                ", " + brandCode("Product") + ", " + brandCode("Resource Item") + ", " + brandCode("Rules") +
                ", " + brandCode("Classification") + ", and " + brandCode("Event") + ". It is where the model says: " +
                "&#8220;Something has been agreed, or may be agreed, and we need to know who, what, why, under what rules, " +
                "and in what state.&#8221;", "m"));

        content.add(bodyTextHtml("Arrangements can represent: " +
                brandCode("loan agreements") + ", " + brandCode("employment contracts") + ", " +
                brandCode("service agreements") + ", " + brandCode("guarantees") + ", " +
                brandCode("leases") + ", " + brandCode("licensing agreements") + ", " +
                brandCode("collateral arrangements") + ", and " + brandCode("confidentiality agreements") + ".", "m"));

        return buildSection("OVERVIEW", "What Are Arrangements?",
                "The agreement hub &#8212; connecting parties, products, rules, and classifications into one canonical view.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((Arrangement))
                    Identity
                      ArrangementID
                      SCD EffectiveFrom / EffectiveTo
                    Classification
                      Purpose
                      Reason
                      Customization
                      Lifecycle Status
                      Financial Status
                    Type
                      ArrangementType
                      ArrangementXArrangementType
                    Rules
                      ArrangementXRules
                      ArrangementXRulesType
                    Relationships
                      ArrangementXInvolvedParty
                      ArrangementXProduct
                      ArrangementXResourceItem
                    Hierarchy
                      ArrangementXArrangement
                      Parent / Child spawning
                    Security
                      ArrangementSecurityToken
                """));

        return buildSection("MENTAL MODEL", "Arrangement Domain Map",
                "An Arrangement is the hub around which the agreement is described.",
                true, content);
    }

    private WaStack<?> buildLifecycle()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Arrangement lifecycle is tracked through " + brandCode("Classifications") +
                " attached via " + brandCode("ArrangementXClassification") + ". The SCD pattern preserves " +
                "historical lifecycle movement.", "m"));

        content.add(mermaidDiagram("""
                stateDiagram-v2
                  [*] --> Potential
                  Potential --> Requested
                  Requested --> Proposed
                  Proposed --> Offered
                  Offered --> Accepted
                  Accepted --> Effective
                  Effective --> Suspended
                  Suspended --> Effective
                  Effective --> Completed
                  Offered --> Cancelled
                  Accepted --> Cancelled
                  Effective --> Terminated
                  Cancelled --> [*]
                  Completed --> [*]
                  Terminated --> [*]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("14rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Potential", "Not yet in existence, but likely enough to track.", null));
        grid.add(featureCard("Requested", "Solicited by an Involved Party.", null));
        grid.add(featureCard("Proposed", "Conditions discussed, no binding offer yet.", null));
        grid.add(featureCard("Offered", "Binding offer submitted.", null));
        grid.add(featureCard("Accepted", "Agreement officially accepted.", null));
        grid.add(featureCard("Effective", "Currently active under its terms.", null));
        grid.add(featureCard("Suspended", "Temporarily on hold.", null));
        grid.add(featureCard("Completed", "Obligations fully discharged.", null));
        grid.add(featureCard("Cancelled", "Never became effective.", null));
        grid.add(featureCard("Terminated", "Ended prematurely.", null));

        content.add(grid);

        return buildSection("LIFECYCLE", "The Arrangement Journey",
                "From potential to completion &#8212; every state change is tracked with full history.",
                false, content);
    }

    private WaStack<?> buildArrangementTypes()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Employment Arrangement", "Defines employment between a party and an organisation.", null));
        grid.add(featureCard("Service Arrangement", "Services provided by one party to another.", null));
        grid.add(featureCard("Authority Arrangement", "Power to act for another (e.g. Power of Attorney).", null));
        grid.add(featureCard("Guarantee Arrangement", "Financial responsibility accepted by a third party.", null));
        grid.add(featureCard("Leasing Arrangement", "Use of equipment or property for a specified period.", null));
        grid.add(featureCard("Collateral Arrangement", "Resource Item pledged as security.", null));
        grid.add(featureCard("Licensing Agreement", "Rights or privileges to use resources.", null));
        grid.add(featureCard("Confidentiality", "Non-disclosure or restricted disclosure obligations.", null));
        grid.add(featureCard("Membership", "Membership under organizational rules.", null));
        grid.add(featureCard("Cooperation", "Cooperation for a project or manner of work.", null));

        content.add(grid);

        return buildSection("TYPES", "Arrangement Types",
                "Arrangements come in many forms &#8212; from employment to collateral to confidentiality.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Arrangement", "Core agreement entity. All relationships radiate from here.", "ArrangementID (UUID)"));
        grid.add(featureCard("ArrangementType", "Named and described agreement type.", "ArrangementTypeName, Description"));
        grid.add(featureCard("ArrangementXClassification", "Classification bridge for purpose, reason, lifecycle, financial status.", "Universal vocabulary"));
        grid.add(featureCard("ArrangementXInvolvedParty", "Party participation with role classification.", "Same party, multiple roles"));
        grid.add(featureCard("ArrangementXProduct", "Product relationships.", "Links to offered products"));
        grid.add(featureCard("ArrangementXResourceItem", "Resource item relationships (sale, lease, collateral).", "Asset linkage"));
        grid.add(featureCard("ArrangementXRules", "Rules that govern the arrangement.", "Terms and conditions"));
        grid.add(featureCard("ArrangementXArrangement", "Parent-child hierarchy for spawned arrangements.", "Master → work orders"));
        grid.add(featureCard("ArrangementSecurityToken", "Row-level access control.", "Per-entity security"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Arrangement domain.",
                false, content);
    }

    private WaStack<?> buildClassificationPattern()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("The " + brandCode("ArrangementXClassification") +
                " table carries what the original FSDM modelled as separate Purpose, Reason, Customization, " +
                "Lifecycle Status, and Financial Status entities. The " + brandCode("Classification value") +
                " determines the meaning.", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Purpose", "Why this arrangement exists.", "ArrangementPurposes concept"));
        grid.add(featureCard("Reason", "What motivated entering it.", "ArrangementReasons concept"));
        grid.add(featureCard("Customization", "Standard or tailored.", "Standard / Tailored"));
        grid.add(featureCard("Lifecycle Status", "Where it is in its journey.", "Potential → Completed"));
        grid.add(featureCard("Financial Status", "Is it financially in order?", "In Order / In Default"));

        content.add(grid);

        return buildSection("CLASSIFICATIONS", "Classification Semantics",
                "One table carries purpose, reason, lifecycle, and financial status &#8212; all through Classification.",
                true, content);
    }

    private WaStack<?> buildHierarchy()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("The " + brandCode("ArrangementXArrangement") +
                " table defines parent-child relationships. This models scenarios where one agreement " +
                "spawns sub-agreements: master agreements spawning work orders, framework loans spawning drawdowns, " +
                "franchises spawning location-specific arrangements.", "m"));

        content.add(mermaidDiagram("""
                flowchart TD
                    Parent["Parent Arrangement"]
                    Child1["Child Arrangement 1"]
                    Child2["Child Arrangement 2"]
                    Child3["Child Arrangement 3"]
                    Parent -->|ArrangementXArrangement| Child1
                    Parent -->|ArrangementXArrangement| Child2
                    Parent -->|ArrangementXArrangement| Child3
                """));

        return buildSection("HIERARCHY", "Arrangements That Spawn Arrangements",
                "Parent-child hierarchy models real-world agreement structures.",
                false, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Creating a service arrangement", """
                // Create the arrangement
                Arrangement arrangement = new Arrangement_();
                
                // Assign type
                ArrangementXArrangementType typeLink = new ArrangementXArrangementType_();
                typeLink.setArrangement(arrangement);
                typeLink.setArrangementType(findType("Service Arrangement"));
                
                // Set lifecycle to Effective
                ArrangementXClassification lifecycle = new ArrangementXClassification_();
                lifecycle.setArrangement(arrangement);
                lifecycle.setClassification(findClassification("ArrangementLifeCycleStatuses"));
                lifecycle.setValue("Effective Arrangement");
                
                // Add a party with role
                ArrangementXInvolvedParty partyLink = new ArrangementXInvolvedParty_();
                partyLink.setArrangement(arrangement);
                partyLink.setInvolvedParty(customer);
                partyLink.setClassification(findClassification("ArrangementRoles"));
                partyLink.setValue("Is Managed By");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating and managing arrangements.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Arrangements connect parties to products under rules. Explore the related concepts.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Brand, Appearance.Outlined));
        ctas.add(buildCta("Event", "/event", Variant.Neutral, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}
