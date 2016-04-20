package model;

public class Issue {
    private int id;
    private String sum;
    private String description;
    private MantisProject project;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSum() {
        return sum;
    }

    public Issue withSum(String sum) {
        this.sum = sum;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public MantisProject getProject() {
        return project;
    }

    public Issue withProject(MantisProject project) {
        this.project = project;
        return this;
    }
}
