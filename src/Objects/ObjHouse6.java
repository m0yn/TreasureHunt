package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjHouse6 extends SuperObject{
    public ObjHouse6() {
        name = "House6";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/house6.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
