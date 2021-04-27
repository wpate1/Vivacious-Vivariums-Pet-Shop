
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Animals {
    protected static int accountsCreated;
    protected String name;
    protected int collarNumber = generateAcctNum();
    protected Date date = new Date();

    public Animals(String name, int ssNum, String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        this.name = name;
        this.collarNumber = ssNum;
        this.date = format.parse(date);
        accountsCreated++;
    }

    public Animals(String name) {
        this.name = name;
    }

    public Date getDate(){
        return date;
    }

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public static int getAccountsCreated() {
        return accountsCreated;
    }

    public abstract void updateAccount();


    public int getAccountNumber() {
        generateAcctNum();
        return this.collarNumber;
    }

    public String toString() {
        return " " + name + "\n Collar Number: " + collarNumber + "\n Arrival: " + date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animals )) return false;
        Animals that = (Animals ) o;
        return collarNumber == that.collarNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(collarNumber);
    }

    public int compareTo(Animals  that) {
        return this.collarNumber - that.collarNumber;
    }

    private int generateAcctNum() {
        return (int) (Math.random() * 900000000) + 100000000;
    }
    public abstract String toText();


}

