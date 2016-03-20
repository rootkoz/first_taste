package addressbook.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String notes;
    private int id;


    public ContactData(String name, String lastName, String nickname, String company, String notes) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickname;
        this.company = company;
        this.notes = notes;
    }

    public ContactData(int id, String name, String lastName, String nickName, String company, String notes) {
        this.id = id;
        this.nickName = nickName;
        this.company = company;
        this.notes = notes;
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompany() {
        return company;
    }

    public String getNotes() {
        return notes;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

}

