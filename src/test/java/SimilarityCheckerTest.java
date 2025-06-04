import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimilarityCheckerTest {

    @Test
    public void testSameLengthStrings_ShouldReturn60() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "hello";
        String str2 = "world";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 공통 알파벳 2개(l,o), 전체 알파벳 7개(h,e,l,o,w,r,d) 
        // (2/7) * 40 = 11.43... ≈ 11점
        // 총점: 71점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(71, result);
    }
    
    @Test
    public void testSameLengthStrings_WithDifferentContent_ShouldReturn60() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "xyz";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 0점 (공통 알파벳 없음)
        // 총점: 60점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(60, result);
    }
    
    @Test
    public void testIdenticalStrings_ShouldReturn60() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "test";
        String str2 = "test";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 40점 (같은 알파벳)
        // 총점: 100점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(100, result);
    }
    
    @Test
    public void testDoubleLengthDifference_ShouldReturn0() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ab";
        String str2 = "abcd";
        // 길이 점수: 0점 (2배 차이)
        // 알파벳 점수: 공통 알파벳 2개(a,b), 전체 알파벳 4개(a,b,c,d)
        // (2/4) * 40 = 20점
        // 총점: 20점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(20, result);
    }
    
    @Test
    public void testMoreThanDoubleLengthDifference_ShouldReturn0() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "a";
        String str2 = "abc";
        // 길이 점수: 0점 (3배 차이)
        // 알파벳 점수: 공통 알파벳 1개(a), 전체 알파벳 3개(a,b,c)
        // (1/3) * 40 = 13.33... ≈ 13점
        // 총점: 13점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(13, result);
    }
    
    @Test
    public void testPartialScore_SmallDifference() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "abcd";
        // 길이 점수: (1 - 1/4) * 60 = 45점
        // 알파벳 점수: 공통 알파벳 3개(a,b,c), 전체 알파벳 4개(a,b,c,d)
        // (3/4) * 40 = 30점
        // 총점: 75점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(75, result);
    }
    
    @Test
    public void testPartialScore_MediumDifference() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ab";
        String str2 = "abcde";
        // 길이 점수: 0점 (2.5배 차이, 2배 이상)
        // 알파벳 점수: 공통 알파벳 2개(a,b), 전체 알파벳 5개(a,b,c,d,e)
        // (2/5) * 40 = 16점
        // 총점: 16점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(16, result);
    }
    
    @Test
    public void testPartialScore_OrderDoesNotMatter() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abcde";
        String str2 = "ab";
        // 길이 점수: 0점 (2.5배 차이, 2배 이상)
        // 알파벳 점수: 공통 알파벳 2개(a,b), 전체 알파벳 5개(a,b,c,d,e)
        // (2/5) * 40 = 16점
        // 총점: 16점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(16, result);
    }
    
    @Test
    public void testEmptyStrings_ShouldReturn100() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 40점 (둘 다 알파벳 없음, 동일한 상태)
        // 총점: 100점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(100, result);
    }
    
    @Test
    public void testOneEmptyString_ShouldReturn0() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "a";
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(0, result);
    }
    
    @Test
    public void testBoundaryCase_ExactlyDoubleLength() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "a";
        String str2 = "ab";
        // 길이 점수: 0점 (2배 차이)
        // 알파벳 점수: 공통 알파벳 1개(a), 전체 알파벳 2개(a,b)
        // (1/2) * 40 = 20점
        // 총점: 20점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(20, result);
    }
    
    @Test
    public void testBoundaryCase_JustUnderDoubleLength() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "abcdef";
        // 길이 점수: 0점 (2배 차이)
        // 알파벳 점수: 공통 알파벳 3개(a,b,c), 전체 알파벳 6개(a,b,c,d,e,f)
        // (3/6) * 40 = 20점
        // 총점: 20점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(20, result);
    }
    
    @Test
    public void testNullStrings_ShouldThrowException() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            checker.calculateSimilarity(null, "test");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            checker.calculateSimilarity("test", null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            checker.calculateSimilarity(null, null);
        });
    }
    
    // ============ 두 번째 조건: 알파벳 검사 테스트 ============
    
    @Test
    public void testSameAlphabets_ShouldReturnMaxAlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "abc";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 40점 (같은 알파벳)
        // 총점: 100점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(100, result);
    }
    
    @Test
    public void testSameAlphabetsDifferentOrder_ShouldReturnMaxAlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "bca";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 40점 (같은 알파벳 종류)
        // 총점: 100점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(100, result);
    }
    
    @Test
    public void testCompletelyDifferentAlphabets_ShouldReturn0AlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "xyz";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 0점 (완전히 다른 알파벳)
        // 총점: 60점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(60, result);
    }
    
    @Test
    public void testPartialAlphabetMatch_ShouldReturnPartialAlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "axy";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 공통 알파벳 1개(a), 전체 사용된 알파벳 5개(a,b,c,x,y)
        // (1/5) * 40 = 8점
        // 총점: 68점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(68, result);
    }
    
    @Test
    public void testPartialAlphabetMatch_WithDuplicates() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "aab";
        String str2 = "aac";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 공통 알파벳 1개(a), 전체 사용된 알파벳 3개(a,b,c)
        // (1/3) * 40 = 13.33... ≈ 13점
        // 총점: 73점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(73, result);
    }
    
    @Test
    public void testAlphabetScore_WithLengthDifference() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ab";
        String str2 = "abc";
        // 길이 점수: (1 - 1/3) * 60 = 40점
        // 알파벳 점수: 공통 알파벳 2개(a,b), 전체 사용된 알파벳 3개(a,b,c)
        // (2/3) * 40 = 26.67... ≈ 27점
        // 총점: 67점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(67, result);
    }
    
    @Test
    public void testAlphabetScore_WithZeroLengthScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "a";
        String str2 = "abc";
        // 길이 점수: 0점 (3배 차이)
        // 알파벳 점수: 공통 알파벳 1개(a), 전체 사용된 알파벳 3개(a,b,c)
        // (1/3) * 40 = 13.33... ≈ 13점
        // 총점: 13점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(13, result);
    }
    
    @Test
    public void testEmptyStrings_AlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 40점 (둘 다 알파벳 없음, 동일한 상태)
        // 총점: 100점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(100, result);
    }
    
    @Test
    public void testOneEmptyString_AlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "a";
        // 길이 점수: 0점 (무한대 배 차이)
        // 알파벳 점수: 0점 (공통 알파벳 없음)
        // 총점: 0점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(0, result);
    }
    
    @Test
    public void testCaseInsensitive_AlphabetScore() {
        // Given
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ABC";
        String str2 = "abc";
        // 길이 점수: 60점 (같은 길이)
        // 알파벳 점수: 40점 (대소문자 구분 없이 같은 알파벳)
        // 총점: 100점
        
        // When
        int result = checker.calculateSimilarity(str1, str2);
        
        // Then
        assertEquals(100, result);
    }
}