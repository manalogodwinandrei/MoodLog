package TestTypes;

import java.util.Scanner;

public class FamilyTest extends BaseTest {
    public int conductTest(Scanner sc, StringBuilder answers) {
        String[] q = {
            "I feel overwhelmed by family responsibilities.",
            "I find it difficult to communicate openly with family members.",
            "I often experience emotional distress due to family conflicts.",
            "I feel unsupported by my family.",
            "I feel pressure to meet family expectations.",
            "I struggle to set boundaries with family members.",
            "I feel anxious about not meeting family expectations.",
            "I experience physical symptoms due to family stress.",
            "I avoid spending time with family due to tension.",
            "I have considered distancing myself from family to protect my well-being."
        };
        return askQuestions(sc, q, answers);
    }
}
