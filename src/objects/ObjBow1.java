package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjBow1 extends SuperObject{
    public ObjBow1() {
        name = "bow";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/bow1.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }

}
