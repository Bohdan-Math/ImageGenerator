package layers.web.vaadin.component.visual;

import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("session")
public class FooterLayout extends VerticalLayout {

    @PostConstruct
    public void postConstruct() {

        Link flagsLink = link("http://www.icondrawer.com");

        addComponents(flagsLink);

        setComponentAlignment(flagsLink, Alignment.BOTTOM_RIGHT);

    }

    private Link link(String sourceURL) {
        Link link                 = new Link();
        ExternalResource resource = new ExternalResource(sourceURL);
        link.setCaption("Thanks for FLAGS!");
        link.setResource(resource);

        return link;
    }
}