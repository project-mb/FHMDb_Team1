package at.ac.fhcampuswien.fhmdb.LogicLayer.State;

public class Unsorted extends State {
    public Unsorted(Sorter sorter) {
        super(sorter);
    }
    @Override
    public void onSort() {
        sorter.state = new AscSorted(sorter);
        sorter.sortAscending();
    }
    @Override
    public void onCurrent() {

    }
    @Override
    public String getName() {
        return "Unsorted";
    }
}
