import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.lang.module.Configuration;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BankCardTest {

    @Test
    public void shouldPass(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Новосибирск");
        $("[data-test-id=date] input").click();
        $("[data-step='1']").click();
        $(withText("15")).click();
        $("[data-test-id=name] input").setValue("Василий Пупкин");
        $("[data-test-id=phone] input").setValue("+79112223344");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(Condition.appear, Duration.ofSeconds(20));
    }

}
