package layers.web.vaadin.component.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import layers.web.vaadin.component.button.download.DownloadButton;
import layers.web.vaadin.component.button.generate.GenerateButton;
import layers.web.vaadin.component.button.upload.UploadButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("session")
public class ButtonsLayout extends HorizontalLayout {

    @Autowired
    private UploadButton uploadButton;

    @Autowired
    private GenerateButton generateButton;

    @Autowired
    private DownloadButton downloadButton;

    @PostConstruct
    public void postConstruct() {
        setSizeFull();

        addComponents(uploadButton, generateButton, downloadButton);

        setComponentAlignment(uploadButton,   Alignment.MIDDLE_RIGHT);
        setComponentAlignment(generateButton, Alignment.MIDDLE_CENTER);
        setComponentAlignment(downloadButton, Alignment.MIDDLE_LEFT);
    }
}
