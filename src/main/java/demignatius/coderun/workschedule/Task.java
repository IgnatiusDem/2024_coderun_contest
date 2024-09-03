package demignatius.coderun.workschedule;

class Task implements Comparable<Task> {
    public Integer day;
    public Integer stress;

    public Task(int day, int stress) {
        this.day = day;
        this.stress = stress;
    }

    @Override
    public int compareTo(Task obj) {
        return this.day.compareTo(obj.day);
    }

    @Override
    public int hashCode() {
        return day.hashCode() + stress.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Task guest = (Task) obj;
        return this.day.equals(guest.day)
                && this.stress.equals(guest.stress);
    }
}

