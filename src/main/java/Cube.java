public class Cube {
    Integer height;
    Integer width;
    Integer length;

    public Integer countVolume(Integer height, Integer width, Integer length) {
        Integer volume = height * width * length;
        return volume;
    }
}
