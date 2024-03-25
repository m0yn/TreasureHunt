package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjHouse3 extends SuperObject{

        public ObjHouse3() {
            name = "House3";

            try {
                image = ImageIO.read(getClass().getResourceAsStream("/items/house3.png"));

            } catch (IOException e) {
                e.printStackTrace();

            }

        }
}
