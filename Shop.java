
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Shop {
    private Animals[] accounts;
    private int count;

    public int writeAccounts(String filename) throws FileNotFoundException {
        int count = 0;
        File file = new File(filename);
        PrintWriter write = new PrintWriter(file);
        for (Animals account : accounts){
            write.println(account.toText());
            ++count;}
        write.close();
        return count;
    }

    public Shop(int cap) {
        accounts = new Animals [cap];
        count = 0;
    }

    public boolean add(Animals a) {
        if (contains(a)) return false;
        if (full()) doubleCapacity();
        accounts[count++] = a;
        return true;
    }

    public boolean remove(Animals a) {
        if (!contains(a)) return false;
        accounts[indexOf(a)] = accounts[--count];
        return true;
    }

    public boolean contains(Animals a) {
        return indexOf(a) != -1;
    }


    public Animals find(int acct) {
        for (int i = 0; i < count; i++)
            if (accounts[i].getAccountNumber() == acct) return accounts[i];
        return null;
    }

    private int indexOf(Animals a) {
        if (a == null) return -1;
        for (int i = 0; i < count; i++)
            if (accounts[i].equals(a)) return i;
        return -1;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i = 0; i < count; i++) {
            sb.append(accounts[i].toString());
            sb.append("\n_____Vivacious Vivariums Pet Shop_____\n");
        }
        return sb.toString();
    }

    public void sort() {
        for (int i = 1; i < count; i++) {
            Animals temp = accounts[i];
            int j;
            for (j = i - 1; j >= 0 && Shop.less(temp, accounts[j]); j--) {
                accounts[j + 1] = accounts[j];
            }
            accounts[j + 1] = temp;
        }
    }

    private void doubleCapacity() {
        Animals[] a = new Animals[accounts.length * 2];
        System.arraycopy(accounts, 0, a, 0, count);
        accounts = a;
    }

    private static boolean less(Animals temp, Animals j) {
        return temp.getAccountNumber() < j.getAccountNumber();
    }

    private boolean full() {
        return count == accounts.length;
    }


}
