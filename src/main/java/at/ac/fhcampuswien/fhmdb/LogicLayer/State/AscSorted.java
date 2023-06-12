package at.ac.fhcampuswien.fhmdb.LogicLayer.State;

public class AscSorted extends State {
    public AscSorted(Sorter sorter) {
        super(sorter);
    }
    @Override
    public void onSort() {
        sorter.state = new DescSorted(sorter);
        sorter.sortDescending();
    }
    @Override
    public void onCurrent() {
        sorter.sortAscending();
    }
    @Override
    public String getName() {
        return "AscSorted";
    }
}
