import com.codeborne.selenide.Condition;
import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.lang.module.Configuration;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BankCardTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldPass(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Новосибирск");
        String planningDate = generateDate(11);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Василий Пупкин");
        $("[data-test-id=phone] input").setValue("+79112223344");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(20));
    }

}
