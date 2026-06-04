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

@NgComponent("am-location")
@NgRoutable(path = "location")
public class LocationPage extends WebsitePage<LocationPage> implements INgComponent<LocationPage>
{
    public LocationPage()
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
        layout.add(buildWhySplit());
        layout.add(buildMentalModel());
        layout.add(buildEntityCatalogue());
        layout.add(buildAddressColumns());
        layout.add(buildGeographyColumns());
        layout.add(buildMappingRules());
        layout.add(buildRelationshipValues());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Address & Geography"));
        hero.add(bodyTextHtml(brandCode("Address") + " stores concrete or logical contact/place values. " +
                brandCode("Geography") + " stores bounded places, jurisdictions, regions, and geographic areas. " +
                "Together they answer every &#8220;where&#8221; question without conflating contact points with territories.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Address", Variant.Success));
        tags.add(buildTag("Geography", Variant.Neutral));
        tags.add(buildTag("Hierarchy", Variant.Warning));
        tags.add(buildTag("Jurisdiction", Variant.Neutral));
        tags.add(buildTag("Contact Points", Variant.Brand));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("ActivityMaster intentionally separates place information into two cleaner concepts:", "m"));

        content.add(bodyTextHtml(brandCode("Address") + " &#8212; concrete or logical values such as street addresses, " +
                "postal addresses, telephone numbers, SWIFT identifiers, office rooms, delivery points, or other contactable points.", "m"));

        content.add(bodyTextHtml(brandCode("Geography") + " &#8212; countries, provinces, cities, counties, regions, " +
                "jurisdictions, market areas, and other bounded places.", "m"));

        content.add(bodyTextHtml("This keeps the model practical without recreating a large number of " +
                "address-component tables too early. The split also prevents address values from carrying " +
                "legal, regional, or hierarchical geography responsibilities.", "m"));

        return buildSection("OVERVIEW", "What Are Address & Geography?",
                "The place layer of the model &#8212; separating contactable points from bounded territories.",
                false, content);
    }

    private WaStack<?> buildWhySplit()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("A single business phrase like &#8220;where something happens&#8221; can mean very different things:", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Statement Delivery", "Where should a statement be sent?", "Address"));
        grid.add(featureCard("Contact Number", "What telephone number should be used?", "Address"));
        grid.add(featureCard("Payment Routing", "What SWIFT or routing point is used?", "Address"));
        grid.add(featureCard("Incorporation", "In which country is this party incorporated?", "Geography"));
        grid.add(featureCard("Governing Law", "Which jurisdiction governs an agreement?", "Geography"));
        grid.add(featureCard("Product Region", "Which region is a product offered in?", "Geography"));
        grid.add(featureCard("Asset Storage", "Where is a physical asset stored?", "Address if exact; Geography if broad area"));

        content.add(grid);

        return buildSection("WHY THE SPLIT", "Different Questions, Different Concepts",
                "Separating contact points from territories prevents semantic collisions.",
                true, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((Place Semantics))
                    Address
                      Street address
                      Postal address
                      Telephone number
                      SWIFT identifier
                      Office room
                      Delivery point
                      Logical contact point
                    Geography
                      Country
                      Province or state
                      City
                      County
                      Region
                      Market area
                      Jurisdiction
                    Relationships
                      AddressXGeography
                      GeographyXGeography
                      InvolvedPartyXAddress
                      EventXAddress
                      EventXGeography
                """));

        return buildSection("MENTAL MODEL", "Place Domain Map",
                "Every place concept routes through either Address or Geography.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Address", "Stores the address/contact/logical-place value.", "Encrypted at rest"));
        grid.add(featureCard("Geography", "Stores a named geographic or jurisdictional area.", "Country, city, region"));
        grid.add(featureCard("AddressXClassification", "Adds address semantics: component meaning, validation status, usage.", "Flexible classification bridge"));
        grid.add(featureCard("AddressXGeography", "Connects an address to its city, region, country, or jurisdiction.", "Located In, Serves"));
        grid.add(featureCard("AddressXResourceItem", "Links documents or artefacts to an address.", "Proof, supporting docs"));
        grid.add(featureCard("GeographyXClassification", "Classifies geography type, role, jurisdiction category.", "Flexible classification bridge"));
        grid.add(featureCard("GeographyXGeography", "Creates geographic hierarchy: country \u2192 province \u2192 city.", "Contains, Is Part Of"));
        grid.add(featureCard("GeographyXResourceItem", "Links maps, permits, certificates to a geography.", "Supporting resources"));
        grid.add(featureCard("InvolvedPartyXAddress", "Assigns an address to a party.", "Mailing, residential, business"));
        grid.add(featureCard("EventXAddress", "Assigns address context to an event.", "Occurs At, Sends To"));
        grid.add(featureCard("EventXGeography", "Assigns regional/jurisdictional context to an event.", "Occurs In, Has Tax Jurisdiction Of"));
        grid.add(featureCard("GeographyHierarchyView", "Supports hierarchy browsing for geographic structures.", "View entity"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Address & Geography domain.",
                false, content);
    }

    private WaStack<?> buildAddressColumns()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml(brandCode("Address.Value") + " is encrypted at rest, making " +
                brandCode("Address") + " the right target for contactable or personally identifying place values.", "m"));

        content.add(codeBlock("""
                Address
                  AddressID         = UUID primary key
                  Value             = encrypted address/contact value
                  ClassificationID  = address type (street, postal, phone, SWIFT)
                  EffectiveFromDate = when the address became effective
                  EffectiveToDate   = when the address ceased being effective
                  EnterpriseID      = owning enterprise
                  ActiveFlagID      = active / deleted / archived state
                  SystemID          = owning system""", "text"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Street Address", "1345 Broad Street", "Concrete physical address"));
        grid.add(featureCard("Postal Address", "PO Box 97, Johannesburg", "Link to Geography = Johannesburg"));
        grid.add(featureCard("Telephone Number", "881-4911", "Logical/contact address"));
        grid.add(featureCard("SWIFT Identifier", "Payment routing address", "Needs seed classification"));
        grid.add(featureCard("Branch Address", "City Center location of Bank ABC", "Link region via AddressXGeography"));
        grid.add(featureCard("Email Address", "user@example.com", "Logical contact point"));

        content.add(grid);

        return buildSection("ADDRESS", "Address Core Structure",
                "Concrete and logical contact values &#8212; encrypted, typed, and time-bounded.",
                false, content);
    }

    private WaStack<?> buildGeographyColumns()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlock("""
                Geography
                  GeographyID       = UUID primary key
                  GeographyName     = name of the geographic area
                  GeographyDesc     = description
                  ClassificationID  = geography type (country, city, region)
                  EffectiveFromDate = when the geography became effective
                  EffectiveToDate   = when the geography ceased being effective
                  EnterpriseID      = owning enterprise
                  ActiveFlagID      = active / deleted / archived state
                  SystemID          = owning system""", "text"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Country", "Germany, South Africa", "Bounded geopolitical area"));
        grid.add(featureCard("City", "Johannesburg, Pretoria", "Can be linked from many addresses"));
        grid.add(featureCard("State / Province", "Virginia, Gauteng", "Legal validity or jurisdiction"));
        grid.add(featureCard("Jurisdiction", "Commonwealth of Virginia", "Laws/governing authority"));
        grid.add(featureCard("County", "Fairfax County", "Legal or administrative area"));
        grid.add(featureCard("Region / Market Area", "North East Region", "Product availability and targeting"));

        content.add(grid);

        return buildSection("GEOGRAPHY", "Geography Core Structure",
                "Named areas and territories &#8212; hierarchical, jurisdictional, and classifiable.",
                true, content);
    }

    private WaStack<?> buildMappingRules()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Use " + brandCode("Address") + " when the value is something that can be " +
                "contacted, delivered to, dialled, routed to, or read as a concrete point. " +
                "Use " + brandCode("Geography") + " when the value is a bounded or named area.", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Contact / Place Value", "Address with ClassificationID = AddressTypes", "Street, postal, phone, SWIFT, email"));
        grid.add(featureCard("Area / Territory", "Geography with ClassificationID = GeographyTypes", "Country, province, city, region"));
        grid.add(featureCard("Address in a City", "AddressXGeography", "Value = Located In"));
        grid.add(featureCard("Geography Hierarchy", "GeographyXGeography", "Value = Contains / Is Part Of"));
        grid.add(featureCard("Party Address", "InvolvedPartyXAddress", "Mailing, Residential, Contact Number"));
        grid.add(featureCard("Event at a Place", "EventXAddress", "Occurs At, Originates From, Sends To"));
        grid.add(featureCard("Event in a Region", "EventXGeography", "Occurs In, Has Tax Jurisdiction Of"));

        content.add(grid);

        return buildSection("MAPPING", "Mapping Rules",
                "Guidelines for choosing between Address and Geography.",
                false, content);
    }

    private WaStack<?> buildRelationshipValues()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagramWithTitle("Address & Geography Relationships", """
                graph LR
                    A[Address] -->|Located In| G[Geography]
                    G -->|Contains| G2[Sub-Geography]
                    P[Party] -->|Mailing Address| A
                    P -->|Residential Address| A
                    E[Event] -->|Occurs At| A
                    E -->|Occurs In| G
                    E -->|Has Tax Jurisdiction Of| G
                """));

        content.add(bodyTextHtml("Use " + brandCode("InvolvedPartyXAddress") + " with " +
                brandCode("ClassificationID = InvolvedPartyAddressRoles") + " for party address roles:", "m"));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Mailing Address", "Address used for correspondence.", null));
        grid.add(featureCard("Residential Address", "Address where a person resides.", null));
        grid.add(featureCard("Business Address", "Address where an org unit operates.", null));
        grid.add(featureCard("Contact Number", "Telephone or similar contact point.", null));
        grid.add(featureCard("Registered Address", "Formal legal address for an organisation.", null));
        grid.add(featureCard("Occurs At", "Event at a concrete address.", "EventXAddress"));
        grid.add(featureCard("Occurs In", "Event in a geographic area.", "EventXGeography"));
        grid.add(featureCard("Has Tax Jurisdiction Of", "Geographic/legal jurisdiction.", "EventXGeography"));

        content.add(grid);

        return buildSection("RELATIONSHIPS", "Common Relationship Values",
                "Typed relationships between parties, events, addresses, and geographies.",
                true, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Creating an address with geography link", """
                // Create the address
                Address address = new Address_();
                address.setClassification(findClassification("AddressTypes"));
                address.setValue("1345 Broad Street");

                // Link the address to a city
                AddressXGeography link = new AddressXGeography_();
                link.setAddress(address);
                link.setGeography(findGeography("Johannesburg"));
                link.setClassification(findClassification("AddressGeographyRelationships"));
                link.setValue("Located In");"""));

        content.add(codeBlockWithTitle("Building a geography hierarchy", """
                Geography country = new Geography_();
                country.setGeographyName("South Africa");
                country.setClassification(findClassification("GeographyTypes"));

                Geography province = new Geography_();
                province.setGeographyName("Gauteng");
                province.setClassification(findClassification("GeographyTypes"));

                // Link province to country
                GeographyXGeography hierarchy = new GeographyXGeography_();
                hierarchy.setGeography(province);
                hierarchy.setRelatedGeography(country);
                hierarchy.setClassification(findClassification("GeographyRelationships"));
                hierarchy.setValue("Is Part Of");"""));

        content.add(codeBlockWithTitle("Assigning address to a party", """
                InvolvedPartyXAddress partyAddr = new InvolvedPartyXAddress_();
                partyAddr.setInvolvedParty(person);
                partyAddr.setAddress(address);
                partyAddr.setClassification(findClassification("InvolvedPartyAddressRoles"));
                partyAddr.setValue("Mailing Address");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating addresses, geographies, and their relationships.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Address and Geography connect to every other domain. " +
                "Parties have addresses, events occur at places, products are available in regions, " +
                "and rules govern jurisdictions.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Event", "/event", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Brand, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}

