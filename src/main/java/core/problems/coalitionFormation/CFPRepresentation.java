package core.problems.coalitionFormation;

/*public class CFPRepresentation {

    ArrayList<Coalition> coalitions;

    public CFPRepresentation( ArrayList<Coalition>  coalitions) {
        this.coalitions = coalitions;
    }

    public CFPRepresentation( Coalition coalition) {
        this.coalitions = new ArrayList<>();
        coalitions.add(coalition);
    }

    @Override
    public Representation clone() {
        ArrayList<Coalition> newList = new ArrayList<>(coalitions.size());

        for (int i = 0; i < coalitions.size(); i++) {
            newList.add(coalitions.get(i).clone());
        }
        return new CFPRepresentation(newList);
    }

    @Override
    public boolean equals(Representation other) {
        CFPRepresentation otherCFP = (CFPRepresentation) other;

        if (coalitions.size()!= otherCFP.coalitions.size())
            return false;

        for (int i = 0; i < coalitions.size(); i++) {
            if (!coalitions.get(i).equals(otherCFP.getCoalition(i)) )
                return false;
        }

        return true;
    }

    @Override
    public double distanceTo(Representation r) {
        return 0;
    }

    public Coalition getCoalition(int index) {
        return coalitions.get(index);
    }

    public String toString(){
        String st = "";
        for(int i = 0 ; i<coalitions.size() ; i++){
            st += "Coalition " + (i+1) + " " + coalitions.get(i);
        }
        return st;
    }

    public int getCoalitionCount() {
        return coalitions.size();
    }
} */
