package com.activitymaster.website.pages;

import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.plugins.markdown.Markdown;

public abstract class ConceptPage<T extends ConceptPage<T>> extends DivSimple<T> implements INgComponent<T> {

    private final Markdown<?> markdown;

    public ConceptPage(String markdownPath) {
        markdown = Markdown.fromSource(markdownPath);
        add(markdown);
    }

    public Markdown<?> getMarkdown() {
        return markdown;
    }
}
