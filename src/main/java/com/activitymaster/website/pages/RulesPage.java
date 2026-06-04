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

@NgComponent("am-rules")
@NgRoutable(path = "rules")
public class RulesPage extends WebsitePage<RulesPage> implements INgComponent<RulesPage>
{
    public RulesPage()
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
        layout.add(buildStructureTypes());
        layout.add(buildPurposes());
        layout.add(buildLifecycle());
        layout.add(buildRuleToRuleRelationships());
        layout.add(buildCrossDomain());
        layout.add(buildEntityCatalogue());
        layout.add(buildCodeExamples());
        layout.add(buildCallToAction());
    }

    private WaStack<?> buildHero()
    {
        var hero = new WaStack<>();
        hero.setGap(PageSize.Large);
        hero.addClass("hero-banner");

        hero.add(captionText("FSDM DOMAIN"));
        hero.add(headingText("h1", "xl", "Rules"));
        hero.add(bodyTextHtml("Reusable business requirements: " + brandCode("limits") + ", " +
                brandCode("thresholds") + ", " + brandCode("eligibility criteria") + ", " +
                brandCode("prices") + ", " + brandCode("rates") + ", " + brandCode("permissions") +
                ", and " + brandCode("timing controls") + " that govern how business is conducted across all domains.", "l"));

        var tags = new WaCluster<>();
        tags.setGap(PageSize.Small);
        tags.add(buildTag("FSDM", Variant.Brand));
        tags.add(buildTag("Business Rules", Variant.Success));
        tags.add(buildTag("Reusable", Variant.Neutral));
        tags.add(buildTag("Cross-Domain", Variant.Warning));
        tags.add(buildTag("Composable", Variant.Neutral));
        hero.add(tags);

        return hero;
    }

    private WaStack<?> buildOverview()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyTextHtml("Rules are where ActivityMaster stores the business meaning behind statements like: " +
                brandCode("initial investment amount must be at least R5,000") + ", " +
                brandCode("grace period is 15 days") + ", " +
                brandCode("eligibility requires age >= 55") + ", or " +
                brandCode("a transaction after 3:00pm applies to the next business day") + ".", "m"));

        content.add(bodyTextHtml("Rules are intentionally " + brandCode("reusable") + ". A single rule such as " +
                brandCode("Length of Service >= 10 years") + " can be used as a prerequisite for vacation allowance, " +
                "retirement benefits, promotion eligibility, or loan eligibility.", "m"));

        content.add(bodyTextHtml("The goal is to preserve the semantic richness of FSDM while using the ActivityMaster pattern: " +
                brandCode("Rules") + " + " + brandCode("ClassificationID") + " + " + brandCode("Value") + " + " +
                brandCode("SCD columns") + " + " + brandCode("SecurityToken") + ".", "m"));

        return buildSection("OVERVIEW", "What Are Rules?",
                "Business logic and decision criteria that govern behaviour across all domains.",
                false, content);
    }

    private WaStack<?> buildMentalModel()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                mindmap
                  root((Rules))
                    Structure
                      Single Rule
                      Statement Rule
                      Range Rule
                      Matrix Rule
                    Purpose
                      Eligibility
                      Limitation
                      Payment
                      Permission
                      Price
                      Rating
                      Handling
                      Cost
                    Expression
                      Argument
                      Operator
                      Value
                      Unit of Measure
                    Relationships
                      Has Precondition
                      Has Preferred Value
                      Has Increment Of
                      Is Alternative To
                      Is Comprised Of
                      Is Derived From
                      Is Exclusive Of
                      Replaces
                      Results In
                    Applies To
                      Arrangement
                      Product
                      InvolvedParty
                      ResourceItem
                      Event
                """));

        return buildSection("MENTAL MODEL", "How Rules Connect",
                "Rules are composable, reusable, and apply across every domain in ActivityMaster.",
                true, content);
    }

    private WaStack<?> buildStructureTypes()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCardHtml("Single Rule",
                "A reusable atomic rule containing an " + brandCode("argument") + ", " +
                        brandCode("operator") + ", " + brandCode("value") + ", and optionally a unit of measure.",
                "Example: Interest Rate = 7%"));

        grid.add(featureCardHtml("Statement Rule",
                "A rule built by combining two or more rules with logical " + brandCode("AND") + ".",
                "Example: Interest Rate = 7% AND Balance = R10,000"));

        grid.add(featureCardHtml("Range Rule",
                "A rule that defines a boundary or valid set of values using " + brandCode("minimum") +
                        ", " + brandCode("maximum") + ", " + brandCode("increment") + ", and optional preferred value.",
                "Example: Face Value >= R100,000 AND <= R200,000"));

        grid.add(featureCardHtml("Matrix Rule",
                "A rule built from alternative statements, usually using logical " + brandCode("OR") + ".",
                "Example: Rate = 7% AND Balance < R100K OR Rate = 10% AND Balance >= R100K"));

        content.add(grid);

        return buildSection("STRUCTURE", "Rule Structure Types",
                "Rules come in four structural forms, from simple atomic values to complex matrices.",
                false, content);
    }

    private WaStack<?> buildPurposes()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Cost Determination", "Determines cost, cost rate, or variables by which costs are derived.", "Labour costs: R10/hour"));
        grid.add(featureCard("Eligibility Determination", "Determines how qualification for something is achieved.", "Age >= 55 for senior account"));
        grid.add(featureCard("Handling Determination", "Expresses how an activity must be carried out.", "SWIFT confirmation required"));
        grid.add(featureCard("Limitation Determination", "Specifies allowable limits, exceptions, and tolerance ranges.", "Drawing limit: R5,000"));
        grid.add(featureCard("Payment Determination", "Describes a remittable transaction to be made.", "Dividend: R30.00 per share"));
        grid.add(featureCard("Permission Determination", "Grants discretionary privilege or authorisation.", "Authority to hire/fire"));
        grid.add(featureCard("Price Determination", "States a price, rate, fee, tax, or exchange rate.", "Interest: 10% per annum"));
        grid.add(featureCard("Rating Determination", "Defines derivation of a score, rating, or scalar estimate.", "+5 points for 3yr employment"));

        content.add(grid);

        return buildSection("PURPOSES", "Rule Purposes",
                "Each rule has a purpose that describes what kind of business requirement it represents.",
                true, content);
    }

    private WaStack<?> buildLifecycle()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(mermaidDiagram("""
                stateDiagram-v2
                  [*] --> Proposed
                  Proposed --> UnderDevelopment : formulated
                  UnderDevelopment --> Active : enacted
                  Active --> Terminated : discontinued
                  Terminated --> [*]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Proposed Rule", "The rule is being formulated and discussed.", null));
        grid.add(featureCard("Under Development", "Formulated and being developed/tested, but not yet enacted.", null));
        grid.add(featureCard("Active Rule", "Implemented and incorporated into at least one business process.", null));
        grid.add(featureCard("Terminated Rule", "Previously existed and was later discontinued intentionally.", null));

        content.add(grid);

        return buildSection("LIFECYCLE", "Rule Lifecycle",
                "Rules progress through states from proposal to active enforcement to termination.",
                false, content);
    }

    private WaStack<?> buildRuleToRuleRelationships()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Rules can be composed from other rules using " + brandCode("RulesXRules") +
                ". This enables complex business logic to be built from simple, reusable atomic rules.", "m"));

        content.add(mermaidDiagramWithTitle("Rule Composition", """
                graph TD
                    R1[Range Rule] -->|Is Comprised Of| R2[Minimum Value Rule]
                    R1 -->|Is Comprised Of| R3[Maximum Value Rule]
                    R1 -->|Has Increment Of| R4[Increment Rule]
                    R1 -->|Has Preferred Value| R5[Preferred Value Rule]
                    R6[Fee Rule A] -->|Is Alternative To| R7[Fee Rule B]
                    R8[Base Rate] -->|Results In| R9[Final Rate]
                    R10[Balance Rule] -->|Has Precondition| R11[Tenure Rule]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Has Precondition", "One rule is a prerequisite for another.", "Balance >= R1,000 required for free checks"));
        grid.add(featureCard("Is Comprised Of", "A structured rule contains another rule.", "Range is comprised of min + max"));
        grid.add(featureCard("Is Derived From", "One rule is based on another.", "Rate 9% derived from prime 7%"));
        grid.add(featureCard("Replaces", "One rule replaces another over time.", "Rate 7% replaces rate 8%"));
        grid.add(featureCard("Results In", "One rule determines the outcome of another.", "> 10 checks results in R15 fee"));
        grid.add(featureCard("Is Alternative To", "One rule may be substituted for another.", "R100 fee OR 1% of loan"));

        content.add(grid);

        return buildSection("COMPOSITION", "Rule-to-Rule Relationships",
                "Complex rules are composed from simpler, reusable rules via RulesXRules.",
                true, content);
    }

    private WaStack<?> buildCrossDomain()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Large);

        content.add(bodyTextHtml("Rules can apply across the entire model. Use the domain relationship table that matches " +
                "the business object being governed. The same rule can be reused across multiple domains.", "m"));

        content.add(mermaidDiagramWithTitle("Cross-Domain Rule Application", """
                graph LR
                    R[Rules] --> RA[RulesXArrangement]
                    R --> RP[RulesXProduct]
                    R --> RIP[RulesXInvolvedParty]
                    R --> RRI[RulesXResourceItem]
                    R --> ER[EventXRules]
                    RA --> A[Arrangement]
                    RP --> P[Product]
                    RIP --> IP[InvolvedParty]
                    RRI --> RI[ResourceItem]
                    ER --> E[Event]
                """));

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Arrangement Rules", "Governs terms, conditions, obligations, and limits on agreements.", "Loan interest rate, grace period"));
        grid.add(featureCard("Product Rules", "Defines eligibility, pricing, features, and restrictions for products.", "Age >= 55, availability region"));
        grid.add(featureCard("Party Rules", "Applies authority, permissions, and eligibility to people and organisations.", "Authority to approve, tenure requirement"));
        grid.add(featureCard("Resource Rules", "Controls how assets and documents are managed or accessed.", "Collateral value minimum"));
        grid.add(featureCard("Event Rules", "Governs or is maintained by business activities and occurrences.", "Transaction limit, processing cutoff"));

        content.add(grid);

        return buildSection("CROSS-DOMAIN", "Rules Apply Everywhere",
                "A single rule can govern arrangements, products, parties, resources, and events.",
                false, content);
    }

    private WaStack<?> buildEntityCatalogue()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        var grid = new WaGrid<>();
        grid.setMinColumnSize("16rem");
        grid.setGap(PageSize.Medium);

        grid.add(featureCard("Rules", "Stores the reusable rule set, statement, or business requirement.", "RulesID, RuleSetName, RuleSetDescription"));
        grid.add(featureCard("RulesType", "Classifies the structural or implementation type of the rule.", "Single, Statement, Range, Matrix"));
        grid.add(featureCard("RulesXRulesType", "Links a rule to its structural rule type.", "Type classification bridge"));
        grid.add(featureCard("RulesXClassification", "Applies semantic buckets: lifecycle, purpose, argument, reference type.", "Universal vocabulary layer"));
        grid.add(featureCard("RulesXRules", "Links one rule to another for composition and dependency.", "Precondition, Comprised Of, Replaces"));
        grid.add(featureCard("RulesXArrangement", "Applies rules to arrangements.", "Terms, conditions, obligations"));
        grid.add(featureCard("RulesXProduct", "Applies rules to products.", "Eligibility, pricing, features"));
        grid.add(featureCard("RulesXInvolvedParty", "Applies rules to parties.", "Authority, permissions, restrictions"));
        grid.add(featureCard("RulesXResourceItem", "Applies rules to documents and assets.", "Collateral rules, access rules"));
        grid.add(featureCard("RulesHierarchyView", "Supports hierarchical browsing of rules and groupings.", "Read-only hierarchy view"));

        content.add(grid);

        return buildSection("ENTITIES", "Entity Catalogue",
                "The complete set of entities that make up the Rules domain.",
                true, content);
    }

    private WaStack<?> buildCodeExamples()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(codeBlockWithTitle("Creating an eligibility rule", """
                // Create an atomic eligibility rule
                Rules ageRule = new Rules_();
                ageRule.setRuleSetName("Senior Citizen Eligibility");
                ageRule.setRuleSetDescription("Customer must be beyond the age of 55");
                
                // Classify it with purpose and structure type
                RulesXClassification purpose = new RulesXClassification_();
                purpose.setClassification(findClassification("RulePurposes"));
                purpose.setValue("Eligibility Determination");
                
                RulesXRulesType structureType = new RulesXRulesType_();
                structureType.setRulesType(findRulesType("Single Rule"));
                
                // Apply it to a product
                RulesXProduct productRule = new RulesXProduct_();
                productRule.setRules(ageRule);
                productRule.setProduct(seniorCheckingProduct);
                productRule.setValue("Eligibility Requirement");"""));

        content.add(codeBlockWithTitle("Composing a range rule from atomic rules", """
                // Create min, max, and increment rules
                Rules minRule = createSingleRule("Face Value Minimum", "Face Value >= R100,000");
                Rules maxRule = createSingleRule("Face Value Maximum", "Face Value <= R200,000");
                Rules incrRule = createSingleRule("Face Value Increment", "Increment = R1,000");
                Rules prefRule = createSingleRule("Preferred Face Value", "Preferred = R150,000");
                
                // Create the composite range rule
                Rules rangeRule = new Rules_();
                rangeRule.setRuleSetName("Jumbo Mortgage Face Value Range");
                
                // Link them via RulesXRules
                linkRule(rangeRule, minRule, "Is Comprised Of");
                linkRule(rangeRule, maxRule, "Is Comprised Of");
                linkRule(rangeRule, incrRule, "Has Increment Of");
                linkRule(rangeRule, prefRule, "Has Preferred Value");"""));

        content.add(codeBlockWithTitle("Applying rules to an arrangement", """
                // Link a grace period rule to a loan arrangement
                RulesXArrangement gracePeriod = new RulesXArrangement_();
                gracePeriod.setRules(findRule("Grace Period 15 Days"));
                gracePeriod.setArrangement(loanArrangement);
                gracePeriod.setClassification(findClassification("ArrangementRuleRoles"));
                gracePeriod.setValue("Payment Term");"""));

        return buildSection("EXAMPLES", "Code Examples",
                "Practical examples of creating and applying rules in ActivityMaster.",
                false, content);
    }

    private WaStack<?> buildCallToAction()
    {
        var content = new WaStack<>();
        content.setGap(PageSize.Medium);

        content.add(bodyText("Rules are the brain of ActivityMaster — the reusable logic that governs behaviour " +
                "across all domains. Explore how rules integrate with the other six FSDM concepts.", "l"));

        var ctas = new WaCluster<>();
        ctas.setGap(PageSize.Small);
        ctas.add(buildCta("Arrangement", "/arrangement", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Product", "/product", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Involved Party", "/involved-party", Variant.Neutral, Appearance.Outlined));
        ctas.add(buildCta("Classifications", "/classifications", Variant.Brand, Appearance.Outlined));
        content.add(ctas);

        return buildSection(null, "Explore Related Concepts",
                "Seven domains. One canonical model. Infinite possibilities.",
                true, content);
    }
}

