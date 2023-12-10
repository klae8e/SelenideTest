import com.codeborne.selenide.Selenide;
import org.junit.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class TestPreview {

    @Test
    public void TestPreview() {
        open("https://angular.realworld.io/");

        int desiredTotalPublications = 12;
        int publicationsPerPage = 10;
        int currentTotalPublications = 0;
        int currentPage = 1;

        while (currentTotalPublications < desiredTotalPublications) {
            // Ожидание появления статей на текущей странице
            $$(".article-preview").shouldHave(size(publicationsPerPage));

            // Считаем количество статей на текущей странице
            int currentPublicationsOnPage = $$(".article-preview").size();

            // Проверяем, не превысили ли мы желаемое общее количество статей после обработки текущей страницы
            int remainingPublicationsNeeded = desiredTotalPublications - currentTotalPublications;

            if (remainingPublicationsNeeded <= 0) {
                // Если достигнуто или превышено желаемое общее количество статей, выходим из цикла
                return;
            }

            // Обработка статей на текущей странице
            $$(".article-preview").first(currentPublicationsOnPage).forEach(element -> {
                // Ваш код для работы с каждой статьей
                // Например, можно добавить проверки и взаимодействия с каждой статьей здесь
            });

            // Увеличиваем общее количество статей
            currentTotalPublications += currentPublicationsOnPage;

            // Если достигнуто или превышено желаемое общее количество статей, выходим из цикла
            if (currentTotalPublications >= desiredTotalPublications) {
                return;
            }


            // Проверяем, существует ли элемент для следующей страницы, прежде чем кликнуть
            if ($(".pagination").$(byText(Integer.toString(currentPage + 1))).exists()) {
                // Клик на следующую страницу
                $(".pagination").$(byText(Integer.toString(currentPage + 1))).click();
                currentPage++;
            } else {
                // Если элемента нет, выходим из цикла
                break;
            }
        }

        // Проверяем, что общее количество статей соответствует ожидаемому
        $$("#articles-container .article-preview").shouldHave(size(desiredTotalPublications));
        System.out.println(currentTotalPublications);
    }
}
