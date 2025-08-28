package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.HomePage;
import pages.MainPage;
import pages.ProductPage;
import testBase.TestData;
import utility.CommonMethods;

import java.util.List;

import static org.apache.poi.sl.draw.geom.GuideIf.Op.max;

public class HomePageSteps extends CommonMethods{

    HomePage homePage = new HomePage();

    @When("user enters {string} in the search box")
    public void user_enters_in_the_search_box(String productName){
        waitForVisibility(homePage.allProductsContainer);
        sendText(homePage.searchBoxInput, productName.toLowerCase());
        click(homePage.searchButton);
        wait(3);
    }

    @Then("all products across all pages contain {string} in their name")
    public void all_products_across_all_pages_contain_in_their_name(String productName){
        List<String> names = homePage.getAllProductNamesAcrossPages();
        System.out.println("List of Names size is ======> " + names.size());
        System.out.println("List of Names are ========> " + names);
        for(String name : names){
            Assert.assertTrue("Product name does not contain keyword => "+ name, name.contains(productName));
        }
    }

    @When("user selects sorting by {string}")
    public void user_selects_sorting_by(String sortingType){
        waitForVisibility(homePage.allProductsContainer);
        click(homePage.sortDropDown);
        click(homePage.selectSortingfromDropdown(sortingType));
    }


    @Then("products across all pages are sorted by name ascending")
    public void products_across_all_pages_are_sorted_by_name_ascending(){
        wait(3);
        Assert.assertTrue("Products not sorted by name ascending", homePage.areProductsSortedByNameAsc());
    }

    @Then("products across all pages are sorted by name descending")
    public void products_across_all_pages_are_sorted_by_name_descending(){
        wait(3);
        Assert.assertTrue("Products not sorted by name descending", homePage.areProductSortedByNameDesc());
    }

    @Then("products across all pages are sorted by price high to low")
    public void products_across_all_pages_are_sorted_by_price_high_to_low(){
        wait(3);
        Assert.assertTrue("Products not sorted by price low to high", homePage.areProductsSortedByPriceLowToHigh());
    }

    @Then("products across all pages are sorted by price low to high")
    public void products_across_all_pages_are_sorted_by_price_low_to_high(){
        wait(3);
        Assert.assertTrue("Products not sorted by price low to high", homePage.areProductsSortedByPriceHighToLow());
    }

    @When("user sets price range from {double} to {double}")
    public void user_sets_price_range_from_to(Double min, Double max) {
        waitForVisibility(homePage.allProductsContainer);
        homePage.setPriceRange(min, max);
    }

    @Then("all products across all pages have prices between {double} and {double}")
    public void all_products_across_all_pages_have_prices_between_and(Double min, Double max){
        wait(3);
        Assert.assertTrue("Products not within price range", homePage.areProductsWithinPriceRange(min, max));
    }

    @Then("no products are displayed")
    public void no_products_are_displayed(){
        wait(3);
        Assert.assertTrue("Products are displayed", homePage.noProductsDisplayed());
    }


}
