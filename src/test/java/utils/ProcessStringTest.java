package utils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

public class ProcessStringTest {

    @Test
    public void areStringEmptyTest() {
        //Given
        String emptyString = "";
        String nonEmptyString = "something";

        //When
        boolean emptyStringAtBeginning = ProcessString.areStringEmpty(emptyString, nonEmptyString, nonEmptyString);
        boolean emptyStringInTheMiddle = ProcessString.areStringEmpty(nonEmptyString, emptyString, nonEmptyString);
        boolean emptyStringAtTheEnd = ProcessString.areStringEmpty(nonEmptyString, nonEmptyString, emptyString);
        boolean nonEmptyStrings = ProcessString.areStringEmpty(nonEmptyString, nonEmptyString, nonEmptyString);

        //Then
        assertThat(emptyStringAtBeginning, is(true));
        assertThat(emptyStringInTheMiddle, is(true));
        assertThat(emptyStringAtTheEnd, is(true));
        assertThat(nonEmptyStrings, is(false));
    }

    @Test(expected=NullPointerException.class)
    public void areStringEmptyNullTest() {
        //Given
        String nullString = null;
        String emptyString = "";
        String nonEmptyString = "something";

        //When
        ProcessString.areStringEmpty(nullString, nonEmptyString, emptyString);

        //Then
        //It will throw a NullPointerException
    }
}