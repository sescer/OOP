package nsu.fit.oop.boryapatrushev.task_2_4_1.html

import groovy.xml.MarkupBuilder
import javafx.util.Pair
import nsu.fit.oop.boryapatrushev.task_2_4_1.stats.StatsGenerator

/**
 * Class that generates html table like in example
 * (see <a href="https://github.com/sescer/OOP/blob/master/task_2_semester.pdf">
 *     https://github.com/sescer/OOP/blob/master/task_2_semester.pdf</a>)
 */
class HtmlGenerator {

    File output
    FileWriter writer
    MarkupBuilder html
    StatsGenerator statsGenerator

    def template = ["", "Build", "Style", "Doc", "Tests", "Credit", "Add", "Total", "", "", ""]

    /**
     * Constructor
     * @param statsGenerator stats
     * @param filePath path to save html file
     */
    HtmlGenerator(StatsGenerator statsGenerator, String filePath) {
        this.statsGenerator = statsGenerator
        output = new File(filePath)
        writer = new FileWriter(output)
        html = new MarkupBuilder(writer)
    }

    /**
     * Generate html tables
     * @return html file
     */
    File generateHtml() {

        generateAttendance()

        html.html {
            br ""
        }

        for (Map.Entry<String, ArrayList<HashMap<String, String>>> entry : statsGenerator.stats.entrySet()) {
            String key = entry.getKey()
            Object value = entry.getValue()

            if (!value.isEmpty())
                generateTable(key, value)

            html.html {
                br ""
            }
        }

        writer.close()
        return output
    }

    /**
     * Generate attendance table
     */
    void generateAttendance() {
        html.table(class: "tg") {

            style(type: "text/css", '''
            .tg  {border-collapse:collapse;border-spacing:0;}
            .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
            .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
            ''')

            tr {
                td class: "tg-0pky", "Student"

                for (Pair<String, String> comm : statsGenerator.commits.get(0).value) {
                    td class: "tg-0pky", comm.key
                }
            }

            for (Pair<String, ArrayList<Pair<String, String>>> commit : statsGenerator.commits) {
                tr {
                    td class: "tg-0pky", commit.key

                    for (Pair<String, String> comm : commit.value) {

                        if (comm.value != 0)
                            td class: "tg-0pky", "+ (" + comm.value + ")"
                        else
                            td class: "tg-0pky", "-"
                    }
                }
            }
        }
    }

    /**
     * Generate task table
     * @param name task name
     * @param data stats
     */
    void generateTable(String name, ArrayList<HashMap<String, String>> data) {

        html.table(class: "tg") {

            style(type: "text/css", '''
            .tg  {border-collapse:collapse;border-spacing:0;}
            .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
            .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
            ''')

            tr {
                td class: "tg-0pky", ""
                td colspan: "7", class: "tg-c3ow", name
                td class: "tg-0pky", "K-1"
                td class: "tg-0pky", "K-2"
                td class: "tg-0pky", "Total"
            }

            tr {
                for (String temp : template) {
                    td class: "tg-0pky", temp
                }
            }

            for (HashMap<String, String> map : data) {
                tr {
                    td class: "tg-0pky", map.get("name")
                    td class: "tg-0pky", map.get("build")
                    td class: "tg-0pky", map.get("style")
                    td class: "tg-0pky", map.get("doc")
                    td class: "tg-0pky", map.get("tests")
                    td class: "tg-0pky", map.get("credit")
                    td class: "tg-0pky", map.get("add")
                    td class: "tg-0pky", map.get("total")

                    td class: "tg-0pky", statsGenerator.checkPoints.get(map.get("name")).get("total") +
                            "/" + statsGenerator.checkPoints.get(map.get("name")).get("K-1")

                    td class: "tg-0pky", statsGenerator.checkPoints.get(map.get("name")).get("total") +
                            "/" + statsGenerator.checkPoints.get(map.get("name")).get("K-2")

                    td class: "tg-0pky", statsGenerator.checkPoints.get(map.get("name")).get("total") +
                            "/" + statsGenerator.checkPoints.get(map.get("name")).get("Total")
                }
            }
        }
    }
}