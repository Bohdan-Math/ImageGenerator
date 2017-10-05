package utility.system;

import org.springframework.stereotype.Component;
import utility.core.InformationalImage;

import java.io.ByteArrayInputStream;
import java.io.File;

import static javax.imageio.ImageIO.read;

@Component
public class ObjectTypeConverter {

    public InformationalImage informationalImage(byte[] bytes) {
        try {
            return new InformationalImage(read(new ByteArrayInputStream(bytes)));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public InformationalImage informationalImage(File fromFile) {
        try {
            return new InformationalImage(read(fromFile));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

}