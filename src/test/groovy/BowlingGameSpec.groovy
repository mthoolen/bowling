import nl.codesquad.demo.bowling.Line
import spock.lang.Specification

class BowlingGameSpec extends Specification {

    def "a line with score of 0"(){
        when:
        def line = new Line("-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")

        then:
        line.score == 0
    }

    def "a line with a score of 1"() {
        when:
        def line = new Line("1,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == 1
    }

    def "a line with only a spare"() {
        when:
        def line = new Line("1,/|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == 10
    }


    def "a line with only a strike"() {
        when:
        def line = new Line("X|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == 10
    }

    def "a line with a score in every frame"() {
        when:
        def line = new Line("1,-|-,1|2,-|-,3|4,-|4,-|1,-|1,-|-,-|-,-")
        then:
        line.score == 1+1+2+3+4+4+1+1
    }

    def "a line with a spare in the first and a score in the next"() {
        when:
        def line = new Line("5,/|5,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == (10+5)+5
    }

    def "a line with a spare in each of the frames except the last"() {
        when:
        def line = new Line("5,/|5,/|4,/|2,/|1,/|-,/|9,/|8,/|5,/|-,-")
        then:
        line.score == (10+5)+(10+4)+(10+2)+(10+1)+(10+0)+(10+9)+(10+8)+(10+5)+(10+0)+0
    }

    def "a line with a strike in the first and a few pins in the next two frames"() {
        when:
        def line = new Line("X|5,-|4,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == (10+5+0)+5+4
    }

    def "a line with a strike in the first and a strike in the next two frames"() {
        when:
        def line = new Line("X|X|X|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == (10+10+10)+(10+10+0)+(10+0+0)
    }


    def "the last frame can contain a bonus roll"() {
        when:
        def line = new Line("-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|5,/,5")
        then:
        line.score == (10+5)
    }

    def "if the last frame has a strike, there are two bonus rolls"() {
        when:
        def line = new Line("-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-|X,X,-")
        then:
        line.score == (10+10)
    }

    def "a line with all strikes"() {
        when:
        def line = new Line("X|X|X|X|X|X|X|X|X|X,X,X")
        then:
        line.score == 300
    }

    def "a line with all spares"() {
        when:
        def line = new Line("5,/|5,/|5,/|5,/|5,/|5,/|5,/|5,/|5,/|5,/,5")
        then:
        line.score == 150
    }

    def "a line with a strike and then a spare"() {
        when:
        def line = new Line("X|5,/|-,-|-,-|-,-|-,-|-,-|-,-|-,-|-,-")
        then:
        line.score == (10+5+5)+(10+0)
    }





}
