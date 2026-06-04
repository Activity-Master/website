package com.activitymaster.website.pages;

import com.activitymaster.website.App;
import com.jwebmp.core.base.angular.client.annotations.references.NgComponentReference;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.components.NgIf;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.plugins.markdown.Markdown;
import com.jwebmp.webawesome.components.BorderTokenCapable;
import com.jwebmp.webawesome.components.PageSize;
import com.jwebmp.webawesome.components.SpaceTokenCapable;
import com.jwebmp.webawesome.components.TypographyTokenCapable;
import com.jwebmp.webawesome.components.Variant;
import com.jwebmp.webawesome.components.WaStack;
import com.jwebmp.webawesome.components.button.Appearance;
import com.jwebmp.webawesome.components.button.WaButton;
import com.jwebmp.webawesome.components.card.WaCard;
import com.jwebmp.webawesome.components.details.WaDetails;
import com.jwebmp.webawesome.components.divider.WaDivider;
import com.jwebmp.webawesome.components.tag.WaTag;
import com.jwebmp.webawesome.components.text.WaText;
import org.apache.commons.text.StringEscapeUtils;

@NgComponentReference(App.class)
public abstract class WebsitePage<J extends WebsitePage<J>> extends DivSimple<J> implements INgComponent<J>, SpaceTokenCapable<J>, BorderTokenCapable<J>, TypographyTokenCapable<J>
{
    protected WebsitePage()
    {
        addClass("website-content");
        addStyle("padding", "0 var(--wa-spacing-x-large) var(--wa-spacing-x-large) var(--wa-spacing-x-large)");
        addStyle("max-width", "72rem");
    }

    @SuppressWarnings("unchecked")
    protected J getMain()
    {
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    protected J getAside()
    {
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setPageSize(PageSize pageSize)
    {
        return (J) this;
    }

    // ── Text helpers ──────────────────────────────────

    protected String escapeAngular(String text)
    {
        if (text == null)
        {
            return null;
        }
        return StringEscapeUtils.escapeHtml4(text)
                                .replace("@", "&#64;")
                                .replace("{", "&#123;")
                                .replace("}", "&#125;")
                                .replace("[", "&#91;")
                                .replace("]", "&#93;")
                                .replace("(", "&#40;")
                                .replace(")", "&#41;")
                                .replace("*", "&#42;")
                                .replace("_", "&#95;");
    }

    protected static String slugify(String text)
    {
        if (text == null) return null;
        return text.toLowerCase()
                   .replaceAll("[^a-z0-9]+", "-")
                   .replaceAll("^-+|-+$", "");
    }

    protected WaText<?> headingText(String tag, String size, String text)
    {
        var heading = new WaText<>();
        heading.setTag(tag);
        heading.setWaHeading(size);
        heading.setText(escapeAngular(text));
        return heading;
    }

    protected WaText<?> bodyText(String text, String size)
    {
        var body = new WaText<>();
        body.setTag("p");
        body.setWaBody(size == null || size.isBlank() ? "m" : size);
        body.setText(escapeAngular(text));
        return body;
    }

    protected WaText<?> bodyTextHtml(String html, String size)
    {
        var body = new WaText<>();
        body.setTag("p");
        body.setWaBody(size == null || size.isBlank() ? "m" : size);
        body.setText(html.replace("{", "&#123;").replace("}", "&#125;"));
        return body;
    }

    protected static String brandCode(String text)
    {
        return "<code class=\"wa-body-s\" style=\"color:color-mix(in srgb, var(--wa-color-brand) 70%, var(--wa-color-text));\">" +
               text.replace("{", "&#123;").replace("}", "&#125;") +
               "</code>";
    }

    protected WaText<?> captionText(String text)
    {
        var caption = new WaText<>();
        caption.setTag("div");
        caption.setWaCaption("s");
        caption.setWaFontWeight("semibold");
        caption.setText(escapeAngular(text));
        return caption;
    }

    // ── Component helpers ─────────────────────────────

    protected WaTag<?> buildTag(String label, Variant variant)
    {
        WaTag<?> tag = new WaTag<>();
        tag.setText(escapeAngular(label));
        tag.setVariant(variant);
        tag.setPill(true);
        return tag;
    }

    protected WaButton<?> buildCta(String label, String route, Variant variant, Appearance appearance)
    {
        WaButton<?> button = new WaButton<>(escapeAngular(label), variant);
        if (appearance != null)
        {
            button.setAppearance(appearance);
        }
        var absoluteRoute = route.startsWith("/") ? route : "/" + route;
        button.addAttribute("[routerLink]", "['" + absoluteRoute + "']");
        return button;
    }

    protected WaCard<?> featureCard(String title, String body, String note)
    {
        var card = new WaCard<>();
        card.setAppearance(Appearance.Outlined);
        card.addClass("feature-card");

        var stack = new WaStack<>();
        stack.setGap(PageSize.Small);

        var titleText = headingText("h3", "m", title);
        titleText.addClass("feature-card-title");
        stack.add(titleText);

        var bodyCopy = bodyText(body, "m");
        bodyCopy.addClass("feature-card-body");
        bodyCopy.setWaColorText("quiet");
        stack.add(bodyCopy);
        if (note != null && !note.isBlank())
        {
            var noteText = captionText(note);
            noteText.addClass("feature-card-body");
            noteText.addClass("feature-card-note");
            noteText.setWaColorText("quiet");
            stack.add(noteText);
        }
        card.add(stack);
        return card;
    }

    protected WaCard<?> featureCardHtml(String title, String bodyHtml, String note)
    {
        var card = new WaCard<>();
        card.setAppearance(Appearance.Outlined);
        card.addClass("feature-card");

        var stack = new WaStack<>();
        stack.setGap(PageSize.Small);

        var titleText = headingText("h3", "m", title);
        titleText.addClass("feature-card-title");
        stack.add(titleText);

        var bodyCopy = bodyTextHtml(bodyHtml, "m");
        bodyCopy.addClass("feature-card-body");
        bodyCopy.setWaColorText("quiet");
        stack.add(bodyCopy);
        if (note != null && !note.isBlank())
        {
            var noteText = bodyTextHtml(note, "s");
            noteText.setTag("div");
            noteText.addClass("feature-card-body");
            noteText.addClass("feature-card-note");
            noteText.setWaColorText("quiet");
            stack.add(noteText);
        }
        card.add(stack);
        return card;
    }

    // ── Section helpers ───────────────────────────────

    protected WaStack section(String eyebrow, String title, String subtitle,
                              com.jwebmp.core.base.interfaces.IComponentHierarchyBase<?, ?> content)
    {
        var section = new WaStack<>();
        section.setGap(PageSize.Medium);
        section.addClass("content-section");

        String idSource = eyebrow != null && !eyebrow.isBlank() ? eyebrow : title;
        if (idSource != null && !idSource.isBlank())
        {
            section.setID(slugify(idSource));
        }

        var divider = new WaDivider<>();
        divider.addClass("section-divider");
        section.add(divider);
        section.add(sectionHeader(eyebrow, title, subtitle));
        if (content != null)
        {
            section.add(content);
        }
        return section;
    }

    protected WaStack sectionHeader(String eyebrow, String title, String subtitle)
    {
        var header = new WaStack<>();
        header.setGap(PageSize.Small);
        if (eyebrow != null && !eyebrow.isBlank())
        {
            var eyebrowText = captionText(eyebrow);
            eyebrowText.addClass("hero-eyebrow");
            header.add(eyebrowText);
        }
        if (title != null && !title.isBlank())
        {
            header.add(headingText("h2", "l", title));
        }
        if (subtitle != null && !subtitle.isBlank())
        {
            var subtitleText = bodyText(subtitle, "m");
            subtitleText.setWaColorText("quiet");
            header.add(subtitleText);
        }
        return header;
    }

    protected WaStack buildSection(String eyebrow, String title, String subtitle,
                                   boolean alt,
                                   com.jwebmp.core.base.interfaces.IComponentHierarchyBase<?, ?> content)
    {
        return section(eyebrow, title, subtitle, content);
    }

    // ── Diagram helpers ──────────────────────────────

    protected DivSimple<?> mermaidDiagram(String mermaidCode)
    {
        var wrapper = new DivSimple<>();
        wrapper.addClass("mermaid-diagram");
        wrapper.addStyle("overflow-x", "auto");
        wrapper.addStyle("width", "100%");

        var darkMd = new Markdown<>("```mermaid\n%%{init: {'theme': 'dark'}}%%\n" + mermaidCode + "\n```");
        darkMd.setMermaid(true);
        var darkIf = new NgIf("app.darkMode()");
        darkIf.add(darkMd);
        wrapper.add(darkIf);

        var lightMd = new Markdown<>("```mermaid\n" + mermaidCode + "\n```");
        lightMd.setMermaid(true);
        var lightIf = new NgIf("!app.darkMode()");
        lightIf.add(lightMd);
        wrapper.add(lightIf);

        return wrapper;
    }

    protected DivSimple<?> mermaidDiagramWithTitle(String title, String mermaidCode)
    {
        var wrapper = new DivSimple<>();
        wrapper.addClass("mermaid-diagram-wrapper");
        var label = captionText(title);
        label.addClass("mermaid-diagram-label");
        wrapper.add(label);
        wrapper.add(mermaidDiagram(mermaidCode));
        return wrapper;
    }

    // ── Code block helpers ────────────────────────────

    protected DivSimple<?> codeBlock(String code)
    {
        return codeBlock(code, "java");
    }

    protected DivSimple<?> codeBlock(String code, String language)
    {
        var md = new Markdown<>("```" + language + "\n" + code + "\n```");
        md.setClipboard(true);
        md.addClass("code-block");
        md.addClass("wa-body-s");
        return md;
    }

    protected DivSimple<?> codeBlockWithTitle(String title, String code)
    {
        return codeBlockWithTitle(title, code, "java");
    }

    protected DivSimple<?> codeBlockWithTitle(String title, String code, String language)
    {
        var details = new WaDetails<>();
        details.setSummary(title);
        details.addClass("code-details");

        var md = new Markdown<>("```" + language + "\n" + code + "\n```");
        md.setClipboard(true);
        md.addClass("code-block");
        md.addClass("wa-body-s");
        details.add(md);
        return details;
    }

    protected WaDivider<?> divider()
    {
        return new WaDivider<>();
    }
}

