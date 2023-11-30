package guru.qa;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AnnotatedSteps {

    @Step("Открываем главную страницу {pageAddress}")
    public AnnotatedSteps openMainPage(String pageAddress) {
        open(pageAddress);
        return this;
    }

    @Step("Ищем репозиторий {repositoryName}")
    public AnnotatedSteps searchRepository(String repositoryName) {
        $(".search-input").click();
        $("#query-builder-test").setValue(repositoryName).pressEnter();
        $("[data-testid='results-list']").shouldHave(text(repositoryName));
        return this;
    }

    @Step("Кликаем по ссылке найденного репозитория {repositoryName}")
    public AnnotatedSteps clickRepositoryLink(String repositoryName) {
        $(byLinkText(repositoryName)).click();
        return this;
    }

    @Step("Кликаем по вкладке Issues")
    public AnnotatedSteps openIssuesTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверяем наличие Issue с названием {issueName}")
    public void checkIssueNameExists(String issueName) {
        $(byText(issueName)).should(exist);
    }
}
