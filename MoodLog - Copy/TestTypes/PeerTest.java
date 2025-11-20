package TestTypes;

import java.util.Scanner;

public class PeerTest extends BaseTest {
    public int conductTest(Scanner sc, StringBuilder answers) {
        String[] q = {
            "I feel anxious or stressed about social interactions.",
            "I worry about being judged by friends or classmates.",
            "I feel left out in social situations.",
            "I struggle to express my true feelings around peers.",
            "I find it difficult to resolve conflicts with friends.",
            "I feel pressure to fit in with my peer group.",
            "I overthink conversations after they happen.",
            "I feel emotionally drained after spending time with peers.",
            "I avoid social situations due to fear of rejection.",
            "I have considered distancing myself from certain friendships."
        };
        return askQuestions(sc, q, answers);
    }
}

