package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String TEXT_IN_INPUT = "Search Wikipedia";

    private String fieldsSearch = "xpath##//*[@resource-id='org.wikipedia:id/search_container']//android.widget.TextView";
    private String inputSearch = "id##org.wikipedia:id/search_src_text";
    private String panelSearchResult = "id##org.wikipedia:id/search_results_list";
    private String itemSearchResult = "xpath##//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private String itemSearchResultDesc = "xpath##//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup//*[@resource-id='org.wikipedia:id/page_list_item_description']";

    private String buttonClearSearch = "id##org.wikipedia:id/search_close_btn";
    private String panelSearchEmptyResult = "id##org.wikipedia:id/search_empty_container";

    private String itemSearchResult_TPL = "xpath##//*[@resource-id='org.wikipedia:id/search_results_list']//android.view.ViewGroup[{INDEX}]//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private String itemSearchResultByTitleAndDescription_TPL = "xpath##//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '{TITLE}')]/following-sibling::*[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text, '{DESCRIPTION}')]";

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
        this.waitElementPresent(
                getLocator(fieldsSearch),
                "Can't click on search panel",
                30
        );

        this.waitElementAndClick(
                getLocator(fieldsSearch),
                "Can't click on search panel",
                30
        );

        this.waitElementPresent(
                getLocator(inputSearch),
                "SearchInput is not presented",
                30
        );

        this.assertElementHasText(
                getLocator(inputSearch),
                TEXT_IN_INPUT,
                "Unexpected text"
        );
    }

    public void searchByInputText(String word) {
        this.waitElementAndSendKeys(
                getLocator(inputSearch),
                word,
                "Can't set the search word",
                20
        );
    }

    public void checkIsResultsPresents() {
        this.waitElementPresent(
                getLocator(panelSearchResult),
                "Panel of search result is not found",
                20
        );

        this.waitElementPresent(
                getLocator(itemSearchResult),
                "Items of search result are not found",
                20
        );

        this.assertCountElements(
                getLocator(itemSearchResult),
                "Unexpected text"
        );
    }

    public void clearResults() {
        this.waitElementAndClick(
                getLocator(buttonClearSearch),
                "Button `Clear` is not found",
                20
        );

        this.waitElementPresent(
                getLocator(panelSearchEmptyResult),
                "Panel of empty result is not found",
                20
        );
    }

    public void checkTextResults(String word) {
        this.assertElementsHasText(
                getLocator(itemSearchResult),
                word,
                "Unexpected text"
        );
    }

    public String getArticleNameByIndex(int index) {
        this.waitElementPresent(
                getLocator(getSearchResultItem(index)),
                "The first item of search result is not found",
                20
        );
        return this.waitElementAndGetAttribute(
                getLocator(getSearchResultItem(index)),
                "text",
                "The first item of search result is not found",
                20
        );
    }

    public void chooseArticleByIndex(int index) {
        this.waitElementAndClick(
                getLocator(getSearchResultItem(index)),
                "Can't click to the article",
                20
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitElementPresent(
                getLocator(getSearchResultItemByTitleAndDescription(title, description)),
                "Can't see the article with title '"+title+"' and description '"+description+"'",
                20
        );
    }

}
