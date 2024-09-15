package rendering;
public class RendererFactory {
    public Renderer build(String renderType) {
        switch (renderType){
            case "console":
                return new ConsoleRenderer();
            case "none":
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
