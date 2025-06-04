import java.util.HashSet;
import java.util.Set;

public class SimilarityChecker {
    
    private static final int SAME_LENGTH_SCORE = 60;
    private static final int SAME_ALPHABET_SCORE = 40;
    private static final int MIN_SCORE = 0;
    private static final double DOUBLE_LENGTH_RATIO = 2.0;
    
    public int calculateSimilarity(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("입력 문자열은 null일 수 없습니다.");
        }
        
        int lengthScore = calculateLengthScore(str1, str2);
        int alphabetScore = calculateAlphabetScore(str1, str2);
        
        return lengthScore + alphabetScore;
    }
    
    private int calculateLengthScore(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        
        if (length1 == length2) {
            return SAME_LENGTH_SCORE;
        }
        
        int longerLength = Math.max(length1, length2);
        int shorterLength = Math.min(length1, length2);
        
        if (shorterLength == 0) {
            return MIN_SCORE;
        }
        
        if (longerLength >= shorterLength * DOUBLE_LENGTH_RATIO) {
            return MIN_SCORE;
        }
        
        int gap = longerLength - shorterLength;
        double ratio = 1.0 - (double) gap / longerLength;
        int partialScore = (int) Math.round(ratio * SAME_LENGTH_SCORE);
        
        return partialScore;
    }
    
    private int calculateAlphabetScore(String str1, String str2) {
        Set<Character> alphabets1 = getUniqueAlphabets(str1);
        Set<Character> alphabets2 = getUniqueAlphabets(str2);
        
        if (alphabets1.isEmpty() && alphabets2.isEmpty()) {
            return SAME_ALPHABET_SCORE;
        }
        
        if (alphabets1.isEmpty() || alphabets2.isEmpty()) {
            return MIN_SCORE;
        }
        
        Set<Character> commonAlphabets = new HashSet<>(alphabets1);
        commonAlphabets.retainAll(alphabets2);
        
        Set<Character> allAlphabets = new HashSet<>(alphabets1);
        allAlphabets.addAll(alphabets2);
        
        if (alphabets1.equals(alphabets2)) {
            return SAME_ALPHABET_SCORE;
        }
        
        if (commonAlphabets.isEmpty()) {
            return MIN_SCORE;
        }
        
        double ratio = (double) commonAlphabets.size() / allAlphabets.size();
        int partialScore = (int) Math.round(ratio * SAME_ALPHABET_SCORE);
        
        return partialScore;
    }
    
    private Set<Character> getUniqueAlphabets(String str) {
        Set<Character> alphabets = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                alphabets.add(Character.toLowerCase(c));
            }
        }
        return alphabets;
    }
}