import java.util.*;

interface ScoringRule {
    double calculate(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return idea * 0.50 + execution * 0.30 + presentation * 0.20;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;
    Team team;

    Project(String name, Team team) {
        this.name = name;
        this.team = team;
    }
}

class Judge {
    String name;

    Judge(String name) {
        this.name = name;
    }

    void score(Project project, double idea, double execution,
               double presentation) {
        project.team.hackathon.recordScore(
                project, idea, execution, presentation
        );
    }
}

class Team {
    String name;
    List<Student> members = new ArrayList<>();
    String track;
    Project project;

    Team(String name, String track) {
        this.name = name;
        this.track = track;
    }

    void addMember(Student student) {
        members.add(student);
    }

    boolean hasStudent(Student student) {
        return members.contains(student);
    }

    Hackathon hackathon;
}

class Hackathon {
    String name;
    String state = "Open";

    Map<String, Team> teams = new HashMap<>();
    Map<Project, Double> scores = new HashMap<>();

    Hackathon(String name) {
        this.name = name;
    }

    void registerTeam(Team team) {

        if (state.equals("Published")) {
            System.out.println("Registration failed: Hackathon is closed.");
            return;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println(
                    "Registration failed: A team must have 2 to 4 members."
            );
            return;
        }

        for (Team existing : teams.values()) {
            for (Student s : team.members) {
                if (existing.hasStudent(s)) {
                    System.out.println(
                            "Registration failed: Student already belongs to a team."
                    );
                    return;
                }
            }
        }

        team.hackathon = this;
        teams.put(team.name, team);

        System.out.println(
                "Team " + team.name + " registered (" +
                        team.members.size() + " members, " +
                        team.track + " track)."
        );
    }

    void submitProject(Team team, Project project) {

        if (team.project != null) {
            System.out.println("Submission failed: Team already submitted.");
            return;
        }

        team.project = project;

        System.out.println(
                "Project '" + project.name +
                        "' submitted by " + team.name + "."
        );
    }

    void startJudging() {
        state = "Judging";
    }

    void recordScore(Project project, double idea,
                     double execution, double presentation) {

        if (state.equals("Published")) {
            System.out.println(
                    "Rescore rejected: Results have already been published."
            );
            return;
        }

        ScoringRule rule;

        if (project.team.track.equals("Innovation")) {
            rule = new InnovationScoring();
        } else {
            rule = new OpenScoring();
        }

        double finalScore =
                rule.calculate(idea, execution, presentation);

        scores.put(project, finalScore);

        System.out.println(
                "Score recorded for '" + project.name + "'."
        );

        System.out.printf(
                "Final score: %.2f%n",
                finalScore
        );
    }

    void publishResults() {
        state = "Published";
        System.out.println("Results published.");
    }
}

public class Q1_CodeSprint {

    public static void main(String[] args) {

        Hackathon hackathon =
                new Hackathon("Code Sprint");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters =
                new Team("ByteBusters", "Innovation");

        byteBusters.addMember(asha);
        byteBusters.addMember(ravi);
        byteBusters.addMember(neha);

        hackathon.registerTeam(byteBusters);

        Team soloCoder =
                new Team("SoloCoder", "Open");

        soloCoder.addMember(kiran);

        hackathon.registerTeam(soloCoder);

        Project project =
                new Project("SmartAttend", byteBusters);

        hackathon.submitProject(byteBusters, project);

        hackathon.startJudging();

        Judge judge = new Judge("Judge 1");

        judge.score(project, 8, 7, 9);

        hackathon.publishResults();

        judge.score(project, 10, 7, 9);
    }
}