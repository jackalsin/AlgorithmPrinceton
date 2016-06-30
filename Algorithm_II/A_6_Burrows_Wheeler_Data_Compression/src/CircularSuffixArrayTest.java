import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a test class for CircularSuffixArray
 *
 * @author jacka
 * @version 1.0 on 6/30/2016.
 */
public class CircularSuffixArrayTest {

    private CircularSuffixArray csa;

    private static final String TEST_STRING = "ABRACADABRA!";

    @Before
    public void setUp() {
        csa = new CircularSuffixArray(TEST_STRING);
    }

    @Test
    public void length() throws Exception {
        assertEquals(TEST_STRING.length(), csa.length());
    }

    /**
     * This is from Example in the websites
     *  i       Original Suffixes           Sorted Suffixes         index[i]
     *  --    -----------------------     -----------------------    --------
     *  0    A B R A C A D A B R A !     ! A B R A C A D A B R A    11
     *  1    B R A C A D A B R A ! A     A ! A B R A C A D A B R    10
     *  2    R A C A D A B R A ! A B     A B R A ! A B R A C A D    7
     *  3    A C A D A B R A ! A B R     A B R A C A D A B R A !    0
     *  4    C A D A B R A ! A B R A     A C A D A B R A ! A B R    3
     *  5    A D A B R A ! A B R A C     A D A B R A ! A B R A C    5
     *  6    D A B R A ! A B R A C A     B R A ! A B R A C A D A    8
     *  7    A B R A ! A B R A C A D     B R A C A D A B R A ! A    1
     *  8    B R A ! A B R A C A D A     C A D A B R A ! A B R A    4
     *  9    R A ! A B R A C A D A B     D A B R A ! A B R A C A    6
     *  10    A ! A B R A C A D A B R     R A ! A B R A C A D A B    9
     *  11    ! A B R A C A D A B R A     R A C A D A B R A ! A B    2
     * @throws Exception
     */
    @Test
    public void index() throws Exception {
        int[] resultIdx = new int[] {11, 10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2};
        for (int i = 0; i < csa.length(); i++) {
            assertEquals(resultIdx[i], csa.index(i));
        }
    }
    
}