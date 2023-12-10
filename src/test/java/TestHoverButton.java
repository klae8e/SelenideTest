import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.$;


public class TestHoverButton {

    @Test
    public void TestSignUp(){
        String email = "test1user4@mail.com";
        String username = "test1user4";
        String password = "passwordtest1user";

        String color = "rgba(184, 92, 92, 1)";

        open("https://angular.realworld.io/");

        $(byText("Sign in")).click();

        $(byAttribute("formcontrolname", "email"))
                .setValue(email);
        $(byAttribute("formcontrolname", "password"))
                .setValue(password);


        sleep(3000);
        $(".btn.btn-lg.btn-primary.pull-xs-right").click();
        sleep(6000);
        $(byText("Settings")).shouldBe(visible).click();
        sleep(4000);

        SelenideElement element = Selenide.$(By.cssSelector("button.btn-outline-danger"));
        String originalColor = element.getCssValue("color");
        element.hover();
        String hoverColor = element.getCssValue("color");

        if (hoverColor.equals(color)){
            System.out.println("Заливка цвета элемента при наведении мышью совпадает.");
        }
        else if (originalColor.equals(color)) {
            System.out.println("Заливка цвета элемента без наведении мышью совпадает.");
        } else {
            System.out.println("Заливка цвета элемента при наведении мышью не совпадает.\nИсходный цвет элемента: " + originalColor + "\nЦвет элемента при наведении: " + hoverColor );
        }

    }
}
