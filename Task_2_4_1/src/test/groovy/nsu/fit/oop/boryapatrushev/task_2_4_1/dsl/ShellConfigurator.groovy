package nsu.fit.oop.boryapatrushev.task_2_4_1.dsl

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

class ShellConfigurator {

    static GroovyShell configureShell() {

        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.GroupsDSL", "group"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.TasksDSL", "task"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AdditionalPointsDSL", "add"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.CheckPointsDSL", "checkpoint"
        importCustomizer.addStaticImport "nsu.fit.oop.boryapatrushev.task_2_4_1.dsl.AttendanceDateDSL", "attendanceDate"

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(importCustomizer)

        GroovyShell groovyShell = new GroovyShell(configuration)

        return groovyShell
    }
}
