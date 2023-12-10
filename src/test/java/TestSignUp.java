import com.codeborne.selenide.Selectors;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;


public class TestSignUp {

    @Test
    public void TestSignUp(){
        String email = "test1user4@mail.com";
        String username = "test1user4";
        String password = "passwordtest1user";

        open("https://angular.realworld.io/");

        $(byText("Sign up")).click();

        $(byAttribute("formcontrolname", "username"))
                .setValue(username);

        $(byAttribute("formcontrolname", "password"))
                .setValue(password);

        $(byAttribute("formcontrolname", "email"))
                .setValue(email);
        sleep(2000);
        $(".btn.btn-lg.btn-primary.pull-xs-right").click();
        sleep(4000);
        $(byText("Settings")).shouldBe(visible).click();
        sleep(4000);
        $(byAttribute("formcontrolname", "email"))
                .shouldHave(value(email));



    }
}
