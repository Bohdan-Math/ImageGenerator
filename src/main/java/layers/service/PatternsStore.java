package layers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utility.helpers.PatternManager;
import utility.helpers.ResourceReader;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import static layers.service.Patterns.*;

@Component
@Scope("singleton")
public class PatternsStore {

    @Autowired
    private PatternManager patternManager;

    @Autowired
    private ResourceReader resourceReader;

    @Resource(name = "patternsLocation")
    private Map<Patterns, String> patternsLocation;

    @Resource(name = "patterns")
    private Map<Patterns, Map<Color, BufferedImage>> patterns;

    @PostConstruct
    public void postConstruct() {
        Map<Color, BufferedImage> commons = patternManager.patternsMap(resourceReader.readFiles(patternsLocation.get(COMMONS)));
        Map<Color, BufferedImage> flags   = patternManager.patternsMap(resourceReader.readFiles(patternsLocation.get(FLAGS)));
        Map<Color, BufferedImage> plains  = patternManager.patternsMap(resourceReader.readFiles(patternsLocation.get(PLAINS)));

        patterns.put(COMMONS, commons);
        patterns.put(FLAGS, flags);
        patterns.put(PLAINS, plains);
    }
}