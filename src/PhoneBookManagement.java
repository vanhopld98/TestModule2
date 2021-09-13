import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBookManagement {
    List<PhoneBook> phoneBookList = new ArrayList<>();

    public List<PhoneBook> getPhoneBookList() {
        return phoneBookList;
    }

    public void addNew(PhoneBook phoneBook) {
        phoneBookList.add(phoneBook);
    }

    public void removePhoneBook(int index) {
        phoneBookList.remove(index);

    }

    public void showAll() {
        int count = 0;
        for (PhoneBook phoneBook : phoneBookList) {
            System.out.println(phoneBook);
            count++;
            if (count == 5) {
                count = 0;
                System.out.println("Ấn Enter để xem tiếp");
                Main.scanner.nextLine();
            }
        }
    }

    public void updatePhoneBook(int index, PhoneBook phoneBook) {
        phoneBookList.set(index, phoneBook);
    }

    public int findByPhoneNumber(String phoneNumber) {
        int index = -1;
        for (int i = 0; i < phoneBookList.size(); i++) {
            if (phoneBookList.get(i).getPhoneNumber().equals(phoneNumber)) {
                index = i;
            }
        }
        return index;
    }

    public void readContactFromFile() {
        try {
            FileReader fileReader = new FileReader("contact.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while (true) {
                if (line == null) {
                    break;
                }
                line = bufferedReader.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeContactToFile() {
        try {
            FileWriter fileWriter = new FileWriter("contact.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (PhoneBook phoneBook : phoneBookList) {
                bufferedWriter.write(String.valueOf(phoneBook));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}