package at.ac.fhcampuswien.fhmdb.LogicLayer.model;

@FunctionalInterface
public interface ClickEventHandler<T> {
    void onCLick(T t);
}
