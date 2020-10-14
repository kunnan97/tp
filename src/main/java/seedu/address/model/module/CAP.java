package seedu.address.model.module;

public enum CAP {
    A_PLUS("A+",5.0),
    A("A",5.0),
    A_MINUS("A-",4.5),
    B_PLUS("B+",4.0),
    B("B",3.5),
    B_MINUS("B-",3.0),
    C_PLUS("C+",2.5),
    C("C",2.0),
    D_PLUS("D+",1.5),
    D("D",1.0),
    F("F",0.0);

    private final String gradeString;
    private final double gradePoint;
    
    CAP(String gradeString, double gradePoint) {
        this.gradeString = gradeString;
        this.gradePoint = gradePoint;
    }

    public String getGradeString() {
        return gradeString;
    }

    public double getGradePoint() {
        return gradePoint;
    }
}
