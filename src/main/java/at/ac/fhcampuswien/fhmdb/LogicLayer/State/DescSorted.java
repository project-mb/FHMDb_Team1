package at.ac.fhcampuswien.fhmdb.LogicLayer.State;

public class DescSorted extends State {
    public DescSorted(Sorter sorter) {
        super(sorter);
    }
    @Override
    public void onSort() {
        sorter.state = new AscSorted(sorter);
        sorter.sortAscending();
    }
    @Override
    public void onCurrent() {
        sorter.sortDescending();
    }
    @Override
    public String getName() {
        return "DescSort";
    }
}
