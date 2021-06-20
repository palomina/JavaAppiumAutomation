package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String TEXT_IN_INPUT = "Search Wikipedia";

    private By fieldsSearch = By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//android.widget.TextView");
    private By inputSearch = By.id("org.wikipedia:id/search_src_text");
    private By panelSearchResult = By.id("org.wikipedia:id/search_results_list");
    private By itemSearchResult = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup//*[@resource-id='org.wikipedia:id/page_list_item_title']");
    private By itemSearchResultDesc = By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup//*[@resource-id='org.wikipedia:id/page_list_item_description']");

    private By buttonClearSearch = By.id("org.wikipedia:id/search_close_btn");
    private By panelSearchEmptyResult = By.id("org.wikipedia:id/search_empty_container");

    private String itemSearchResult_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup[{INDEX}]//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private String itemSearchResultByTitleAndDescription_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '{TITLE}')]/following-sibling::*[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text, '{DESCRIPTION}')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    /** TEMPLATES **/
    private String getSearchResultItem(int index) {
        return itemSearchResult_TPL.replace("{INDEX}", String.valueOf(index));
    }

    private String getSearchResultItemByTitleAndDescription(String title, String description) {
        return itemSearchResultByTitleAndDescription_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /** TEMPLATES **/

    public void initSearchInput() {
        this.waitElementAndClick(
                fieldsSearch,
                "Can't click on search panel",
                20
        );

        this.assertElementHasText(
                inputSearch,
                TEXT_IN_INPUT,
                "Unexpected text"
        );
    }

    public void searchByInputText(String word) {
        this.waitElementAndSendKeys(
                inputSearch,
                word,
                "Can't set the search word",
                20
        );
    }

    public void checkIsResultsPresents() {
        this.waitElementPresent(
                panelSearchResult,
                "Panel of search result is not found",
                20
        );

        this.waitElementPresent(
                itemSearchResult,
                "Items of search result are not found",
                20
        );

        this.assertCountElements(
                itemSearchResult,
                "Unexpected text"
        );
    }

    public void clearResults() {
        this.waitElementAndClick(
                buttonClearSearch,
                "Button `Clear` is not found",
                20
        );

        this.waitElementPresent(
                panelSearchEmptyResult,
                "Panel of empty result is not found",
                20
        );
    }

    public void checkTextResults(String word) {
        this.assertElementsHasText(
                itemSearchResult,
                word,
                "Unexpected text"
        );
    }

    public String getArticleNameByIndex(int index) {
        return this.waitElementAndGetAttribute(
                By.xpath(getSearchResultItem(index)),
                "text",
                "The first item of search result is not found",
                20
        );
    }

    public void chooseArticleByIndex(int index) {
        this.waitElementAndClick(
                By.xpath(getSearchResultItem(index)),
                "Can't click to the article",
                20
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitElementPresent(
                By.xpath(getSearchResultItemByTitleAndDescription(title, description)),
                "Can't see the article with title '"+title+"' and description '"+description+"'",
                20
        );
    }

}
