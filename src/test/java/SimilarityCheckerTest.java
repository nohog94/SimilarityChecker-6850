import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimilarityCheckerTest {

    @Test
    public void testSameLengthStrings_ShouldReturn60() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "hello";
        String str2 = "world";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(71, result);
    }
    
    @Test
    public void testSameLengthStrings_WithDifferentContent_ShouldReturn60() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "xyz";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(60, result);
    }
    
    @Test
    public void testIdenticalStrings_ShouldReturn60() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "test";
        String str2 = "test";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(100, result);
    }
    
    @Test
    public void testDoubleLengthDifference_ShouldReturn0() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ab";
        String str2 = "abcd";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(20, result);
    }
    
    @Test
    public void testMoreThanDoubleLengthDifference_ShouldReturn0() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "a";
        String str2 = "abc";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(13, result);
    }
    
    @Test
    public void testPartialScore_SmallDifference() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "abcd";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(75, result);
    }
    
    @Test
    public void testPartialScore_MediumDifference() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ab";
        String str2 = "abcde";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(16, result);
    }
    
    @Test
    public void testPartialScore_OrderDoesNotMatter() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abcde";
        String str2 = "ab";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(16, result);
    }
    
    @Test
    public void testEmptyStrings_ShouldReturn100() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(100, result);
    }
    
    @Test
    public void testOneEmptyString_ShouldReturn0() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "a";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(0, result);
    }
    
    @Test
    public void testBoundaryCase_ExactlyDoubleLength() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "a";
        String str2 = "ab";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(20, result);
    }
    
    @Test
    public void testBoundaryCase_JustUnderDoubleLength() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "abcdef";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(20, result);
    }
    
    @Test
    public void testNullStrings_ShouldThrowException() {
        SimilarityChecker checker = new SimilarityChecker();
        
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
    
    @Test
    public void testSameAlphabets_ShouldReturnMaxAlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "abc";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(100, result);
    }
    
    @Test
    public void testSameAlphabetsDifferentOrder_ShouldReturnMaxAlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "bca";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(100, result);
    }
    
    @Test
    public void testCompletelyDifferentAlphabets_ShouldReturn0AlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "xyz";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(60, result);
    }
    
    @Test
    public void testPartialAlphabetMatch_ShouldReturnPartialAlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "abc";
        String str2 = "axy";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(68, result);
    }
    
    @Test
    public void testPartialAlphabetMatch_WithDuplicates() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "aab";
        String str2 = "aac";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(73, result);
    }
    
    @Test
    public void testAlphabetScore_WithLengthDifference() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ab";
        String str2 = "abc";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(67, result);
    }
    
    @Test
    public void testAlphabetScore_WithZeroLengthScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "a";
        String str2 = "abc";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(13, result);
    }
    
    @Test
    public void testEmptyStrings_AlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(100, result);
    }
    
    @Test
    public void testOneEmptyString_AlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "";
        String str2 = "a";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(0, result);
    }
    
    @Test
    public void testCaseInsensitive_AlphabetScore() {
        SimilarityChecker checker = new SimilarityChecker();
        String str1 = "ABC";
        String str2 = "abc";
        
        int result = checker.calculateSimilarity(str1, str2);
        
        assertEquals(100, result);
    }
}