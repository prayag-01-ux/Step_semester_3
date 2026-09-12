class LibraryMember1 {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // No-argument constructor
    public LibraryMember1() {
        this(null, null);
    }

    // Name-only constructor
    public LibraryMember1(String name) {
        this(null, name);
    }

    // ID + name constructor
    public LibraryMember1(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    // Membership ID getter
    public String getMembershipId() {
        return membershipId;
    }

    // Write-once membership ID
    public void setMembershipId(String id) {

        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    // Name getter
    public String getName() {
        return name;
    }

    // Name setter
    public void setName(String name) {
        this.name = name;
    }

    // Boolean JavaBean getter
    public boolean isPremiumMember() {
        return premiumMember;
    }

    // Premium setter
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        if (answer == null) {
            this.securityAnswer = null;
        } else {
            // Deterministic one-way transformation
            this.securityAnswer = Integer.toHexString(answer.hashCode());
        }
    }
}