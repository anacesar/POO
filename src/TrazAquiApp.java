import controller.TrazAquiController;
import exceptions.EmailJaExisteException;
import model.TrazAquiModel;

public class TrazAquiApp {

    public static void main(String[] args) throws EmailJaExisteException {
        TrazAquiModel model = new TrazAquiModel();
        TrazAquiController controller = new TrazAquiController(model);

        controller.run();
    }
}
