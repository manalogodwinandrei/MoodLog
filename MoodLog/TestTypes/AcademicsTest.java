package TestTypes;

import java.util.Scanner;

public class AcademicsTest extends BaseTest {
    public int conductTest(Scanner sc, StringBuilder answers) {
        String[] q = {
            "I feel overwhelmed by the amount of schoolwork or deadlines.",
            "I experience anxiety before exams or assessments.",
            "I have trouble concentrating on my studies due to stress.",
            "I often feel physically exhausted due to academic pressure.",
            "I struggle to balance academics with personal life.",
            "I feel pressured to meet high academic expectations.",
            "I worry about grades and performance outside school hours.",
            "I feel unmotivated or burned out from studying.",
            "I have difficulty sleeping due to academic stress.",
            "I have considered skipping classes due to academic pressure."
        };
        return askQuestions(sc, q, answers);
    }
}
