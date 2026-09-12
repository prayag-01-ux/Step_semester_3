class BrokenLibraryMember {

    // WRONG: These are shared by all members.
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(
        String name,
        String memberId,
        int booksIssued
    ) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {

    // Instance fields:
    // each member must have separate values.
    String name;
    String memberId;
    int booksIssued;

    // Static fields:
    // these belong to the library as a whole.
    static String libraryName = "SRM Library";
    static int memberCount = 0;

    LibraryMember(
        String name,
        int booksIssued
    ) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId =
            "LM-" +
            (1000 + memberCount);
    }

    void printMemberCard() {

        System.out.println(
            name + " | " + memberId
        );
    }

    static void printTotalMembers() {

        System.out.println(
            "Total members: " +
            memberCount
        );
    }
}

public class Main2 {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
            new BrokenLibraryMember(
                "Aditi",
                "LM-1001",
                2
            );

        BrokenLibraryMember member2 =
            new BrokenLibraryMember(
                "Rohan",
                "LM-1002",
                3
            );

        System.out.println(
            BrokenLibraryMember.name
        );

        System.out.println(
            BrokenLibraryMember.name
        );

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember m1 =
            new LibraryMember(
                "Aditi",
                2
            );

        LibraryMember m2 =
            new LibraryMember(
                "Rohan",
                3
            );

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}