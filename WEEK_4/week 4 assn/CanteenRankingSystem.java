class CanteenRecord {

    String canteenCode;
    String canteenName;
    int trustScore;

    CanteenRecord(
            String canteenCode,
            String canteenName,
            int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    CanteenRecord(
            String canteenCode,
            String canteenName) {

        this(canteenCode, canteenName, 3);
    }

    int compareTo(CanteenRecord other) {

        // Higher trust score first
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        // Code comparison ignoring case
        int result =
            this.canteenCode.compareToIgnoreCase(
                other.canteenCode
            );

        if (result != 0)
            return result;

        // Shorter name first
        return Integer.compare(
            this.canteenName.length(),
            other.canteenName.length()
        );
    }
}

public class CanteenRankingSystem {

    static CanteenRecord[] rankCanteens(
            CanteenRecord[] canteens) {

        for (int i = 1; i < canteens.length; i++) {

            CanteenRecord key = canteens[i];

            int j = i - 1;

            while (j >= 0 &&
                   canteens[j].compareTo(key) > 0) {

                canteens[j + 1] = canteens[j];
                j--;
            }

            canteens[j + 1] = key;
        }

        return canteens;
    }

    public static void main(String[] args) {

        CanteenRecord[] canteens = {
            new CanteenRecord(
                "HB3-C",
                "Spice Junction",
                3
            ),

            new CanteenRecord(
                "hb1-c",
                "Grand Mess",
                5
            ),

            new CanteenRecord(
                "HB2-C",
                "Southern Treats"
            )
        };

        rankCanteens(canteens);

        for (CanteenRecord c : canteens) {
            System.out.println(c.canteenCode);
        }
    }
}