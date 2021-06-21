package meetingrooms;

import java.util.Scanner;

public class MeetingroomsController {
    private Scanner scanner = new Scanner(System.in);

    private MeetingroomsService meetingroomsService;
    public static void main(String[] args) {
        new MeetingroomsController().start();
    }

    public void start() {
        System.out.println("A. Memoria\nB. MariaDB\nChoose one:");
        String chosen = scanner.nextLine().toUpperCase();
        switch (chosen) {
            case "A":
                meetingroomsService = new MeetingroomsService(new InMemoryMeetingRoomsRepository());
               break;
            case "B":
                meetingroomsService = new MeetingroomsService(new MariaDbMeetingRoomsRepository());
                break;
            default:
               throw new IllegalArgumentException("Error!");
        }
        menu();
    }

    public void menu() {
        System.out.println("0. Tárgyaló rögzítése\n1. Tárgyalók névsorrendben\n" +
                "2. Tárgyalók név alapján visszafele sorrendben\n3. Minden második tárgyaló" +
                "4. Területek\n5. Keresés pontos név alapján\n6. Keresés névtöredék alapján\n" +
                "7. Keresés terület alapján");
        String chosen = scanner.nextLine();
        try {
            int i = Integer.parseInt(chosen);
            if (i < 0 || i > 7) {
                throw new NumberFormatException();
            }
            switch (i) {
                case 0:
                    subMenu();
                    break;
                case 1:
                    System.out.println(meetingroomsService.listABC());
                    break;
                case 2:
                    System.out.println(meetingroomsService.reservedListABC());
                    break;
                case 3:
                    meetingroomsService.evenList();
                    break;
                case 4:
                    meetingroomsService.areaList();
                    break;
                case 5:
                    meetingroomsService.search();
                    break;
                case 6:
                    meetingroomsService.partialSearch();
                    break;
                case 7:
                    meetingroomsService.areaSearch();
                    break;
                default:
                    System.out.println("Error!");
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Only number between 0 and 7.");
        }
    }

    public void subMenu() {
        int width;
        int length;
        System.out.println("Tárgyaló neve:");
        String name = scanner.nextLine();
        System.out.println("Tárgyaló szélessége méterben:");
        try {
            width = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Tárgyaló hosszúsága méterben:");
            length = scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Only number!");
        }
        meetingroomsService.create(name, width, length);
    }
}
