package layers.web.vaadin.component.button.download.listener;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;

public interface Downloader {

    void extend(AbstractComponent abstractComponent);
    void setFileDownloadResource(Resource resource);
}
