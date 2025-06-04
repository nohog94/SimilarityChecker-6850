import java.util.HashSet;
import java.util.Set;

public class SimilarityChecker {
    
    private static final int SAME_LENGTH_SCORE = 60;
    private static final int SAME_ALPHABET_SCORE = 40;
    private static final int MIN_SCORE = 0;
    private static final double DOUBLE_LENGTH_RATIO = 2.0;
    
    /**
     * 두 문자열의 유사도를 계산합니다.
     * 
     * @param str1 첫 번째 문자열
     * @param str2 두 번째 문자열
     * @return 유사도 점수 (0-100점)
     * @throws IllegalArgumentException null 입력시 발생
     */
    public int calculateSimilarity(String str1, String str2) {
        // null 체크
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("입력 문자열은 null일 수 없습니다.");
        }
        
        // 첫 번째 조건: 길이 기반 점수
        int lengthScore = calculateLengthScore(str1, str2);
        
        // 두 번째 조건: 알파벳 기반 점수
        int alphabetScore = calculateAlphabetScore(str1, str2);
        
        return lengthScore + alphabetScore;
    }
    
    /**
     * 문자열 길이 기반 점수를 계산합니다.
     * 
     * @param str1 첫 번째 문자열
     * @param str2 두 번째 문자열
     * @return 길이 기반 점수 (0-60점)
     */
    private int calculateLengthScore(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        
        // 같은 길이인 경우 60점 반환
        if (length1 == length2) {
            return SAME_LENGTH_SCORE;
        }
        
        // 긴 문자열과 짧은 문자열 길이 구하기
        int longerLength = Math.max(length1, length2);
        int shorterLength = Math.min(length1, length2);
        
        // 빈 문자열 처리 (한쪽이 빈 문자열인 경우)
        if (shorterLength == 0) {
            return MIN_SCORE;
        }
        
        // 2배 이상 차이나는 경우 0점 반환
        if (longerLength >= shorterLength * DOUBLE_LENGTH_RATIO) {
            return MIN_SCORE;
        }
        
        // 부분 점수 계산
        int gap = longerLength - shorterLength;
        double ratio = 1.0 - (double) gap / longerLength;
        int partialScore = (int) Math.round(ratio * SAME_LENGTH_SCORE);
        
        return partialScore;
    }
    
    /**
     * 알파벳 기반 점수를 계산합니다.
     * 
     * @param str1 첫 번째 문자열
     * @param str2 두 번째 문자열
     * @return 알파벳 기반 점수 (0-40점)
     */
    private int calculateAlphabetScore(String str1, String str2) {
        // 각 문자열에서 사용된 고유한 알파벳 추출 (대소문자 구분 없이)
        Set<Character> alphabets1 = getUniqueAlphabets(str1);
        Set<Character> alphabets2 = getUniqueAlphabets(str2);
        
        // 빈 문자열 처리
        if (alphabets1.isEmpty() && alphabets2.isEmpty()) {
            return SAME_ALPHABET_SCORE; // 둘 다 알파벳이 없으면 동일한 상태로 간주
        }
        
        if (alphabets1.isEmpty() || alphabets2.isEmpty()) {
            return MIN_SCORE; // 한쪽만 알파벳이 없으면 0점
        }
        
        // 공통 알파벳 계산
        Set<Character> commonAlphabets = new HashSet<>(alphabets1);
        commonAlphabets.retainAll(alphabets2);
        
        // 전체 사용된 알파벳 계산
        Set<Character> allAlphabets = new HashSet<>(alphabets1);
        allAlphabets.addAll(alphabets2);
        
        // 모든 알파벳이 같은 경우
        if (alphabets1.equals(alphabets2)) {
            return SAME_ALPHABET_SCORE;
        }
        
        // 공통 알파벳이 없는 경우
        if (commonAlphabets.isEmpty()) {
            return MIN_SCORE;
        }
        
        // 부분 점수 계산: (공통 알파벳 수 / 전체 알파벳 수) * 40
        double ratio = (double) commonAlphabets.size() / allAlphabets.size();
        int partialScore = (int) Math.round(ratio * SAME_ALPHABET_SCORE);
        
        return partialScore;
    }
    
    /**
     * 문자열에서 고유한 알파벳을 추출합니다 (대소문자 구분 없이).
     * 
     * @param str 입력 문자열
     * @return 고유한 알파벳 집합
     */
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