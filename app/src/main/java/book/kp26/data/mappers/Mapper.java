package book.kp26.data.mappers;

public interface Mapper<I,O> {
    O map(I value);
}
