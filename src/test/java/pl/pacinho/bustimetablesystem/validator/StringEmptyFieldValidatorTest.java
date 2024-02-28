package pl.pacinho.bustimetablesystem.validator;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class StringEmptyFieldValidatorTest {

    @Test
    void falseShouldBeReturnedWhenTextIsNull(){
        //given
        String text = null;

        //when
        boolean isValid = StringEmptyFieldValidator.isNonNullAndNotEmpty(text);

        //then
        assertThat(isValid, is(false));
    }

    @Test
    void falseShouldBeReturnedWhenTextIsEmpty(){
        //given
        String text = "";

        //when
        boolean isValid = StringEmptyFieldValidator.isNonNullAndNotEmpty(text);

        //then
        assertThat(isValid, is(false));
    }

    @Test
    void trueShouldBeReturnedWhenTextIsNotEmpty(){
        //given
        String text = "Aaaa";

        //when
        boolean isValid = StringEmptyFieldValidator.isNonNullAndNotEmpty(text);

        //then
        assertThat(isValid, is(true));
    }
}