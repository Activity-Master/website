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

@NgComponent("am-involved-party")
@NgRoutable(path = "involved-party")
public class InvolvedPartyPage extends WebsitePage<InvolvedPartyPage> implements INgComponent<InvolvedPartyPage>
{
    public InvolvedPartyPage()
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
        layout.add(buildClassificationPattern());
        layout.add(buildNamesAndIdentifiers());
        layout.add(buildPartyRelationships());
        layout.add(buildOrganicClassifications());
        layout.add(buildHierarchyStrategy());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Involved Party"));
        hero.add(bodyTextHtml("Any " + brandCode("person") + ", " + brandCode("organisation") + ", " +
                brandCode("organisational unit") + ", " + brandCode("position") + ", or other participant " +
                "that ActivityMaster needs to know about. Customers, employees, vendors, regulatory bodies " +
                "&#8212; any entity that can act or be acted upon.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("People", Variant.Success));
        tags.add(buildTag("Organisations", Variant.Neutral));
        tags.add(buildTag("Relationships", Variant.Warning));
        tags.add(buildTag("Classifications", Variant.Neutral));
        tags.add(buildTag("Security", Variant.Brand));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml(brandCode("InvolvedParty") + " is the shared party anchor. " +
                brandCode("InvolvedPartyOrganic") + " is a naturally occurring person. " +
                brandCode("InvolvedPartyNonOrganic") + " is an organisation, unit, position, or other constructed party.", "m"));

        content.add(bodyTextHtml("This keeps the model simple enough to use in real systems while still carrying " +
                "the FSDM richness needed for a full canonical warehouse. The key insight: combine a small number of " +
                "stable entities with reusable " + brandCode("classification concepts") + " to avoid table explosion.", "m"));

        content.add(bodyTextHtml("Involved parties include: " +
                brandCode("customers") + " applying for products, " +
                brandCode("employees") + " participating in arrangements, " +
                brandCode("organisations") + " providing services, " +
                brandCode("regulators") + " referenced in compliance, " +
                brandCode("beneficiaries") + " named in arrangements, and " +
                brandCode("owners") + " of resource items.", "m"));

        return buildSection("OVERVIEW", "What Are Involved Parties?",
                "The human and organisational side of the model &#8212; who exists, what kind of party they are, and how they relate.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((InvolvedParty))
                    Organic
                      Person
                      Customer
                      Employee
                      Applicant
                      Beneficiary
                    NonOrganic
                      Organisation
                      Organisation Unit
                      Employment Position
                      External Party
                    Party Details
                      Names
                      Identifiers
                      Addresses
                      Classifications
                      Relationships
                    Organic Details
                      Lifecycle Status
                      Employment Status
                      Legal Competency
                      Marital Status
                      Occupations
                      Skills
                      Professional Titles
                    Relationships
                      InvolvedPartyXInvolvedParty
                      InvolvedPartyXArrangement
                      InvolvedPartyXProduct
                      InvolvedPartyXResourceItem
                      InvolvedPartyXRules
                """));

        return buildSection("MENTAL MODEL", "Party Domain Map",
                "Every party concept connects through typed relationships and classifications.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("InvolvedParty", "Shared root for all party records. The canonical party anchor.", "UUID primary key, SCD-tracked"));
        grid.add(featureCard("InvolvedPartyOrganic", "A person / individual party. Uniquely identifiable natural person.", "Customer, employee, applicant"));
        grid.add(featureCard("InvolvedPartyNonOrganic", "Organisation, unit, position, or other non-person party.", "Company, branch, department, role"));
        grid.add(featureCard("InvolvedPartyType", "Classifies the broad kind of party via InvolvedPartyXInvolvedPartyType.", "Type classification bridge"));
        grid.add(featureCard("InvolvedPartyNameType", "Records party names and the kind of name supplied.", "Legal, preferred, trading name"));
        grid.add(featureCard("InvolvedPartyIdentificationType", "Records party identifiers and the identifier type.", "National ID, employee number"));
        grid.add(featureCard("InvolvedPartyXClassification", "Captures statuses, traits, skills, titles, and other descriptors.", "Flexible classification bridge"));
        grid.add(featureCard("InvolvedPartyXInvolvedParty", "Relates one party to another party with classification and value.", "Employment, ownership, reporting"));
        grid.add(featureCard("InvolvedPartyXAddress", "Links a party to addresses and contact points.", "Mailing, residential, business"));
        grid.add(featureCard("InvolvedPartyXProduct", "Links parties to products or product categories.", "Marketed by, owned by, managed by"));
        grid.add(featureCard("InvolvedPartyXResourceItem", "Links parties to documents, assets, and evidence.", "Certificates, identity docs"));
        grid.add(featureCard("InvolvedPartyXRules", "Links parties to policies, procedures, or constraints.", "Authority, eligibility rules"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Involved Party domain.",
                false, content);
    }

    private WaStack<?> buildClassificationPattern()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Most party-specific detail uses the " + brandCode("InvolvedPartyXClassification") +
                " pattern rather than dedicated tables. This keeps the model flexible while preserving business meaning:", "m"));

        content.add(codeBlock("""
                InvolvedPartyXClassification
                  InvolvedPartyID   = the party being described
                  ClassificationID  = the semantic bucket / concept
                  Value             = the assigned business value""", "text"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Lifecycle Status", "Living Individual, Missing Individual, Deceased Individual", "InvolvedPartyLifeCycleStatuses"));
        grid.add(featureCard("Employment Status", "Employed, Not Employed, Self Employed", "InvolvedPartyEmploymentStatuses"));
        grid.add(featureCard("Legal Competency", "Minor, Adult, Mentally Incompetent", "InvolvedPartyLegalCompetencyStatuses"));
        grid.add(featureCard("Marital Status", "Married, Divorced, Widowed, Unmarried", "InvolvedPartyMaritalStatuses"));
        grid.add(featureCard("Occupations", "Doctor, Farmer, Consultant, Author", "InvolvedPartyOccupations"));
        grid.add(featureCard("Skills", "Analysis, Business, Communication, Technical", "InvolvedPartySkills"));
        grid.add(featureCard("Professional Titles", "CPA, Attorney at Law, Doctor of Medicine", "InvolvedPartyProfessionalTitles"));
        grid.add(featureCard("Characteristics", "Handwriting, Voice, Physical Feature", "InvolvedPartyCharacteristics"));

        content.add(grid);

        return buildSection("CLASSIFICATION PATTERN", "Classification and Value",
                "Flexible descriptors without table explosion &#8212; one pattern for all party attributes.",
                true, content);
    }

    private WaStack<?> buildNamesAndIdentifiers()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Names and identifiers are common enough to keep dedicated type anchors. " +
                "Use " + brandCode("InvolvedPartyXInvolvedPartyNameType") + " for names and " +
                brandCode("InvolvedPartyXInvolvedPartyIdentificationType") + " for identifiers.", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Legal Name", "Official registered name of the party.", "Name type classification"));
        grid.add(featureCard("Preferred Name", "Display name for contact handling.", "Name type classification"));
        grid.add(featureCard("National Identifier", "Government-issued ID. Sensitive, security-protected.", "Identification type"));
        grid.add(featureCard("Employee Number", "Internal staff identifier.", "Identification type"));
        grid.add(featureCard("Customer Number", "Customer master link identifier.", "Identification type"));
        grid.add(featureCard("External Reference", "Source-system alignment identifier.", "Identification type"));

        content.add(grid);

        return buildSection("IDENTITY", "Names and Identifiers",
                "Dedicated type anchors for the most common party identity attributes.",
                false, content);
    }

    private WaStack<?> buildPartyRelationships()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Use " + brandCode("InvolvedPartyXInvolvedParty") +
                " when one party needs to be related to another party. The " +
                brandCode("ClassificationID") + " identifies the relationship concept and " +
                brandCode("Value") + " carries the specific meaning.", "m"));

        content.add(mermaidDiagramWithTitle("Party Relationships", """
                graph LR
                    P[Person] -->|Is Employed By| O[Organisation]
                    O -->|Employs| P
                    P -->|Occupies Position| EP[Employment Position]
                    OU[Org Unit] -->|Is Department Of| O
                    P -->|Reports To| M[Manager]
                    C[Customer] -->|Is Customer Of| O
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Is Employed By", "A person is employed by an organisation.", null));
        grid.add(featureCard("Is Customer Of", "A person or organisation is a customer of another.", null));
        grid.add(featureCard("Is Department Of", "An org unit belongs to an organisation.", null));
        grid.add(featureCard("Occupies Position", "A person occupies an employment position.", null));
        grid.add(featureCard("Reports To", "A person, unit, or position reports to another party.", null));
        grid.add(featureCard("Is Contact For", "A person acts as a contact for another party.", null));

        content.add(grid);

        return buildSection("RELATIONSHIPS", "Party-to-Party Relationships",
                "Rich, typed relationships between parties &#8212; employment, ownership, membership, authority.",
                true, content);
    }

    private WaStack<?> buildOrganicClassifications()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Organic parties (people) carry rich descriptors through classification. " +
                "Some attributes are " + brandCode("sensitive") + " and must be optional, security-controlled, " +
                "and only captured when there is a lawful, explicit, and necessary business reason.", "m"));

        content.add(codeBlockWithTitle("Example: Person lifecycle status", """
                InvolvedPartyXClassification
                  InvolvedPartyID   = John Smith
                  ClassificationID  = InvolvedPartyLifeCycleStatuses
                  Value             = Living Individual
                  EffectiveFromDate = date the status became effective
                  EffectiveToDate   = end-of-time until replaced""", "text"));

        content.add(codeBlockWithTitle("Example: Person occupation history", """
                // Row 1
                InvolvedPartyXClassification
                  ClassificationID = InvolvedPartyOccupations
                  Value            = Lawyer
                  EffectiveFromDate = 1975-06-01
                
                // Row 2
                InvolvedPartyXClassification
                  ClassificationID = InvolvedPartyOccupations
                  Value            = Accountant
                  EffectiveFromDate = 1989-05-10""", "text"));

        return buildSection("ORGANIC DETAILS", "Person Classifications",
                "Rich descriptors for people &#8212; lifecycle, employment, skills, occupations, and certifications.",
                false, content);
    }

    private WaStack<?> buildHierarchyStrategy()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Use " + brandCode("InvolvedPartyHierarchyView") + " and " +
                brandCode("InvolvedPartyXInvolvedParty") + " for structural relationships. " +
                "Use " + brandCode("ClassificationXClassification") + " for classification hierarchy.", "m"));

        content.add(mermaidDiagram("""
                flowchart TD
                  A[InvolvedParty] --> B[InvolvedPartyOrganic]
                  A --> C[InvolvedPartyNonOrganic]
                  A --> D[InvolvedPartyXClassification]
                  D --> E[Classification]
                  E --> F[ClassificationDataConcept]
                  A --> G[InvolvedPartyXInvolvedParty]
                  G --> H[InvolvedPartyHierarchyView]
                """));

        return buildSection("HIERARCHY", "Hierarchy Strategy",
                "Structural party relationships and classification hierarchies work together.",
                true, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Creating a person (organic party)", """
                // Create the party anchor
                InvolvedParty party = new InvolvedParty_();
                
                // Create the organic (person) specialisation
                InvolvedPartyOrganic person = new InvolvedPartyOrganic_();
                person.setInvolvedParty(party);
                
                // Assign a name
                InvolvedPartyXInvolvedPartyNameType name = new InvolvedPartyXInvolvedPartyNameType_();
                name.setInvolvedParty(party);
                name.setInvolvedPartyNameType(findNameType("Legal Name"));
                name.setValue("John Smith");
                
                // Assign a lifecycle status
                InvolvedPartyXClassification status = new InvolvedPartyXClassification_();
                status.setInvolvedParty(party);
                status.setClassification(findClassification("InvolvedPartyLifeCycleStatuses"));
                status.setValue("Living Individual");"""));

        content.add(codeBlockWithTitle("Creating an employment relationship", """
                InvolvedPartyXInvolvedParty employment = new InvolvedPartyXInvolvedParty_();
                employment.setInvolvedParty(person);
                employment.setRelatedInvolvedParty(organisation);
                employment.setClassification(findClassification("InvolvedPartyRelationships"));
                employment.setValue("Is Employed By");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating and managing involved parties.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Involved Parties connect to every other domain. Explore how parties " +
                "participate in arrangements, interact with products, and are governed by rules.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Event", "/event", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Brand, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}
