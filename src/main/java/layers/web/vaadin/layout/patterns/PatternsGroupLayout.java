package layers.web.vaadin.layout.patterns;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import layers.web.vaadin.layout.patterns.group.PatternsGroup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("session")
public class PatternsGroupLayout extends VerticalLayout {

    @Getter
    @Autowired
    private PatternsGroup patternsGroup;

    @PostConstruct
    public void postConstruct() {
        addComponent(patternsGroup);
        setComponentAlignment(patternsGroup, Alignment.TOP_CENTER);
    }
}
