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

@NgComponent("am-event")
@NgRoutable(path = "event")
public class EventPage extends WebsitePage<EventPage> implements INgComponent<EventPage>
{
    public EventPage()
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
        layout.add(buildEventTypes());
        layout.add(buildLifecycle());
        layout.add(buildEntityCatalogue());
        layout.add(buildRelationships());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Event"));
        hero.add(bodyTextHtml("An activity, " + brandCode("communication") + ", " + brandCode("transaction") +
                ", " + brandCode("maintenance action") + ", or " + brandCode("external occurrence") +
                " that ActivityMaster needs to record because it matters to the business. " +
                "Events are the temporal heartbeat &#8212; the activity trail of the model.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Activity Trail", Variant.Success));
        tags.add(buildTag("Temporal", Variant.Neutral));
        tags.add(buildTag("Audit", Variant.Warning));
        tags.add(buildTag("Cross-Domain", Variant.Neutral));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Events capture " + brandCode("what happened") + ", " +
                brandCode("when it happened") + ", " + brandCode("who was involved") + ", " +
                brandCode("what it affected") + ", and " + brandCode("which business meaning") +
                " should be attached to that occurrence.", "m"));

        content.add(bodyTextHtml("ActivityMaster keeps this manageable by using a small Event core plus reusable " +
                "relationship tables and classification values: " + brandCode("Event") + " + " +
                brandCode("EventType") + " + " + brandCode("EventX...") + " relationships + " +
                brandCode("ClassificationID") + " + " + brandCode("Value") + ".", "m"));

        return buildSection("OVERVIEW", "What Are Events?",
                "The model's memory of activity &#8212; transactions, communications, maintenance, and occurrences.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((Event))
                    EventType
                      Communication
                      Business Activity
                      Transaction Event
                      Economic Event
                      Political Event
                      Criminal Event
                      Education Event
                    Classifications
                      Execution Mode
                      Origination Type
                      Lifecycle Status
                      Lifecycle Reason
                    Relationships
                      EventXInvolvedParty
                      EventXArrangement
                      EventXProduct
                      EventXResourceItem
                      EventXRules
                      EventXAddress
                      EventXGeography
                      EventXEvent
                    Time
                      Day / Hour / Minute
                      Effective Dates
                      Lifecycle History
                """));

        return buildSection("MENTAL MODEL", "Event Domain Map",
                "Events connect to every other domain through typed relationship tables.",
                true, content);
    }

    private WaStack<?> buildEventTypes()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagramWithTitle("Event Type Hierarchy", """
                graph TD
                  ET[EventType]
                  ET --> Economic[Economic Event]
                  ET --> Comm[Communication]
                  ET --> Bus[Business Activity]
                  Bus --> Proj[Project Event]
                  Bus --> Trans[Transaction Event]
                  ET --> Pol[Political Event]
                  Pol --> Elect[Election]
                  Pol --> Reg[Regulation Change]
                  ET --> Crim[Criminal Event]
                  Crim --> Fraud[Fraud]
                  Crim --> Rob[Robbery]
                  ET --> Edu[Education Event]
                  Edu --> Conf[Conference]
                  Edu --> Train[Training Course]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Communication", "Exchange of information with an Involved Party.", "Requests, notifications, statements"));
        grid.add(featureCard("Business Activity", "Action performed while fulfilling business purpose.", "Operational and maintenance work"));
        grid.add(featureCard("Transaction Event", "Changes the financial position or information base.", "Debits, credits, postings"));
        grid.add(featureCard("Economic Event", "Event with economic implications or context.", "Currency devaluation, market collapse"));
        grid.add(featureCard("Political Event", "Elections, campaigns, regulation/policy changes.", "Regulation affecting lending"));
        grid.add(featureCard("Criminal Event", "Fraud, robbery, embezzlement.", "Security-sensitive events"));

        content.add(grid);

        return buildSection("EVENT TYPES", "Event Type Taxonomy",
                "Events are classified by their inherent nature through a hierarchical type system.",
                false, content);
    }

    private WaStack<?> buildLifecycle()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                stateDiagram-v2
                  [*] --> Pending
                  Pending --> InProgress
                  InProgress --> Suspended
                  Suspended --> InProgress
                  InProgress --> Completed
                  InProgress --> Cancelled
                  InProgress --> Abandoned
                  Completed --> [*]
                  Cancelled --> [*]
                  Abandoned --> [*]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("14rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Pending", "Waiting for an appointed future time.", null));
        grid.add(featureCard("In Progress", "Currently being carried out.", null));
        grid.add(featureCard("Suspended", "Temporarily interrupted.", null));
        grid.add(featureCard("Completed", "Fulfilled the intended activity.", null));
        grid.add(featureCard("Cancelled", "Terminated before completion.", null));
        grid.add(featureCard("Abandoned", "Did not accomplish its intended effect.", null));

        content.add(grid);

        content.add(bodyTextHtml("Lifecycle reasons explain why: " + brandCode("Insufficient Funds") + ", " +
                brandCode("Customer Request") + ", " + brandCode("System Down") + ", " +
                brandCode("Missing Required Information") + ", " + brandCode("Account Closed") + ".", "m"));

        return buildSection("LIFECYCLE", "Event Lifecycle",
                "From pending to completion &#8212; with reasons for every state change.",
                true, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Event", "Canonical record of the activity or occurrence.", "EventID (UUID), time fields"));
        grid.add(featureCard("EventType", "Classifies the inherent kind of event.", "Hierarchical type taxonomy"));
        grid.add(featureCard("EventXClassification", "Lifecycle, execution mode, origination, reasons.", "Flexible descriptors"));
        grid.add(featureCard("EventXArrangement", "Links events to arrangements they maintain or result from.", "Maintains, Results From"));
        grid.add(featureCard("EventXInvolvedParty", "Links events to requesting, authorising, or affected parties.", "Is Authorized By, Applies To"));
        grid.add(featureCard("EventXProduct", "Links events to products they maintain or affect.", "Maintains, Advertises, Affects"));
        grid.add(featureCard("EventXResourceItem", "Links events to documents, receipts, and evidence.", "Is Documented By"));
        grid.add(featureCard("EventXRules", "Links events to rules that govern or are maintained.", "Is Controlled By, Maintains"));
        grid.add(featureCard("EventXEvent", "Links related events: triggers, sequences, reversals.", "Triggered, Corrects, Groups"));
        grid.add(featureCard("EventXAddress", "Links events to concrete address/contact points.", "Occurs At, Sends To"));
        grid.add(featureCard("EventXGeography", "Links events to regions, jurisdictions, areas.", "Occurs In, Originates From"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Event domain.",
                false, content);
    }

    private WaStack<?> buildRelationships()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Event relationship tables carry " + brandCode("ClassificationID") +
                " (the semantic bucket) and " + brandCode("Value") + " (the assigned business meaning). " +
                "This pattern connects events to every other domain without creating tiny single-purpose tables.", "m"));

        content.add(mermaidDiagramWithTitle("Event Cross-Domain Relationships", """
                graph LR
                    E[Event] --> EA[EventXArrangement]
                    E --> EIP[EventXInvolvedParty]
                    E --> EP[EventXProduct]
                    E --> ERI[EventXResourceItem]
                    E --> ER[EventXRules]
                    E --> EAd[EventXAddress]
                    E --> EG[EventXGeography]
                    E --> EE[EventXEvent]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Automated vs Manual", "Execution mode describes how the event was carried out.", "EventExecutionModes"));
        grid.add(featureCard("Internal vs External", "Origination type: initiated from inside or outside.", "EventOriginationTypes"));
        grid.add(featureCard("Event Chaining", "Events trigger, result from, correct, or reverse other events.", "EventXEvent"));
        grid.add(featureCard("Evidence Pattern", "Documents, receipts, and logs linked via ResourceItem.", "Is Documented By"));

        content.add(grid);

        return buildSection("RELATIONSHIPS", "Cross-Domain Connections",
                "Events connect to parties, arrangements, products, resources, rules, addresses, and other events.",
                true, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Recording a customer beneficiary change request", """
                // Create the event
                Event event = new Event_();
                
                // Assign type: Communication
                EventXEventType typeLink = new EventXEventType_();
                typeLink.setEvent(event);
                typeLink.setEventType(findEventType("Communication"));
                
                // Link to the requesting party
                EventXInvolvedParty partyLink = new EventXInvolvedParty_();
                partyLink.setEvent(event);
                partyLink.setInvolvedParty(customer);
                partyLink.setClassification(findClassification("EventInvolvedPartyRelationships"));
                partyLink.setValue("Is Requested By");
                
                // Link to the arrangement being maintained
                EventXArrangement arrLink = new EventXArrangement_();
                arrLink.setEvent(event);
                arrLink.setArrangement(trustAccount);
                arrLink.setClassification(findClassification("EventArrangementRelationships"));
                arrLink.setValue("Maintains");
                
                // Set lifecycle to Completed
                EventXClassification lifecycle = new EventXClassification_();
                lifecycle.setEvent(event);
                lifecycle.setClassification(findClassification("EventLifeCycleStatuses"));
                lifecycle.setValue("Completed Event");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of recording and linking events.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Events are the activity trail connecting all domains. Explore the related concepts.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Rules", "/rules", Variant.Brand, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}
