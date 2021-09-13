import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PhoneBookManagement phoneBookManagement = new PhoneBookManagement();
        int choice;
        do {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    phoneBookManagement.showAll();
                    break;
                }
                case 2: {
                    PhoneBook phoneBook = inputNewPhoneBook();
                    phoneBookManagement.addNew(phoneBook);
                    break;
                }
                case 3: {
                    updatePhoneBook(phoneBookManagement);
                    break;
                }
                case 4: {
                    removePhoneBook(phoneBookManagement);
                    break;
                }
                case 5: {
                    findNumberPhoneInfo(phoneBookManagement);
                    break;
                }
                case 6: {
                    phoneBookManagement.readContactFromFile();
                    break;
                }
                case 7: {
                    phoneBookManagement.writeContactToFile();
                    break;
                }
                case 8:{
                    System.exit(0);
                    break;
                }
            }
        } while (choice != 8);
    }

    private static void removePhoneBook(PhoneBookManagement phoneBookManagement) {
        System.out.println("Nhập số điện thoại cần xóa");
        scanner.nextLine();
        String phoneNumber = scanner.nextLine();
        int index = phoneBookManagement.findByPhoneNumber(phoneNumber);
        if (phoneNumber.equals("")) {
            return;
        } else {
            if (index != -1) {
                System.out.println("Bạn có chắc chắn muốn xóa ?(Nhấn Y để xóa)");
                String choice1 = scanner.nextLine();
                switch (choice1) {
                    case "y":
                    case "Y": {
                        phoneBookManagement.removePhoneBook(index);
                        System.out.println("Đã xóa thành công");
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } else {
                System.err.println("Không tìm thấy số điện thoại này trong danh bạ");
            }
        }
    }

    private static void updatePhoneBook(PhoneBookManagement phoneBookManagement) {
        System.out.println("Nhập số điện thoại cần chỉnh sửa");
        String phoneNumber = scanner.nextLine();
        int index = phoneBookManagement.findByPhoneNumber(phoneNumber);
        if (phoneNumber.equals("")) {
            return;
        } else {
            if (index != -1) {
                PhoneBook phoneBook = inputNewPhoneBook();
                phoneBookManagement.updatePhoneBook(index, phoneBook);
            } else {
                System.err.println("Không tìm thấy số điện thoại này trong danh bạ");
            }
        }
    }

    private static PhoneBook inputNewPhoneBook() {
        Matcher matcherEmail;
        Matcher matcherPhonerNumber;
        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại:");
            scanner.nextLine();
            phoneNumber = scanner.nextLine();
            String regex = "^[0-9\\-\\+]{9,15}$";
            Pattern pattern = Pattern.compile(regex);
            matcherPhonerNumber = pattern.matcher(phoneNumber);
            if (!matcherPhonerNumber.matches()) {
                System.err.println("Sai định dạng số điện thoại");
            }
        } while (!matcherPhonerNumber.matches());
        System.out.println("Nhập nhóm");
        String group = scanner.nextLine();
        System.out.println("Nhập họ tên");
        String name = scanner.nextLine();
        System.out.println("Nhập giới tính");
        String sex = scanner.nextLine();
        System.out.println("Nhập địa chỉ");
        String address = scanner.nextLine();
        String email;
        do {
            System.out.println("Nhập email");
            email = scanner.nextLine();
            String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            Pattern pattern = Pattern.compile(regex);
            matcherEmail = pattern.matcher(email);
            if (!matcherEmail.matches()) {
                System.err.println("Sai định dạng email");
            }
        } while (!matcherEmail.matches());
        return new PhoneBook(phoneNumber, group, name, sex, address, email);
    }

    private static void menu() {
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi từ file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng");
    }

    private static void findNumberPhoneInfo(PhoneBookManagement phoneBookManagement) {
        System.out.println("Nhập tên hoặc số điện thoại : ");
        scanner.nextLine();
        String stringFind = scanner.nextLine();
        boolean checkFind = checkFind(stringFind,phoneBookManagement);
        if (checkFind) {
            for (PhoneBook phoneBook : phoneBookManagement.getPhoneBookList()) {
                System.out.println(phoneBook);
            }
        }else {
            System.out.println("KHÔNG TÌM THẤY THÔNG TIN ĐÃ NHẬP");
        }
    }

    private static boolean checkFind(String stringFind, PhoneBookManagement phoneBookManagement) {
        for (PhoneBook phoneBook : phoneBookManagement.getPhoneBookList()) {
            if (!stringFind.contains(phoneBook.getPhoneNumber()) ||
                    !stringFind.contains(phoneBook.getName())) {
                return true;
            }
        }
        return false;
    }
}
