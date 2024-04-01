package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjHouse4 extends SuperObject{
    public ObjHouse4() {
        name = "House4";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/house4.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
