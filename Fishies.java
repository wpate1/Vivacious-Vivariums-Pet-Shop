import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fishies extends Animals {

    private int collarNumber;

    public Fishies(String name) {
        super(name);
    }

    public Fishies(String[] data) throws ParseException{
        super((data[1]), Integer.parseInt(data[3]), (data[4]));
        collarNumber = Integer.parseInt(data[5]);
    }

    public int getCheckNumber() {
        return collarNumber;
    }

    public void writeCheck() {getCheckNumber();}

    @Override
    public void updateAccount() { writeCheck();}

    public String toString() {
        return "Fishy " +  "\n" + super.toString();
    }
    @Override
    public String toText() {
        String animalInfo = "Fish \t" + name + "\t" +
                (new SimpleDateFormat("yyyy-MM-dd").format(date));
        return animalInfo;
    }
}
