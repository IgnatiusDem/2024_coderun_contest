package demignatius.coderun.ballsandbaskets;

class Segment implements Comparable<Segment> {
    public Integer l;
    public Integer r;

    public Segment(int l, int r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public int compareTo(Segment obj) {
        int result = this.l.compareTo(obj.l);
        if (result == 0) {
            result = this.r.compareTo(obj.r);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return l.hashCode() + r.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Segment guest = (Segment) obj;
        return this.l.equals(guest.l) && this.r.equals(guest.r);
    }
}
