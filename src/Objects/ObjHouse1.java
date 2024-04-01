package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjHouse1 extends SuperObject{
    public ObjHouse1() {
        name = "House1";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/house1.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
