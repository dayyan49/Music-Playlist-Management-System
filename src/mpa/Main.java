package mpa;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    private static void play(LinkedList<Song> playList) {

        if (playList.isEmpty()) {
            System.out.println("This playlist has no song");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> listIterator = playList.listIterator();

        System.out.println("Now playing " + listIterator.next());
        printMenu();

        while (!quit) {
            try {
                int action = sc.nextInt();
                sc.nextLine();

                switch (action) {

                    case 0:
                        System.out.println("Playlist complete");
                        quit = true;
                        break;

                    case 1: // next song
                        if (!forward) {
                            if (listIterator.hasNext()) {
                                listIterator.next();
                            }
                            forward = true;
                        }

                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else {
                            System.out.println("Reached end of playlist");
                            forward = false;
                        }
                        break;

                    case 2: // previous song
                        if (forward) {
                            if (listIterator.hasPrevious()) {
                                listIterator.previous();
                            }
                            forward = false;
                        }

                        if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        } else {
                            System.out.println("You are at the first song");
                        }
                        break;

                    case 3: // replay song
                        if (forward) {
                            if (listIterator.hasPrevious()) {
                                System.out.println("Replaying " + listIterator.previous());
                                forward = false;
                            } else {
                                System.out.println("At start of playlist");
                            }
                        } else {
                            if (listIterator.hasNext()) {
                                System.out.println("Replaying " + listIterator.next());
                                forward = true;
                            } else {
                                System.out.println("At end of playlist");
                            }
                        }
                        break;

                    case 4:
                        printList(playList);
                        break;

                    case 5:
                        printMenu();
                        break;

                    case 6: // delete current song
                        if (!playList.isEmpty()) {
                            listIterator.remove();

                            if (listIterator.hasNext()) {
                                System.out.println("Now playing " + listIterator.next());
                            } else if (listIterator.hasPrevious()) {
                                System.out.println("Now playing " + listIterator.previous());
                            } else {
                                System.out.println("Playlist is now empty");
                            }
                        }
                        break;

                    default:
                        System.out.println("Invalid option");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                sc.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                Available options:
                0 - Quit
                1 - Play next song
                2 - Play previous song
                3 - Replay current song
                4 - List all songs
                5 - Print menu
                6 - Delete current song
                """);
    }

    private static void printList(LinkedList<Song> playList) {
        System.out.println("----------------");
        for (Song song : playList) {
            System.out.println(song);
        }
        System.out.println("----------------");
    }

    public static void main(String[] args) {

        Album album = new Album("Album1", "Green Day");
        album.addSong("Boulevard of Broken Dreams", 4.5);
        album.addSong("Wake Me Up When September Ends", 4.0);
        album.addSong("Good Riddance", 3.5);
        albums.add(album);

        album = new Album("Album2", "Imagine Dragons");
        album.addSong("Monster", 4.5);
        album.addSong("Thunder", 3.0);
        album.addSong("Believer", 4.0);
        albums.add(album);

        LinkedList<Song> playList1 = new LinkedList<>();
        albums.get(0).addToPlaylist("Boulevard of Broken Dreams", playList1);
        albums.get(0).addToPlaylist("Wake Me Up When September Ends", playList1);
        albums.get(1).addToPlaylist("Monster", playList1);
        albums.get(1).addToPlaylist("Thunder", playList1);

        play(playList1);
    }
}
