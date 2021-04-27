
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Dogs extends Animals {

    private int collarNumber;

    public Dogs(String name) {
        super(name);
    }

    public Dogs(String[] data) throws ParseException{
        super((data[1]), Integer.parseInt(data[3]), (data[4]));
        collarNumber = Integer.parseInt(data[5]);
    }

    public int getCollarNumber() {
        return collarNumber;
    }

    public void writeCheck() {getCollarNumber();}

    @Override
    public void updateAccount() { writeCheck();}

    public String toString() {
        return "Dog " +  "\n" + super.toString();
    }
    @Override
    public String toText() {
        String animalInfo = "Doggos\t" + name + "\t" +
                (new SimpleDateFormat("yyyy-MM-dd").format(date));
        return animalInfo;
    }
}

