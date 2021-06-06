import java.time.LocalDate
import java.time.format.DateTimeFormatter

DeadLine op = new DeadLine()

task("Task_2_3_1") {

    points 6
    deadLine "12-12-2021"
    op.setReference("12-12-2021")
    op.shiftDeadline()
}
task("Task_2_1_1") {

    points 6
    deadLine op.getRef()
    op.shiftDeadline()

}
task("Task_1_1") {

    points 6
    deadLine "01-10-2020"
}
task("Task_2_1") {

    points 6
    deadLine op.getRef()
    op.shiftDeadline()

}

class DeadLine {

    String reference

    void shiftDeadline() {

        String pattern = "dd-MM-yyyy"

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        LocalDate ref = LocalDate.parse(reference, df)
        ref = ref.plusDays(21)

        this.reference = ref.format(df)

    }

    String getRef() {
        return this.reference
    }

    void setReference(String date) {
        this.reference = date
    }
}