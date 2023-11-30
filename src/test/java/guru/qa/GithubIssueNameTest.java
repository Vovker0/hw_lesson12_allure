package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GithubIssueNameTest {

    private static final String WEBSITE = "https://github.com/";
    private static final String REPOSITORY = "Vovker0/hw_lesson12_allure";
    private static final String ISSUE_NAME = "Test issue";


    @Test
    @Owner("Vladimir")
    @Link(value = "Repository on Github", url = WEBSITE + REPOSITORY)
    @DisplayName("Поиск Issue по названию в репозитории Github")
    public void searchIssueNameTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(WEBSITE);
        $(".search-input").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $("[data-testid='results-list']").shouldHave(text(REPOSITORY));
        $(byLinkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(byText(ISSUE_NAME)).should(exist);
    }

    @Test
    @Owner("Vladimir")
    @Link(value = "Repository on Github", url = WEBSITE + REPOSITORY)
    @DisplayName("Поиск Issue по названию в репозитории Github с лямбда шагами")
    public void searchIssueNameLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу " + WEBSITE, () -> {
            open(WEBSITE);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
            $("[data-testid='results-list']").shouldHave(text(REPOSITORY));
        });
        step("Кликаем по ссылке найденного репозитория " + REPOSITORY, () -> {
            $(byLinkText(REPOSITORY)).click();
        });
        step("Кликаем по вкладке Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue c названием" + ISSUE_NAME, () -> {
            $(byText(ISSUE_NAME)).should(exist);
        });
    }

    @Test
    @Owner("Vladimir")
    @Link(value = "Repository on Github", url = WEBSITE + REPOSITORY)
    @DisplayName("Поиск Issue по названию в репозитории Github степами с аннотацией")
    public void searchIssueNameWithAnnotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        AnnotatedSteps steps = new AnnotatedSteps();
        steps.openMainPage(WEBSITE)
                .searchRepository(REPOSITORY)
                .clickRepositoryLink(REPOSITORY)
                .openIssuesTab()
                .checkIssueNameExists(ISSUE_NAME);
    }
}