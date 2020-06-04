import controller.TrazAquiController;
import model.TrazAquiModel;

public class TrazAquiApp {

    public static void main(String[] args) {
        TrazAquiModel model = new TrazAquiModel();
        TrazAquiController controller = new TrazAquiController(model);

        controller.run();
    }
}
