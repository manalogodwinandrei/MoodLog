package TestTypes;

import java.util.Scanner;

public class WorkTest extends BaseTest {
    public int conductTest(Scanner sc, StringBuilder answers) {
        String[] q = {
            "I feel overwhelmed by my workload or responsibilities.",
            "I have trouble concentrating on tasks due to work stress.",
            "I feel emotionally exhausted at the end of the workday.",
            "I experience physical symptoms due to work stress.",
            "I have difficulty balancing work and personal life.",
            "I feel underappreciated by colleagues or superiors.",
            "I often worry about work-related issues outside of work hours.",
            "I experience frustration because of workplace conflicts.",
            "I feel a lack of motivation or job satisfaction.",
            "I have considered quitting due to work stress."
        };
        return askQuestions(sc, q, answers);
    }
}
