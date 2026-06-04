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

@NgComponent("am-classifications")
@NgRoutable(path = "classifications")
public class ClassificationsPage extends WebsitePage<ClassificationsPage> implements INgComponent<ClassificationsPage>
{
    public ClassificationsPage()
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
        layout.add(buildUsagePatterns());
        layout.add(buildCrossDomainReuse());
        layout.add(buildHierarchy());
        layout.add(buildSeedConcepts());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Classifications"));
        hero.add(bodyTextHtml("The " + brandCode("shared vocabulary") + " of ActivityMaster. A " +
                brandCode("ClassificationDataConcept") + " defines the question or bucket; a " +
                brandCode("Classification") + " provides one of the valid business answers. " +
                "A universal categorisation system that can tag any entity.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Vocabulary", Variant.Success));
        tags.add(buildTag("Hierarchy", Variant.Neutral));
        tags.add(buildTag("Cross-Domain", Variant.Warning));
        tags.add(buildTag("Reusable", Variant.Neutral));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Instead of modelling every category, status, role, reason, and descriptor as a " +
                "new physical table, ActivityMaster stores them as " + brandCode("Classifications") +
                " under a named " + brandCode("ClassificationDataConcept") + ".", "m"));

        content.add(bodyTextHtml("This is the main simplification: many old tiny lookup/type tables become " +
                brandCode("ClassificationDataConcept") + " + " + brandCode("Classification") +
                " &#8212; reusable concepts, values, hierarchy, and cross-domain links.", "m"));

        content.add(bodyTextHtml("In plain terms: " + brandCode("ClassificationDataConcept") +
                " says what kind of meaning we are talking about. " +
                brandCode("Classification") + " says the specific meaning we selected.", "m"));

        return buildSection("OVERVIEW", "The Shared Vocabulary",
                "Enough structure to reason cleanly, enough flexibility to avoid table explosion.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((Classification Domain))
                    ClassificationDataConcept
                      Defines the semantic bucket
                      ArrangementRoles
                      ArrangementLifeCycleStatuses
                      IndustryClassifications
                    Classification
                      Defines the reusable value
                      Is Managed By
                      Effective Arrangement
                      Manufacturing
                    ClassificationXClassification
                      Builds hierarchy
                      Groups values
                      Parent-child meaning
                    Cross Reference Tables
                      ClassificationID = semantic bucket
                      Value = assigned meaning
                    ClassificationHierarchyView
                      Presents hierarchy cleanly
                """));

        return buildSection("MENTAL MODEL", "Classification Domain Map",
                "Concepts define the question. Classifications provide the answer. Hierarchy groups them.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("ClassificationDataConcept", "Named container for a classification scheme. The question.", "ArrangementRoles, IndustryClassifications"));
        grid.add(featureCard("Classification", "A value inside a concept. The answer.", "Is Managed By, Manufacturing"));
        grid.add(featureCard("ClassificationXClassification", "Parent-child hierarchy between classifications.", "Groups, structures, trees"));
        grid.add(featureCard("ClassificationDataConceptXClassification", "Explicit concept-to-value link.", "Cross-reference bridge"));
        grid.add(featureCard("ClassificationXResourceItem", "Supporting documentation for a classification.", "Attached specs, policies"));
        grid.add(featureCard("ClassificationDataConceptXResourceItem", "Supporting documentation for a concept.", "Scheme documentation"));
        grid.add(featureCard("ClassificationHierarchyView", "Readable hierarchy presentation.", "Navigation and browsing"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Classification domain.",
                false, content);
    }

    private WaStack<?> buildUsagePatterns()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Classifications appear in two major ways across ActivityMaster:", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCardHtml("Direct Classification",
                "Some entities carry a " + brandCode("ClassificationID") + " directly when the classification is intrinsic.",
                "Address type, Geography type, SecurityToken type"));

        grid.add(featureCardHtml("Relationship Table Pattern",
                brandCode("ClassificationID") + " identifies the semantic bucket. " +
                        brandCode("Value") + " stores the assigned business meaning.",
                "ArrangementXClassification, ProductXClassification"));

        content.add(grid);

        content.add(codeBlock("""
                // Relationship table pattern
                ArrangementXClassification
                  ArrangementID    = the arrangement
                  ClassificationID = ArrangementLifeCycleStatuses
                  Value            = Effective Arrangement""", "text"));

        return buildSection("USAGE", "How Classifications Are Used",
                "Direct classification on entities, or through relationship tables with ClassificationID + Value.",
                true, content);
    }

    private WaStack<?> buildCrossDomainReuse()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("The same classification concept can be reused across multiple domains. " +
                "For example, " + brandCode("IndustryClassifications") + " can classify both organisations " +
                "and geographic areas without duplication.", "m"));

        content.add(mermaidDiagram("""
                erDiagram
                  CLASSIFICATION_DATA_CONCEPT ||--o{ CLASSIFICATION : "defines values for"
                  CLASSIFICATION ||--o{ CLASSIFICATION_X_CLASSIFICATION : "can parent-child"
                  INVOLVED_PARTY ||--o{ INVOLVED_PARTY_X_CLASSIFICATION : "is classified by"
                  GEOGRAPHY ||--o{ GEOGRAPHY_X_CLASSIFICATION : "is classified by"
                  ARRANGEMENT ||--o{ ARRANGEMENT_X_CLASSIFICATION : "is classified by"
                """));

        content.add(bodyTextHtml("Example: " + brandCode("IndustryClassifications") + " with value " +
                brandCode("Manufacturing") + " can classify an organisation via " +
                brandCode("InvolvedPartyXClassification") + " and simultaneously classify a geographic area via " +
                brandCode("GeographyXClassification") + ".", "m"));

        return buildSection("REUSE", "Cross-Domain Classification",
                "One classification concept, many domains &#8212; reuse without duplication.",
                false, content);
    }

    private WaStack<?> buildHierarchy()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Classification hierarchies allow ActivityMaster to group, structure, and browse " +
                "business meaning. Use " + brandCode("ClassificationXClassification") + " for parent-child links " +
                "and " + brandCode("ClassificationHierarchyView") + " for readable presentation.", "m"));

        content.add(mermaidDiagram("""
                graph TD
                  CDC[ClassificationDataConcept]
                  C1[Classification: Parent]
                  C2[Classification: Child A]
                  C3[Classification: Child B]
                  CX[ClassificationXClassification]
                  HV[ClassificationHierarchyView]
                  CDC --> C1
                  C1 --> CX
                  CX --> C2
                  CX --> C3
                  CX --> HV
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Main Hierarchy", "Groups/classes of things.", "Party → Organisation → Manufacturer"));
        grid.add(featureCard("Descriptor Hierarchy", "Attributive information.", "Party → Marital Status → Unmarried"));
        grid.add(featureCard("Relationship Hierarchy", "How things relate.", "Party → Is Customer Of → Organisation"));

        content.add(grid);

        return buildSection("HIERARCHY", "Classification Hierarchies",
                "Group, structure, and browse business meaning through parent-child relationships.",
                true, content);
    }

    private WaStack<?> buildSeedConcepts()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("ArrangementRoles", "Is Managed By, Is Advised By, Is Guaranteed By, Has Witness", "Arrangement party roles"));
        grid.add(featureCard("ArrangementLifeCycleStatuses", "Potential, Offered, Accepted, Effective, Completed", "Arrangement state"));
        grid.add(featureCard("ArrangementFinancialStatuses", "In Order, Out Of Order, In Default", "Financial health"));
        grid.add(featureCard("IndustryClassifications", "Agricultural, Manufacturing, Light Industrial, Services", "Cross-domain industry"));
        grid.add(featureCard("MarketSegments", "High Income Metropolitan, Selected Product Users, Dormant", "Business grouping"));
        grid.add(featureCard("AddressTypes", "Street Address, Postal Address, Telephone, SWIFT, Email", "Address classification"));
        grid.add(featureCard("GeographyTypes", "Country, State, City, County, Region, Jurisdiction", "Geographic area types"));
        grid.add(featureCard("ResourceItemNatures", "Collateral, Documentation, Equipment, Intangible Asset", "Resource classification"));

        content.add(grid);

        return buildSection("SEED CONCEPTS", "Suggested Classification Concepts",
                "Starter concepts for a new ActivityMaster deployment &#8212; stable, code-safe names.",
                false, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Creating a classification concept and values", """
                // Create the concept
                ClassificationDataConcept concept = new ClassificationDataConcept_();
                concept.setConceptName("ArrangementRoles");
                concept.setConceptDescription("Role assigned to an Involved Party in an Arrangement.");
                
                // Create classification values
                Classification managed = new Classification_();
                managed.setClassificationName("Is Managed By");
                managed.setClassificationDesc("Party manages the arrangement.");
                managed.setClassificationDataConcept(concept);
                managed.setClassificationSequenceNumber(1);
                
                Classification advised = new Classification_();
                advised.setClassificationName("Is Advised By");
                advised.setClassificationDesc("Party provides advice on the arrangement.");
                advised.setClassificationDataConcept(concept);
                advised.setClassificationSequenceNumber(2);"""));

        content.add(codeBlockWithTitle("Using a classification in a relationship", """
                // Classify an arrangement's lifecycle
                ArrangementXClassification lifecycle = new ArrangementXClassification_();
                lifecycle.setArrangement(loanAgreement);
                lifecycle.setClassification(findClassification("ArrangementLifeCycleStatuses"));
                lifecycle.setValue("Effective Arrangement");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating and using classifications.",
                true, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Classifications are the shared vocabulary that gives meaning to every other domain. " +
                "Explore how they integrate across the FSDM.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Brand, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                false, content);
    }
}
