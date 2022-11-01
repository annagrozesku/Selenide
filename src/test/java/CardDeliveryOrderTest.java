import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryOrderTest {
    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldCheckFromSubmission(){

        Selenide.open("http://localhost:9999/");                //открываем сраницу
        $("[data-test-id=city] input").setValue("Астрахань");          //вводим город
        String date = generateDate(3);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);  //стираем дату
        $("[data-test-id=date] input").setValue(date);         //вводим дату дотавки
        $("[data-test-id=name] input").setValue("Пупкин Василий");     //вводим фамилию и имя
        $("[data-test-id=phone] input").setValue("+79995673426");      //вводим телефон
        $("[data-test-id=agreement]").click();                         //жмякаем чекбокс
        $$("button").find(exactText("Забронировать")).click();         //жмякаем забронировать
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)) .shouldHave(exactText("Успешно! " + "Встреча успешно забронирована на " + date));


    }

}
